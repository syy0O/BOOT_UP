package com.example.dopamines.domain.user.model.response;

import com.example.dopamines.domain.user.model.entity.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignupResponse {

    private Long idx;
    private String email;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;

    public UserSignupResponse toDto(User user){
        return UserSignupResponse.builder()
                .idx(user.getIdx())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
