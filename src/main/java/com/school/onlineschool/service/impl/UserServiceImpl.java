package com.school.onlineschool.service.impl;


import com.school.onlineschool.controller.VM.LoginVM;
import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.request.users.UserRequestDto;
import com.school.onlineschool.domain.dto.response.LoginResponseDTO;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.domain.dto.response.UserDetailsResponseDto;
import com.school.onlineschool.domain.dto.response.users.UserResponseDto;
import com.school.onlineschool.domain.entiy.User;
import com.school.onlineschool.exeption.UserException;
import com.school.onlineschool.jwt_utils.JwtTokenProvider;
import com.school.onlineschool.mapping.UserMapper;
import com.school.onlineschool.repository.EnrollmentsRepository;
import com.school.onlineschool.repository.UserRepository;
import com.school.onlineschool.repository.dao.StudentsDao;
import com.school.onlineschool.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EnrollmentsRepository enrollmentsRepository;
    private final StudentsDao studentsDao;

    @Override
    public ResponseDTO<LoginResponseDTO> login(LoginVM loginVM) {
        User user = userRepository.findByUsername(loginVM.getUsername().trim().toLowerCase());
        if (user == null) {
            throw new UserException("Bu foydalanuvch mavjud emas");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername().trim().toLowerCase(), loginVM.getPassword()));
        return jwtTokenProvider.createToken(user.getUsername().trim().toLowerCase(), loginVM.isRememberMe());
    }

    @Override
    @Transactional
    public ResponseDTO<UserResponseDto> createOrUpdateUser(UserRequestDto userRequestDto, Long userId) {
        ResponseDTO<UserResponseDto> responseDTO = new ResponseDTO<>();
        User user = userMapper.toEntity(userRequestDto);
        if (Boolean.TRUE.equals(checkPassword(userRequestDto.getPassword()))) {
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        } else {
            log.error("Parol juda qisqa,{}", userRequestDto.getPassword());
            throw new UserException("Parol juda qisqa");
        }
        user.setUsername(userRequestDto.getUsername().trim().toLowerCase());
        if (userId != null) {
            log.info("Foydalanuvchi ma'lumotlari yangilanmoqda...");
            user = userRepository.findById(userId).orElseThrow(() -> new UserException("Foydalanuvchi topilmadi"));
            userMapper.updateFromDto(userRequestDto, user);
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            userRepository.save(user);
            responseDTO.setMessage("Foydalanuvchi ma'lumotlari yangilandi");
            log.info("Foydalanuvchi ma'lumotlari yangilandi");
            return responseDTO;
        } else {
            if (Boolean.TRUE.equals(checkUsername(userRequestDto.getUsername()))) {
                log.error("Bu username band, {}", userRequestDto.getUsername());
                throw new UserException("Bu username band");
            }
            responseDTO.setMessage("Foydalanuvchi muvaffaqiyatli qo'shildi");
        }
        responseDTO.setSuccess(true);
        responseDTO.setReason(null);
        responseDTO.setData(userMapper.toDto(userRepository.save(user)));
        return responseDTO;
    }

    @Override
    public Boolean checkUsername(String username) {
        try {
            return userRepository.existsByUsername(username);

        } catch (Exception e) {
            log.error("talaba topilmadi, {}", e.getMessage());
            throw new UserException("talaba topilmadi");
        }
    }

    @Override
    public Boolean checkPassword(String password) {
        return password.length() >= 4;
    }

    @Override
    public void logout(String username) {
        log.info("The process of logout has started");
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                log.error("Foydalanuvchi topilmadi, {}", username);
                throw new UserException("Foydalanuvchi topilmadi");
            }
            user.setRefreshToken(null);
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Foydalanuvchi topilmadi, {}", e.getMessage());
            throw new UserException("Foydalanuvchi topilmadi");
        }
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("there is not such students in database")
        );

        List<UserDetailsResponseDto> studentDetails = enrollmentsRepository.findCourseByStudentId(id);

        return new StudentResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getUsername(),
                studentDetails
        );
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new RuntimeException("there are no students in database");
        }
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for (User user : users) {
            List<UserDetailsResponseDto> courseList = enrollmentsRepository.findCourseByStudentId(user.getId());

            StudentResponseDto studentResponse = StudentResponseDto.builder()
                    .id(user.getId())
                    .name(user.getFirstName())
                    .username(user.getUsername())
                    .studentDetails(courseList)
                    .build();
            studentResponseDtos.add(studentResponse);
        }

        return studentResponseDtos;
    }

    @Override
    public List<StudentResponseDto> getStudentByName(MultiValueMap<String, Object> filter) {

        List<User> users = studentsDao.findStudentsbyName(filter);
        if (users.isEmpty()) {
            throw new RuntimeException("There is no student with the name:" + filter.get("name"));
        }

        List<StudentResponseDto> dtos = new ArrayList<>();

        for (User user : users) {
            List<UserDetailsResponseDto> studentDetails = enrollmentsRepository.findCourseByStudentId(user.getId());
            StudentResponseDto dto = StudentResponseDto.builder()
                    .id(user.getId())
                    .name(user.getFirstName())
                    .username(user.getUsername())
                    .studentDetails(studentDetails)
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}
