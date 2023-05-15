package com.ecommerce.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String role;

    private MultipartFile img;

    private byte[] returnedImg;

    public UserDto(Long id, String email, String name, String role) {
    }
}
