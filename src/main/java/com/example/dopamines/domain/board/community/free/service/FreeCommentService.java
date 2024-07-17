package com.example.dopamines.domain.board.community.free.service;

import com.example.dopamines.domain.board.community.free.model.entity.FreeBoard;
import com.example.dopamines.domain.board.community.free.model.entity.FreeComment;
import com.example.dopamines.domain.board.community.free.model.request.FreeCommentReq;
import com.example.dopamines.domain.board.community.free.model.response.FreeBoardReadRes;
import com.example.dopamines.domain.board.community.free.model.response.FreeCommentReadRes;
import com.example.dopamines.domain.board.community.free.repository.FreeBoardRepository;
import com.example.dopamines.domain.board.community.free.repository.FreeCommentRepository;
import com.example.dopamines.domain.user.model.entity.User;
import com.example.dopamines.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.dopamines.global.common.BaseResponseStatus.COMMUNITY_BOARD_NOT_FOUND;
import static com.example.dopamines.global.common.BaseResponseStatus.COMMUNITY_CONTENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FreeCommentService {
    private final FreeCommentRepository freeCommentRepository;
    private final FreeBoardRepository freeBoardRepository;

    @Transactional
    public String create(User user, FreeCommentReq req) {
        if(req.getContent() == null){
            throw new BaseException(COMMUNITY_CONTENT_NOT_FOUND);
        }
        freeCommentRepository.save(FreeComment.builder()
                .content(req.getContent())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build()
        );

        return "자유 게시판 댓글 등록";
    }

    public List<FreeCommentReadRes> read(Long idx) {
        FreeBoard freeBoard = freeBoardRepository.findById(idx).orElseThrow(() -> new BaseException(COMMUNITY_BOARD_NOT_FOUND));
        List<FreeCommentReadRes> freeCommentReadResList = new ArrayList<>();
        for(FreeComment freeComment : freeBoard.getComments()){
            freeCommentReadResList.add(FreeCommentReadRes.builder()
                    .idx(freeComment.getIdx())
                    .freeBoardIdx(freeBoard.getIdx())
                    .content(freeComment.getContent())
                    .createdAt(freeComment.getCreatedAt())
                    .likeCount(freeComment.getLikes().size())
                    .build());

        }
        return freeCommentReadResList;
    }

}
