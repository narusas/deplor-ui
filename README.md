# deplor-ui

# 초기화
 - 로컬에 Mysql 설치
 - DB: deplor ID: deplor PW: deplor 생성

  ./gradlew eclipse

  ./gradlew flywayClean flywayBaseline flywayMigrate
 
# 실행 옵션 
VM argumenets에 다음 추가
  -javaagent:${project_loc}/lib/spring-instrument-4.1.6.RELEASE.jar
