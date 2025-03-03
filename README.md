# 텐텐
<img width="700" alt="image" src="https://github.com/user-attachments/assets/3d18dd80-09de-4d67-ae8a-947d4fc56401" />

## 📕 프로젝트 개요
비사이드에서 주최한 [포텐데이407 X 네이버클라우드] 해커톤 출품작으로 Naver Cloud Platform과 Hyper Clova X를 활용하여 갈등없이 평화로운 가족 여행을 위해 가족 구성원의 여행 성향을 분석해, 가족 맞춤형 '가족 여행 10계명'을 생성해주는 서비스입니다.

<br><br>

## 🛠️ 활용 기술 스택
### BE
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/> 

### DevOps
<img src="https://img.shields.io/badge/Naver Cloud Platform-03C75A?style=flat-square&logo=naver&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white"/> <img src="https://img.shields.io/badge/Github Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white"/>

<br><br>

## 📖 ERD
<img width="700" alt="tentenerd" src="https://github.com/user-attachments/assets/0a3a1d40-99ad-4471-8982-25458056485c" />

<br><br>

## ⚙️ 아키텍처 설계도
<img width="700" alt="image" src="https://github.com/user-attachments/assets/8f5d1d05-4e55-4b08-9078-53a57e994794" />

<br><br>

## 🌊 유저 플로우
<img width="700" alt="image" src="https://github.com/user-attachments/assets/56f9f5be-c2e2-4051-bc18-26613cc6e22d" />

<br><br>

## 📌 주요 기능
- 카카오 소셜 로그인
- 첫 회원가입 시 여행 성향 테스트
- 테스트 결과 기반 여행 성향 도출
- 여행방 생성, 초대, 입장, 탈퇴 및 권한 처리
- Hyper Clova X 연동 및 가족 구성원 성향 맞춤 여행 10계명 도출
- 비로그인 시 간단한 여행 10계명 도출 체험 가능

<br><br>

## 😎 여행 성향 테스트 결과 구분표
**부모 유형(Parent)**

| ID | 유형명 | 부정적 감정 표현 | 새로운 시도 | 계획성 | 의견 우선순위 |
| --- | --- | --- | --- | --- | --- |
| P1 | **열정 넘치는 불꽃 부모**(불꽃 카리스마 박 여사/서 과장) | 강한 부정적 감정 표현 | 매우 도전적 | 조금 즉흥형 | 부모 의견 조금 우선 |
| P2 | **긍정 에너지 만렙 부모**(긍정 파워 원더우먼/슈퍼맨) | 긍정적 태도 | 매우 도전적 | 완전 즉흥형 | 자녀 의견 조금 우선 |
| P3 | **걱정 많은 안전제일 부모** (걱정 대마왕 헬리콥터 맘/대디) | 강한 부정적 감정 표현 | 매우 소극적 | 완전 계획형 | 부모 의견 가장 우선 |
| P4 | **느긋한 여유파 부모** (느긋한 zen 요가 맘/명상 파파) | 긍정적 태도 | 매우 소극적 | 조금 계획형 | 자녀 의견 가장 우선 |
| P5 | **융통성 있는 중재자 부모** (만능 해결사 국민 엄마/아빠) | 제한적 타협 시도 | 약간 도전적 | 조금 계획형 | 자녀 의견 조금 우선 |
| P6 | **꼼꼼한 현실주의 부모** (철두철미 계산기 맘/엑셀 대장 파파) | 실망감 표현 | 약간 소극적 | 조금 즉흥형 | 부모 의견 조금 우선 |


**자녀 유형(Child)**

| ID | 유형명 | 부정적 감정 표현 | 새로운 시도 | 계획성 | 의견 우선순위 |
| --- | --- | --- | --- | --- | --- |
| C1 | **열정 가득 도전 자녀(**불꽃 튀는 엣지 있는 막내) | 강한 부정적 감정 표현 | 매우 도전적 | 완전 즉흥형 | 자녀 의견 가장 우선 |
| C2 | **긍정 뿜뿜 모험 자녀(**긍정 요정 해피 바이러스) | 긍정적 태도 | 매우 도전적 | 조금 즉흥형 | 자녀 의견 조금 우선 |
| C3 | **걱정 많은 신중 자녀(**걱정 인형 부모님 복사기) | 강한 부정적 감정 표현 | 매우 소극적 | 완전 계획형 | 부모 의견 가장 우선 |
| C4 | **느긋한 안전 자녀(**마이 페이스 여유만만 선배) | 긍정적 태도 | 매우 소극적 | 조금 계획형 | 부모 의견 조금 우선 |
| C5 | **균형 잡힌 협력 자녀(**중재의 달인 외교관 꿈나무) | 제한적 타협 시도 | 약간 도전적 | 조금 즉흥형 | 자녀 의견 조금 우선 |
| C6 | **꼼꼼한 실속 자녀(**꼼꼼 실속 가성비 전문가) | 실망감 표현 | 약간 소극적 | 조금 계획형 | 부모 의견 조금 우선 |

## ❗️ 기술적 의사결정 및 트러블슈팅
- 
