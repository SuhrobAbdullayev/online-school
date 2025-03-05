package com.school.onlineschool.domain.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponseDTO extends UserBaseDTO {
    private String token;
    private String refreshToken;
}
