package com.tpe.BackendProject.dto.response.abstracts;

import com.tpe.BackendProject.entity.enums.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public  abstract class BaseContactMessageResponse {
    private Long id;
    private String name;
    private String email;
    private SubjectType subject;
    private String message;
    private LocalDateTime localDateTime;
}
