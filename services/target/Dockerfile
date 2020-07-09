FROM centos:7

WORKDIR /
COPY ./app/ /app/

RUN yum update -y && \
curl -sL https://rpm.nodesource.com/setup_12.x | bash - && \
yum install -y nodejs && \
curl -sL https://dl.yarnpkg.com/rpm/yarn.repo | tee /etc/yum.repos.d/yarn.repo && \
yum install -y yarn && \
cd /app && npm install
