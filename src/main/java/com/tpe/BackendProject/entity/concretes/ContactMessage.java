package com.tpe.BackendProject.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpe.BackendProject.entity.enums.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_contact_message")
public class ContactMessage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private SubjectType subject;

    @Column(length = 1000)
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm" ,timezone = "US")
    private LocalDateTime localDateTime;

    @PrePersist
    protected void onCreate(){
        this.localDateTime=LocalDateTime.now();
    }
}
