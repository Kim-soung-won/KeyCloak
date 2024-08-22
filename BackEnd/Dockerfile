FROM ubuntu:20.04
#SSH 서버 설치

# APT 리포지토리 업데이트 및 JDK 설치
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y tzdata && \
    apt-get install -y openjdk-11-jdk openssh-server curl zip unzip git vim && \
    apt-get clean

# 한국 시간대 설정
ENV TZ=Asia/Seoul
RUN ln -fs /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN curl -s "https://get.sdkman.io" | bash

RUN bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install gradle 8.8"

RUN apt-get clean
RUN mkdir /var/run/sshd
#root 암호 설정
RUN echo 'root:1234' | chpasswd
#sshd_config 설정 변경
RUN sed -ri 's/^#?PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config
#작업 공간 지정
WORKDIR /work

# JAR 파일을 컨테이너에 복사
# (예: ./build/libs/your-application.jar 경로를 사용한다고 가정)
COPY ./build/libs/Keycloak-1.0-SNAPSHOT.jar /work/Keycloak-1.0-SNAPSHOT.jar

# JAR 파일을 실행
ENTRYPOINT ["java", "-jar", "/work/Keycloak-1.0-SNAPSHOT.jar"]
