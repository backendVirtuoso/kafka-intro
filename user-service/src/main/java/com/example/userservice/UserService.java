package com.example.userservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void signUp(SignUpRequestDto signUpRequestDto) {
        // 회원 가입한 사용자 정보 DB에 저장
        User user = new User(
                signUpRequestDto.getEmail(),
                signUpRequestDto.getName(),
                signUpRequestDto.getPassword()
        );
        User savedUser = userRepository.save(user);

        // 카프카에 메시지 전송
        UserSignedUpEvent userSignedUpEvent = new UserSignedUpEvent(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName()
        );
        this.kafkaTemplate.send("user.signed-up", toJsonString(userSignedUpEvent));
    }

    private String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(object);
            return message;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json 직렬화 실패");
        }
    }
}