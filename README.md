# 인증 블로그 v2

## 1. 기술 스택
- session, cookie
- ORM (Object Relation Mapping)
- lazy loading
- response(응답), DTO (왜 필요한가?)
- Optional, Stream API(map, filter...)
- 권한(Authorization, 403)과 인증(Authentication, 401)

## 2. 리팩토링
- ResponseDTO를 내부 클래스로 수정

## 3. 기능
- 회원가입(ID 중복 체크)
- 로그인(cookie 기능 활용)
- 게시글쓰기 수정(인증된 사람만이 글쓰기가 가능하도록)
- 게시글 상세보기 수정(인증/권한 체크, DTO 만들기)
- 게시글 수정/삭제 (권한체크 - 수정)

## 4. Task

### 1. 회원가입
- 그림 다운로드 (완)
- user 폴더 UserController 만들어서 그림 연결   (완)
- User 테이블 생성 - 더미데이터 (완)
- UserRepository 만들어 DB 테스트하기 (완)
- 컨트롤러, 서비스, Repository 연결해서 기능 완료하기 (완)

### 2. 로그인
- 컨트롤러, 서비스, DTO, 세션