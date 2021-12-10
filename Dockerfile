FROM openjdk:13-jdk-alpine

LABEL maintainer "Carlos M. Abreu <magno.mabreu@gmail.com>"

COPY ./target/pointgen-1.0.war /opt/lib/
ENTRYPOINT ["java"]
ENV LANG=pt_BR.utf8 
CMD ["-jar", "/opt/lib/pointgen-1.0.war"]
	