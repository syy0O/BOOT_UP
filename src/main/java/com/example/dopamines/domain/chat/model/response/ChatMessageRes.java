package com.example.dopamines.domain.chat.model.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRes {
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    private String roomId;
    private String content;
    private String sender;
    private MessageType type;
    private LocalDate createdAt;
}