package com.tpe.BackendProject.repository;

import com.tpe.BackendProject.entity.concretes.ContactMessage;
import com.tpe.BackendProject.entity.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
    boolean existsByEmail(String email);

    List<ContactMessage> findBySubject(SubjectType subjectType);

    List<ContactMessage> findByEmail(String email);

    List<ContactMessage> findByLocalDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
