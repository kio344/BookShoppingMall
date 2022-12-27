# BookShoppingMall
1. 수행기간 : 2022. 10. 12 ~ 2022. 12. 28
![스크린샷 2022-12-28 시간: 00 14 40](https://user-images.githubusercontent.com/105355770/209686209-6d450f13-47ca-4c51-a7ad-9ee77006344f.png)

2. 목표 : 이전 프로젝트인 WorkOutProject 개선판, 새로운 기능들 추가.
  프로젝트에 참여한 인원 : 김민호, 박문수, 탁정모, 정민상, 배민서
  <br>
  3-1 : [김민호](#관리자와-판매자-페이지--김민호)
    <br>
  3-2 : [박문수](#회원이-접근-가능한-페이지--박문수)
    <br>
  3-3 : [탁정모](#bookshoppingmall-메인-페이지--탁정모)
    <br>
  3-4 : [정민상](#쇼핑몰-구매-페이지--정민상)
    <br>
  3-5 : 배민서
    <br>
3. 내용 : 사이트 내에 게시판과 리뷰등을 작성할 수 있게 만들어서 소비자가 서로 상호작용할 수 있도록 게시판 기능 구현,

  ## 관리자와 판매자 페이지 ( 김민호 )

  3-1-1 : 관리자와 판매자 페이지르 만들어 판매자가 팔고자 하는 상품을 신청하면,
  ![6](https://user-images.githubusercontent.com/105355770/209686445-3e89e876-34b2-45e6-8f88-dc11940f58d5.png)
  
  관리자가 수락 또는 거절을 할 수 있으며, 수락한 경우 판매 페이지에 등록된다.
  ![2](https://user-images.githubusercontent.com/105355770/209686646-c612d3b2-0f66-46b4-908b-0cfc3ed773c1.png)
  
  3-1-2 : 관리자는 게시판을 추가, 삭제, 수정이 가능하며, 회원 전용 게시글로 변경도 가능하다.
  ![1](https://user-images.githubusercontent.com/105355770/209686775-816d607e-eed7-463e-9a7e-f7732b6b77c2.png)
  
  3-1-3 : 관리자가 가입한 모든 회원들의 정보(이름, 닉네임, 주소, 유저타입)를 변경 가능하다.
  ![3](https://user-images.githubusercontent.com/105355770/209687144-c28715d5-e0b4-447f-8aad-2febe387a402.png)
  
  3-1-4 : 판매자는 배송 관리 페이지에서 책을 구매한 회원에게 상품을 배송, 취소 처리가 가능하다
  ![5](https://user-images.githubusercontent.com/105355770/209687489-8c16eff2-fa60-4efd-8b21-a49f993e3a8f.png)
  
  3-1-5 : 판매자 페이지에서 회원이 구매한 책에 리뷰들을 볼 수 있다.
  ![7](https://user-images.githubusercontent.com/105355770/209689965-0c1a55c8-60a6-44e6-a71e-68464d556fc3.png)
  
  3-1-6 : 판매자 페이지에서 판매 페이지에 올라간 책들을 수정할 수 있다 ( 수량, 가격 )
  ![8](https://user-images.githubusercontent.com/105355770/209690072-cd40c524-f88a-472e-8521-ae855a8ad57a.png)
  
  3-1-7 : 판매자 페이지에서 거절 또는 승인된 상품을 수정할 수 있다. ( 수량, 가격 )
  ![스크린샷 2022-12-28 시간: 00 52 07](https://user-images.githubusercontent.com/105355770/209690241-86ce4674-e70f-4930-9a16-b6725e4578e6.png)
  
  
  # 회원이 접근 가능한 페이지 ( 박문수 ) 

  3-2-1 : 로그인 페이지 카카오 로그인 API를 이용해 카카오아이디로 로그인이 가능하다. 
  ![스크린샷 2022-12-28 시간: 00 55 54](https://user-images.githubusercontent.com/105355770/209690617-75a61e05-e9db-4eeb-91c4-2138ef2f2fc2.png)
  
  3-2-2 : QnA 페이지에 들어가면 작성자가 문의한 글과
  ![박문수-qna](https://user-images.githubusercontent.com/105355770/209691425-00085888-9b9f-4188-8d07-78eb56c01e00.png)
  
  3-2-3 : 답변이 나온다
  ![박문수-qna2](https://user-images.githubusercontent.com/105355770/209691452-a8e688f9-9c71-4447-b081-f3fd89ecd431.png)
  
  3-2-4 : 커뮤니티에 들어가면 게시판 종류들이 나온다.
  ![박문수-자유게시판](https://user-images.githubusercontent.com/105355770/209691498-5d138654-491c-4e0c-888c-0fdd98a9781f.png)
  
  3-2-5 : 커뮤니티의 종류는 총 4가지다.
  ![박문수-정보공유](https://user-images.githubusercontent.com/105355770/209691522-a9f48f00-42a5-4967-8beb-e3248a9e1508.png)
  
  ![박문수-추천게시판](https://user-images.githubusercontent.com/105355770/209691534-bcea9fc1-5d25-4329-8166-9141370026dc.png)
  ![박문수-커뮤니티-공지사항](https://user-images.githubusercontent.com/105355770/209691550-68d00d64-5448-44f1-8cde-25a2b344702b.png)
  
  # 쇼핑몰 구매 페이지 ( 정민상 ) 
  3-4-1 : 로고 옆 메뉴를 클릭 후 Book Shop을 클릭하면 쇼핑몰 메인 페이지가 나온다
  ![정민상-판매 페이지 메인페이지](https://user-images.githubusercontent.com/105355770/209692374-0d40e450-2832-4f06-aa7c-5878f1fd1dfc.png)
  
  3-4-2 : 쇼핑몰 메인 페이지에서 상품을 클릭시 상품 구매 또는 공유하기 와 비슷한 상품 또는 리뷰가 나온다
  ![정민상-판매 페이지-상품 클릭시](https://user-images.githubusercontent.com/105355770/209692461-7fcb4aa6-bb9c-4057-8836-8adb4d08d9b3.png)
  
  3-4-3 : 아래에 있는 리뷰를 클릭 시 해당 상품에 달린 리뷰가 나온다 (리뷰가 없으면 작성된 리뷰가 없습니다. 라는 문구 출력)
  ![정민상-판매 페이지-리뷰](https://user-images.githubusercontent.com/105355770/209692545-4b834f55-4257-4534-8ef9-39b5423867d1.png)
  
  3-4-4 : 상품 구매하기를 누르면, 기본 정보를 입력하는 창이 나오고, 해당 회원의 정보가 구매자 정보에 자동으로 입력이 된다. 배송 주소를 기본 배송주소, 새로운 배송주소로 선택이 가능하다.
  ![정민상-상품구매-정보입력](https://user-images.githubusercontent.com/105355770/209692681-b649651a-777a-4a30-bdc6-aa49f3052f23.png)
  
  3-4-5 : 주문 정보를 입력하고 결제하기를 누르면 결제수단 창이 나오게 된다.
  ![정민상-상품구매-결제수단](https://user-images.githubusercontent.com/105355770/209692749-baac83ec-8c2b-4a69-8ee5-c6f28d0922ee.png)
  
  # BookShoppingMall 메인 페이지 ( 탁정모 ) 
  3-3-1 : BookShoppingMall 메인 페이지로 들어오게 되면 상단에 회원가입과 로그인을 할 수 있다.
  ![탁정모-회원가입-로그인](https://user-images.githubusercontent.com/105355770/209693370-b9d96961-cbe1-4263-b015-696bc217c2c7.png)
  3-3-2 : 회원가입을 누를 시 회원가입이 가능한 페이지가 나오고 회원가입을 누르면 로그인 페이지로 이동한다.
  ![탁정모-회원가입](https://user-images.githubusercontent.com/105355770/209693496-d0014948-43bd-4bb4-8c8b-bebb52fda5c2.png)
  3-3-3 : 로그인 페이지이다.
  ![탁정모-로그인](https://user-images.githubusercontent.com/105355770/209693547-ce1efa5d-46e4-48e9-9bd7-aee6899d3097.png)






  
