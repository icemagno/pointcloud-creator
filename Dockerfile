FROM hiparco/geoprocessor-core-bullseye:1.0

LABEL maintainer "Carlos M. Abreu <magno.mabreu@gmail.com>"

COPY ./target/geoprocessor-1.0.war /opt/lib/
ENTRYPOINT ["java"]
ENV LANG=pt_BR.utf8 
CMD ["-jar", "/opt/lib/geoprocessor-1.0.war"]
	