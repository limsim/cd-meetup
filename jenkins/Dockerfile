FROM jenkins

USER root

RUN apt-get -y update
RUN apt-get -y install ansible maven

RUN rm -rf boto
RUN git clone https://github.com/boto/boto.git
WORKDIR boto
RUN python setup.py install

RUN mkdir /var/jenkins_home

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/jenkins.sh"]