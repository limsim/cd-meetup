FROM jenkins

USER root

RUN apt-get -y update
# RUN apt-get install -y software-properties-common
# RUN apt-add-repository -y ppa:ansible/ansible
# RUN apt-get -y update
RUN apt-get -y install ansible

RUN git clone git://github.com/boto/boto.git
WORKDIR boto
RUN python setup.py install

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/jenkins.sh"]