package com.school.onlineschool.controller;

import com.school.onlineschool.controller.VM.LoginVM;
import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.request.users.UserRequestDto;
import com.school.onlineschool.domain.dto.response.users.UserResponseDto;
import com.school.onlineschool.jwt_utils.JwtTokenProvider;
import com.school.onlineschool.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@Tag(name = "Foydalanuvchi", description = "Foydalunvhilarni ro'yxatga olish va login qilish qismi")
@RequiredArgsConstructor
@Slf4j
public class UserJwtController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Foydalanuvchini ro'yxatga olish uchun api", description = "Foydalanuvchini ro'yxatga olish uchun api")
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserResponseDto>> register(@RequestBody UserRequestDto userRequestDto, @RequestParam(required = false) Long userId) {
        return new ResponseEntity<>(userService.createOrUpdateUser(userRequestDto, userId), HttpStatus.CREATED);
    }


    @Operation(summary = "Foydalanuvchi login qilish uchun API", description = "Foydalanuvchi login qilish uchun API")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginVM loginVM) {
        return new ResponseEntity<>(userService.login(loginVM), HttpStatus.CREATED);
    }


    @Operation(summary = "Foydalanuvchi logout qilish uchun API", description = "Foydalanuvchi logout qilish uchun API")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(Principal principal) {
        userService.logout(principal.getName());
        return ResponseEntity.ok("Logged out successfully");
    }

    @Operation(summary = "Token eskirganda Refresh Token qilish uchun API", description = "param qilib token yuboriladi")
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(jwtTokenProvider.refreshToken(refreshToken));
    }
}
