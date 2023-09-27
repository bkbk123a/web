# JAVA-Spring-WEB

## 😃 프로젝트 소개
JAVA Spring기반 WAS 구현

## ✏ 프로젝트 목적
개발자 김범경의 코딩 스타일 예시

## 🕰 개발 기간
* 22.09.27일 ~ 진행중

## ⚙️ 개발 환경
- Java 17
- JDK 17
- IDE : IntelliJ
- Framework : Springboot(3.1.4)
- Database : MSSQL DB(2022, 도커 기반)
- ORM : JPA(Spring Data JPA, QueryDSL)

## 📌 주요 기능
### 로그인 

## 💾 데이터 베이스 관련
- MSSQL DB 구축 도커 명령어 : docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=password1477!" -p 1433:1433 --name db_web --hostname db_web -d mcr.microsoft.com/mssql/server:2022-latest
- 추후 도커 컴포즈로 구축 예정