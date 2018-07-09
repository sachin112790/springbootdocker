FROM openjdk:8-jdk
ENV PORT=8080
EXPOSE 8080
VOLUME /tmp
RUN mkdir /work
ENV GOOGLE_APPLICATION_CREDENTIALS=/tmp/ci-cd.json
COPY . /work
COPY ci-cd.json /tmp/
WORKDIR /work
RUN cp /work/build/libs/*.jar /work/app.jar

CMD ["java", "-jar", "/work/app.jar"]