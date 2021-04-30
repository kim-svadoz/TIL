# **Java Script**

---

> 모던 자바스크립트에서 지원하는 모든 기능을 활성화하려면 스크랩트 맨 위에 **`use strict`**를 적어줘야 한다.
>
> ```javascript
> 'use strict'
> 
> ...
> ```
>
> 이 **`'use strict'`**는 스크립트 최상단이나 함수 본문 최상단에 있어야 한다.
>
> **`'use strict'`**가 없어도 코드는 정상적으로 동작한다. 다만, 모던한 방식이 아닌 옛날 방식으로 동작한다. (**하위 호환성**을 지키면서 말이다!)
>
> 되도록이면 모던항 방식을 사용하는것을 추천하고, 추후에 배우게 될 클래스와 같은 몇몇 모던 기능은 이 엄경 모드를 자동으로 활성화한다.

# 1. 변수

## var

자바스크립트에는 변수형(int, double, string)이 존재하지 않는다. **var**이라는 가변형 변수만 존재하여 초기화 할 때 형태에 따라서 알아서 할당된다.

```javascript
var i;
var x = 123;
var y = "123";
var z = false;
var w = null;
```

덧붙히자면 var의 유효 범위는 함수 내부로 조건문이나 반복문 안에서 초기화 된 경우라도 함수 내부 블록이라면 어디서나 호출 할 수 있다. 최신 자바 스크립트에선 **`let`**이라는 블럭 지역 변수를 제공한다.

## let

```javascript
let name
name = 'monkey'
```

## const

>  상수 선언

모던 자바스크립트에서는 더이상 `var`를 사용하지 않는다. 함수를 포함한 대부분의 선언은 모두 **`const`**로 한다. `let`이 필요한 경우에만 `let`을 쓴다.

```javascript
const onShowModal = () => {
    ...
}
```

## 자료형

자바스크립트는 총 여덟 가지 기본 자료형을 지원하며, 별도의 선언 없이 변수에 대입할 수 있다.

- 정수와 부동 소수점을 저장하는데 쓰이는 **`숫자형`**
- 아주 큰 숫자를 저장할 수 있는 **`BigInt형`**
- 문자열을 저장하는 데 쓰이는 **`문자형`**
- 논리값 `true/false`을 저장하는 데 쓰이는 **`boolean형`**
- ‘비어있음’, '존재하지 않음’을 나타내는 `null` 값만을 위한 독립 자료형 **`null`**
- 값이 할당되지 않은 상태를 나타내는 `undefined` 값만을 위한 독립 자료형 **`undefined`**
- 복잡한 자료구조를 저장하는 데 쓰이는 `객체형`과 고유한 식별자를 만들 때 사용되는 **`심볼형`**

**`typeof`** 연산자는 값의 자료형을 반환해준다. 그런데 두 가지 예외사항이 있다.

```javascript
typeof null == "object" // 언어 자체의 오류
typeof function(){} == "function" // 함수는 특별하게 취급됩니다.
```

## 명명규칙

- 숫자와 문자를 사용하되 첫 글자는 숫자가 될 수 없다.
- 특수기호는 `&`와 `_`만 사용할 수 있다.
- 비 라틴계 언어의 문자나 상형문자도 사용할 수 있지만 잘 쓰이진 않는다.

# 2. 연산자

자바스크립트는 다양한 연산자를 제공한다.

## 산술연산자

사칙 연산에 관련된 연산자 `* + - /`와 나머지 연산자 `%`, 거듭제곱 연산자 `**`가 대표적인 산술 연산자에 속한다.

이항 덧셈 연산자 `+`는 피연산자 중 하나가 문자열일 때 나머지 하나를 문자형으로 바꾸고 두 문자열을 연결한다.

```javascript
alert( '1' + 2 ); // '12', 문자열
alert( 1 + '2' ); // '12', 문자열
```

## 할당연산자

`a = b` 형태의 할당 연산자와 `a *= 2` 형태의 복합 할당 연산자가 있다.

## 비트 연산자

비트 연산자는 인수를 32비트 정수로 변환하여 이진 연산을 수행합니다.

## 조건부 연산자

조건부 연산자는 자바스크립트 연산자 중 유일하게 매개변수가 3개인 연산자이다. 

`cond ? resultA : resultB`와 같은 형태로 사용하고, `cond`가 truthy면 `resultA`를, 아니면 `resultB`를 반환한다.

## 논리 연산자

AND 연산자 `&&`와 OR 연산자 `||`은 단락 평가를 수행하고, 평가가 멈춘 시점의 값을 반환한다(꼭 `true`나 `false`일 필요는 없다). 

NOT 연산자 `!`는 피연산자의 자료형을 불린형으로 바꾼 후 그 역을 반환한다.

## null 병합 연산자

null 병합 연산자 `??`는 피연산자 중 실제 값이 정의된 피연산자를 찾는 데 쓰인다. 

`a`가 `null`이나 `undefined`가 아니면 `a ?? b`의 평가 결과는 `a`이고, `a`가 `null`이나 `undefined`이면 `a ?? b`의 평가 결과는 `b`가 된다.

## 비교 연산자

동등 연산자 `==`는 형이 다른 값끼리 비교할 때 피연산자의 자료형을 숫자형으로 바꾼 후 비교를 진행한다.

`null`과 `undefined`는 자기끼리 비교할 땐 참을 반환하지만 다른 자료형과 비교할 땐 거짓을 반환한다.

```javascript
alert( 0 == false ); // true
alert( 0 == '' ); // true
```

기타 비교 연산자들 `< > <= >=` 역시 피연산자의 자료형을 숫자형으로 바꾼 후 비교를 진행한다.

일치 연산자 `===`는 피연산자의 형을 변환하지 않는다. 형이 다르면 무조건 다르다고 평가한다.

`null`과 `undefined`는 특별한 값으로, 두 값을 `==` 연산자로 비교하면 `true`를 반환하지만, 다른 값과 비교하면 무조건 `false`를 반환한다.

크고 작음을 비교하는 연산자의 피연산자로 문자열이 들어오면 글자 단위로 크기 비교가 이뤄진다. 다른 타입의 값이 들어오면 숫자형으로 형 변환한 후 비교를 진행한다.

## 기타 연산자

쉼표 연산자 등의 기타 연산자도 있다.

# 3. 조건문

## if문

특정 조건이 true를 반환할 경우에만 if문 안의 코드를 실행한다.

```javascript
if (condition) {
    code..
}
```

if 문 안과 밖은 블록으로 구분되어져 있기 때문에 `const`를 이중으로 정의해도 무방하다.

```javascript
const a = 1;
if (true) {
    const a = 2
    console.log('if 문 안의 a 값은 ' + a)
}
console.log('if 문 밖의 a 값은' + a)
```

## if-else문

둘 중 하나만 실행하고 싶은 분기를 할 때 사용한다.

## if-else if문

여러 조건 중 하나를 실행하고 싶은 분기를 할 때 사용한다.

## switch / case 문

특정 값이 무엇이냐에 따라 다른 작업을 하고 싶을 때 사용한다.

단, case값부터 시작해서 마지막까지 순차적으로 실행하기 때문에 각 케이스 내용 끝에 break를 써주어야 한다.

default는 해당하는 case 값이 없을 때 실행하는 코드이다.

또한, `switch`문은 조건을 확인할 때 내부적으로 일치 연산자 `===`를 사용하여 비교를 진행한다.

```javascript
const device = 'iphone';

switch (device) {
    case 'iphone' :
        console.log('아이폰!');
        break;
    case 'ipad' :
        consoe.log('아이패드~!');
        break;
    case 'galaxy note' :
        console.log('갤럭시 노트!');
        break;
	default :
        console.log('무엇일까요..');
}
```

# 4. 함수

자바스크립트는 세 가지 방법으로 함수를 만들 수 있다.

## 함수 선언문

주요 코드 흐름을 차지하는 방식

```javascript
function sum(a, b) {
    let result = a + b;
    
    return result;
}
```

## 함수 표현식

표현식 형태로 선언된 함수

```javascript
let sum = function(a, b) {
    let result = a + b;
    
    return result;
}
```

## 화살표 함수

```javascript
// 화살표 ( => ) 우측엔 표현식이 있음
let sum = (a, b) => a + b;

// 대괄호 { ... }를 사용하면 본문에 여러 줄의 코드를 작성할 수 있음. return 필수
let sum = (a, b) => {
    ...
    return a + b;
}

// 인수가 없는 경우
let sayHi = () => alert("Hello");

// 인수가 하나인 경우
let double = n => n * 2;
```

- 함수는 지역변수를 가질 수 있다. 지역변수는 함수의 본문에 선언된 변수로, 함수 내부에서만 접근 가능하다.

- 매개변수에 기본값을 설정할 수 있다. 문법은 다음과 같다.

  ```javascript
  function sum(a = 1, b = 2) {
      ...
  }
  ```

- 함수는 항상 무언가를 반환한다. `return`문이 없는 경우는 `undefined`를 반환한다.

# 5. 배열

변수의 값이 연속적으로 나열된 형식

## 배열의 선언

```javascript
var arr = new Array
var arr = [];
```

## 배열에 값 삽입

```javascript
var job = [];
job[0] = "Warrior";
job[1] = "Archer";
job[2] = "Wizard";

var job = ["Warrior", "Archer", "Wizard"];
```

## 배열 관련 메소드

```javascript
var job = ["Warrior", "Archer", "Wizard"];
document.write("Total Jobs:" + job.length);
```

```bash
Total Jobs:3
```

`length`는 메소드라 표현하기 애매하다. 자바스크립트에서 Array는 하나의 객체이며 length는 Array가 가진 멤버 변수로 보여진다.

배열의 길이가 필요할 때마다 길이를 읽어오는 것 보단(`O(N)`), 길이를 가지고 있는게 (`O(1)`) 효율적일테니까.



```javascript
var job = ["Warrior", "Archer", "Wizard"];
document.write("Total Jobs : " + job.join(","));
```

```bash
Total Jobs Name : Warrior,Archer,Wizard
```



```javascript
var job = ["Warrior", "Archer", "Wizard"];
document.write("Total Jobs Sort : " + job.sort());
```

```bash
Total Jobs Sort : Archer,Warrior,Wizard
```



```javascript
var job = ["Warrior", "Archer", "Wizard"];
var DLC_job = ["Assassin", "Samurai"]
document.write("Total Jobs : " + job.concat(DLC_job));
```

```bash
Total Jobs : Warrior,Archer,Wizard,Assassin,Samurai
```



```javascript
var job = ["Warrior", "Archer", "Wizard"];
document.write("<p>" + "Total Jobs : " + job.push("Devil", "Outlaw" + "</p>"));
document.write("<p>" + "Total Jobs : " + job.pop( + "</p>"));
```

```bash
Total Jobs : 5
Total Jobs : Outlaw
```

push와 pop은 자료구조 Stack에서 다뤄지며 javascript에도 동일한 기능을 수행한다. push는 맨 뒤에 요소를 삽입 pop은 맨 뒤 요소를 꺼내는 것이다.



```javascript
var job = ["Warrior", "Archer", "Wizard"];
job.shift();
job.unshift("Knight")
document.write("Total Jobs : " + job);
```

```bash
Total Jobs : Knight,Archer,Wizard
```

shift, unshift는 맨 앞에 요소를 빼거나 삽입하는 기능을 한다.

# 6. 오브젝트(객체)

**구조체(struct)** 처럼 내부에 여러 변수를 가질 수 있고 **클래스(class)**처럼 내부에 메소드를 포함하고 있는 형식이다. `JSON`이라고 많이 알려진 형식이다.

## 오브젝트의 선언

```javascript
const obj = new Object;
const obj = {};
```

## 변수를 가진 오브젝트

hp와 mp를 가진 player를 생성해보자.

```javascript
const player = {};
player.hp = 100;
player.mp = 300;

const player = {
    hp: 100,
    mp: 300
};
```

```javascript
const dog = {
    // key : value
    name : '멍멍이',
    age : 2
};
console.log(dog.name); // 멍멍이
console.log(dog.age); // 2
```

일반적으로 key는 공백이 없어야 하지만 공백이 필요한 경우에는 따옴표로 감싸서 문자열로 넣어주면 된다.

```javascript
const sample = {
    'key with space' : true
};
```

## 함수에서 객체를 파라미터로 받기

```javascript
const ironMan = {
    name: '토니 스타크',
    actor: '로버트 다우니 주니어',
    alias: '아이언맨'
};

const captainAmercia = {
    name: '스티븐 로저스',
    actor: '크리스 에반스',
    alias: '캡틴 아메리카'
};

function print(hero) {
    const text = `${hero.alias}(${hero.name}) 역할을 맡은 배우는 ${hero.actor} 입니다.`;
    console.log(text);
}
print(ironMan);	// 아이언맨(토니스타크) 역할을 맡은 배우는 로버트 다우니 주니어 입니다.
print(captainAmerica); // 캡틴 아메리카(스비튼 로저스) 역할을 맡은 배우는 크리스 에반스 입니다.
```

## 객체 비구조화 할당

```javascript
const ironMan = {
  name: '토니 스타크',
  actor: '로버트 다우니 주니어',
  alias: '아이언맨'
};

const captainAmerica = {
  name: '스티븐 로저스',
  actor: '크리스 에반스',
  alias: '캡틴 아메리카'
};

// 이를 객체 구조 분해 라고 부른다.
function print(hero) {
    const {alias, name, actor} = hero;
    const text = `${alias}(${name}) 역할을 맡은 배우는 ${actor} 입니다.`;
    console.log(text);
}
print(ironMan);
print(captainAmerica);

```

더 나아가 파라미터 단계에서 객체 비구조화 할당을 할 수도 있다.

```javascript
function print({ alias, name, actor }) {
  const text = `${alias}(${name}) 역할을 맡은 배우는 ${actor} 입니다.`;
  console.log(text);
}
```



## 메소드를 가진 오브젝트

플레이어가 후려맞는(?) 기능을 넣어보자.

```javascript
const player = {
    hp : 100,
    mp : 300,
    hit : function() {
        this.hp -= 10; // 여기서 this는 자기 자신을 가리킨다.
        document.write("HIT!!");
    }
};
player.hit();
document.write(player.hp);
```

```bash
HIT!!90
```

여기서 함수를 화살표함수로 작성하면 제대로 작동하지 않는데, 그 이유는 화살표함수의 this가 객체를 가리키지 않기 때문이다.

## 오브젝트 할당

위는 플레이어가 선언됨가 동시에 사용되고 있다. 만일 클래스 혹은 구조체처럼 단지 구조만 선언하고 싶은 경우엔 어떻게 표현할 수 있을까?

오브젝트를 함수로 선언하면 된다.

```javascript
const Player = function(name) {
    const name = name;
    const hp = 100;
    const mp = 300;
    return {
        hit: function(demage) {
            hp -= demage;
            document.write("HIT!!");
        },
        die: function() {
            return hp == 0 ? true : false;
        }
    }
}

const medic = new Player('medic');
medic.hit(50);	// HIT!!
document.write(medic.die()) // false

const fireBat = new Player('frieBat');
fireBat.hit(100);	// HIT!!
document.write(fireBat.die());	// true
```

위가 **클로져**라는 개념을 응용한 방식인데, 위와 같이 선언하여 하나의 구조체를 여러 변수에서 할당받아 사용할 수 있다.

## Getter와 Setter

Getter함수와 Setter함수를 사용하게 되면 특정값을 바꾸려고 하거나, 특정 값을 조회하려고 할 때 우리가 원하는 코드를 실행시킬 수 있다.

```jsx
const numbers = {
  a: 1,
  b: 2,
  get sum() {
    console.log('sum 함수가 실행됩니다!');
    return this.a + this.b;
  }
};

console.log(numbers.sum);  // numbers.sum()을 통해 함수를 실행시키지 않아도 함수가 실행되고 값까지 반환된다.
numbers.b = 5;
console.log(numbers.sum);
```

이런식으로 Getter 함수는 특정 값을 조회 할 때 우리가 설정한 함수로 연산된 값을 반환한다.

```jsx
const numbers = {
  _a: 1,
  _b: 2,
  sum: 3,
  calculate() {
    console.log('calculate');
    this.sum = this._a + this._b;
  },
  get a() {
    return this._a;
  },
  get b() {
    return this._b;
  },
  set a(value) {
    console.log('a가 바뀝니다.');
    this._a = value;
    this.calculate();
  },
  set b(value) {
    console.log('b가 바뀝니다.');
    this._b = value;
    this.calculate();
  }
};

console.log(numbers.sum);  // 3
numbers.a = 5;    // sum => 7
numbers.b = 7;    // sum => 12
numbers.a = 9;    // sum => 16
console.log(numbers.sum);  // 16
console.log(numbers.sum);  // 16
console.log(numbers.sum);  // 16
```

setter함수는 객체 내 변수의 값을 바꾸는 역할을 하는데 set 키워드가 없으면 `numbers.a(5)`이런식으로 바꿔야할 것을 `numbers.a = 5` 이렇게 더 직관적인 키워드로 바꿀 수 있다.

# 7. 반복문

반복문이 이렇게 뒤에 나오는 이유는 위에서 얻은 개념으로 반복문을 더욱 효율적으로 응용할 수 있기 때문이다.

아래는 가장 기본적으로 사용되는 방식의 반복문이다.

```javascript
for (var i = 0; i < 5; i++) {
    document.write(i);
}
```

```javascript
const i = 0;
while (i < 5) {
    document.write(i++);
}
```

```bash
01234
```



## + in

```javascript
const arr = [10, 20, 30, 40, 50];

for (const i in arr) {
    document.write(i);
}
```

```bash
01234
```

in의 경우에는 배열이나 **객체의 갯수의 인덱스가 i에 할당**되어 반복이 진행된다.

## + of

```javascript
const arr = [10, 20, 30, 40, 50];

for (const i of arr) {
    document.write(i);
}
```

```bash
1020304050
```

of의 경우에는 배열이나 **객체의 값이 i에 할당**되어 반복이 진행된다.

**`forEach`**나 **`map`**을 이용해서 객체의 반복을 실행할 수 도 있다.

```javascript
const arr = [10, 20, 30, 40, 50];

arr.forEach((value) => {
    document.write(value);
});

arr.map((value) => {
    document.wrtie(value);
});
```

```bash
1020304050
```

속도는 **`forEach`**가 빠르다고 알려져 있다. **`map`**은 함수형 프로그래밍에 사용되는 개념으로 forEach와 달리 새로운 객체를 반환하며 map에선 동시에 인덱스를 출력 할 수 도 있다.

```javascript
arr = [10, 20, 30, 40, 50];

arr.map((value, index) => {
    // value = 10, index = 0
    // value = 20, index = 1
    // value = 30, index = 2
    // value = 40, index = 3
    // value = 50, index = 4
})
```

# 8. 문자열

문자열 에서도 간단하고 중요한 것만 나열하겠다.

```javascript
const Welcome = "ONDE Planet is the most peaceful space in the universe";

document.write(Welcome.charAt(0));
// charAt(n) : n번째 문자를 출력한다 // 0

document.write(Welcome.charCodeAt(0));
// charCodeAt(n) : n번째 문자의 유니코드를 출력한다. // 79

document.write(Welcome.indexOf("x"));
// indexOf("?") : ? 라는 글자가 있다면 글자의 인덱스를, 없다면 false(-1)을 출력한다. // -1

document.write(Welcome.includes("space"));
// includes("?") : ?라는 글자가 있다면 true(0), 없다면 false(-1)을 출력한다. 결과는 0

document.write(Welcome.replace("peaceful", "nasty"));
// replace("a", "b") : a를 b로 교체한다. 결과는 ONDE Planet is the most nasty space in the universe.

document.write(Welcome.search("universe"));
// search("?") : ?라는 글자를 검색하여 첫 문자의 시작 지점을 알려준다. 결과는 46

document.write(Welcome.slice(0,4));
// slice(n, n') : n~n'-1 까지의 문자를 가져온다. 결과는 ONDE

document.write(Welcome.split(" "));
// split("?") : ?라는 문자를 기준으로 문자열을 분리한다. 결과는 ONDE,Planet,is,the,most,peaceful...

document.write(Welcome.trim());
// trim() : 앞, 뒤의 공백을 제거하는 역할을 한다. 이 값에서는 앞뒤에 공백이 없으므로 결과가 본래의 문자열과 동일하다.

document.write(Welcome.length);
// length : 문자열의 길이를 반환한다. 결과는 55
```



**replace**의 경우에는 처음 발견된 문장만 변경하는데 만일 다수의 문장을 변경하고 싶은 경우엔 어떻게 할 수 있을까? 가령 replaceAll처럼 말이다.

바로 정규표현식을 사용하면 되는데, 여기선 자세한 내용을 다루지 않겠다. 대략 아래와 같은 모양이다.

```javascript
document.write(Welcome.replace(/ /gi, "-"));
// 모든 공백이 -로 바뀐다.
```

**slice**에는 음수를 넣을 수 있다. 음수를 선택하면 뒤에서부터 가져오므로 상당히 유용한 기능이며 배열에도 사용할 수 있다.

```javascript
document.write(Welcome.slice(-3));
// 맨 뒤 3글자만 가져온다.
```

# 9. 수학연산

**Math**라는 기능을 이용하여 사용할 수 있는 연산들이다.

```javascript
Math.abs(-3);
// Math.abs(n) : n을 절댓값으로 바꾼다.

Math.ceil(3.1);
// Math.ceil(n) : n값을 올림한다.

Math.floor(3.9);
// Math.floor(n) : n값을 내림한다.

Math.round(3.5);
// Math.round(n) : n값을 반올림한다.

const a = Math.random();
// Math.random() : 난수를 생성한다.

const b = Math.random() * 10 + 10;
// Math.random() * x + y : y ~ x + y 범위에서 난수가 생성된다.

const c = Math.floor(Math.random() * (max - min)) + min;
// min 부터 max - 1 까지 범위의 난수
```

# 10. 형변환

```javascript
// Num -> String
num = 2021;
const str = string(num);
const str = num.toString();

// String -> Num
const str = "2021.04";
const mInt = Number(str);	// 2021
const mInt = parseInt(str); // 2021
const mFloat = parseFloat(str); // 2021.04

// JSON -> String
const myInfo = {
    name: '김성현',
    age: 99,
};
console.log(myInfo); // 이와 같이 출력하면 Object로 출력된다.
console.log(JSON.stringify(myInfo)); // 이렇게 출력해야 문자열 JSON으로 출력된다.

const myInfoStr = JSON.stringify(myInfo);
const transMyInfoToJson = JSON.parse(myInfoStr);
// JSON형식의 문자열이라면 parse를 이용해서 JSON으로 변환할 수 있다.
```

