# 웹 디자인 프로젝트

[toc]

# 팀원

![image-20210820131039043](README.assets/image-20210820131039043.png)

## 기술 스택 정리

![image-20210820130539214](README.assets/image-20210820130539214.png)

## SCM Git Flow

![image-20210820132713651](README.assets/image-20210820132713651.png)



## Firebase Monitoring

![image-20210820132818263](README.assets/image-20210820132818263.png)

## 프로젝트 기능 리스트 업

```
📦 경쟁 컨텐츠
├─ 모집
│  ├─ 방장이 챌린지 만들기 (ex: 1일 1만두 챌린지)
│  └─ 일반 유저가 챌린지 참
├─ 경쟁
│  ├─ 매칭:
│  │  ├─ 대주제: 비슷한 시간대
│  │  ├─ 중주제: 비슷한 주제
│  │  └─ 시스템이 정하는 방식
│  │     └─ 보험: 붙어보자 탭 증설 계획
│  └─ 기간 동안 
│     ├─ 현재 점수 
│     ├─ 상대방 챌린지 피드
│     ├─ 우리 챌린지 피드
│     └─ 상대방 챌린지 달성율
├─ 결과
│  ├─ 챌린지 경쟁 결과
│  │  ├─ 참여도 점수
│  │  ├─ 지속율 (참가 인원 및 참가 기간에 따른 가산 적용)
│  │  └─ (텍스트, 이미지, 비디오 등 참여도 차등)
│  │     └─ 포인트로 대체할 수 있음
│  ├─ 시즌제 결과
│  │  ├─ 시즌에 참가한 챌린지 보상
│  │  ├─ 개인 보상
│  │  └─ 굼벵이 상 (사실상 블랙리스트)
│  └─ 결과에 따른 보상
│     ├─ 뱃지 시스템
│     └─ 최근 획득한 뱃지를 프로필에 보여준다.
│        └─ 혹은 뱃지 도감 
├─ -----------------------------------------------------------------
├─ 피드 
│  ├─ 단체 챌린지 방
│  └─ 자신이 가입한 챌린지
├─ 검색
│  ├─ 챌린지 검색
│  ├─ 아이디 검색
│  └─ 카테고리 검색
├─ 알림
│  ├─ 출석체크 알림
│  ├─ 팔로우 알림
│  ├─ 댓글, 새글, 인증글 알림
│  └─ 방장, 챌린지 장 (새로운 참가자 알림)
└─ 프로필
   ├─ 팔로우, 팔로잉
   ├─ 완료한 챌린지
   ├─ 완료한 챌린지 보상 - 뱃지
   ├─ 진행 중인 챌린지
   ├─ 관심 있는 챌린지
   └─ 통계 및 데이터 챌린지
```



## 메인 화면

![image-20210820130847744](README.assets/image-20210820130847744.png)

## Mobile

![image-20210820132329313](README.assets/image-20210820132329313.png)

![image-20210820132440625](README.assets/image-20210820132440625.png)

![image-20210820132556079](README.assets/image-20210820132556079.png)



- Minimum SDK level 24

- [Kotlin](https://kotlinlang.org/) based, [introducing Coroutines](https://github.com/Kotlin/kotlinx.coroutines)

- JetPack

  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
  - RecyclerView + RecyclerView .Selection
  - Hilt (DI)

- Architecture

  - MVVM Architecture 
  - etc ViewBinding
  - Clean Architecture

- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.

- [Timber](https://github.com/JakeWharton/timber) - logging.

- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.

- [Firebase](https://firebase.google.com/) - Crashlytics, Analytics, and in AppMessaging

- [spotless](https://github.com/diffplug/spotless) - for ktlint

- [Git](https://git-scm.com/) - for scm with Git flow strategy

  

## 프로젝트 일정

```mermaid
gantt
    dateFormat  MM-DD
    title       project_orange

    section 기획
    주제 선정				  :done, 07-19, 3d
    기능 목록 상세 도출		    :done, 07-19, 3d
    화면 기획				  :done, 07-21, 4d
    개발 환경 구성             :done, 07-22, 5d
	
	section Frontend
    notification		  	:done, 07-26, 5d
    auth	 				:done, 07-28, 5d
	develp->master 			:done, 07-29, 1d
    profile	 	 			:done, 08-02, 5d
    feed   	  				:done, 08-04, 4d
   	develp->master 			:done, 08-05, 1d
    develp->master 			:done, 08-12, 1d
	Challange		 	   	:done, 08-06, 7d
	search					:done, 08-12, 2d
	api-sync 				:done, 08-11, 7d

	
    section Mobile
    Feed		  			:done, 07-26, 5d
    notification			:done, 07-28, 5d
   	FCM						:done, 07-28, 5d
    develp->master			:done, 07-29, 1d
    develp->master			:done, 08-05, 1d
    develp->master			:done, 08-12, 1d
	auth   	  				:done, 07-31, 4d
    aws ec2 setting 		:done, 08-03, 2d
    profile					:done, 08-02, 5d
	Challange				:done, 08-06, 7d
	search					:done, 08-12, 2d
    api-sync 				:done, 08-11, 7d

	
    section Backend
    auth   	  				:done, 07-27, 4d
    JPA 학습 			  	   :done, 07-28, 4d
    profile	 	 			:done, 07-28, 5d
    develp->master 			:done, 07-29, 1d
    develp->master 			:done, 08-05, 1d
    develp->master 			:done, 08-12, 1d
    search					:done, 08-01, 3d
    contrller level 		:done, 08-02, 7d
    Feed		  			:done, 08-01, 5d
    notification	 		:done, 08-03, 5d
	Challange				:done, 08-06, 9d
	sync (unit)				:done, 08-14, 4d 
	
	section DevOps
	compose deploy passive :done, 08-06, 2d
	compose pipeline 	   :done, 08-08, 1d
	compose automation     :done, 08-09, 3d
	

	section 문서 정리 및 마무리
    QA 및 버그 찾기			:done, 08-16, 4d
    UCC제작 				  :done, 08-18, 2d
    발표준비				 :done, 08-18, 2d
```
