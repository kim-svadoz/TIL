# 빅데이터

## 20-02-11 화

### << 빅데이터 플랫폼 구축 >>

### - 리눅스

* 리눅스 상용화=> 레드햇 계열? 발전=>CentOS(서버구축에 적합)
* 내부 자원에 대해 모든 접근 권한을 정의할 수 있다.
  * 보안 유지에 적합하다.
  * 네트워크를 저가로 구성하기 좋다.

### - 머신 생성( VMWare설치 ) 총 4대의 CentOS 설치

* root => 프롬프트 #
* 일반계정 =.프롬프트 $

#### home(디렉토리)

- 특정 계정으로 로그인 했을 때 자동으로 위치하는 폴더
- 모든 계정은 홈디렉토리를 갖고 있다.
- 기본 설정은 홈디렉토리 명이 계정명과 동일
- root의 홈디렉토리명은 root폴더
- " / " <<< 제일 처음!!! (시작 위치)
- su
  - 
- su -
  - 
- 처음들어가면 ~] 이 들어 있고, ~은 홈 디렉토리를 의미한다.
- 일반계정의 홈디렉토리 => /home/hadoop(계정명)
- ls 현재 위치의 폴더
- cd/ => 최상위로 빠져나감
- cd~ => 홈으로 빠져나감
- cd.. => 한단게 빠져나감 



* 컴퓨터 - etc => 리눅스 설정 파일들이 담겨 있는 폴더
* 컴퓨터 - usr => 윈도우로 치면 programfiles
* 컴퓨터 - dev => 장치에 대한 정보

## 02-20-12 수

## << 빅데이터 플랫폼 구축>>

### 1. VMware 설치

### 2. 머신생성  - CentOS7

### 3. 머신 복제

- Hadoop01 : 192.168.111.128
- Hadoop02 :  192.168.111.129
- Hadoop03 : 192.168.111.131
- Hadoop04 : 192.168.111.130

### 4. 머신 4대를 클러스터링

#### 1) 방화벽 해제

* 방확벽보기

  ```bash
  systemctl status firewalld
  ```

* 방화벽 중지

  ```bash
  systemctl stop firewalld
  ```

* 시스템 리부팅

  ```bash
  reboot
  ```

* 방화벽 정지

  ```bash
  systemctl disable firewalld - "이렇게 해야 정지가 된다."
  ```

#### 2) hostname 변경

* hostname 이름 바꾸기

  ``` bash
  " tab누르면 자동완성 가능 "
  hostnamectl set-hostname hadoop01
  ```

#### 3) DNS 설정

- 우리는 Host-only와 NAT네트워크망의 구성.
- Bridge형식을 취하면 host 레벨의 ip를 받아서 사용가능

* 특정 ip로 접속하기

* ``` bash
  ssh 192.168.111.128
  ```

* system 서비스 목록보기

  ```bash
  systemctl list-units --type=service
  ```

* host에 hadoop 저장

  ```bash
  '다른위치-컴퓨터-etc-hosts파일'
  192.168.111.128 hadoop01
  192.168.111.129 hadoop02
  192.168.111.131 hadoop03
  192.168.111.130 hadoop04
  ```

* hadoop01머신에서 hadoop02, hadoop03, hadoop04에 직접 접속

  ```bash
  /etc/init.d/network restart
  ssh hadoop02
  ssh hadoop03
  ssh hadoop04
  ```

* 원격 서버로 copy

  ```bash
  scp 	copy할파일(위치까지 명시) 	copy받을서버의 위치
  scp		/etc/hosts 		root@hadoop02:/etc/hosts
  '명령어	copy할파일		target서버의 위치와 파일명'
  ```

* 원격 서버에 실행명령

  ```bash
  ssh		서버		'실행할 명령문'
  ```

* 암호화된 통신을 위해서 공개키 생성 후 배포

  * pub : 공개키

  ```bash
  cd ~
  ssh-keygen -t rsa
  cd. ssh
  ls
  ssh-copy-id -i id_rsa.pub hadoop@hadoop02
  ```

  

## 20-02-13 목

> 리눅스는 소유권한이라는게 있다. 따라서 권한을 신경써주자.

* jdk(RPM) 다운 후 설치하기

  ```bash
  rpm -Uvh jdk-8u231-linux-x64.rpm
  'U' - 'update'
  'V' - 'view'
  'H' - '설치하겠다.'
  ```

* Hadoop 다운 후 설치하기

  ```bash
  1. tar-zxvf hadoop-1.2.1.tar.gz 
  'Z' - 'gzip사용'
  'X' - '기존의 tar파일의 압축을 풀어주겠다'
  'V' - '명령어 실행시 화면에 출력'
  'F' - '파일의 이름을 지정'
  
  2. scp /home/hadoop/hadoop-1.2.1.tar.gz hadoop@hadoop02:/home/hadoop/ - '복사하기'
  3. ssh hadoop03 "tar zxvf hadoop-1.2.1.tar.gz" - '압축풀기'
  
  ```

* 각종 설정하기

  ```bash
  <<< home - hadoop - hadopo.1.2.1. - conf >>>
  "<hadoop-env.sh>"
  # The java implementation to use.  Required.
  export JAVA_HOME=/usr/java/jdk1.8.0_231-amd64
  
  "<master>"
  hadoop02
  
  "<slaves>"
  hadoop02
  hadoop03
  hadoop04
  
  "<core-site.xml>"
  	<configuration>
  		<property>
  			<name>fs.default.name</name>
  			<value>hdfs://hadoop01:9000</value>
  		</property>
  		<property>
  			<name>hadoop.tmp.dir</name>
  			<value>/home/hadoop/hadoop-data</value>
  		</property>
  	</configuration>
  	
  "<hdfs-site.xml>"	
  	<configuration>
  	<property>
  		<name>dfs.replication</name>
  		<value>3</value>
  	</property>
  	<property>
  		<name>dfs.http.address</name>
  		<value>hadoop01:50070</value>
  	</property>
  	<property>
  		<name>dfs.secondary.http.address</name>
  		<value>hadoop02:50090</value>
  	</property>
  	</configuration>
  	
  "<mapred-site.xml"
  	<configuration>
  	<property>
  		<name>mapred.job.tracker</name>
  		<value>hadoop01:9001</value>
  	</property>
  	</configuration>
  	
  "02,03,04에 설정 복사"
  scp /home/hadoop/hadoop-1.2.1/conf/* hadoop@hadoop02:/home/hadoop/hadoop-1.2.1/conf
  scp /home/hadoop/hadoop-1.2.1/conf/* hadoop@hadoop03:/home/hadoop/hadoop-1.2.1/conf
  scp /home/hadoop/hadoop-1.2.1/conf/* hadoop@hadoop04:/home/hadoop/hadoop-1.2.1/conf
  	
  "format하기"	
  /home/hadoop/hadoop-1.2.1/bin/hadoop namenode -format
  
  "Hadoop 시작하기"
  /home/hadoop/hadoop-1.2.1/bin/start-all.sh
  
  "02,03,04에 밀어넣기"
  jps
  ssh hadoop02 "jps"
  ssh hadoop03 "jps"
  ssh hadoop04 "jps"
  ```


## 02-20-14 금

1. jps 로 hadoop 머신들 역할 확인

2. hadoop 실행

   ```bash
   /home/hadoop/hadoop-1.2.1/bin/start-all.sh
   ```

3. input 추가

   ```bsh
   /home/hadoop/hadoop-1.2.1/bin/hadoop fs -ls /input
   ```

4. input 삭제

   ```bsh
   /home/hadoop/hadoop-1.2.1/bin/hadoop fs -rmr /input
   ```

5. 폴더 생성

   ```bash
   /home/hadoop/hadoop-1.2.1/bin/hadoop fs -mkdir /input
   ```

6. 파일 복사하기

   ```bash
   /home/hadoop/hadoop-1.2.1/bin/hadoop fs -copyFromLocal README.txt /input
   ```

7. wordcount 적용하기

   ```bash
   ./bin/hadoop jar hadoop-examples-1.2.1.jar wordcount /input/README.txt /output
   ```



![image-20200214104343107](images/image-20200214104343107.png)



* 여기서 쓰고 파폭에서 http://hadoop04:50075로 확인가능

![image-20200214104741331](images/image-20200214104741331.png)



예제) hadoop-examples-1.2.1.jar의 wordcount를 이용해서 작업하기

* HDFS에 myinput폴더를 작성한다
* LICENSE.txt를 복사한다
* wordcount를 적용
* 출력결과는 myoutput으로 작성

