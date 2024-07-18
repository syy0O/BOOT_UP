package com.example.dopamines.domain.board.community.free.model.entity;

import com.example.dopamines.domain.user.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ElementCollection
    private List<String> imageUrlList;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "freeBoard")
    List<FreeLike> likes;

    @OneToMany(mappedBy = "freeBoard")
    private List<FreeComment> comments;
}
