# OrderManagementSystem

<details>
<summary> 요구사항 </summary>  


> ### **상품 (Product)**

- 판매자 회원만 상품을 관리할수 있다.
- 상품을 등록할수 있다.
- 상품 가격을 변경할수 있다.
- 상품 가격은 0원이상이어야 등록/수정 가능하다.
- 상품 정보가 올바르지 않으면 등록할수 없다.
- 상품을 삭제할수 있다.
- 상품의 목록을 조회 할 수 있다.

> ### **장바구니 (Cart)**

- 장바구니에 상품을 추가할수있다.
- 상품재고가 0인 상품은 장바구니에 추가할수 없다.
- 장바구니를 모두 비울 수 있다.
- 장바구니의 상품을 선택적으로 삭제 할 수 있다.
- 장바구니 목록을 조회 할 수 있다.
- 장바구니에 담겨있다면  해당 상품의 주문 수량을 증가하거나 감소시킬수있다.
- 장바구니 등록일로 부터 일주일간 주문되지 않으면 자동 삭제된다.
- 장바구니 목록에서 전체 혹 은 일부만 주문접수할수 있다.
- 현재 최신 상품가격과 선택된 가격 총합을 보여준다.
- 장바구니 상품들은 [주문대기]를 기본상태값으로 갖는다.

> ### **주문 (Order)**

- 주문은 장바구니를 통해서만 진행된다.
- 장바구니에 등록된 1개 이상의 상품을 주문할수있다.
- 주문 형식이 올바르지 않으면 등록할 수 없다.

- 비회원, 회원 모두 주문을 등록할수 있다.
- 회원은 회원정보를 통해 배송정보를 받는다
- 비회원은 직접 배송정보를 입력받는다.
- 배송 정보 전부 필수값으로 는 비워 둘 수 없다.
- 결제 방식을 선택할수있다.

- 주문을 접수한다.

- 주문 접수시 실시간으로 재고 조회요청한다.
- 주문 접수 성공한다면 장바구]에서 성공상품들을 삭제한다.

- 재고가 있다면 재고 차감요청한다.
- 재고가 없다면 [주문실패]를 알리고, 장바구니에서 재고가없는 상품을 삭제한다.

- 주문 접수 성공한다면  [결제대기] 로 주문상태를 변경한다.
- [결제대기] 주문은 24시간 동안 유효하다.
- [결제대기] 24시간 이후 상태 변경이 없다면 주문삭제 후 상태를 [주문실패]로 변경한다.
- 주문 상태가 [주문실패]로 업데이트 된다면 재고 복구 요청한다.
- 주문상태는 실시간으로 확인 가능하다.

> ### **결제 (Payment)**

- 결제 정보는 실시간으로 처리되어야한다.
- 주문정보에 기재된 결제방식으로 결제한다.
- 포인트 결제방식은 유저 포인트에서 잔액조회요청 후 차감요청한다.
- 일반 결제시 PG사 외부api를 호출한다.
- PG사 연동 결제는 아래와 같은 절차를 따른다
    - 우리측에서 결제KEY + 금액 + 메타정보를 전송
    - PG사측에서 Transaction key 발급
    - Transaction key를 통해 결제 모듈 실행
    - 이후 callback 값에 따라 결과 처리
- 결제처리 후 결제 결과로그를 저장한다.
- 결제처리 성공시  주문 상태를 [주문확정]으로 업데이트 요청한다.
- 결제처리 실패시  주문 상태를 [주문실패]로 업데이트 요청한다.

- 환불요청시 환불 묘둘을 실행한다.
- 환불 성공시 주문상태를 [환불처리]로 업데이트 요청한다.
- 환불 실패시 [환불실패] 고객안내 알림을 요청한다.

> ### **재고 (Inventory)**

- 재고를 조회할수있다.
- 판매자회원만 수동으로 재고를 증가/차감 할 수 있다.
- 다른 도메인 요청으로 인한 재고 증가/차감 할 수 있다.
- 재고변화가 일어난다면 변화 내역을 기록한다.

> ### **사용자 (User)**

- 회원 등록이 가능하다
- 회원 삭제가 가능하다
- 회원 포인트 잔액 조회가 가능하다
- 회원 포인트 충전이 가능하다
- 회원정보는 이름/ 배송지 주소/전화번호를 필수값으로 받는다.
- 회원은 주문이력을 조회할수 있다.

> ### **알림  (Notifcation)**

- 실시간 알림을 전송한다
- 각 도메인 요청으로 인한 알림을 전송한다
  
</details>



<details>
<summary> 용어사전 </summary>  
</details>
