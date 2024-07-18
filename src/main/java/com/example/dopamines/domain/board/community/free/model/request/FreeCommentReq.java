package com.example.dopamines.domain.board.community.free.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeCommentReq {
    private Long idx; // freeBoardIdx
    private String content;
}