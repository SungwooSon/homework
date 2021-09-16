# 알서포트 공지사항 웹 애플리케이션 제작 과제
___
과제는 다음의 환경에서 제작했습니다.
* Mac OS
* IntelliJ IDEA Community 2011.1
* Java 11
* Gradle 7.1.1
* Springboot 2.5.4

프로젝트 빌드, 실행 방법은 다음과 같습니다.
1. 아래 주소를 이용해서 프로젝트를 clone 해주세요.
    * https://github.com/SungwooSon/homework.git
2. 터미널에서 프로젝트 루트 폴더로 이동해 다음 명령실행
    *  ./gradlew build (or ./gradlew clean build)
    * 통합 테스트 성공한다면 build를 성공합니다.
    * 빌드가 성공하면 build/libs에 .jar 파일이 생깁니다.
3. build/libs 폴더로 이동해서 아래 명령실행하여 애플리케이션을 실행합니다.
    * java -jar homework-0.0.1-SNAPSHOT.jar\
4. localhost:8080 으로 접속합니다.
   * 메인 화면에 애플리케인션이 실행되면서 저장한 공지사항 목록이 조회됩니다.
   * 번호 기준으로 내림차순되어 있으며, 10개 단위로 페이징 되어있습니다.
   * 목록하단에 previous, next 버튼으로 페이지 이동을 할 수 있습니다.
   * 공지 하나의 제목을 클릭하면, 수정 또는 삭제를 할수 있는 페이지로 이동합니다.
   * 목록 상단에 '공지등록' 버튼을 누르면 새로운 공지를 입력하는 페이지로 이동합니다.
   > 로그인, 파일첨부 기능은 구현하지 못했습니다.

REST API 테스트 주소
* http://localhost:8080/api/v1/notices/list
* 등록된 모든 공지사항 정보를 JSON 형태로 응답합니다.
