package com.trainings_notebook.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String postName;
    private String url;
    private String content;
    private String userName;
    private String createdDate;
}
