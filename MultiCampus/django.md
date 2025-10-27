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

![image-20200610100316308](https://user-images.githubusercontent.com/58545240/89759699-b7ac0b80-db25-11ea-8b1a-d8a5d257dbdf.png)


- **URLs:** 단일 함수를 통해 모든 URL 요청을 처리하는 것이 가능하지만, 분리된 뷰 함수를 작성하는 것이 각각의 리소스를 유지보수하기 훨씬 쉽습니다. URL mapper는 요청 URL을 기준으로 HTTP 요청을 적절한 view로 보내주기 위해 사용됩니다. 또한 URL mapper는 URL에 나타나는 특정한 문자열이나 숫자의 패턴을 일치시켜 데이터로서 뷰 함수에 전달할 수 있습니다.
- **View:** view는 HTTP 요청을 수신하고 HTTP 응답을 반환하는 요청 처리 함수입니다. View는 Model을 통해 요청을 충족시키는 데 필요한 데이터에 접근합니다. 그리고 탬플릿에게 응답의 서식 설정을 맡깁니다.
- **Models:** Model은 application의 데이터 구조를 정의하고 데이터베이스의 기록을 관리(추가, 수정, 삭제)하고 query하는 방법을 제공하는 파이썬 객체입니다.. 
- **Templates:** 탬플릿은 파일의 구조나 레이아웃을 정의하고(예: HTML 페이지), 실제 내용을 보여주는 데 사용되는 플레이스홀더를 가진 텍스트 파일입니다. view는 HTML 탬플릿을 이용하여 동적으로 HTML 페이지를 만들고 model에서 가져온 데이터로 채웁니다. 탬플릿으로 모든 파일의 구조를 정의할 수 있습니다.탬플릿이 꼭 HTML 타입일 필요는 없습니다!

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

![image-20200609163627886](https://user-images.githubusercontent.com/58545240/89759735-ceeaf900-db25-11ea-891f-2ca3018b1ccd.png)




![image-20200609163824929](https://user-images.githubusercontent.com/58545240/89759748-d9a58e00-db25-11ea-9f2e-d6df5299fe91.png)


- 다시 서버 시작해보기

```bash
cd intro/
python manage.py runserver
```

![image-20200609164007909](https://user-images.githubusercontent.com/58545240/89759760-e1653280-db25-11ea-97a7-72c365189085.png)


언어(한글) 및 시간(서울) 설정 완료!



### 2. urlPattern 및 `View`, `Template` 설정

- url. py : 경로설정

![image-20200610094001890](https://user-images.githubusercontent.com/58545240/89759781-ea560400-db25-11ea-88d0-7444cc6bf66c.png)

- views.py : 함수설정

![image-20200610094119658](https://user-images.githubusercontent.com/58545240/89759799-f0e47b80-db25-11ea-82d5-dee5d1864be3.png)

- templates 안에 html파일 생성

![image-20200610094359900](https://user-images.githubusercontent.com/58545240/89759830-00fc5b00-db26-11ea-87ec-39744b3fbbbd.png)

`{{ }}` 안에 value 값을 넣어준다~

![image-20200610094441250](https://user-images.githubusercontent.com/58545240/89759845-05c10f00-db26-11ea-8fed-0ae8e7d0a029.png)!!!

- 이렇게 key값과 value형태인 dictionary로 사용가능하다!

![image-20200610101459650](https://user-images.githubusercontent.com/58545240/89759865-0f4a7700-db26-11ea-9f8b-44d90e07a773.png)

![image-20200610101543298](https://user-images.githubusercontent.com/58545240/89759875-14a7c180-db26-11ea-987b-91f3476f9d00.png)

- **조금 더 깔끔하게 작성하기.**

![image-20200610102608265](https://user-images.githubusercontent.com/58545240/89759900-20938380-db26-11ea-8043-e2e5b3908ff5.png)

![image-20200610102620796](https://user-images.githubusercontent.com/58545240/89759918-28ebbe80-db26-11ea-81a0-655ce09a20b7.png)


- url에서 입력을 받을 때 특정 문자/숫자만 요청할 수 있다.

![image-20200610111540035](https://user-images.githubusercontent.com/58545240/89759937-2f7a3600-db26-11ea-8dc4-e61d879046ea.png)

Q1

```python
# urls.py
path('urlcopy/<str:name>/<int:age>', views.urlcopy)

# views.py
def urlcopy(request, name, age):
    name = name
    age = age
    context={
        'name' : name,
        'age' : age
    }
    return render(request, 'urlcopy.html', context)


# urlcopy.html
<h2>URL로 입력받은 이름 : {{name}}, 나이 : {{age}}</h2>
```

![image-20200610112032946](https://user-images.githubusercontent.com/58545240/89759966-3ef97f00-db26-11ea-8267-31b7cf0fbe8e.png)




Q2

```python
# urls.py
path('multiply/<int:num1>/<int:num2>', views.multiply)

# views.py
def multiply(request, num1, num2):
    num1 = num1
    num2 = num2
    result = num1 * num2
    context = {
        'num1' : num1,
        'num2' : num2,
        'result' : result
    }
    return render(request, 'multiply.html', context)

# multiply.html
URL로 받은 숫자1 : {{num1}} , 숫자2 : {{num2}}

곱한 값 => {{result}}
```

![image-20200610113316969](https://user-images.githubusercontent.com/58545240/89759984-491b7d80-db26-11ea-9472-607cfcb4f922.png)



Q3

```python
# urls.py
path('multipletable/<int:big>/<int:small>', views.multipletable),

# views.py
def multipletable(request, big, small):
    result = []
    if(big < small):
        big, small = small, big
    for num in range(1, small+1):
        result.append(big*num)
    context={
        'result' : result
    }
    return render(request, 'multipletable.html', context)

# multiplly.html
<p>{{result}}</p>
<h1>Django Temlplate Lagnuage ( DTL )</h1>

<!--  -->
{# 이게 DTL의 주석 #}
{% comment %}
DTL의 주석은 개발자모드(F12) 에서도 보이지 않는다!
여기는 전부
출력이 안됨... 주석임!
되도록이면 template에서는 주석사용 XX
{% endcomment %}

{% for num in result %}
  <p>{{num}}</p>
{% endfor %}
```

![image-20200610131929723](https://user-images.githubusercontent.com/58545240/89759999-520c4f00-db26-11ea-9b94-154b4be8e7e5.png)



### 3. DTL 사용해보기

```python
# urls.py
path('dtl/', views.dtl),

# views.py
def dtl(request):
    mList = ['짜장면', '차돌짬뽕', '탕수육', '콩국수']
    empty_list = []
    mString = "Life is short, You need Python"
    today = datetime.now()
    context = {
        'mList' : mList,
        'empty_list' : empty_list,
        'mString' : mString,
        'today' : today
    }
    return render(request, 'dtl.html', context)

# dtl.html
<h1>1. for문</h1>
{% for food in mList %}
  <p> {{ forloop.counter }}.{{ food }} </p>
{% endfor %}

{% for data in empty_list %}
  <p>data</p>
{% empty %}
  <p>비어있습니다.</p>
{% endfor %}
<hr>

<h1>2. 조건문</h1>
{% for food in mList %}
  {% if food == '짜장면' %}
    <p>짜장면엔 고춧가루지 아암 이게맞지</p>
  {% else %}
    <p>뭔 소리야 {{ food }}을 먹어줘야지</p>
  {% endif %}
{% endfor %}
<hr>

{% for food in mList %}
  {% if forloop.first %}
    <p> {{forloop.first}} </p>
    <p> {{forloop.last}} </p>
    <p>짜장면엔 고춧가루지 아암 이게맞지</p>
  {% else %}
    <p>뭔 소리야 {{ food }}을 먹어줘야지</p>
  {% endif %}
{% endfor %}

<h1>3. filter 활용</h1>
  <p>{{ mString|lower }}</p>
  <p>{{ mString|upper }}</p>
  <p>{{ mString|title }}</p>
  <p>{{ mString|length }}</p>
  <p>{{ mString|truncatewords:3 }}</p>
  <p>{{ mString|truncatechars:10 }}</p>
<hr>

<h1>4. lorem ipsum</h1>
<p>{% lorem %}</p>
<p>{% lorem 3 w %}</p>
<p>{% lorem 3 p %}</p>
```

![image-20200610134054558](https://user-images.githubusercontent.com/58545240/89760028-69e3d300-db26-11ea-886f-ae9c61f67aa5.png)

![image-20200610142052635](https://user-images.githubusercontent.com/58545240/89760032-6e0ff080-db26-11ea-9534-99455bfc9863.png)


- advanced 조건문, 반복문

```python
# urls.py
path('forif/', views.forif),

# views.py
# 1. 간단한 반복문으로 리스트 각 요소를 출력
# 2. if, else 활용해서 문자열 비교
# 2-1. 내가 넘긴 문자열과 특정한 문자열 비교
# 3. if, elif, else 사용해보기
# 3-1. 문자열의 길이가 5 이하이면 short
# 3-2. 문자열의 길이가 10 이상이면 long
# 3-3. 모두 아니면 적당 출력

# 모두 작성했다면,
## 1) 반복문으로 리스트 각 요소를 출력해서
## 2) 해당 요소가 90 이상이면 A
## 3) 해당 요소가 70 이상이면 B
## 4) 그 외엔 C 출력
def forif(request):
    mList = [100, 50, 80, 71, 10]
    mString = '간단한 문자열zzzzzzzzzzzz'
    mInput = input("문자열을 입력하세요: ")
    data_a = '첫번째 데이터'
    data_b = '두번째 데이터'
    data_a, data_b = data_b, data_a
    context = {
        'mList' : mList,
        'mString' : mInput,
        'data_a' : data_a,
        'data_b' : data_b
    }
    return render(request, 'forif.html', context)

# forif.html
<h1>간단한 반복문으로 리스트 출력</h1>
{% for data in mList %}
  {% if data >= 90 %}
  <P>{{ data }} : A</P>
  {% elif data >= 70%}
  <P>{{ data }} : B</P>
  {% else %}
  <p>{{ data }} : C</p>
  {% endif %}
{% endfor %}
<hr>

<h1>문자열의 길이</h1>
{% if mString|length <= 5 %}
  <p>{{ mString|title }} : short</p>
{% elif mString|length >= 10 %}
  <p>{{ mString|title }} : long</p>
{% else %}
  <p>{{ mString|title }} : 적당</p>
{% endif %}
```

![image-20200610150956879](https://user-images.githubusercontent.com/58545240/89760051-7831ef00-db26-11ea-9559-9803e6a2929d.png)

## Django form태그 사용

- 프로젝트 기획 단계에서는 `페르소나` 를 중시해라!
- form 태그는 기본이 GET방식!

- **가져오는 방식이 2가지**
  - []  ---> 값이 없을 때 오류 발생

  - .get --->  값이 없어도 오류 발생 안함. 값이 없으면 none 출력
    - request.GET == 딕셔너리와 유사하다.
    - request.GET != dict()

```bas
print(request.GET['message'])
print(request.GET.get('message'))
```



### 절대경로? 상대경로?

- 절대경로 : 파일이 같이 올라가도 C:\에서 시작하는 그 경로로 접근할 수 없어서 이미지가 깨진다.

- 상대경로 : 루트경로로부터 작성된 경로로 찾아가기 때문에 해당 위치에 이미지가 있으면 찾을 수 있다.



Q1

```python
# 1. 사용자가 숫자 입력
# 2. 입력 받은 횟수 만큼 반복해서
# 3. 리스트에 로또 번호 담는다.
# 3-1. random.sample(range(1, 46), 6)
# 4. 사용자가 입력한 숫자와 로또번호가 담긴 리스트를 출력
# 5. ul태그를 사용하여 각 번호들을 한줄 씩 출력
# urls.py
path('lottoT/', views.lottoT),
path('lottoC/', views.lottoC),

# views.py
def lottoT(request):
    return render(request, 'lottoT.html')

def lottoC(request):
    num = int(request.GET.get('num'))
    lottos = []
    for data in range(num):
        lottos.append(random.sample(range(1, 46), 6))
    context={
        'num' : num,
        'lottos' : lottos,
    }

    return render(request, 'lottoC.html', context)

# lottoT.html
<h1> 로또 번호를 입력해주세용~ </h1>
<form action="/lottoC/">
  <label for="buy"> 구매 하고자 하는 갯수 : </label>
  <input type="number" name="num" id="buy">
  <input type="submit" value="제출" >
</form>

# lottoC.html
<h1> 결과는? </h1>
<p>로또를 {{num}}개 구매하셨습니다.</p>
<ul>
  {% for lotto in lottos %}
  <li>{{ lotto }}</li>
  {% endfor %}
</ul>
```



## Second App

### 경로 분리하기

- 기존의 pages Application에 있던 urls의 view는 pages폴더 하위로 이사한다.

  ```python
  # urls.py ( project )
  from django.contrib import admin
  from django.urls import path, include
  
  urlpatterns = [
      path('secondapp/', include('secondapp.urls')),
      path('pages/', include('pages.urls')),
      path('admin/', admin.site.urls),
  ]
  
  # urls.py ( pages )
  from django.urls import path
  from . import views
  
  urlpatterns = [
      path('index/', views.index, name="index"),
      path('hello/', views.hello, name="hello"),
      path('name/', views.name, name="name"),
      path('introduce/', views.introduce, name="introduce"),
      path('classRandomChoice/', views.classRandomChoice, name="classRandomChoice"),
      path('yourname/<str:name>/', views.yourname, name="yourname"),
      path('urlcopy/<str:name>/<int:age>', views.urlcopy, name="urlcopy"),
      path('multiply/<int:num1>/<int:num2>', views.multiply, name="multiply"),
      path('multipletable/<int:big>/<int:small>', views.multipletable, name="multipletable"),
      path('dtl/', views.dtl, name="dtl"),
      path('forif/', views.forif, name="forif"),
      path('loop/', views.loop, name="loop"),
      path('throw/', views.throw, name="throw"),
      path('catch/', views.catch, name="catch"),
      path('lottoT/', views.lottoT, name="lottoT"),
      path('lottoC/', views.lottoC, name="lottoC"),
      path('artii/', views.artii, name="artii"),
      path('result/', views.result, name="result"),
  ]
  ```

- 추후에 페이지가 커지고 원활한 유지보수를 위해 path 경로에 name을 지정할 수 가 있다.

  - 경로가 바뀌더라도 form태그나 경로로 무언가를 요청하던 일이 있을 때 이름으로 지정하기.

  ex)

  ```python
  # urls.py
  path('catch/', views.catch, name="catch"),
  
  # html
  #### 원래는 "/pages/catch/" ####
  <form action="{% url 'catch' %}" method="GET">
    이름 : <input type="text" name="name">
    나이 : <input type="number" name="age">
    <input type="submit" value="제출">
  </form>
  ```

  

- 장고는 자동으로 templates 폴더를 가서 index를 가져온다.

  - settings와 urls에서 먼저 선언한것을 먼저 찾아옴
  - 따라서 모든 html을 templates 하위에 앱이름으로 폴더를 생성하고 옮겨준다.
  - views에 html을 return하는 부분에도 경로 수정하기.

  ex)

  ```python
  return render(request, 'pages/index.html', context)
  ```

### base bootstrap 설정

- 베이스 페이지를 만들기 위해 프로젝트에 templates 폴더를 만들고 html에 부트스트랩 적용

- base.html

![image-20200613142000567](https://user-images.githubusercontent.com/58545240/89760067-86800b00-db26-11ea-8ef5-76b2f6bfd46b.png)

- setting.py

![image-20200613142820935](https://user-images.githubusercontent.com/58545240/89760088-926bcd00-db26-11ea-8289-bd8b5624808d.png)

=> **mysite에 있는 템플릿을 베이스로 사용하겠다!!**

- 다른 app에 있는 html에서 베이스로 지정되있는 템플릿을 extends 하여 사용할 수 있다.

![image-20200613142247147](https://user-images.githubusercontent.com/58545240/89760101-9b5c9e80-db26-11ea-86df-bb6a18547620.png)


### static 설정

- *폴더 구조가 바뀌면 서버를 껐다 켜주자!!*

- image와 같은 정적 파일을 사용하기 위해 template과 같은 위치에 static폴더 생성하기

  - setting.py

  ![image-20200613153426971](https://user-images.githubusercontent.com/58545240/89760116-a31c4300-db26-11ea-8554-5611364d3ef3.png)

  - html

  ![image-20200613153350207](https://user-images.githubusercontent.com/58545240/89760143-af080500-db26-11ea-8f1b-0d71d1769f39.png)

### 링크로 다른 앱 넘어가기

- urls.py에 app_name을 설정해주고 해당 app_name을 통해 페이지를 연결

![image-20200613161415674](https://user-images.githubusercontent.com/58545240/89760153-b62f1300-db26-11ea-8a16-9307918a30a3.png)


![image-20200613161435318](https://user-images.githubusercontent.com/58545240/89760159-bb8c5d80-db26-11ea-8da7-72d9813ddf9a.png)


![image-20200613161454569](https://user-images.githubusercontent.com/58545240/89760170-c0511180-db26-11ea-936a-472a2df629a2.png)



# Django Project

## 프로젝트 생성

- Project Name : mysite
- App Name : articles

## 페이지 생성

1. **index page**
   - `/index/` : 현재 게시글 목록을 보여 줄 페이지
2. **create page**
   - `/new/` : 글 작성을 위한 form(제목&내용) 입력 페이지
   - `/create/` : 글 작성 결과(제목&내용)를 출력하는 페이지

## 추가

- base template 생성

---

## !!주의사항!!

- **urls.py 설정 분리**
  - app_name 설정 등
- **template 폴더 구조 분리 **



# Django ORM

> Object Relational Mapping 
>
> 스프링에서는 mybatis를 썼었고, 장고에서는 이를 위한 기능이 따로 존재한다!

드디어 models.py를 사용하기 시작합니다~

파이썬의 객체와 DB의 객체를 Mapping해주어야 한다.

그럼 파이썬의 객체는 어떻게 생성하는 가?

- Articles에 models 작성

```python
# models.py

# Create your models here.
class Article(models.Model) : 
    # articles_article
    # CharField 는 글자 수 제한 할 때 사용
    title = models.CharField(max_length=150)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    
$ python manage.py makemigrations
$ python manage.py migrate articles
```

- python manage.py makemigrations : DB에 설계도 대로 반영을 해라!! 
  - migrations폴더에 아래의 설계도가 생성된다.

![image-20200615142700436](https://user-images.githubusercontent.com/58545240/89848451-2ab69000-dbc1-11ea-95f9-78c5b5ff1763.png)

- python manage.py migrate articles : 해당 aritcles 설계도 대로만 스키마 테이블 만들어달라!
- **실제 DB에 반영되기 위해서는?!**

```python
$ python manage.py migrate
```



---

## CREATE

```python
# 직접 shell창 에서 작업하겠다.
$ python manage.py shell
>>> from articles.models import Article
>>> Article.objects.all()
```

**1. INSERT INTO 테이블명 (column1, column2...) VALUES (values1, values2...)**

```python
# 첫 번째 방법
>>> article = Article()
>>> article.title = 'first'
>>> article.content = 'django!!'
>>> article.save()
>>> article
### 실제로 DB가 들어갔는지 확인! (N) 이 생겼어요!
<Article: Article object (1)>
    
# 두 번째 방법
# 어느 변수에 어떤 값을 넣을건지 명시
# id가 생략되어 있을 뿐, 자동으로 생성된다.
>>> article = Article(title='second', content='django~!')
>>> article.save()
>>> article
<Article: Article object (2)>
    
# 세 번째 방법
# save() 과정 없이 바로 저장이 된다. DB에 반영하는 방법이 포함되어 있다 !
>>> Article.objects.create(title='third', content'django~~')
<Article: Article object (3)>
```

- 첫, 두번째 방법은 인스턴스를 생상하고 그 안에 넣었다면 세번째는 그냥 넣어준다.
  - 사용 용도가 다른것이다!!!!

---

## READ

**2. SELECT * FROM articles_article(테이블명)**

```python
# 전체 조회
>>> article = Article.objects.all()
>>> article
<QuerySet [<Article: Article object (1)>, <Article: Article object (2)>, <Article: Article object (3)>]>

## 보기가 좋지 않으니
>>> article[0].title
'first'

## 설정을 해줍시다~~
```

![image-20200615152758808](https://user-images.githubusercontent.com/58545240/89848452-2ab69000-dbc1-11ea-9719-afa8f7a7245b.png)

*스키마의 형태가 변경이 되면 migration을 해줘야 하지만 단지 데이터의 출력 형태가 변경되는 것은 쉘 창을 종료하고 다시 켜준다.*

```python
$ exit()
$ python manage.py shell
>>> from articles.models import Article
>>> Article.objects.all()
<QuerySet [<Article: 1번째 글 - first : django!!>, <Article: 2번째 글 - second : django~!>, <Article: 3번째 글 - third : django~~>]>
```



**3. SELECT * FROM articles_article WHERE title = 'first'**

```python
# 특정 제목 불러오기
>>> Article.objects.filter(title='first')
<QuerySet [<Article: 1번째 글 - first : django!!>]>

>>> Article.objects.create(title='first', content='hahahahahahahaha')
<Article: 4번째 글 - first : hahahahahahahaha>

>>> Article.objects.filter(title='first')        
<QuerySet [<Article: 1번째 글 - first : django!!>, <Article: 4번째 글 - first : hahahahahahahaha>]>
```



**SELECT * FROM articles_article WHERE title='first' LIMIT= 1**

```python
>>> Article.objects.filter(title='first').first()
>>> Article.objects.filter(title='first').last()

>>> Article.objects.filter(title='first')[0]
<Article: 1번째 글 - first : django!!>
```



**SELECT * FROM articles_article WHERE id=1**

> PK처럼 고유값을 가지고 있어서 단 하나만 가지고 올 수 있는 방법!

```python
>>> Articles.objects.get(id=1)
>>> Articles.objects.get(pk=1)
<Article: 1번째 글 - first : django!!>
        
# !!주의점!!
# 1.고유값이 아닌 내용을 필터링 해서 2개 이상의 값이 찾아지면 오류를 발생한다.
# 	-> .get()은 반드시 하나의 객체만 가져올 수 있다.
# 2. 없는 것을 가지고 오려고 해도 오류가 발생한다.
# 	-> filter는 빈 쿼리셋이 반환이 된다.
>>> Article.objects.filter(pk=10)
<QuerySet []>
```



**Like / startswith / endswith**

```python
# 특정 문자로 가져오기
# XXX__contains : XXX에 해당 ''을 포함하고 있는 객체 반환
>>> Article.objects.filter(title__contains='fir')
<QuerySet [<Article: 1번째 글 - first : django!!>, <Article: 4번째 글 - first : hahahahahahahaha>]>

>>> Article.objects.filter(title__startswith='se')
<QuerySet [<Article: 2번째 글 - second : django~!>]>

>>> Article.objects.filter(content__endswith='ha')
<QuerySet [<Article: 4번째 글 - first : hahahahahahahaha>]>
```



**ASC / DESC**

```python
# 오름차순
>>> Article.objects.all().order_by('pk')

# 내림차순
>>> Article.objects.all().order_by('-pk')

## Order_by는 DB단에서 역순으로 가져오는 것이고

## 이것은 이미 가져온 것을 파이썬에서 역순으로 처리하는 것이다.
>>> Article.objects.all()[::-1]
```



## UPDATE

**UPDATE articles_article SET title='byebye' WHERE id=1**

```python
# 수정
>>> article = Article.objects.get(pk=1)
>>> article.title = 'byebye'
>>> article
<Article: 1번째 글 - byebye : django!!>

## 보이는 shell에는 바뀐 것처럼 보이지만 실제 DB에 저장이 되려면 저장을 해줘야 한다.
>>> article.save()
```

---

## DELETE

**DELETE FROM articles_article WHERE id=1**

```python
# 삭제
>>> article = Article.objects.get(pk=1)
>>> article.delete()
(1, {'articles.Article': 1})

>>> article = Article.objects.get(pk=1)
## pk=1은 삭제하고 없기 때문에 오류가 난다.

## delete는 별도로 저장을 해주지 않아도 자동으로 DB에 반영이 된다.
```



## 관리자 페이지로 확인하기

![image-20200615161910998](https://user-images.githubusercontent.com/58545240/89848453-2b4f2680-dbc1-11ea-9a2d-0b4e6d368231.png)

```python
python manage.py runserver
```

- http://127.0.0.1:8000/admin

![image-20200615162017084](https://user-images.githubusercontent.com/58545240/89848455-2b4f2680-dbc1-11ea-8bc3-8fb463214573.png)

- 파이썬에는 슈퍼계정이 존재한다~

```python
# 모든 설정파일을 migrate 해준 뒤 슈퍼유저 생성
$ python manage.py migrate
$ python manage.py createsuperuser
```

![image-20200615162405109](https://user-images.githubusercontent.com/58545240/89848811-0e672300-dbc2-11ea-8c1f-e0ba10d6b0b3.png)

- 이메일 생략하고 비밀번호 1q2w3e4r 설정 후 다시 http://127.0.0.1:8000/admin로 접속

![image-20200615162500379](https://user-images.githubusercontent.com/58545240/89848456-2be7bd00-dbc1-11ea-9263-62850300adb4.png)



- admin.py에서 커스터마이징하기!

![image-20200615162943645](https://user-images.githubusercontent.com/58545240/89848857-2e96e200-dbc2-11ea-94c7-c88c87614d46.png)



![image-20200615162957932](https://user-images.githubusercontent.com/58545240/89848459-2c805380-dbc1-11ea-9fbf-3ca8f0ca9312.png)

customizing된 admin페이지를 확인할 수 있다 :)



# Django ORM 복습 버억

## index페이지에 전체 DB 보여주기

```python
# views.py

from articles.models import Article

# Create your views here.
def index(request):
    # 전체 데이터 가져오기
    # 그 데이터 템플릿에게 넘겨주기
    # 템플릿에서 반복문으로 각각의 게시글 pk, title 보여주기
    article = Article.objects.all()
    context = {
        'articles' : article
    }
    return render(request, 'articles/index.html', context                            
```

```python
# index.html
{% extends 'base.html' %}
{% block body %}
<h1>게시판</h1>
<hr>
<a href="{% url 'articles:new' %}">NEW</a>
<a href="{% url 'articles:introduce' %}">introduce</a>

{% for article in articles %}
  <h3>{{article.pk}}번 째 글</h3>
  <h4>{{article.title}}</h4>
  <h5>{{article.content}}</h5>
  <hr>
{% endfor %}

{% endblock %}
```

![image-20200616111039547](https://user-images.githubusercontent.com/58545240/89848460-2d18ea00-dbc1-11ea-8df8-2dcdb06cb578.png)

## new페이지에서 글 작성하기

```python
# views.py
from django.shortcuts import render, redirect

def new(request):
    return render(request, 'articles/new.html')

def create(request):
    title = request.POST.get('title')
    text = request.POST.get('text')
    Article.objects.create(title=title, content=text)
    
	return redirect('articles:index')
```

```python
# new.html

{% block body %}
<h1>글 작성 페이지</h1>

<form action="{% url 'articles:create' %}" method="POST">
  {% csrf_token %}
  <label for="name">제목 : </label>
  <input type="text" name="title">

  <label for="cnt">내용 : </label>
  <input type="text" name="text">

  <input type="submit" value="글 작성">
</form>
{% endblock %}

# create.html

{% block body %}
<p>{{ title }}, {{ text }}</p>
{% endblock %}
```

- **csrf_token** 이란??
  - 내 DB에 어떠한 조작을 할 수 있는 요청을 보낼 땐 항상 세트로 넣어줘야 한다.
  - 보안을 위한 것. ( 없어도 요청은 감 )

- redirect를 import하여 index로 redirect한다.

## index페이지에서 상세페이지보기

```python
# urls.py
path('<int:article_pk>detail/', views.detail, name="detail"),

# views.py
# 1. 상세 페이지를 보기위한 경로
# 1-1. 특정 게시글에 대한 고유 값
# 1-2. /articles/1/, /articles/2/...
# 2. 해당 게시글에 대한 상세 내용
# 2-1. 게스글의 pk, title, ...
# 3. 인덱스 페이지로 돌아가는 링크
def detail(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    context = {
        'article' : article
    }
    return render(request, 'articles/detail.html', context)
```

```python
# index.html
{% for article in articles %}
  <a href="{% url 'articles:detail' article.pk %}"><h3>{{article.pk}}번 째 글</h3></a>
  <h4>{{article.title}}</h4>
  <h5>{{article.content}}</h5>
  <hr>
{% endfor %}

# detail.html
{% block body %}
	<h1> 상세 페이지^^</h1>
    <h2>{{article.pk}}번 째 글</h2>
    <h4>제목 : {{article.title}}</h4>
    <h5>내용 : {{article.content}}</h5>
    <p>생성 시간 : {{article.created_at}}</p>
    <p>수정 시간 : {{article.updated_at}}</p>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock %}
```

## 상세페이지에서 글 삭제하기

```python
# urls.py
path('<int:article_pk>/delete/', views.delete, name="delete"),

# views.py
# 1. 특정 글 삭제를 위한 경로 작성
# 1.1 /articles/delete/
# 2. 글 삭제 처리를 해주는 view 작성
# 3. 글 삭제 후, index page로 redirect
# 4. 글 삭제를 위한 링크 detail에 작성
def delete(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    article.delete()
    return redirect('articles:index')
```

```python
# detail.html
{% block body %}
  <h1> 상세 페이지^^</h1>
    <h2>{{article.pk}}번 째 글</h2>
    <h4>제목 : {{article.title}}</h4>
    <h5>내용 : {{article.content}}</h5>
    <p>생성 시간 : {{article.created_at}}</p>
    <p>수정 시간 : {{article.updated_at}}</p>
    <a href="{% url 'articles:delete' article.pk %}">삭제</a>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock %}
```

## 게시글 수정하기

```python
# urls.py
path('<int:article_pk>/edit', views.edit, name="edit"),
path('<int:article_pk>/update', views.update, name="update"),

# views.py
# 1. 특정 글 수정을 위한 경로 생성
# 1-1. /articles/1/edit
# 2. 글 수정 template를 render하는 edit view 작성
# 2-1. 해당 templateㄹ에 form tag 생성
# 2-2. 각 input tag 내부에 기존 내용이 들어있어야 함.
# 3. edit 보낸 데이터 처리를 위한 경로 생성
# 3-1. /articles/1/update
# 4. 글 수정 처리를 하는 update view 작성
# 5. 해당 글 상세 페이지로 redirect
# 6. 글 수정을 위한 edit 링크 해당 글 상세 페이지에 생성
# 6-1. {% url 'articles:edit' articles.pk %}
def edit(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    context = {
        'article' : article
    }
    return render(request, 'articles/edit.html', context)

def update(request, article_pk):
    edit_title = request.POST.get('edit_title')
    edit_content = request.POST.get('edit_content')
    article = Article.objects.get(pk=article_pk)
    article.title = edit_title
    article.content = edit_content
    article.save()
    return redirect('articles:detail', article_pk)
```

```python
# detail.html
{% block body %}
  <h1> 상세 페이지^^</h1>
    <h2>{{article.pk}}번 째 글</h2>
    <h4>제목 : {{article.title}}</h4>
    <h5>내용 : {{article.content}}</h5>
    <p>생성 시간 : {{article.created_at}}</p>
    <p>수정 시간 : {{article.updated_at}}</p>
    <a href="{% url 'articles:edit' article.pk %}">수정</a>
    <a href="{% url 'articles:delete' article.pk %}">삭제</a>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock %}

# edit.html
{% block body %}
<form action="{% url 'articles:update' article.pk %}" method="POST">
  {% csrf_token %}
  {{ article.pk }}번 째 글
  제목 : <input type="text" name="edit_title" value="{{ article.title }}">
  내용 : <input type="text" name="edit_content" value="{{ article.content }}">

  <input type="submit" value="수정하기">
</form>

<a href="{% url 'articles:index' %}">[back]</a>
{% endblock %}
```

# Past Job APP

---

## Create APP

- **APP Name** : jobs

## Model

- **Class Name**: Person

- **Fields**

  | name         | CharField     |
  | ------------ | ------------- |
  | **past_job** | **TextField** |

  - makemigrations로 설계도 작성
  - migrate로 DB에 설계도 반영

---

## 직업 리스트

https://bit.ly/past_job_list

## urls

- urls 분리 필수: 프로젝트 폴더, jobs 아래 urls 
- app_name, path name 설정 필수

## views 

1. `/index/`
   - index.html 렌더링
2. `/past_life/`
   - 사용자가 form으로 날린 이름을 받아 저장 
   - DB에 사용자가 입력한 이름이 있는지 확인 
   - 만약 사용자가 입력한 이름이 DB에 있다면 기존 그 사용자의 past_job을 past_job 변수에 담기
   - 직업군 리스트에서 무작위 하나를 뽑아 사용자에게 받은 이름과 새로 뽑은 직업을 DB에 저장 
   - context로 담아서 past_life.html 로 넘김

### ++수정 

past_life 지문 수정

사용자가 입력한 이름이 DB에 있다면 해당 이름과 직업 그대로 출력

DB에 없다면 직업 리스트에서 무작위 하나를 뽑아 DB에 이름과 직업 저장 후 출력

## templates

1. 템플릿은 기본 `app/templates/app` 형태로 구분 
   - base.html: 기존의 프로젝트 폴더의 base.html 활용(템플릿 확장)  
   - index.html: 사용자에게 자신의 이름을 입력할 수 있는 form 제공 
   - past_life.html: 무작위로 선정된 직업과 사용자에게 받은 이름 출력 
   - 예시. {{ person.name }}님의 전생은 {{ person.past_job }}입니다.

---

# Django Model-Form

```python
# urls.py

from django.urls import path
from . import views

app_name = "articles"

urlpatterns = [
    path('index/', views.index, name="index"),
    path('create/', views.create, name="create"),
    path('<int:article_pk>/update/', views.update, name="update"),
    path('<int:article_pk>/', views.detail, name="detail"),
]
```

```python
# views.py

from django.shortcuts import render, redirect, get_object_or_404
from .forms import ArticleForm
from .models import Article

# Create your views here.
def index(request):
    articles = Article.objects.all()
    context = {
        'articles' : articles
    }
    return render(request, 'articles/index.html', context)

def create(request):
    if request.method =="POST":
        form = ArticleForm(request.POST)
        # 사용자로부터 받은 form이 유효하다면 TRUE를 리턴, 아니면 FALSE를 리턴한다.
        if form.is_valid():
            # article = Article.objects.get(pk=pk)
            # form에 담긴 정보가 ArticleForm이고
            # ArticleForm은 Article의 정보를 가지고 있다.
            article = form.save()
            return redirect('articles:index')
    else:
        form = ArticleForm()
    context={
        'form' : form		
    }
    return render(request, 'articles/form.html', context)

def update(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    if request.method=="POST":
        form = ArticleForm(request.POST, instance=article)
        if form.is_valid():
            article = form.save()
            return redirect('articles:index')
    else:
        form = ArticleForm(instance=article)
    context={
        'form' : form
    }
    return render(request, 'articles/form.html', context)

def detail(request, article_pk):
    article = get_object_or_404(Article, pk=article_pk)    
    # article = Article.objects.get(pk=article_pk)
    context={
        'article' : article
    }
    return render(request, 'articles/detail.html', context)
```

```python 
### index.html
{% extends 'base.html' %}

{% load bootstrap4 %}

{% block body %}
<h1>메인 페이지 입니다.</h1>

<a href="{% url 'articles:create' %}">글 생성</a>

<hr>
{% for article in articles %}
  <a href="{% url 'articles:detail' article.pk %}">{{article.title}}</a>
{% endfor %}
{% endblock %}


### form.html
{% extends 'base.html' %}

{% load bootstrap4 %}

{% block body %}
{% if request.resolver_match.url_name == 'create' %}
  <h1> 글 생성 </h1>
{% else %}
  <h1> 글 수정 </h1>
{% endif %}

<hr>
<form action="" method="post">
  {% csrf_token %}
  {{ form.as_p }}
  <input type="submit" value="제출">
</form>

<a href="{% url 'articles:index' %}">index 페이지 슝</a>

{% endblock %}

### detail.html
{% extends 'base.html' %}

{% load bootstrap4 %}

{% block body %}
  <h2>{{ article.pk }}번 째 글^^</h2>
  <h3>{{ article.title }}</h3>
  <h4>{{ article.content }}</h4>
  <hr>
  <a href="{% url 'articles:update' article.pk %}">글 수정</a>
  <a href="{% url 'articles:index' %}">[back]</a>
{% endblock %}
```

## Django-Bootstrap-4

```python
$ pip install django-bootstrap4

# 각 html에서 불러오기!
$ {% load bootstrap4 %}
```

- settings.py 에서 부트스트랩사용한다고 설정해주기

![image-20200617170926574](https://user-images.githubusercontent.com/58545240/89848461-2d18ea00-dbc1-11ea-9cd8-04ca2e7d1be3.png)

- form.html 도 부트스트랩4에 맞게 수정

![image-20200617171216549](https://user-images.githubusercontent.com/58545240/89848463-2db18080-dbc1-11ea-85b1-b14ec36e3735.png)

- base.html 모두 바꿔주기

![image-20200617171259506](https://user-images.githubusercontent.com/58545240/89848464-2db18080-dbc1-11ea-923e-262868094864.png)



![image-20200617171422329](https://user-images.githubusercontent.com/58545240/89848466-2e4a1700-dbc1-11ea-8322-0d29c0c28fa5.png)

**짜잔!!!**





# PRACTICE(Movie)



# django_review

![image-20200620140155502](https://user-images.githubusercontent.com/58545240/89848469-2e4a1700-dbc1-11ea-9fa0-14672cd325ac.png)



![image-20200620140916416](https://user-images.githubusercontent.com/58545240/89848472-2ee2ad80-dbc1-11ea-86bc-491bf47bd6e5.png)

원래는 html의 form태그에서 경로를 넣어줬는데 경로를 안넣어주면 내 현재 위치로 다시 보내주는 것.

차이점은 method를 POST로 지정해줬기 때문에 action을 비워놔도 된다.



# django relation 1:N 

>  06/22

cascade만 사용



django 확장 툴 설치

```bash
$ pip install django-extensions
```

settings에 등록해줘야 한다.

'django_extensions' 으로 등록



쉘창을 켠다  -> mysite 위치로 가서 켜야한다.

```bash
$ python manage.py shell_plus
```



ipython 설치

```bash
$ pip install ipython
```



0623

## Admin 관리자 계정 생성

```python
# musicians/admin.py -admin 정의
from.models import Musician, Album
admin.site.register(Musician, Alubm)
```

```bash
# bash 관리자계정 생성
python manage.py createsuperuser
```



http -> 일반적으로 상태를 저장하지 않는다.

## CREATE

```python
# 1. 댓글 생성
#인스턴스화
comment = Comment()
comment.content = '첫번째 댓글'
comment.save()

# 2. 게시글 하나 불러오기
article = Article.objects.get(pk=1)

# 2-1. comment와 article 연결하기
comment.article = article
comment.save()

# 또 다른 방법
# 작성하는 숫자는 article의 pk값
# 주의할 점 -> 참조하고자 하는 이름은 article_id로 해줘야 한다. not pk
comment.article_id = 1
comment.save()
```

## READ

```python
# comment 변수들 불러오기
# 1. 댓글 pk 조회
comment.pk

# 2. 댓글 content 조회
comment.content

# 3. 댓글이 어느 게시글에 연결되어 있는가
comment.article_id 

# 4. 댓글이 연결된 게시글
comment.article

# 4-1. 댓글이 연결된 게시글의 제목과 내용
comment.article.pk
comment.article.title
comment.article.content

# article의 경우는?
article.comment_set.all()
# querySet 형태로 가지고 온다
```



# Django 로그인 /회원가입

## 회원가입

```python
# urls.py
path('signup/', views.signup, name="signup"),

# views.py
from django.contrib.auth.forms import UserCreationForm

# Create your views here.
def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            return redirect('articles:index')
    else:
        form = UserCreationForm()
    context = {
        'form' : form
    }
    return render(request, 'accounts/signup.html', context)

# signup.html
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block body %}
<h1>회원가입</h1>
<form action="" method="POST">
  {% csrf_token %}
  {% bootstrap_form form %}
  {% buttons %}
    <button type="submit" class="btn btn-primary">
      Submit
    </button>
  {% endbuttons %}
</form>

{% endblock %}
```

## 로그인

```python
# urls.py
path('login/', views.login, name="login"),

# views.py
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import login as auth_login

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        # AuthenticationForm은 ModelForm이 아닌 Form을 상속하기 때문에 생긴게 달라진다.
        # 별도로 정의된 Model이 없다는 뜻 => 고로 넘겨주는 인자가 달라진다.
        if form.is_valid():
            # 로그인은 DB에 뭔가 작성하는 것은 동일하지만 연결된 모듈이 있는 것은 아니다.
            # 그럼 확인해야 하는것은????
            #   => 세션과 유저정보를 확인해야 하기때문에
            #   => 세션(request)와 유저정보(form.get_user())를 확인해야 한다.
            auth_login(request, form.get_user())
			return redirect(request.GET.get('next') or 'articles:index')
    else:
        form = AuthenticationForm
    context={
        'form' : form
    }
    return render(request, 'accounts/login.html', context)

# login.html
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block body %}
<h1>로그인</h1>
<form action="" method="POST">
  {% csrf_token %}
  {% bootstrap_form form %}
  {% buttons %}
    <button type="submit" class="btn btn-primary">
      Submit
    </button>
  {% endbuttons %}
</form>

{% endblock %}
```

## 로그아웃

```python
# urls.py
path('logout/', views.logout, name="logout"),

# views.py
from django.contrib.auth import logout as auth_logout
def logout(request):
    auth_logout(request)
    return redirect('articles:index')

# base.html
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
  <title>Document</title>
</head>
<body>
  <h1>{{ user.username }}</h1>
  
  {% if user.username == "" %}
  <a href="{% url 'accounts:login' %}">login</a>
  {% else %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  {% endif %}
  <div class="container">
    {% block body %}
    {% endblock %}
  </div>
  {% bootstrap_javascript jquery='full' %}
</body>
</html>
```

![image-20200623130701596](https://user-images.githubusercontent.com/58545240/89848475-2ee2ad80-dbc1-11ea-86ee-d13910566c3e.png)

user가 anonymous일때는 항상 false를 리턴.

```python
from IPython import embed

def index(request):
    embed()
    ...

#shell
request.user

# 로그인 안했을 시
request.user.is_anonymous
-> True

request.user.is_authenticated
-> False

# 로그인 안했을 시
request.user.is_anonymous
-> False

request.user.is_authenticated
-> True
```

shell 창에서 request를 출력해보면 anonymous가 들어있는 것을 확인할 수 있음.

**따라서 다음과 같이 base.html의 조건문을 변경해주는게 좋다 **

```python
{% if user.is_authenticated %}
<a href="{% url 'accounts:login' %}">login</a>
{% else %}
<a href="{% url 'accounts:logout' %}">logout</a>
{% endif %}
```

=> 세션에 접근을 해서 통과를 해 정보를 가져와서 무엇을 하는 게 아니라 단지 로그인했는지만 체크하는것

> 템플릿에서  user를 쓸 수 있는 이유는 우리가 항상 request를 보내주기 때문이다.

## 로그인/비로그인 페이지 분할

ex ) 네이버와 구글의 로그인 페이지 

- 로그인이 되어있는데도 또 로그인 페이지 접근을 시도하면 index페이지로 redirect시키기(회원가입도)

```python
###### 회원가입 하자마자 로그인 하기 위한 로직 추가
def signup(request):
    if request.user.is_authenticated:
        return redirect('articles:index')
    else:
        if request.method == 'POST':
            form = UserCreationForm(request.POST)
            if form.is_valid():
                user = form.save()
				##### VVVVVVV
                auth_login(request, user)
                return redirect('articles:index')
        else:
            form = UserCreationForm()
        context = {
            'form' : form
        }
    return render(request, 'accounts/signup.html', context)

def login(request):
    if request.user.is_authenticated:
        return redirect('articles:index')
    else:
        if request.method == 'POST':
            form = AuthenticationForm(request, request.POST)
            # AuthenticationForm은 ModelForm이 아닌 Form을 상속하기 때문에 생긴게 달라진다.
            # 별도로 정의된 Model이 없다는 뜻 => 고로 넘겨주는 인자가 달라진다.
            if form.is_valid():
                # 로그인은 DB에 뭔가 작성하는 것은 동일하지만 연결된 모듈이 있는 것은 아니다.
                # 그럼 확인해야 하는것은????
                #   => 세션과 유저정보를 확인해야 하기때문에
                #   => 세션(request)와 유저정보(form.get_user())를 확인해야 한다.
                auth_login(request, form.get_user())
				return redirect(request.GET.get('next') or 'articles:index')
        else:
            form = AuthenticationForm
        context={
            'form' : form
        }
    return render(request, 'accounts/login.html', context)
```





```python
from django.contrib.auth.decorators import login_required
```

- articles app 에 있는 detail, index 제외한 모든 함수에 @login_required를 붙여준다...

![image-20200623140626122](https://user-images.githubusercontent.com/58545240/89848476-2f7b4400-dbc1-11ea-990d-e178feea40ac.png)

=> login이 안된상태에서 create버튼을 누르면 login페이지로 넘어간다.

## 회원 탈퇴

먼저 `@require_POST`를 사용하기 위해 import해주는 작업

```python
from django.views.decorators.http import require_POST
```

```python
# urls.py
path('delete/', views.delete, name="delete"),

# views.py
# @login_required가 있으면 페이지405 오류가 뜬다. 따라서 GET방식 뭐시기
@require_POST
def delete(request):
    request.user.delete()
    return redirect('articles:index')

# base.html
{% if user.is_authenticated %}
  <a href="{% url 'accounts:logout' %}"><h5>logout</h5></a>
  <form action="{% url 'accounts'delete %}" method='POST'>
    {% csrf_token %}
    <input type="submit" value="회원 탈퇴">
  </form>
  {% else %}
  <a href="{% url 'accounts:login' %}"><h5>login</h5></a>
  <a href="{% url 'accounts:signup' %}"><h5>[회원가입]</h5></a>
{% endif %}
```

## 회원수정

```python
# urls.py
path('update/', views.update, name="update"),

# views.py
from .forms import CustomUserChangeForm
@login_required
def update(request):
    if request.method == 'POST':
        form = CustomUserChangeForm(request.POST, instance = request.user)
        if form.is_valid():
            form.save()
            return redirect('articles:index')
    else:
        form = CustomUserChangeForm(instance = request.user)
    context = {
        'form' : form
    }
    return render(request, 'accounts/update.html', context)

# base.html
<a href="{% url 'accounts:update' %}">회원 수정</a>
```

- UserChangeForm 커스터마이징

```python
# forms.py
from django.contrib.auth.forms import UserChangeForm
from django.contrib.auth import get_user_model

class CustomUserChangeForm(UserChangeForm):
    class Meta:
        model = get_user_model()
        fields = ['username', 'email', 'first_name','last_name']

```

## 비밀번호 수정

```python
from django.contrib.auth.forms import PasswordChangeForm
```

```python
# urls.py
path('password/', views.password, name="password"),

# views.py
@login_required
def password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
			user = form.save()
            auth_login(request, user)
            return redirect('articles:index')
    else:
        form = PasswordChangeForm(request.user)
    context = {
        'form' : form
    }
    return render(request, 'accounts/password.html', context)
```

- 비밀번호 수정 후에도 세션을 유지시키기 위한 작업

```python
from django.contrib.auth import update_session_auth_hash

@login_required
def password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
            user = form.save()
            update_session_auth_hash(request, user)
            return redirect('articles:index')
    else:
        form = PasswordChangeForm(request.user)
    context = {
        'form' : form
    }
    return render(request, 'accounts/password.html', context)
```

=> 눈에 보이지 않는 세션 값이 기존과 그대로 유지한다.



# Django 사용자 분할(1:N)

- Article 모델에 외래키 설정 후 마이그레이션 작업 진행
  - 연결할 User 모델은 settings.AUTH_USER_MODEL이다.
  - 마이그레이션 작업을 진행 시 기존의 데이터에 USER 정보가 없으므로 Default값 설정.
  - 현재 프로젝트의 admin 계정의 PK 값을 찾아 해당 값을 Default로 설정.

- CREATE 로직 수정 & 게시글 작성자 표기
  - 게시글 생성 시 , 게시글 작성자 정보를 넣어서 저장한다.

- UPDATE & DELETE 로직 수정
  - 사용자가 자신의 글만 수정/삭제 할 수 있도록 내부 로직 수정
  - 해당 게시글의 작성자가 아니라면, 수정/삭제 버튼이 보이지 않도록 Template 수정

- user 모델을 외래키로 참조

![image-20200624130814231](https://user-images.githubusercontent.com/58545240/89848477-2f7b4400-dbc1-11ea-85b4-2e102405af09.png)

- 해당 사용자만 수정/삭제가 가능하도록 하자

![image-20200624130932120](../../blogImg/image-20200624130932120.png)

- 객체는 생성하지만 DB에는 반영하지 않겠다.

![image-20200624131456721](https://user-images.githubusercontent.com/58545240/89848483-31450780-dbc1-11ea-91ef-668b99a301a2.png)

![image-20200624134036835](../../blogImg/image-20200624134036835.png)

- 필요없는 field가 나오지 않도록 해주자

![image-20200624131018154](https://user-images.githubusercontent.com/58545240/89848480-30ac7100-dbc1-11ea-8905-4bffe2c4c2fc.png)

- html도 변경해주자

![image-20200624131119816](https://user-images.githubusercontent.com/58545240/89848482-30ac7100-dbc1-11ea-8d45-5d21dd69deb3.png)



## 메시지 출력하기

```python
# views.py
from django.contrib import messages
messages.success(request, '게시글 작성 완료')

# base.html
<body>
  {% if messages %}
    {% for message in messages %}
      <div class="alert alert-{{ message.tags }}">
        {{ message.message }}
      </div>
    {% endfor %}
  {% endif %}
</body>

# settings.py

import os
from django.contrib.messages import constants as messages
MESSAGE_TAGS = {
    messages.ERROR : 'danger',
}
```

## static 파일 관리하기

### image

- blank=True
  - 유효성 검사시
- null=True
  - DB상의 컬럼에 null이 가능. TextField에서는 사용하지마세요!!

```python
$ pip install Pillow # Python image 관리

class Article(models.Model):
	image = models.ImageField(blank=True, null=True)
    
$ python manage.py makemigrations
$ python manage.py migrate
```

- enc type 설정하기

```python
# form.html
<form action="" method="POST" enctype="multipart/form-data">
  {% csrf_token %}
  {% bootstrap_form form %}
  {% buttons %}
    <button type="submit" class="btn btn-primary">
      Submit
    </button>
  {% endbuttons %}
</form>
```

- html 설정하기

```python
# detail.html

{% if article.image %}
<img src="{{ article.image.url }}" alt="{{ article.image }}">
{% endif %}
```

- views.py

![image-20200624141816812](https://user-images.githubusercontent.com/58545240/89848485-31dd9e00-dbc1-11ea-9127-f39d6745b199.png)

- settings.py

![image-20200624142952884](https://user-images.githubusercontent.com/58545240/89848488-32763480-dbc1-11ea-8cbd-955cd70f2486.png)

- urls.py ( project )

![image-20200624142716430](https://user-images.githubusercontent.com/58545240/89848487-31dd9e00-dbc1-11ea-8ee2-3010e672ff74.png)

- 아린

![image-20200624143015990](https://user-images.githubusercontent.com/58545240/89848490-330ecb00-dbc1-11ea-8902-476346e08862.png)



- 날짜별로 폴더 정리

![image-20200624143251489](https://user-images.githubusercontent.com/58545240/89848491-330ecb00-dbc1-11ea-90a4-a60856683086.png)

![image-20200624143349944](https://user-images.githubusercontent.com/58545240/89848493-33a76180-dbc1-11ea-8042-a37b7bf5781f.png)



- 이미지 크기를 관리하는 방법

```python
$ pip install pilkit
$ pip install django-imagekit

# settings.py
INSTALLED_APPS = [
    'imagekit',
]

# models.py
from imagekit.models import ImageSpecField
from imagekit.processors import Thumbnail
```

![image-20200624151337397](https://user-images.githubusercontent.com/58545240/89848494-33a76180-dbc1-11ea-84fd-ab97fb1ecee4.png)



- user가 저장한 사진 별로 이미지 관리를 하고 싶은 경우 models.py 

![image-20200624151826256](https://user-images.githubusercontent.com/58545240/89848496-343ff800-dbc1-11ea-992d-10a8b209135e.png)





---

# 1:N 관계 복습

### 유저 생성 ORM

```python
User.objects.create(username='test', password='test')
```

### 게시글 생성 ORM

```python
$ python manage.py shell_plus
```

```python
user1 = User.objects.get(pk=2)
In[] user1
Out[] <User:ksh>
Article.objects.create(title='aaaa', content='bbbbb', user=user1)
Article.objects.create(title='aaaa', content='bbbbb', user_id=user1.pk)
```

### 댓글 생성 ORM

```python
comment = Comment.objects.get()

comment.article.pk
4

comment.article.title
title

article = Article.objects.get(pk=4)


# article과 user의 입장에서 댓글과 게시글이 있는지 없는지 모르기때문에 set.all()로 가져온다.
article.comment_set.all()
user1.article_set.all()

# 반복문으로도 사용가능
for article in user1.article_set.all():
    print(article.title)


article1 = Article.objects.get(pk=?)
Comment.objects.create(content='content', user=user1, article=article1)
```

- 1:N 관계에서 N의 입장은 항상 참조하는 관계가 존재하고 1을 보장할 수 있기 때문에 바로바로 접근가능
  - 1의 입장에서는 접근하는 방법이 달라진다
    - `_set.all()` !!

### 특정 게시글이 가지고 있는 전체 댓글 불러오기

```python
article = Article.objects.get(pk=?)
article.comment_set.all()
= Article.objects.get(pk=?).comment_set.all()
```

### 특정 댓글이 어느 게시글과 연결되어 있는지 확인하기

```python
comment = Comment.objects.get(pk=?)
comment.article.title
```

### 특정 게시글이 어느 유저와 연결되어 있는지 확인하기

```python
article = Article.objects.get(pk=?)
article.user.username
```

### 특정 유저가 작성한 전체 게시글 가져오기

```python
user1 = User.objects.get(pk=?)
user1.article_set.all()
```

### 특정 유저가 작성한 전체 댓글 가져오기

```python
user1 = User.objects.get(pk=?)
user1.comment_set.all()
```





# M:N 관계

## ex) 의사-환자 모델링

- models.py

```python
from django.db import models

# Create your models here.
class Doctor(models.Model):
    name = models.CharField(max_length=20)

    def __str__(self):
        return f'{self.pk}번 의사 {self.name}'

class Patient(models.Model):
    name = models.CharField(max_length=20)

    def __str__(self):
        return f'{self.pk}번 환자 {self.name}'

class Reservation(models.Model):
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE)
    patient = models.ForeignKey(Patient, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.doctor}의 {self.patient}'
```

- shell_plus

```shell
In [1]: doctor = Doctor.objects.create(name='KIM')

In [2]: patient = Patient.objects.create(name='TOM')

In [3]: doctor
Out[3]: <Doctor: 1번 의사 KIM>

In [4]: patient
Out[4]: <Patient: 1번 환자 TOM>

# reservation을 활용해서 의사와 환자를 연결한다.
In [5]: Reservation.objects.create(doctor=doctor, patient=patient)
Out[5]: <Reservation: 1번 의사 KIM의 1번 환자 TOM>

# 의사 입장에서 예약정보를 가져오기
# 1은 N을 보장할 수 없기 때문에
In [6]: doctor.reservation_set.all()
Out[6]: <QuerySet [<Reservation: 1번 의사 KIM의 1번 환자 TOM>]>

# 환자 입장에서 예약정보를 가져오기
In [7]: patient.reservation_set.all()
Out[7]: <QuerySet [<Reservation: 1번 의사 KIM의 1번 환자 TOM>]>

# 2번환자 생성하고 연결하기
In [8]: patient2 = Patient.objects.create(name='KANG')

In [9]: Reservation.objects.create(doctor=doctor, patient=patient2)
Out[9]: <Reservation: 1번 의사 KIM의 2번 환자 KANG>

In [10]: doctor.reservation_set.all()
Out[10]: <QuerySet [<Reservation: 1번 의사 KIM의 1번 환자 TOM>, <Reservation: 1번 의사 KIM의 2번 환자 KANG>]>

# 의사1의 환자이름
In [11]: for reservation in doctor.reservation_set.all():
    ...:     print(reservation.patient.name)
    ...: 
TOM
KANG

# 의사1의 환자번호
In [12]: for reservation in doctor.reservation_set.all():
    ...:     print(reservation.patient.pk)
    ...: 
1
2
```

- 위의 중개모델 말고도 조금 더 쉽게 가져올 수 있는 방법이 존재한다.(ManyToManyField)

![image-20200625103231363](https://user-images.githubusercontent.com/58545240/89848497-34d88e80-dbc1-11ea-8f0c-3b50a31960a6.png)

```shell
# 환자 입장에서 의사 가져오기
In [1]: patient = Patient.objects.get(pk=1)
In [2]: patient.doctors.all()
Out[2]: <QuerySet [<Doctor: 1번 의사 KIM>]>

# 의사 입장에서 환자 가져오기
doctor = Doctor.objects.get(pk=1)
In [10]: doctor.patient_set.all()
Out[10]: <QuerySet [<Patient: 1번 환자 TOM>, <Patient: 2번 환자 KANG>]>
```

![image-20200625103949825](https://user-images.githubusercontent.com/58545240/89848498-34d88e80-dbc1-11ea-8f64-5328e6f24208.png)

```shell
# 이제는 patient_set.all()로 가져올 수 없고 지정한 patients로만 가져올 수 있다.
In [1]: doctor = Doctor.objects.get(pk=1)
In [2]: doctor.patients.all()
Out[2]: <QuerySet [<Patient: 1번 환자 TOM>, <Patient: 2번 환자 KANG>]>
```

= > **relate_name을 쓸거면 reservation 클래스가 필요없다.**

![image-20200625104225288](https://user-images.githubusercontent.com/58545240/89848501-35712500-dbc1-11ea-8907-458a442e17ba.png)

```shell
In [1]: doctor = Doctor.objects.create(name='KIM')
In [2]: patient = Patient.objects.create(name='TOM')

# 환자 추가
In [3]: doctor.patients.add(patient)
# 환자 제거
In [4]: doctor.patients.remove(patient)

In [5]: doctor.patients.all()
Out[5]: <QuerySet []>
```

**!! 추가 필드가 요구될 시에는 중개모델(ex. Reservaion class)을 만들어줘야한다.!!**

- 언제 related_name이 반드시 필요한가?
  - ?

## 게시글 좋아요 만들기

![image-20200625111435406](https://user-images.githubusercontent.com/58545240/89848503-35712500-dbc1-11ea-9ed8-a3b49a0fcb50.png)



```python
# urls.py
path('<int:article_pk>/like/', views.like, name="like"),

# views.py
@login_required
def like(request, article_pk):
    # 특정 게시물에 대한 정보
    article = get_object_or_404(Article, pk=article_pk)
    # 좋아요를 누른 유저에 대한 정보
    user = request.user
    # 사용자가 게시글의 좋아요 목록에 있으면 지우고 없으면 추가한다.
    if user in article.like_users.all():
        article.like_users.remove(user)
    else:
        article.like_users.add(user)
    return redirect('articles:index')

# index.html
{% if user in article.like_users.all %}
<a href="{% url 'articles:like' article.pk %}"> 좋아요 취소 </a>
{% else %}
<a href="{% url 'articles:like' article.pk %}"> 좋아요 </a>
{% endif %}
```



- font awesome 사용하기
  - base.html에 Kit code를 붙여넣는다.

![image-20200625130756735](https://user-images.githubusercontent.com/58545240/89848504-3609bb80-dbc1-11ea-8899-f70f4581f6ef.png)



- 좋아요를 하나의 모듈로 만들기

```python
# _like.html
{% if user in article.like_users.all %}
<a href="{% url 'articles:like' article.pk %}"> 좋아요취소<i class="fas fa-thumbs-down"></i> </a>
{% else %}
<a href="{% url 'articles:like' article.pk %}"> 좋아요<i class="fas fa-thumbs-up"></i> </a>
{% endif %}
</div>

<div class="col-lg-2">
{% if user in article.recommend_users.all %}
<a href="{% url 'articles:recommend' article.pk %}"> 추천 취소 </a>
{% else %}
<a href="{% url 'articles:recommend' article.pk %}"> 추천 </a>
{% endif %}

# index.html
## 넣고 싶은 부분에 include를 이용
{% include 'articles/_like.html' %}      

```



## 프로필만들기

```python
# urls.py
path('<str:username>/', views.profile, name="profile"),

# views.py
from django.shortcuts import get_object_or_404
from django.contrib.auth import get_user_model

def profile(request, username):
    person = get_object_or_404(get_user_model(), username=username)
    context={
        'person' : person
    }
    return render(request, 'accounts/profile.html', context)

# profile.html
{% extends 'base.html' %}
{% block body %}
<h3>{{ person.username }}</h3>
<!-- 유저가 작성한 모든 게시물 -->
<p>유저가 작성한 게시글들</p>
<ul>
  {% for article in person.article_set.all %}
    <li>{{ article.title }}</li>
    <li>{{ article.content }}</li>
  {% endfor %}
</ul>

<p>유저가 작성한 게시글들</p>
<ul>
  {% for comment in person.comment_set.all %}
    <li>{{ comment.content }}</li>
  {% empty %}
    <p>댓글을 단 적이 없습니다.</p>
  {% endfor %}
</ul>

<p>유저가 좋아요 누른 게시글들</p>
<ul>
  {% for comment in person.like_articles.all %}
    <li>{{ like.content }}</li>
  {% empty %}
    <p>좋아요를 누른 적이 없습니다.</p>
  {% endfor %}
</ul>

{% endblock %}
```



## 팔로우하기

- `get_user_model` vs `AUTH_USER_MODEL`
  - 전자는 객체를 반환하기때문에 accounts를 바라보게된다? => 활성화된 객체를 찾아간다.
  - 후자는 스트링값 반환=> migrations -> migrate 과정에서 문자열이기 때문에 원활하게 진행

- articles에 있는 models.py에서는 AUTH_USER_MODEL을 사용
- accounts에 있는 models.py에서는 get_user_model을 사용
- **Models.py에서 정의할 때 빼고는 전부 get_user_model을 사용하면된다!!**

```python
# admin.py ( accounts )
###### ^^^^^^ 여기서만 예외 ########
from django.contrib.auth.admin import UserAdmin
from .models import User

admin.site.register(User, UserAdmin)
```

```python
# models.py ( accounts )
from django.db import models
from django.conf import settings
from django.contrib.auth.models import AbstractUser

# Create your models here.
class User(AbstractUser):
    followers = models.ManyToManyField(
        settings.AUTH_USER_MODEL,
        related_name="followings",
        blanck=True
    )
```

- 추가로 forms.py도 커스터마이징해줘야한다!!

```python
# forms.py ( accounts )
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import get_user_model
class CustomUserCreationForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = get_user_model()
        # fileds = 
        
# views.py ( accounts )        
from .forms import CustomUserCreationForm
```

UserCreationForm.Meta를 써주면 기존에 있는 Meta를 쓰기때문에 필드를 생략해도 된다.

![image-20200625154026206](https://user-images.githubusercontent.com/58545240/89848506-3609bb80-dbc1-11ea-95d8-119a8e7ded3a.png)



```
# 기존 에서
def signup(request):    
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)
            return redirect('articles:index')
    else:
        form = UserCreationForm()
    context = {
        'form' : form
    }
    return render(request, 'accounts/signup.html', context)

# 이렇게 바꾼다.
def signup(request):    
    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)
            return redirect('articles:index')
    else:
        form = CustomUserCreationForm()
    context = {
        'form' : form
    }
    return render(request, 'accounts/signup.html', context)

# accounts/urls.py
urlpatterns = [
    path('signup/', views.signup, name="signup"),
    path('login/', views.login, name="login"),
    path('logout', views.logout, name="logout"),
    path('delete/', views.delete, name="delete"),
    path('update/', views.update, name="update"),
    path('password/', views.password, name="password"),
    path('follow/<int:user_pk>/', views.follow, name="follow"),  # 여기에 추가했다
    path('<str:username>/', views.profile, name="profile"), # 문자열 하나만 받을 친구는 밑에 둬야한다.(오류생김)
]

# accounts/views.py
def follow(request, user_pk):
    # person에 담긴 user_pk값을 가진 유저는
    # 프로필의 주인이다.
    # request.user는 나. 요청을 보내온 사용자이다
    person = get_object_or_404(get_user_model(), pk=user_pk)
    if request.user in person.followers.all():
        person.followers.remove(request.user)
    else :
        person.followers.add(request.user)
    return redirect('accounts:profile',person.username)
<!--profile.html-->
{% extends 'base.html' %}
{% block body %}
<h3>{{ person.username }}</h3>
<!-- 팔로우 로직 구현-->
{% if user != person %} <!-- 본인일때는 팔로우 안보이도록-->
{% if user in person.followers.all %}
<a href="{% url 'accounts:follow' person.pk %}">팔로우 취소</a>
{% else %}
<a href="{% url 'accounts:follow' person.pk %}">팔로우</a>
{% endif %}
{% endif %}
...
...
...
```

**DB다 지우고 makemigrations와 migrate한다**

## 페이징 구현

```
# articles/views.py
from django.core.paginator import Paginator

def index(request):  #index 부분을 수정한다.(paging 추가)
    #embed()
    articles = Article.objects.all()
    # 1. Paginator(전체 리스트, 한 페이지당 개수)
    paginator = Paginator(articles, 3)
    # 2. 몇 번째 페이지를 보여줄 것인지 GET으로 받
    # 'articles/?page=3'
    page = request.GET.get('page')
    # 해당하는 페이지의 게시글만 가져오기
    articles = paginator.get_page(page)
    context = {
        'articles': articles
    }
    return render(request, 'articles/index.html', context)
```

- index.html

```
{% extends 'base.html' %}
{% block body %}
<h1>메인 페이지 입니다.</h1>
<hr>
<a href="{% url 'articles:create' %}">[CREATE]</a>
<hr>
<p>{{ articles.all|length }}개의 글</p>
<hr>
{% for article in articles %}
 <p>{{ article.pk }}번째 글</p>
 <h2>{{ article.title }}</h2>
<p>좋아요 개수 : {{ article.like_users.all|length }}</p>
<p>추천 개수 : {{ article.recommend_users.all|length }}</p>
<p>댓글 개수 : {{ article.comment_set.all|length }}</p>

<div class="container">
  <div class="row">
    <div class="col-lg-2">
      {% include 'articles/_like.html' %}
    </div>

    <div class="col-lg-2">
      <a href="{% url 'articles:detail' article.pk %}">[DETAIL]</a>
    </div>
  </div>
</div>
 <hr>
 
{% endfor %}
{% for num in articles.paginator.page_range %}
<a href="{% url articles:index' %}?page={{ num }}">{{ num }}</a>
{% endfor %}
{% endblock %}
```

아이디 2개를 만들어 주소로 내 아이디 말고 다른 사람 아이디를 들어가보면

![85811879-2ada0800-b79a-11ea-9c99-8b654ea9ddf0](https://user-images.githubusercontent.com/58545240/89848509-36a25200-dbc1-11ea-9f0c-da95453b247e.png)



# 자바스크립트

## 자바스크립트를 활용해 좋아요 구현

- base.html

```python
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
```

- _like.html

```python
{% if user in article.like_users.all %}
<i class="fas fa-thumbs-down like-button" data-id="{{ article.id }}" style="color : tomato"></i>
{% else %}
<i class="fas fa-thumbs-up like-button" data-id="{{ article.id }}" style="color : black" ></i>
{% endif %}
<span class="like-count-{{ article.id }}"> {{ article.like_users.count }}</span>

{% if user in article.recommend_users.all %}
<a href="{% url 'articles:recommend' article.pk %}"> 추천 취소 </a>
{% else %}
<a href="{% url 'articles:recommend' article.pk %}"> 추천 </a>
{% endif %}
```

- index.html

```python
{% load static %}
...

<script src="{% static 'articles/js/like.js' %}">
```

- detail.html

```python
{% load static %}
...

<script src="{% static 'articles/js/like.js' %}">
```

- like.js

```javascript
const likeButton = document.querySelectorAll('.like-button')
likeButton.forEach(button =>{
    button.addEventListener('click', function(event){
        const articleId = event.target.dataset.id
        const likeCount = document.querySelector(`.like-count-${articleId}`)

        $httpProvider.defaults.xsrfCookieName = 'csrftoken';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken'

        axios.post(`/articles/${articleId}/like/`)
        .then(response => {
            likeCount.innerText = response.data.count
            if(response.data.liked){
                event.target.className = 'fas fa-thumbs-up like-button'
                event.target.style.color = 'crimson'
            } else{
                event.target.className = 'fas fa-thumbs-down like-button'
                event.target.style.color = 'black'
            }
        })
    })
})
```

=> csrf를 쓰기 위해선 httpcsrftoken을 사용하면 된다!

- views.py

```python
from django.http import JsonResponse

@login_required
def like(request, article_pk):
    # 특정 게시물에 대한 정보
    article = get_object_or_404(Article, pk=article_pk)
    # 좋아요를 누른 유저에 대한 정보
    user = request.user
    # 사용자가 게시글의 좋아요 목록에 있으면 지우고 없으면 추가한다.
    if user in article.like_users.all():
        article.like_users.remove(user)
        liked = False
    else:
        article.like_users.add(user)
        liked = True
    context = {
        'liked' : liked,
        'count' : article.like_users.count()
    }
    return JsonResponse(context)
```

