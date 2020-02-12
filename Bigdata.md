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
  "다른위치-컴퓨터-etc-hosts파일"
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
  "명령어	copy할파일		target서버의 위치와 파일명"
  ```

* 원격 서버에 실행명령

  ```bash
  ssh		서버		"실행할 명령문"
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

  