package com.tpe.BackendProject.service.helper;

import com.tpe.BackendProject.dto.response.contactmessage.ContactMessageResponse;
import com.tpe.BackendProject.entity.concretes.ContactMessage;
import com.tpe.BackendProject.entity.enums.SubjectType;
import com.tpe.BackendProject.mapper.ContactMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MethodHelper {
    private final ContactMessageMapper contactMessageMapper;


    public SubjectType getSubjectType(String subject) {
        if (subject == null || subject.isEmpty()) {
            return SubjectType.OTHER;
        }
        try {
            return SubjectType.valueOf(subject.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return SubjectType.OTHER;
        }
    }

    public List<ContactMessageResponse> mapContactMessagesToResponseList(List<ContactMessage> contactMessages) {
        return contactMessages.stream()
                .map(contactMessageMapper::mapContactMessageToResponse)
                .collect(Collectors.toList());
    }





}
