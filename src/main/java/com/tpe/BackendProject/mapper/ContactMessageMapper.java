package com.tpe.BackendProject.mapper;

import com.tpe.BackendProject.dto.request.contactmessage.ContactMessageRequest;
import com.tpe.BackendProject.dto.response.contactmessage.ContactMessageResponse;
import com.tpe.BackendProject.entity.concretes.ContactMessage;
import com.tpe.BackendProject.service.helper.MethodHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactMessageMapper {
    private final MethodHelper methodHelper;

    public ContactMessage mapRequestToContactMessage(ContactMessageRequest contactMessageRequest){
        return ContactMessage.builder()
                .name(contactMessageRequest.getName())
                .email(contactMessageRequest.getEmail())
                .subject(methodHelper.getSubjectType(String.valueOf(contactMessageRequest.getSubject())))
                .message(contactMessageRequest.getMessage())
                .build();
    }

    public ContactMessageResponse mapContactMessageToResponse(ContactMessage contactMessage){
        return ContactMessageResponse.builder()
                .id(contactMessage.getId())
                .name(contactMessage.getName())
                .email(contactMessage.getEmail())
                .message(contactMessage.getMessage())
                .subject(contactMessage.getSubject())
                .localDateTime(contactMessage.getLocalDateTime())
                .build();

    }
}
