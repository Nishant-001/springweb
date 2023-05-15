package com.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecondProductDto {

    private Long id;

    private String name;

    private String code;

    private String discount;

    private Long price;
    
    private String userId;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private String categoryId;
}
