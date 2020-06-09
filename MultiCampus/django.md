# django

> 파이썬으로 이루어진 WEB 프레임워크.

## django의 성격

- 파이썬을 이용하기 때문에 개발 속도가 빠르다.
- 프레임워크라는 것은? ----> 프랜차이즈의 성격을 갖는다 ( 내가 다 줄테니 너넨 사용만 해라~ )

## why django?

- 풍부한 언어와 프레임워크 사용 경험을 배양하기 위함.

## MTV

> 스프링의 MVC패턴과 같은 패턴임

Model - Template(사용자가 보는 화면) - View(중간 관리자)



# Django Intro

## Strat Django

1. 장고 설치하기

```bash
pip install djang==2.1.15
pip list
```

2. 프로젝트 생성

```bash
django-admin startproject <프로젝트 명>
```

```bash
python manage.py runserver
```

3. 프로젝트 생성시 제공되는 파일
   - `manage.py`
     - 현재 django와 관련된 모든 명령어를 `manage.py`를 통해 실행합니다.
   - `__init__.py`
     - 현재 `__init__.py` 파일이 존재하는 폴더를 하나의 프로젝트 혹은 패키지로 인식시키게 해주는 파일
   - `settings.py`
     - 현재 프로젝트의 전체적인 설정 및 관리를 위해 존재하는 파일
   - `urls.py`
     - 내 프로젝트에 접근할 수 있는 경로를 설정하기 위한 파일
   - `wsgi.py`
     - ??배포용??



## Start App

### 1. Application 생성

``` bash
python manage.py startapp pages(프로젝트 명)
```

- setting.py에서 application 출생신고(?) 및 설정하기

![image-20200609163627886](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200609163627886.png)



![image-20200609163824929](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200609163824929.png)

- 다시 서버 시작해보기

```bash
cd intro/
python manage.py runserver
```

![image-20200609164007909](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200609164007909.png)

언어(한글) 및 시간(서울) 설정 완료!



### 2. @@@@@@@@@@@@@@@@