# spring-gift-wishlist

## step1 - 유효성 검사 및 예외처리

### 기능 구현
- **상품 추가, 수정 시 유효성 검사**
    -[x] 상품 이름은 공백 포함 최대 15자까지 입력 가능
    -[x] 가능한 특수 문자: ( ), [ ], +, -, &, /, _
    -[x] "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능

- **상품 수정, 삭제 시 유효성 검사**
    -[x] 존재하지 않는 ID의 상품 수정, 삭제를 시도할 시 예외처리

- **응답 처리**
    -[x] ResponseEntity 이용하여 응답값 통일

- **테스트**
    -[x] Service 테스트코드 다시 작성


## step2 - 인증 

### step1 피드백 기반 수정

- [ ]  Custom exception 비즈니스 에러코드를 표현할 수 있는 멤버변수를 두고 상황에 맞게 조절
- [ ]  전역 예외 처리기 Depth 보완
- [ ]  데이터 모델과 요청/응답 수행하는 클래스를 역할과 책임에 맞게 분리하기
- [ ]  매번 비슷한 검증 → 값 객체의 개념 이용
- [ ]  Dao에서 select를 할 때 반환 값으로 `Product` 가 아닌 `Optional` 사용해보기
- [ ]  도메인에서 처리될 수 있는 로직은 도메인에서 처리하도록 하기