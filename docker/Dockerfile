from ubuntu:20.04

RUN apt-get update --fix-missing -y
RUN apt-get upgrade -y
RUN apt-get update --fix-missing -y
RUN apt-get install gcc -y
RUN apt-get install g++ -y
RUN apt-get install gcc-multilib -y
RUN apt-get install wget -y
RUN apt-get install nano -y
RUN apt-get install clang -y
RUN apt-get install build-essential -y
RUN DEBIAN_FRONTEND=noninteractive apt-get install cmake -y
RUN apt-get install clang-tools -y
RUN apt-get install lcov -y
RUN apt-get install screen -y
RUN apt-get install gdb -y
RUN apt-get install python3 -y
RUN apt-get install python3-pip -y
RUN apt-get install default-jdk -y
RUN apt-get install xxd -y
RUN apt-get install git -y
RUN apt-get install rsync -y
RUN apt-get install nano -y
RUN apt-get install python3-dev automake flex bison libglib2.0-dev libpixman-1-dev python3-setuptools python3-pip -y
WORKDIR /tmp
RUN git clone https://github.com/AFLplusplus/AFLplusplus
WORKDIR /tmp/AFLplusplus
RUN git checkout 3.14c
RUN CC=clang CXX=clang++ LLVM_CONFIG=$(find /usr/ -name llvm-config) make 
RUN make install

RUN python3 -m pip install virtualenv

WORKDIR /root

ENTRYPOINT ["bash"]
