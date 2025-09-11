package com.example.emailsendconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

    @KafkaListener(
            topics = "email.send",
            groupId = "email-send-group", // 컨슈머 그룹 이름
            concurrency = "3" // 멀티 쓰레드를 활용해 병렬적으로 처리할 파티션의 개수
    )
    @RetryableTopic(
            attempts = "5", // 총 시도 횟수 (최초 시도 1회 + 재시도 4회)
            backoff = @Backoff(delay = 1000, multiplier = 2),    // 재시도 간격 (1000ms -> 2000ms -> 4000ms -> 8000ms 순으로 재시도 시간이 증가한다.)
            dltTopicSuffix = ".dlt"     // DLT 토픽 이름에 붙일 접미사
    )
    public void consume(String message) {
        System.out.println("Kafka로부터 받아온 메시지: " + message);

        EmailSendMessage emailSendMessage = EmailSendMessage.fromJson(message);

        // 잘못된 이메일 주소일 경우 실패 가정
        if (emailSendMessage.getTo().equals("fail@naver.com")) {
            System.out.println("잘못된 이메일 주소로 인해 발송 실패");
            throw new RuntimeException("잘못된 이메일 주소로 인해 발송 실패");
        }

        // ... 실제 이메일 발송 로직은 생략 ...
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException("이메일 발송 실패");
        }

        System.out.println("이메일 발송 완료");
    }
}