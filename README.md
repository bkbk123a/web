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
- Database : H2(Docker 기반)
- ORM : JPA(Spring Data JPA, QueryDSL)

## 📌 주요 기능
### 1. API 로깅
	- Request, Response 
	
### 2. 네이버 외부 API를 이용한 로그인
	- WAS 실행 후 밑의 내용 주소창에 localhost:8080 입력후, 로그인 버튼을 누른다.
		
### 3. 유저 관련 API
	- 유저 정보 조회 API : /users/info
		
### 4. 출석 관련 API
	- 출석 하기 API : /attend
	- 출석 정보 조회 API : /attend/info
	
### 5. 아이템 관련 API
	- 아이템 정보 조회 API : /item/info
	- 유저 아이템 정보 조회 API : /item/user-info
	
## 📌 인지 사항
### 1. Request header에 Authorization 추가해야 합니다.
	- JWT를 이용한 인증 체계 
 
## 💾 데이터 베이스 관련
- 1. docker-compose 를 설치하여야 한다.(추천 : 도커 데스크톱 설치)
- 2. cmd창을 열어서 docker-compose.yml 파일이 있는 위치로 이동 (cd명령어)
- 3. 해당 경로의 cmd창에서 docker-compose up -d 입력 (DB 구축)