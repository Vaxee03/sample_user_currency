<p align="center">
<img src="https://cdn.imweb.me/thumbnail/20231120/9b1551ea109cf.png" width="40%" height="30%" title="px(픽셀) 크기 설정" alt="Calculator"></img>
</p>

# 📌 Todo-Log

---
>- ## ⚙ 구현 기능
>1. 유저(user) CRUD 기능
>2. 피드(feed) CRUD 기능
>3. 댓글(comment) CRUD 기능
>4. 좋아요(like) 기능
>5. 유저 로그인 기능
>6. 팔로우 관리 기능
>7. 비밀번호 암호화 기능
>8. 피드 카테고리 기능
>9. 프로필 기능
> 

---

## 👷‍♂️ API 명세서


유저 등록

method : POST

URI : /users

request : @RequestBody

    {
        "nickname": "user1",
        "email": "aa@aa.com",
        "status_ms": "상태메세지",
        "mbti" : "infp",
        "password": "1234"
    }

response :

    //정상등록 예시 
    {   
        "id": 1,
        "nickname": "user1",
        "email": "aa@aa.com",
        "status_ms": "상태메세지",
        "mbti" : "infp",
        "password": "$2a$04$SAdzROzAX./dInQwejqhheaaA9G.uWzj2sv0S20TSWf87/nal/VAS"
    }

    //실패 예시
    {
        "password": "password 은 1~10 글자여야 합니다.",
        "email": "이메일 형식이 올바르지 않습니다.",
        "username": "username 은 1~5 글자여야 합니다."
    }

status code :

201(Created) : 정상등록

400(Bad_Request) : 요청 실패

---

유저 조회

method : GET

URI : /users/{id}

request :  @RequestParam

response :

    //정상조회 예시 
    {   
        "nickname": "user1",
        "email": "aa@aa.com",
        "status_ms": "상태메세지",
        "mbti" : "infp",
        "password": "$2a$04$CJYd4Qr8n3/ol9KKLPTYyukHvCVEevOKpH.vrYI7R.IuiTAmZcMny"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T05:57:49.734068",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "USER_NOT_FOUND",
        "message": "해당 id 로 인한 유저정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상조회

404(NOT_FOUND) : id 조회 실패

---

유저 삭제


method : DELETE

URI : /users/{id}

request :  @RequestParam

response :

    //정상 삭제 예시
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T06:11:38.465995",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_USER",
        "message": "권한이 없습니다. 해당유저만 가능합니다."
    }

status code :

200(OK) : 정상삭제

401(UNAUTHORIZED) : 권한 실패

---


유저 로그인

method : POST

URI : /users/login

request :  -

response :

    //정상 로그인 예시
    {
        "email" : "aa@aa.com",
        "password" : "1234"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T11:43:23.341849",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_PASSWORD",
        "message": "password 가 일치하지 않습니다."
    }

status code :

200(OK) : 정상 로그인

401(UNAUTHORIZED) : 권한 실패

---


유저 로그아웃

method : POST

URI : /users/logout

request :  -

response :

    //정상 session삭제 예시
    logout

    //실패 예시
    session not exist


status code :

200(OK) : 정상

---

피드(feed) 등록

method : POST

URI : /feeds

request : @RequestBody

    {
        "categoryid" : 1,
        "title" : "제목",
        "detail" : "내용"
    }

response : 

    //정상 등록 예시
    
    {
        "id": 1,
        "categoryid" : 1,
        "title": "제목",
        "detail": "내용"
    }

    //실패 예시
    {
           "type": "about:blank",
            "title": "Bad Request",
            "status": 400,
            "detail": "Failed to read request",
            "instance": "/feeds"
    }

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

----


피드(feed) 단건 조회

method : GET

URI : /feeds/{id}

request :   @RequestParam



response :

    //성공 예시
    
    {
        "id": 1,
        "nickname": "myoma",
        "categoryname": "코딩",
        "title": "제목1",
        "detail": "내용1",
        "likeCount": 0,
        "createdAt": "2024-11-22T20:38:37.292558",
        "updatedAt": "2024-11-22T20:38:37.292558"
    }

    //실패 예시
    {
            "type": "about:blank",
            "title": "Not Found",
            "status": 404,
            "detail": "No static resource feeds.",
            "instance": "/feeds/"
    }

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

404(NOT_FOUND) : id 조회 실패

---

피드(feed)  paging 수정순 조회

method : GET

URI : /feeds/paging/updatedAt

request : @RequestParam , 

@Pageable(size=페이지 사이즈 , page : 현재 페이지 번호(0부터 시작) , sort : 정렬 기준이 될 필드와 방향(ASC 또는 DESC)),

@RequestParams 없을시 @PageableDefault



response :

    //성공 예시

    [
        {
            "id": 4,
            "nickname": "myoma",
            "categoryname": "코딩",
            "title": "제목1",
            "detail": "내용",
            "likeCount": 0,
            "createdAt": "2024-11-22T20:41:46.001329",
            "updatedAt": "2024-11-22T20:41:46.001329"
        },

            '''

    ]

    //data 없을시 (빈배열 반환)
    
    []

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

404(NOT_FOUND) : id 조회 실패

---

피드(feed)  paging 좋아요 순 조회

method : GET

URI : /feeds/paging/like

request : @RequestParam 

response :

    //성공 예시

    [
        {
            "id": 1,
            "nickname": "myoma",
            "categoryname": "코딩",
            "title": "제목1",
            "detail": "내용1",
            "likeCount": 0,
            "createdAt": "2024-11-22T20:38:37.292558",
            "updatedAt": "2024-11-22T20:38:37.292558"
        },

            '''

    ]

    //data 없을시 (빈배열 반환)
    
    []

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

404(NOT_FOUND) : id 조회 실패

---

피드(feed)  기간별 검색 기능

method : POST

URI : /feeds/searchDate

request : @RequestBody
    
    [
        "startDate" : "2024-11-22",
        "endDate" : "2024-11-22"
    ]

response :
    
    //성공 예시

    [
        {
            "id": 1,
            "nickname": "myoma",
            "categoryname": "코딩",
            "title": "제목1",
            "detail": "내용1",
            "likeCount": 0,
            "createdAt": "2024-11-22T19:53:06.102235",
            "updatedAt": "2024-11-22T19:53:06.102235"
        },

            '''

    ]

    //data 없을시 (빈배열 반환)
    
    []

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

404(NOT_FOUND) : id 조회 실패

---

피드(feed) 수정

method : PATCH

URI : /feeds/{id}

request : @RequestBody

    {
        "title" : "변경된 제목",
        "detail" : "변경된 내용",
    }

response :

    //정상 등록 예시
    
    
    1 //조회 시 변경된 내용 확인 가능.
    

    //실패 예시
    {
        "title": "title 은 10글자 이내여야 합니다.",
        "detail": "detail 은 20글자 이내여야 합니다."
    }

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

401(UNAUTHORIZED) : 권한 실패

---

피드(feed) 전체 조회

method : GET

URI : /feeds

request : @RequestParam

response :

    //성공 예시

    [
        {
            "id": 3,
            "nickname": "myoma",
            "categoryname": "코딩",
            "title": "제목3",
            "detail": "내용3",
            "likeCount": 4,
            "createdAt": "2024-11-22T20:38:37.292558",
            "updatedAt": "2024-11-22T20:38:37.292558"
        },

            '''

    ]

    //data 없을시 (빈배열 반환)
    
    []

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패

404(NOT_FOUND) : id 조회 실패

---

피드(feed) 단건 삭제

method : GET

URI : /feeds/{id}

request :   @RequestParam



response :

    //성공 예시
    
    1

    //실패 예시
    {
        "type": "about:blank",
        "title": "Not Found",
        "status": 404,
        "detail": "ID를 찾을 수 없습니다.",
        "instance": "/feeds/6"
    }

status code :

200(OK) : 정상

404(NOT_FOUND) : id 조회 실패

---

팔로우 추가 기능

method : POST

URI : /follow/{follower}/{following}

request : @RequestParam

response :

    //성공 예시
    
    {
        팔로우 신청 완료!
    }

    //실패 예시
    {
        "timestamp": "2024-11-22T21:06:10.25302",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "FOLLOWING_NOT_FOUND",
        "message": "해당 id로 인한 팔로잉 정보를 찾을 수 없습니다."
    }

status code :

200(OK) : 정상

404(Not Found)

---

팔로잉 목록 조회

method : GET

URI : /follow/following/{nickname}

request : @RequestParam

response :

    //성공 예시
    [
        {
            "nickname": "myoma",
            "email": "1111@1111.com",
            "mbti": null,
            "statusMs": null
        }
    ]

    //팔로잉 없는 경우 빈 배열
    []


    //실패 예시
    {
        "timestamp": "2024-11-22T21:10:14.672799",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "USER_NOT_FOUND",
        "message": "해당 id 로 인한 유저 정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상

404(Not Found)

---

팔로워 목록 조회

method : GET

URI : /follow/follower/{nickname}

request : @RequestParam

response :

    //성공 예시
    [
        {
            "nickname": "myoma1",
            "email": "1111@1112.com",
            "mbti": null,
            "statusMs": null
        }
    ]

    //팔로워 없는 경우 빈 배열
    []


    //실패 예시
    {
        "timestamp": "2024-11-22T21:10:14.672799",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "USER_NOT_FOUND",
        "message": "해당 id 로 인한 유저 정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상

404(Not Found)

---

팔로잉 게시물 조회

method : GET

URI : /follow/{nickname}/followingFeeds

request : @RequestParam

response :

    //성공 예시
    [
        {
            "id": 4,
            "nickname": "myoma",
            "categoryname": "코딩",
            "title": "제목1",
            "detail": "내용",
            "likeCount": 0,
            "createdAt": "2024-11-22T20:41:46.001329",
            "updatedAt": "2024-11-22T20:41:46.001329"
        },
        ...
    ]

    //팔로잉 없는 경우 빈 배열
    []


    //실패 예시
    {
        "timestamp": "2024-11-22T21:10:14.672799",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "USER_NOT_FOUND",
        "message": "해당 id 로 인한 유저 정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상

404(Not Found)

---

언팔로우

method : DELETE

URI : /follow/{follower}/{following}

request : @RequestParam

response :

    //성공 예시
    [
        팔로우 삭제 완료!
    ]


    //실패 예시
    {
        "timestamp": "2024-11-22T21:18:47.426527",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "FOLLOWING_NOT_FOUND",
        "message": "해당 id로 인한 팔로잉 정보를 찾을 수 없습니다."
    }

status code :

200(OK) : 정상

404(Not Found)

---

댓글 등록

method : POST

URI : /comments

request :

    {
        "feedid" : 1,
        "detail" : "댓글"
    }

response :

    //성공 예시
    
    {
        "id": 1,
        "feedid": 1,
        "username": "user1",
        "detail": "댓글"
    }

    //실패 예시
    {
        "detail": "detail 은 20글자 이내여야 합니다."
    }

status code :

200(OK) : 정상

400(Bad_Request) : validation 실패

---

댓글 조회

method : GET

URI : /comments/{commentId}

request : @PathVariable



response :

    //정상 예시
    
    {
        "id": 1,
        "feedid": 7,
        "username": "user1",
        "detail": "user1댓글"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T13:07:39.71697",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "COMMENT_NOT_FOUND",
        "message": "해당 id 로 인한 댓글정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상

404(NOT_FOUND) : id 조회 실패

---

댓글 수정

method : PUT

URI : /comments/{commentId}

request :@PathVariable , @RequestBody

    {
        "detail" : "dfdfdfdf"
    }

response :

    //성공 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T13:11:36.239848",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_USER",
        "message": "권한이 없습니다. 해당유저만 가능합니다."
    }

status code :

200(OK) : 정상

400(Bad_Request) : validation 실패

401(UNAUTHORIZED) : 권한 실패

404(NOT_FOUND) : id 조회 실패

---

댓글 삭제

method : DELETE

URI : /comments/{commentId}

request :  @PathVariable


response :

    //성공 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T13:11:36.239848",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_USER",
        "message": "권한이 없습니다. 해당유저만 가능합니다."
    }

status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패

404(NOT_FOUND) : id 조회 실패

---

피드 좋아요 기능

method : POST

URI : /likes/feed/{feedId}

request :  @PathVariable


response :

    //성공 예시
    
    {
        "id": 1,
        "feedId": 1,
        "userId": 1,
        "likeStatus": true
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T13:11:36.239848",
        "status": 404,
        "error": "Not Found",
        "code": "FEED_NOT_FOUND",
        "message": "해당 id 로 인한 피드 정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패

404(NOT_FOUND) : id 조회 실패

---

댓글 좋아요 기능

method : POST

URI : /likes/comment/{commentId}

request :  @PathVariable


response :

    //성공 예시
    
    {
        "id": 1,
        "commentId": 1,
        "userId": 1,
        "likeStatus": true
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T13:11:36.239848",
        "status": 404,
        "error": "Not Found",
        "code": "FEED_NOT_FOUND",
        "message": "해당 id 로 인한 댓글 정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패

404(NOT_FOUND) : id 조회 실패

---

피드 좋아요 카운트 기능

method : GET

URI : /likes/feed/{feedId}

request :  @PathVariable


response :

    //성공 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-22T07:26:21.963+00:00",
        "status": 401,
        "error": "Unauthorized",
        "path": "/categories"
    }

status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패(비로그인시)


---
댓글 좋아요 카운트 기능

method : GET

URI : /likes/comment/{commentId}

request :  @PathVariable


response :

    //성공 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-22T07:26:21.963+00:00",
        "status": 401,
        "error": "Unauthorized",
        "path": "/categories"
    }

status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패(비로그인시)




---
카테고리(부모카테고리 없는) 생성

method : POST

URI : /categories

request :  @RequestBody

    {
        "name": "카테고리 이름"
    }


response :

    //성공 예시
    
    {
        "id": 1,
        "parentId": null,
        "name": "카테고리 이름"
    }

    //실패 예시
    {
        "timestamp": "2024-11-22T07:26:21.963+00:00",
        "status": 401,
        "error": "Unauthorized",
        "path": "/categories"
    }

status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패(비로그인시)

---


카테고리(부모카테고리 있는) 생성

method : POST

URI : /categories?parentId=1

request :  @RequestBody , @RequestParam

    {
        "name": "카테고리 이름"
    }


response :

    //성공 예시
    
    {
        "id": 2,
        "parentId": 1,
        "name": "카테고리 이름"
    }

    //실패 예시
    {
        "timestamp": "2024-11-22T16:31:42.651442",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "CATEGORY_NOT_FOUND",
        "message": "해당 id 로 인한 카테고리 정보를 찾을 수 없습니다"
    }
    
    {
        "timestamp": "2024-11-22T16:32:24.08521",
        "status": 400,
        "error": "BAD_REQUEST",
        "code": "CATEGORY_STEP_OVER",
        "message": "카테고리는 3단계 까지 가능합니다."
    }

status code :

200(OK) : 정상

400(BAD_REQUEST) : 요청 실패(카테고리는 3단계 까지 가능)

401(UNAUTHORIZED) : 권한 실패(비로그인시)

404(NOT_FOUND) : id 조회 실패

---

카테고리 조회

method : POST

URI : /categories/{categoryId}

request :  @PathVariable


response :

    //성공 예시
    
    {
        "firstCategory": "코딩",
        "secondCategory": "java",
        "thirdCategory": "spring",
        "stepCategory": 3
    }

    //실패 예시
    {
        "timestamp": "2024-11-22T16:31:42.651442",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "CATEGORY_NOT_FOUND",
        "message": "해당 id 로 인한 카테고리 정보를 찾을 수 없습니다"
    }


status code :

200(OK) : 정상

401(UNAUTHORIZED) : 권한 실패(비로그인시)

404(NOT_FOUND) : id 조회 실패

---
