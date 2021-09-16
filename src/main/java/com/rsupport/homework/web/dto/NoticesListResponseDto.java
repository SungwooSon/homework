package com.rsupport.homework.web.dto;

import com.rsupport.homework.domain.notices.Notices;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticesListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public NoticesListResponseDto(Notices entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
