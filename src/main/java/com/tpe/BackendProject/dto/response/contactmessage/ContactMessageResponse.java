package com.tpe.BackendProject.dto.response.contactmessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tpe.BackendProject.dto.response.abstracts.BaseContactMessageResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactMessageResponse extends BaseContactMessageResponse {
}
