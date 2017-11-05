FROM tomcat:8

ARG ARTIFACT_ID
ARG ARTIFACT_VERSION

COPY build/libs/$ARTIFACT_ID-$ARTIFACT_VERSION.war /usr/local/tomcat/webapps/$ARTIFACT_ID.war
RUN rm -rf /usr/local/tomcat/webapps/host-manager
RUN rm -rf /usr/local/tomcat/webapps/manager
RUN rm -rf /usr/local/tomcat/webapps/examples

CMD ["catalina.sh", "run"]