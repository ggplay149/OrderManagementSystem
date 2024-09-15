# OrderManagementSystem

<details>
<summary> 요구사항 </summary>  


> ### **상품 (Product)**

- 판매자 회원만 상품을 관리(상품 등록, 삭제, 가격수정, 재고량 수정)할수 있다.
- 상품은 [판매중]/[품절]/[삭제대기]/[판매중지]의 상태값을 갖는다.
- 상품 최초 등록시 default 상태값은 [판매중] 이다.
- 상품을 등록할수 있다.
- 상품 가격을 변경할수 있다.
- 상품 가격은 0원 이상이다. (0원 상품도 등록/수정 가능)  
- 상품 가격에 음수가 올수 없다.
- 상품의 목록을 조회 할 수 있다.
- 상품을 삭제할수 있다. (= 상품 상태를 [삭제대기] 로 업데이트한다)
- [주문완료] 주문에 속해있는 상품을 삭제할 수 없다. 하지만 [판매중지]로 상품상태 변경은 가능하다.   
  

<br>

> ### **장바구니 (Cart)**

- 유저는 하나의 장바구니를 갖는다.
- 장바구니에 상품을 추가할수있다.  
- 상품재고가 0인 상품은 장바구니에 추가할수 없다.
- 장바구니를 모두 비울 수 있다.
- 장바구니의 상품을 선택적으로 삭제 할 수 있다.
- 장바구니 내 상품 목록을 조회 할 수 있다.
- 장바구니에 담겨져있는 상품의 주문 수량을 증가하거나 감소시킬수있다.
- 장바구니 등록일로 부터 일주일간 주문되지 않으면 자동 삭제된다.
- 장바구니 목록에서 전체 혹 은 일부만 주문접수할수 있다.
- 현재 최신 상품가격과 선택된 가격 총합을 보여준다.
- 다음 주문단계로 넘어갈 때마다 상품의 상태값을 체크한다.
- 삭제된 상품이 있다면 안내메세지를 띄우고 장바구니에서 해당 상품를 삭제한다.


<br>

> ### **주문 (Order)**

- 주문은 장바구니를 통해서만 진행된다.
- 장바구니에 등록된 1개 이상의 상품을 주문할수있다.
- 주문 형식이 올바르지 않으면 등록할 수 없다.
- 비회원, 회원 모두 주문을 등록할수 있다.
- 회원은 회원정보를 통해 배송정보를 받는다
- 비회원은 직접 배송정보를 입력받는다.
- 배송 정보 전부 필수값으로 비워 둘 수 없다.
- 결제 방식을 선택할수있다.
- 주문을 접수한다.
- 주문은 [주문대기],[결제대기],[주문확정],[주문실패]의 상태값을 갖는다.
- 재고확인전에 상품의 상태값을 체크한다. 삭제된 상품이 있다면 안내메세지를 띄우고 해당 상품를 주문리스트에서 삭제한다
- 주문 접수시 실시간으로 재고 조회요청한다.
- 주문 접수 성공한다면 장바구니에서 성공상품들을 삭제한다.
- 재고가 있다면 재고 차감요청한다.
- 재고가 없다면 [주문실패]를 알리고, 장바구니에서 재고가없는 상품을 삭제한다.
- 주문 접수 성공한다면  [결제대기] 로 주문상태를 변경한다.
- [결제대기] 주문은 15분 동안 유효하다.
- [결제대기] 15분 이후 상태 변경이 없다면 주문삭제 후 상태를 [주문실패]로 변경한다.
- 주문 상태가 [주문실패]로 업데이트 된다면 재고 복구 요청한다.
- 주문상태는 실시간으로 확인 가능하다.
- 주문번호는 회원 : 1, 비회원 : 0 으로 시작한다
- 주문번호는 회원구분값/날짜/장바구니아이디/주문카운트횟수로 구성된다.
- [결제완료]된 주문은 고유 한 결제번호를 갖는다.


<br>

> ### **결제 (Payment)**

- 결제 정보는 실시간으로 처리되어야한다.
- 주문정보에 기재된 결제방식으로 결제한다.
- 포인트 결제방식은 멤버 포인트에서 잔액조회요청 후 차감요청한다.
- 일반 결제시 PG사 외부api를 호출한다.
- PG사 연동 결제는 아래와 같은 절차를 따른다
    - 우리측에서 결제KEY + 금액 + 메타정보를 전송
    - PG사측에서 Transaction key 발급
    - Transaction key를 통해 결제 모듈 실행
    - 이후 callback 값에 따라 결과 처리
- 결제처리 후 결제 결과로그를 저장한다.
- 결제로그는 [결제완료],[결제실패],[환불처리],[환불실패]를 상태값으로 갖는다.
- 결제처리 성공시 주문 상태를 [주문확정]으로 업데이트 요청한다.
- 결제처리 성공시 해당 주문 레코드에 결제 번호를 업데이트 한다.
- 결제번호는 "3"+주문번호로 구성한다.
- 결제처리 성공시 해당 주문 레코드에 결제 번호를 업데이트 한다.
- 결제처리 실패시 주문 상태를 [주문실패]로 업데이트 요청한다.


<br>

<br>

> ### **환불 (Refund)**

- 환불 정보는 실시간으로 처리되어야한다.
- 결제방식이 포인트결제 였다면 차감된 포인트 복구를 요청한다.
- 일반 환불 요청시 PG사 외부api를 호출한다.
- PG사 연동 환불은 아래와 같은 절차를 따른다
	- 우리측에서 환불KEY + 금액 + 메타정보를 전송
    - PG사측에서 Transaction key 발급
    - Transaction key를 통해 환불 모듈 실행
    - 이후 callback 값에 따라 결과 처리
- 환불처리 성공시 주문상태를 [환불처리]로 업데이트 요청한다.
- 환불처리 성공시 결제상태를 [환불처리]로 업데이트 요청한다.
- 환불처리 성공시 환불번호와 내역을 저장한다.
- 환불번호는 "4"+주문번호로 구성한다.
- 환불처리 실패시 1:1 고객문의 안내메세지를 띄운다.  


<br>

> ### **재고 (Inventory)**

- 재고를 조회할수있다.
- 판매자회원만 수동으로 재고를 증가/차감 할 수 있다.
- 다른 도메인 요청으로 인한 재고 증가/차감 할 수 있다.
- 재고변화가 일어난다면 변화 내역을 기록한다.

<br>

> ### **회원 (Memeber)**

- 회원 등록이 가능하다
- 회원 삭제가 가능하다
- 회원은 등급을 갖는다. 최초등록시 defalut bronze등급로 설정한다.
- 회원 등급은 [bronze]/[silver]/[gold] 이다.
- 회원정보는 이름/ 배송지 주소/전화번호를 필수값으로 받는다.
- 회원은 주문이력을 조회할수 있다.

<br>

> ### **포인트 (Point)**

- 회원은 포인트를 갖는다.
- 회원은 포인트로 결제할수있다.
- 회원은 매 결제 등급에 따라 [bronze] : 1% /[silver] : 2% /[gold] : 3% 적립받는다

<br>

<br>

> ### **비회원 (non-member)**

- 비회원 주문후 결제완료시에 비회원 주문번호를 부여한다.
- 비회원 주문후 비회원 주문내역이 저장된다.
- 비회원 주문번호로 주문 내역을 조회할수 있다.
<br>

> ### **알림  (Notifcation)**

- 실시간 알림을 전송한다
- 각 도메인 요청으로 인한 알림을 전송한다
  
</details>



<details>
<summary> 용어사전 </summary>  

### **상품 (Product)**

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 주문항목을 구성하는 데이터 |
| 이름 | displayed name | 상품판매시 사용될 제품명 |
| 판매자 | seller | 판매자명 |
| 재고 | inventory | 주문상태를 결정짓는 데이터 |
| 재고량 | quantity | 남아있는 재고량 |
| 장바구니 | cart | 주문 상품을 담는 데이터 |
| 가격 | price | 상품의 가격 |

<br>

> ### **주문 (Order)**

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | order | 주문 시스템에 기준이 되는 데이터 |
| 주문 항목 | items | 주문된 상품 리스트 |
| 주문 상태 | order status | 현재 주문 상태 |
| 주문 대기 | pending | 장바구니에 담겼을시, Default 주문상태값 |
| 주문 확정 | confirmed | 결제까지 성공한 후 최종 주문이 확정된 상태 |
| 주문 실패 | failed | 재고부족 혹은 결제 실패로 인한 주문실패 |
| 결제 대기 | pending | 재고 조회, 재고차감 이후 결제 대기중인 주문상태 , 24시간의 유효시간을 갖는다 |

<br>

> ### **결제 (Payment)**

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 결제 | payment | 결제 관련 정보 및 프로세스 |
| 결제 금액 | amount | 가격 등을 포함한 결제를 위한 메타데이터 |
| 결제 방식 | method | 주문접수시, 유저가 요청한 결제방식  |
| 결제 상태 | Status | 현재 결제 상태 |
| 결제 완료 | payment completed | 결제 방식을 통해 결제가 완료된 상태 |
| 결제실패 | payment failed | 결제 방식을 통해 결제가 실패된 상태 |
| 환불 | refund | 환불시, 필요한 결제 정보 데이터 |
| 환불 처리 | refund completed | 환불 처리가 완료된 상태 |
| 환불 실패 | refund Failed | 환불 처리가 실패한 상태 |

<br>


    
</details>

<details>
<summary> 모델링 </summary>

### **상품 (Product)**
- `Product`는 식별자와 `DisplayedName`, `Seller`, `Price` 를 가진다.
- `Product` 의 재고관련 정보는 `Inventory` 데이터를 통해 관리된다.
- `Product` 는 `Cart` 에 담겨진다.
- 주문시, `Cart` 에 있는 상품들의 정보가 전달된다.

<br>

### **주문 (Order)**
- `Order`는 식별자와 `Items`, `Order Status` 를 가진다.
- `Items` 의 수량은 0보다 커야한다.
- `Items` 의 가격과 수량을 갖는다.
- `Order`는 `Product` 의 `Inventory` 상황에 따라 진행된다.
- `Order`는 주문대기 → 결제대기 → 주문확정 순서로 진행된다.
- `Order Status` 는 `Payment` 와  `Inventory` 처리에의해 결정된다.

  <br>  

### **결제 (Payment)**
- `Payment`는 식별자와 `Payment Status`, `Payment Method` 를 가진다.
- `Amount`는 `Order` 정보에 의해 결정된다.
- `Payment` 는 결과는 `Order` 상태를 결정한다.
- `Refund`는 `Payment` 정보를 바탕으로 진행된다.


</details>


