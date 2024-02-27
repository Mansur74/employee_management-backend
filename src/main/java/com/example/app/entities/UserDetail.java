package com.example.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String gender;
    private LocalDateTime birthdate;
    private String location;
    private Long phoneNumber;
    private String imgURL;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
