![Last Update](https://img.shields.io/badge/Last_Upadate-2024--04--28-blue)

# 🛒 더 커머스 백엔드 개발자 기술 과제

## ▶️ 실행 방법
### 프로젝트
1. Repository Pull 받은 후 로컬에서 도커 실행
2. 루트 디렉토리에 있는 `docker-compose.yml` 실행
```
docker compose up -d
```
> 참고: 프로젝트 처음 실행 시, DB에 테이블이 자동으로 생성됩니다.

### 테스트 코드
테스트 코드는 테스트 컨테이너 환경에서 실행되어 도커만 실행중이면 별도 설정이 필요없습니다.

## 📄 API 문서
API 문서는 Swagger로 작성되었으며, 프로젝트 실행 후 하기 링크에서 확인 가능합니다.
- http://localhost:8080/swagger-ui/index.html

## 📂 프로젝트 구조
### main

```
.
├── MemberApplication.java
├── api // API 모듈
│   ├── controller
│   ├── domain
│   ├── dto
│   ├── exception
│   ├── repository
│   └── service
└── global // 글로벌로 사용되는 설정 파일, BaseEntity 등을 모아둔 모듈
    ├── config
    ├── entity
    ├── exception
    └── security
```

### test
```
.
├── global
│   └── RestAssuredTest.java
├── member // 인수 테스트
│   ├── fixture
│   ├── step
│   └── test
└── unit  // 단위 테스트
    └── security
```
