# 유자차

![1569322071561](https://user-images.githubusercontent.com/39547788/65505491-4d39af00-df04-11e9-8e66-4bf1507395d2.png)

CAN 통신을 이용한 P2P Car Sharing Platform : PCSP 서비스 구현

프로토콜 정의 계속 적어주세요

 <h3> <u>~~ 홧팅~~</u></h3>

<br><br>

<br>



## 라떼판다 -> 서버 

#### 라떼 판다에서 나 라떼 판다에요~(소켓 만들자 )

- CAR/PANDA/
  <br>
#### 라뗴 판다에서 로그인이 되었을때 CarNum알려주기

- CAR/PANDA/carNum
<br>
#### 라떼 판다에서 차량을 등록을 할때 

- CAR/CARREGISTER/userId,userName,carNum



<br><br>

<br>

## 안드로이드 -> 서버

#### 안드로이드에서 나 안드로이드에요~(소켓 만들자 )

- APP/ANDROID/
<br>
#### 안드로이드에서 로그인 성공 후 userID알려주기
 - APP/ANDROID/userID
 <br>

#### 안드로이드에서 키를 요청할때 (모든 조건 충족시) 

- APP/KEYREQUEST/userId,carNum
  <br>

#### 안드로이드에서 로그인 할때

- APP/LOGIN/userID,PW

<br>

#### 안드로이드에서 차가 있는 마커를 클릭했을때 

- APP/MARKERCLICKED/~~

<br>

#### 안드로이드에서 

- APP/

<br>





<br><br>

<br>



## 서버 -> 안드로이드

#### 로그인 성공,실패
- ANDROID/LOGIN/OK
- ANDROID/LOGIN/FAIL
<br>
#### 마커를 클릭해서 차리스트를 줄때 

- ANDROID/

<br>

#### 서버로 부터 키값을 받을때

- 

<br>



<br><br>

<br>

## 서버 -> 라떼판다

#### 로그인 성공,실패
- PANDA/LOGIN/OK
- PANDA/LOGIN/FAIL
<br>

#### 서버로 부터 키값을 받을때

- PANDA/

<br>



<br><br><br>
