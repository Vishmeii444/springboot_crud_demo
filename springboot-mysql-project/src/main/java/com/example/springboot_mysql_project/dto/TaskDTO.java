package com.example.springboot_mysql_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generate getDescr
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String description;
    private String status;
}