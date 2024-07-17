package com.example.dopamines.domain.user.service;

import com.example.dopamines.domain.user.model.entity.User;
import com.example.dopamines.domain.user.model.entity.UserEmailVerify;
import com.example.dopamines.domain.user.model.request.UserSignupRequest;
import com.example.dopamines.domain.user.repository.UserEmailVerifyRepository;
import com.example.dopamines.domain.user.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEmailService {

    private final UserRepository userRepository;

    private final UserEmailVerifyRepository userEmailRepository;

    private final JavaMailSender emailSender;

    // 메일을 보내고, UUID를 생성한다.
    public String sendEmail(UserSignupRequest request){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("[도파민즈 사이트] 인증 메일 요청");

        String uuid = UUID.randomUUID().toString();
        message.setText("가입을 완료하시려면 아래 링크를 클릭해주세요."
                + "http://localhost:8080/user/active?email=" + request.getEmail()+"&uuid="+uuid);

        emailSender.send(message);

        return uuid;
    }

    public void save(UserSignupRequest request, String uuid) {
        UserEmailVerify emailVerify = UserEmailVerify.builder()
                .email(request.getEmail())
                .uuid(uuid)
                .build();

        UserEmailVerify result = userEmailRepository.save(emailVerify);
    }

    public boolean verifyUser(String email, String uuid) {
        Optional<UserEmailVerify> getEmailVerifyUser = userEmailRepository.findByEmailAndUuid(email,uuid);
        //요청받은 이메일과 uuid 가 일치하면 계정 활성화
        if(getEmailVerifyUser.isPresent()){
            return true;
        }
        return false;
    }
}