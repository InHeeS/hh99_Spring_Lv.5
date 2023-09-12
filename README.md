# Spring-Lv.5
![image](https://github.com/InHeeS/hh99_Spring_Lv.5/assets/140541167/b1d301fe-3836-403d-9ded-3485da3345d9)


# api 명세서
https://galactic-rocket-548808.postman.co/workspace/galactic-rocket-548808-Workspac~6d6bb417-25a4-4a1c-893a-879e2595050c/collection/29336568-cb3e88b3-b997-4afd-b51c-db842b9b67be?action=share&creator=29336568

# URL 테스트 결과
https://documenter.getpostman.com/view/29336568/2s9YC2zZ1X

# 구현 기능

## Default
    좋아요 기능 추가
    1.1. 게시글, 선택 게시글 조회 & 수정 시 좋아요 포함해서 반환
    1.2. 좋아요 클릭하는 기능 추가

    AOP 개념을 이용해서 예외 처리

## Plus
* 회원 탈퇴 기능 구현

* 탈퇴 시 게시글 -> 댓글 ... 등 연관 데이터 모두 삭제

  *  대댓글 기능 추가

   * 대댓글 작성
    * 게시글 조회시 댓글과 대댓글 전부 조회

    * 게시글 및 댓글 조회시 페이징 및 정렬 기능 추가
    * 게시글 카테고리 샐성
    * Refresh Token 적용
    * 프로젝트에 swagger 적용
