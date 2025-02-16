package com.tpe.BackendProject.dto.request.abstracts;

import com.tpe.BackendProject.entity.enums.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractContactMessageRequest
{
    @NotNull(message = "Please enter your name")
    @Size(min = 4, max = 16,message = "Your name should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull(message = "Please enter your email")
    @Email(message = "Please enter valid email")
    @Size(min=5, max=50 , message = "Your email should be between 5 and 50 chars")
    private String email;

    @NotNull(message = "Subject cannot be null")
    private SubjectType subject;

    @Size(max = 1000, message = "Message cannot exceed 1000 characters")
    @NotNull(message = "Message cannot be null")
    private String message;

}
