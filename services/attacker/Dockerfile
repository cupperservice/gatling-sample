FROM centos:7

RUN yum update -y && \
yum install -y unzip && \
yum install -y zip && \
yum install -y which && \
curl -s "https://get.sdkman.io" | bash && \
source "$HOME/.sdkman/bin/sdkman-init.sh" && \
sdk install sbt && \
sdk install java 11.0.9.j9-adpt
