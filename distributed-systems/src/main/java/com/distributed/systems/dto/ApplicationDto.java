package com.distributed.systems.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationDto {

    @NotNull(message = "AFM should not be empty")
    private String applicantsAFM;

    @NotNull(message = "Description should not be empty")
    private String description;

    @NotNull(message = "Location should not be empty")
    private String location;

    @NotNull
    private String state;

    private String staffId;

//    @NotNull(message ="Date should not be empty")
//    private Date applicationDate;

    @NotNull
    private Long applicationId;

}
