package com.tpe.BackendProject.controller;

import com.tpe.BackendProject.dto.request.contactmessage.ContactMessageRequest;
import com.tpe.BackendProject.dto.response.business.ResponseMessage;
import com.tpe.BackendProject.dto.response.contactmessage.ContactMessageResponse;
import com.tpe.BackendProject.service.contactmessage.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class ContactMessageController {
    private final ContactMessageService contactMessageService;


    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<ContactMessageResponse>> saveContactMessage(
            @RequestBody @Valid ContactMessageRequest contactMessageRequest) {
        return ResponseEntity.ok(contactMessageService.saveContactMessage(contactMessageRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMessage<List<ContactMessageResponse>>> getAllContactMessages() {
        return ResponseEntity.ok(contactMessageService.getAllContactMessages());
    }

    @GetMapping("/getMessagesByPage")
    public ResponseEntity<Page<ContactMessageResponse>> getMessagesByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {
        Page<ContactMessageResponse> messageResponses = contactMessageService.getContactMessageByPage(page, size, sort, type);
        return ResponseEntity.ok(messageResponses);
    }

    @GetMapping("/subject")
    public ResponseEntity<ResponseMessage<List<ContactMessageResponse>>> getContactMessagesBySubject(
            @RequestParam String subject) {
        return ResponseEntity.ok(contactMessageService.getContactMessagesBySubject(subject));
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<ResponseMessage<List<ContactMessageResponse>>> getContactMessagesByEmail(
            @RequestParam String email) {
        return ResponseEntity.ok(contactMessageService.getContactMessagesByEmail(email));

    }


    @GetMapping("/searchByDate")
    public ResponseEntity<ResponseMessage<List<ContactMessageResponse>>> getContactMessagesByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(contactMessageService.getContactMessagesByDateRange(startDate, endDate));
    }

    @GetMapping("/searchByTime")
    public ResponseEntity<ResponseMessage<List<ContactMessageResponse>>> getMessagesByTimeRange(
            @RequestParam("startDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endDateTime) {

        return ResponseEntity.ok(contactMessageService.getContactMessagesByTimeRange(startDateTime, endDateTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<String>> deleteContactMessage(@PathVariable Long id) {
        return ResponseEntity.ok(contactMessageService.deleteContactMessageById(id));
    }



}