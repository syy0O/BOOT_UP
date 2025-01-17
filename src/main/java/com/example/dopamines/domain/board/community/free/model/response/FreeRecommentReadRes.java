package com.example.dopamines.domain.board.community.free.model.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FreeRecommentReadRes {
    private Long idx;
    private Long freePostIdx;
    private Long commentIdx;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Integer likeCount;
}
