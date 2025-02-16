package com.tpe.BackendProject.service.contactmessage;

import com.tpe.BackendProject.dto.messages.ErrorMessages;
import com.tpe.BackendProject.dto.messages.SuccessMessages;
import com.tpe.BackendProject.dto.request.contactmessage.ContactMessageRequest;
import com.tpe.BackendProject.dto.response.business.ResponseMessage;
import com.tpe.BackendProject.dto.response.contactmessage.ContactMessageResponse;
import com.tpe.BackendProject.entity.concretes.ContactMessage;
import com.tpe.BackendProject.entity.enums.SubjectType;
import com.tpe.BackendProject.exception.ResourceNotFoundException;
import com.tpe.BackendProject.mapper.ContactMessageMapper;
import com.tpe.BackendProject.repository.ContactMessageRepository;
import com.tpe.BackendProject.service.helper.MethodHelper;
import com.tpe.BackendProject.service.helper.PageableHelper;
import com.tpe.BackendProject.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactMessageService {
    private final ContactMessageRepository contactMessageRepository;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final ContactMessageMapper contactMessageMapper;
    private final MethodHelper methodHelper;
    private final PageableHelper pageableHelper;
    public ResponseMessage<ContactMessageResponse> saveContactMessage(ContactMessageRequest contactMessageRequest) {
        uniquePropertyValidator.checkUniqueEmail(contactMessageRequest.getEmail());
        ContactMessage contactMessageToSave=contactMessageMapper.mapRequestToContactMessage(contactMessageRequest);
        ContactMessage savedContactMessage=contactMessageRepository.save(contactMessageToSave);
        return ResponseMessage.<ContactMessageResponse>builder()
                .message(SuccessMessages.CONTACT_MESSAGE_SAVED)
                .returnBody(contactMessageMapper.mapContactMessageToResponse(savedContactMessage))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<List<ContactMessageResponse>> getAllContactMessages() {
        List<ContactMessage> contactMessages = contactMessageRepository.findAll();
        List<ContactMessageResponse> messageResponses = methodHelper.mapContactMessagesToResponseList(contactMessages);

        return ResponseMessage.<List<ContactMessageResponse>>builder()
                .message(SuccessMessages.CONTACT_MESSAGES_FETCHED)
                .returnBody(messageResponses)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public Page<ContactMessageResponse> getContactMessageByPage(int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageable(page, size, sort, type);
        return contactMessageRepository.findAll(pageable)
                .map(contactMessageMapper::mapContactMessageToResponse);
    }

    public ResponseMessage<List<ContactMessageResponse>> getContactMessagesBySubject(String subject) {
       SubjectType subjectType= methodHelper.getSubjectType(subject);
        List<ContactMessage> contactMessages = contactMessageRepository.findBySubject(subjectType);
        List<ContactMessageResponse> messageResponses = methodHelper.mapContactMessagesToResponseList(contactMessages);
        return ResponseMessage.<List<ContactMessageResponse>>builder()
                .message(SuccessMessages.CONTACT_MESSAGES_BY_SUBJECT_FETCHED)
                .returnBody(messageResponses)
                .httpStatus(HttpStatus.OK)
                .build();

    }

    public ResponseMessage<List<ContactMessageResponse>> getContactMessagesByEmail(String email) {
        List<ContactMessage> contactMessages=contactMessageRepository.findByEmail(email);
        List<ContactMessageResponse> messageResponses = methodHelper.mapContactMessagesToResponseList(contactMessages);
        return ResponseMessage.<List<ContactMessageResponse>>builder()
                .message(SuccessMessages.CONTACT_MESSAGES_BY_EMAIL_FETCHED)
                .returnBody(messageResponses)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<List<ContactMessageResponse>> getContactMessagesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<ContactMessage> contactMessages = contactMessageRepository.findByLocalDateTimeBetween(startDate, endDate);
        List<ContactMessageResponse> messageResponses = methodHelper.mapContactMessagesToResponseList(contactMessages);

        return ResponseMessage.<List<ContactMessageResponse>>builder()
                .message(SuccessMessages.CONTACT_MESSAGES_BY_DATE_RANGE_FETCHED)
                .returnBody(messageResponses)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<List<ContactMessageResponse>> getContactMessagesByTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<ContactMessage> contactMessages = contactMessageRepository.findByLocalDateTimeBetween(startDateTime, endDateTime);
        List<ContactMessageResponse> messageResponses = methodHelper.mapContactMessagesToResponseList(contactMessages);

        return ResponseMessage.<List<ContactMessageResponse>>builder()
                .message(SuccessMessages.CONTACT_MESSAGES_BY_TIME_RANGE_FETCHED)
                .returnBody(messageResponses)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<String> deleteContactMessageById(Long id) {
        ContactMessage contactMessage = contactMessageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CONTACT_MESSAGE_NOT_FOUND + id));

        contactMessageRepository.delete(contactMessage);

        return ResponseMessage.<String>builder()
                .message(SuccessMessages.CONTACT_MESSAGE_DELETED)
                .returnBody("Message with ID " + id + " has been deleted successfully.")
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
