# planner
sparta  일정관리앱 

<details><summary> 일정 CRUD 
</summary>

## 일정 생성

#### 1. 기본정보
| Method | URL | 인증방식 |
|------|--------|-----|
| POST | /api/schedules | - |

#### 2. 요청

(1) Request Header

- Content-Type: application/json

(2) Request Body

- 유저명 (필수)
- 일정 제목 (필수)
- 일정 내용 (필수)
- 작성일시 (필수)

#### 3. 응답

#### Status Code : 201 Created
 
(1) Response Body

- 유저명 
- 일정 제목 
- 일정 내용 
- 작성일시 

#### Status Code : 400 Bad Request

(1) Response Body

" 유저명은 필수입니다."


## 전체 일정 조회

#### 1. 기본정보
| Method | URL | 인증방식 |
|------|--------|-----|
| GET | /api/schedules | - |

#### 2. 요청

(1) Request Header

- content-type: application/json

#### 3. 응답

#### Status Code : 200 OK
 
(1) Response Body

- 일정 목록 (리스트)
  - 유저명
  - 일정 제목 
  - 일정 내용
  - 작성일시
  - 수정일시


#### Status Code : 400 Bad Request

(1) Response Body

" 요청이 올바르지 않습니다."

## 선택 일정 조회

#### 1. 기본정보

| Method | URL | 인증방식 |
|------|--------|-----|
| GET | /api/schedules/{Id} | - |

#### 2. 요청

(1) Request Header

- content-type: application/json

(2) Path Parameters

| 이름 | 타입 | 설명 |
|---|---|---|
| Id | Long | 조회할 일정 ID |

#### 3. 응답

#### Status Code : 200 OK
 
(1) Response Body

- 유저명 
- 일정 제목
- 일정 내용
- 작성일시 
- 수정일시 


#### Status Code : 404 Not Found

(1) Response Body

" 요청하신 일정이 존재하지 않습니다."

## 일정 수정

#### 1. 기본정보
| Method | URL | 인증방식 |
|------|--------|-----|
| PATCH | /api/schedules/{Id} | - |

#### 2. 요청

(1) Request Header

- content-type: application/json

(2) Path Parameters

| 이름 | 타입 | 설명 |
|---|---|---|
| Id | Long | 수정할 일정 ID |

(3) Request Body

- 유저명 
- 일정 내용
- 작성일시 
- 수정일시 

#### 3. 응답

#### Status Code : 200 OK
 
(1) Response Body

- id
- 유저명 
- 일정 내용 (변경됨)
- 작성일시 
- 수정일시 

#### Status Code : 401 Unauthorized

(1) Response Body

" 로그인이 필요합니다. "

## 일정 삭제

#### 1. 기본정보
| Method | URL | 인증방식 |
|------|--------|-----|
| DELETE | /api/schedules/{Id} | - |

#### 2. 요청

(1) Path Parameters

| 이름 | 타입 | 설명 |
|---|---|---|
| Id | Long | 삭제할 일정 ID |

#### 3. 응답

#### Status Code : 204 No Content
 
(1) Response Body

" 일정 삭제가 완료 됐습니다."

#### Status Code : 401 Unauthorized

(1) Response Body

" 로그인이 필요합니다. "
</details>







<details><summary> 유저 CRUD 
</summary>


</details>








<details><summary> 로그인
</summary>


</details>







<details><summary> 회원가입
</summary>

</details>
