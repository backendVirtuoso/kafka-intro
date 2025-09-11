package com.example.emailsendproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // <메시지의 Key 타입, 메시지의 Value 타입>
    // Kafka에 넣는 메시지는 Key-Value 형태로 넣을 수도 있고,
    // Key는 생략한 채로 Value만 넣을 수도 있다고 얘기했다.
    // 실습에서는 메시지를 만들 때 key는 생략한 채로 value만 넣을 예정이다.
    private final KafkaTemplate<String, String> kafkaTemplate;

    public EmailService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmail(SendEmailRequestDto request) {
        EmailSendMessage emailSendMessage = new EmailSendMessage(
                request.getFrom(),
                request.getTo(),
                request.getSubject(),
                request.getBody()
        );

        // 위에서 메시지의 value 타입을 String으로 설정을 했다.
        // 그래서 EmailSendMessage 객체를 String으로 변환해서 넣어주어야 한다.
        this.kafkaTemplate.send("email.send", toJsonString(emailSendMessage));
    }

    // 객체를 Json 형태의 String으로 만들어주는 메서드
    // (클래스로 분리하면 더 좋지만 편의를 위해 메서드로만 분리)
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