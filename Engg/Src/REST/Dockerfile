FROM java:8
VOLUME /tmp
COPY . /
EXPOSE 8080
RUN /bin/sh /RestAPIDocker.sh
CMD "./openfda-service/bin/openfda-service"
