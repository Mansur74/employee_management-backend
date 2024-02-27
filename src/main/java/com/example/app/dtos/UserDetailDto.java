package com.example.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
    private int id;
    private String gender;
    private LocalDateTime birthdate;
    private String location;
    private Long phoneNumber;
    private String imgURL;
}
