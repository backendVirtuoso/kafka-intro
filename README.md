## email-send-consumer

Kafka의 Consumer 개념을 학습하기 위한 예제 프로젝트입니다. Spring Boot 기반으로 작성되었으며, Kafka에서 이메일 전송 메시지를 수신하여 처리하는 역할을 합니다.

- **주요 파일 및 기능**
  - `EmailSendConsumer.java`: Kafka에서 이메일 전송 메시지를 수신하여 처리
  - `EmailSendDltConsumer.java`: Dead Letter Topic(DLT) 메시지 처리
  - `EmailSendMessage.java`: 이메일 메시지 데이터 모델
  - `EmailSendConsumerApplication.java`: 애플리케이션 진입점
  - `application.yml`: Kafka 및 앱 설정
- **목적**
  - Kafka Consumer 동작 방식 이해
  - DLT 처리 실습
  - 메시지 역직렬화 및 예외 처리 학습

---

## email-send-producer

Kafka의 Producer 개념을 학습하기 위한 예제 프로젝트입니다. 이메일 전송 요청을 받아 Kafka로 메시지를 발행합니다.

- **주요 파일 및 기능**
  - `EmailController.java`: 이메일 전송 REST API
  - `EmailService.java`: 이메일 전송 로직
  - `EmailSendProducerApplication.java`: 애플리케이션 진입점
  - `EmailSendMessage.java`: 이메일 메시지 데이터 모델
  - `SendEmailRequestDto.java`: 이메일 전송 요청 DTO
  - `application.yml`: Kafka 및 앱 설정
- **목적**
  - Kafka Producer 동작 방식 이해
  - REST API와 Kafka 연동 실습
  - 메시지 직렬화 및 발행 과정 학습

---

## user-service

강의 최종 프로젝트의 일부로, 사용자 관리 기능을 제공하는 Spring Boot 기반 마이크로서비스입니다. 회원가입, 사용자 정보 관리, 이벤트 발행 기능을 포함합니다.

- **주요 파일 및 기능**
  - `UserController.java`: 사용자 REST API
  - `UserService.java`: 사용자 비즈니스 로직
  - `UserRepository.java`: JPA 리포지토리
  - `SignUpRequestDto.java`: 회원가입 요청 DTO
  - `User.java`: 사용자 엔티티
  - `UserSignedUpEvent.java`: 회원가입 이벤트 모델 (Kafka 메시지)
  - `UserServiceApplication.java`: 애플리케이션 진입점
  - `application.yml`: 서비스 설정
- **목적**
  - 사용자 관리 및 회원가입 기능 구현
  - 회원가입 시 Kafka 이벤트 발행
  - 마이크로서비스 아키텍처 실습

---

## email-service

강의 최종 프로젝트의 일부로, 이메일 발송 및 로그 관리 기능을 제공하는 Spring Boot 기반 마이크로서비스입니다. Kafka를 통해 회원가입 이벤트를 수신하여 이메일을 발송하고, 발송 내역을 저장합니다.

- **주요 파일 및 기능**
  - `UserSignedUpEventConsumer.java`: 회원가입 이벤트 수신 및 이메일 발송
  - `UserSignedUpEventDltConsumer.java`: DLT 메시지 처리
  - `EmailLog.java`: 이메일 발송 내역 엔티티
  - `EmailLogRepository.java`: 이메일 로그 JPA 리포지토리
  - `UserSignedUpEvent.java`: 회원가입 이벤트 모델
  - `EmailServiceApplication.java`: 애플리케이션 진입점
  - `application.yml`: 서비스 설정
- **목적**
  - 회원가입 이벤트 기반 이메일 발송
  - 이메일 발송 내역 저장 및 관리
  - Kafka Consumer 및 DLT 처리 실습
  - 마이크로서비스 아키텍처 실습

---
