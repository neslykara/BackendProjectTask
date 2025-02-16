package com.tpe.BackendProject.service.validator;


import com.tpe.BackendProject.dto.messages.ErrorMessages;
import com.tpe.BackendProject.exception.ConflictException;
import com.tpe.BackendProject.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePropertyValidator {

    private final ContactMessageRepository contactMessageRepository;

    public void checkUniqueEmail( String email){
        if (contactMessageRepository.existsByEmail(email)){
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL,email));
        }
    }
}
