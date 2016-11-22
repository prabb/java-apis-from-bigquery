FROM jetty
MAINTAINER Prabhdeep Singh <sing1763@sheridancollege.ca>
ADD ./target/BigQueryJavaAPI-*.war /var/lib/jetty/webapps/root.war
EXPOSE 8080
# docker build -t "user/project" .
# docker run -d -p 8080:8080 'user/project'
# Go to url:: docker-machine ip machinename (http:/10.80.2.242:8080)