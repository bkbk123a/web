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
### 1. API 로깅
	- Request, Response 
	
### 2. 네이버 외부 API를 이용한 로그인
	- WAS 실행 후 밑의 내용 주소창에 Enter
	- https://nid.naver.com/oauth2.0/authorize
		?response_type=code
		&client_id=8tJLZMOJa9yCZ9yV5Y57
		&state=Ucz6F7zlAB
		&redirect_uri=http://localhost:8080/oauth/naver/login-callback
		
### 3. 유저 관련 API
	- 유저 정보 조회 API : /users/info
		
### 4. 출석 관련 API
	- 출석 API : /attend
	- 출석 정보 조회 API : /attend/info
	
## 📌 인지 사항
### 1. Request header에 Authorization 추가해야 합니다.
	- JWT를 이용한 인증 체계 
	- 추가 필요한 API	
		- /attend, /attend/info
 
## 💾 데이터 베이스 관련
- 1. docker-compose 를 설치하여야 한다.(추천 : 도커 데스크톱 설치)
- 2. cmd창을 열어서 docker-compose.yml 파일이 있는 위치로 이동 (cd명령어)
- 3. 해당 경로의 cmd창에서 docker-compose up -d 입력 (DB 구축)