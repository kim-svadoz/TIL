# SQLite3 with C/C++

> 이 포스트는 Zetcode.com의 글 "SQLite C Tutorial"을 번역한 것이다.
>
> http://zetcode.com/db/sqlitec/

# **==========기본==========**

# **SQLite C 튜토리얼**

---

SQLite 는 관계형 데이터베이스 엔진입니다. 개발자는 SQLite 를 자급자족하는, 서버도 필요 없고, 설정도 필요없는 트랜잭셔널 SQL 데이터베이스 엔진이라고 표현합니다. 지금은 전세계에 수십 수백만 개가 사용되고 있을 정도로 유명하죠. SQLite 는 Solaris 10, Mac OS, 안드로이드, 아이폰에도 사용됩니다. Qt4 라이브러리도 파이썬, PHP 와 함께 SQLite 를 내부적으로 지원하죠. 또 파이어폭스, 구글 크롬 등의 아주 유명한 애플리케이션들도 내부적으로 SQLite 를 사용하고 있습니다. 대단하죠!?

# **SQLite 3 툴**

---

SQlite3 툴은 SQLite 라이브러리의 터미널 기반 프론트엔드입니다. [(다운로드 링크)](https://www.sqlite.org/download.html) 대화식으로 쿼리를 보내고, 여러 가지 포맷으로 결과를 확인할 수 있죠. 물론 스크립트도 사용 가능합니다. 이 툴은 **`.tables`**, **`.load`,** **`.databases`**, **`.dump`** 같은 메타 명령어도 가지고 있습니다. 모든 명령어를 확인하려면 **`.help`** 를 입력하면 됩니다.

C 라이브러리를 가지고 SQLite 를 사용하는 프로그램을 만들기 전에, 이 툴을 가지고 몇 가지 실습을 해보겠습니다. 먼저, sqlite3 tool 을 실행하고 새로운 데이터베이스를 생성해보겠습니다. 리눅스 터미널을 기준으로 진행합니다. (윈도우즈의 경우 sqlite3.exe 파일을 실행해 진행하면 됩니다. `ls` 명령어 대신 `dir` 를 사용하는 등의 차이는 있습니다.)

```bash
$ sqlite3 test.db
SQLite version 3.22.0 2018-01-22 18:45:57
Enter ".help" for usage hints.
```

명령어를 보면 sqlite3 툴에 `test.db` 라는 인자를 전달했는데요, `test.db` 는 데이터베이스의 이름입니다. 이 데이터베이스는 디스크의 파일로 존재하게 됩니다. 명령어를 입력했을때 이미 파일이 존재하면 그 파일을 열고, 없다면 새로 생성하게 됩니다.

```bash
sqlite> .tables
sqlite> .exit
$ ls
test.db
```

`.table` 명령어는 현재 데이터베이스(`test.db`)의 테이블들을 보여줍니다. 지금은 테이블이 하나도 없으니 아무 것도 표시되지 않죠. `.quit` 또는 `.exit` 명령어는 대화형 세션을 종료하는 명령어입니다. `ls` 명령어로 확인해보면 `test.db` 파일이 생성된 것을 확인할 수 있습니다. 모든 데이터가 이 파일 하나에 저장됩니다!

# **SQLite 버전 확인하기**

---

첫 번째 코드 예제로, 사용하는 SQLite 라이브러리의 버전을 표시하는 프로그램을 보겠습니다.

(역자주: 원문에는 설명이 생략되어 있는데, SQLite 라이브러리를 프로젝트에 추가하는 방법은 아주 쉽습니다. [SQLite 다운로드 페이지](https://www.sqlite.org/download.html)에서 sqlite 아말감화 버전 파일 **`sqlite-amalgamation-*.zip`** 을 다운받고, 안의 sqlite3.h, sqlite3.c 파일을 프로젝트에 추가하면 끝입니다. 와!)

```c
#include <sqlite3.h>
#include <stdio.h>

int main(void)
{
    printf("%s\n", sqlite3_libversion()); 
    
    return 0;
}
```

**`sqlite3_libversion()`** 함수는 SQLite 라이브러리의 버전을 반환합니다.

```c
#include <sqlite3.h>
```

**`sqlite3.h`** 헤더파일은 SQLite 라이브러리의 인터페이스를 정의합니다. 함수, 변수의 정의들과 친절한 주석들을 포함하고 있습니다.

```bash
$ gcc -o test test.c -lsqlite3 -std=c99
```

GNU C 컴파일러로 컴파일한 뒤 실행해보겠습니다.

```bash
$ ./test
3.22.0
```

버전이 출력됩니다.

두 번째 코드예제에서도 SQLite 라이브러리의 버전을 출력해보겠습니다. 하지만, 이번에는 **SQL쿼리**를 이용해 보겠습니다.

```c
#include <sqlite3.h>
#include <stdio.h>

int main(void)
{
    sqlite3* db;
    sqlite3_stmt* res;
    
    int rc = sqlite3_open(":memory:", &db);
    
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    rc = sqlite3_prepare_v2(db, "SELECT SQLITE_VERSION()", -1, &res, 0);    
    
    if (rc != SQLITE_OK) 
    {
        fprintf(stderr, "Failed to fetch data: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    rc = sqlite3_step(res);
    
    if (rc == SQLITE_ROW)
    {
        printf("%s\n", sqlite3_column_text(res, 0));
    }
    
    sqlite3_finalize(res);
    sqlite3_close(db);
    
    return 0;
}
```

**`SELECT SQLITE_VERSION()`** 쿼리는 SQLite 라이브러리 버전을 얻어오는데 사용합니다.

```c
sqlite3* db;
```

위의 **`sqlite3`** 구조체 포인터는 데이터베이스의 핸들입니다. 열린 하나의 데이터베이스는 데이터베이스 핸들로 표현됩니다.

```c
sqlite3_stmt* res;
```

**`sqlite3_stmt`** 구조체 포인터는 하나의 SQL 쿼리를 표현하는 핸들입니다.

```c
int rc = sqlite3_open(":memory:", &db);
```

**`sqlite3_open`** 함수는 새로운 데이터베이스를 열어 연결합니다. 함수 인자는 데이터베이스 이름과, 연결할 데이터베이스 핸들입니다. 위의 **`:memory:`** 는 특별한 데이터베이스 이름입니다. 파일 이름 대신 **`:memory:`** 를 인자로 넘겨주면 인-메모리 데이터베이스로 동작하게 됩니다! 함수 반환값은 데이터베이스가 성공적으로 열렸는지를 나타내고, 성공적이였다면 `SQLITE_OK` 를 반환합니다.

```c
if (rc != SQLITE_OK)
{
    fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
    sqlite3_close(db);
    
    return 1;
}
```

반환값이 SQLITE_OK 가 아니면, 즉 오류이면, 오류메세지를 출력하고 데이터베이스를 닫은 뒤 프로그램을 종료합니다. **`sqlite3_errmsg()`** 함수가 오류메세지를 반환해줍니다. 오류가 발생했든 아니든 열었던 데이터베이스 핸들은 **`sqlite3_close()`** 함수로 정리해줘야 합니다.

```c
rc = sqlite3_prepare_v2(db, "SELECT SQLITE_VERSION()", -1, &res, 0);
```

SQL 쿼리는 실행하기 전에, 반드시 **`sqlite3_prepare*`** 함수를 통해 바이트코드로 컴파일해줘야 합니다. (sqlite3_prepare() 함수는 deprecated 되었습니다.)

**`sqlite3_prepare_v2()`** 함수는 다섯 개의 인자를 받습니다. 첫 번째 인자는 sqlite3_open() 함수로 연 데이터베이스 핸들입니다. 두 번째 인자는 컴파일할 SQL 쿼리입니다. 세 번째 인자는 SQL 쿼리의 byte 단위 길이입니다. **`-1`** 을 넘겨주면 Null(\0) 문자를 찾을 때까지 읽습니다. 문서에 의하면 정확한 크기를 넘겨주는게 성능상 조금 유리하다고 합니다. 네 번째 인자는 연결할 SQL 쿼리 핸들입니다. 마지막 인자는 SQL 쿼리에서 사용되지 않은 부분을 받을 포인터입니다. 첫 번째 구문만 컴파일 되고, 남은 구문들은 이 인자를 통해 반환됩니다. 우리는 관심이 없으니까 0을 넘겨주겠습니다.

성공하면, 역시 `SQLITE_OK` 를 반환합니다. 실패할 경우 [오류 코드](https://www.sqlite.org/rescode.html)를 반환합니다.

```c
if (rc != SQLITE_OK)
{
    fprintf(stderr, "Failed to fetch data: %s\n", sqlite3_errmsg(db));
    sqlite3_close(db);
    
    return 1;
}    
```

`sqlite3_prepare_v2()` 함수의 오류 처리 코드입니다.

```c
rc = sqlite3_step(res);
```

**`sqlite3_step()`** 함수는 `sqlite3_prepare*` 함수로 컴파일된 쿼리를 실행합니다. 반환값이 `SQLITE_ROW` 이면 쿼리 결과로 행(row) 하나가 준비되었다는 뜻입니다. 우리가 작성한 쿼리는 한 행만 출력하니까 한 번만 호출해줍니다.

(역자주: `SELECT * from table` 처럼 여러 행을 출력할 쿼리는 반복해서 sqlite3_step() 함수를 호출해 한 행씩 처리하는 방식입니다. 더 이상 출력이 없다면 `SQLITE_DONE` 이 반환됩니다.)

```c
sqlite3_finalize(res);
```

**`sqlite3_finalize()`** 함수는 컴파일된 SQL 쿼리 핸들을 정리합니다.

```c
sqlite3_close(db);
```

**`sqlite3_close()`** 함수로 데이터베이스를 닫고 끝냅니다.

# **INSERT로 테이블에 행 추가하기**

---

세 번째 코드 예제로는 **`Cars`** 테이블을 만들고 Insert 쿼리로 행을 몇 개 추가해 보겠습니다.

```c
#include <sqlite3.h>
#include <stdio.h>

int main(void)
{
    sqlite3 *db;
    char *err_msg = 0;
    
    int rc = sqlite3_open("test.db", &db);
    
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    char *sql = "DROP TABLE IF EXISTS Cars;" 
                "CREATE TABLE Cars(Id INT, Name TEXT, Price INT);" 
                "INSERT INTO Cars VALUES(1, 'Audi', 52642);" 
                "INSERT INTO Cars VALUES(2, 'Mercedes', 57127);" 
                "INSERT INTO Cars VALUES(3, 'Skoda', 9000);" 
                "INSERT INTO Cars VALUES(4, 'Volvo', 29000);" 
                "INSERT INTO Cars VALUES(5, 'Bentley', 350000);" 
                "INSERT INTO Cars VALUES(6, 'Citroen', 21000);" 
                "INSERT INTO Cars VALUES(7, 'Hummer', 41400);" 
                "INSERT INTO Cars VALUES(8, 'Volkswagen', 21600);";

    rc = sqlite3_exec(db, sql, 0, 0, &err_msg);
    
    if (rc != SQLITE_OK )
    {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        
        sqlite3_free(err_msg);        
        sqlite3_close(db);
        
        return 1;
    } 
    
    sqlite3_close(db);
    
    return 0;
}
```

`test.db` 를 열고, `Cars` 테이블을 만든 뒤 8개의 행을 만들어진 테이블에 추가할 겁니다.

```c
char *err_msg = 0;
```

만약 오류가 발생하면, 이 포인터가 오류 메세지를 가리킬 겁니다.

```c
int rc = sqlite3_open("test.db", &db);
```

`test.db` 데이터베이스를 열었습니다.

```c
char *sql = "DROP TABLE IF EXISTS Cars;" 
            "CREATE TABLE Cars(Id INT, Name TEXT, Price INT);" 
            "INSERT INTO Cars VALUES(1, 'Audi', 52642);" 
            "INSERT INTO Cars VALUES(2, 'Mercedes', 57127);" 
            "INSERT INTO Cars VALUES(3, 'Skoda', 9000);" 
            "INSERT INTO Cars VALUES(4, 'Volvo', 29000);" 
            "INSERT INTO Cars VALUES(5, 'Bentley', 350000);" 
            "INSERT INTO Cars VALUES(6, 'Citroen', 21000);" 
            "INSERT INTO Cars VALUES(7, 'Hummer', 41400);" 
            "INSERT INTO Cars VALUES(8, 'Volkswagen', 21600);";
```

이 쿼리들은 `Cars` 테이블을 만들고 데이터를 넣습니다. 꼭 세미콜론으로 구분해줘야 합니다.

```c
rc = sqlite3_exec(db, sql, 0, 0, &err_msg);
```

**`sqlite3_exec()`** 함수는 sqlite3_prepare_v2(), sqlite3_step(), sqlite3_finalize() 세 함수를 묶은 편리한 함수입니다. 적은 코드로 쿼리를 실행할 수 있게 도와줍니다.

이 함수의 세 번째 인자는 쿼리 결과로 출력되는 행 하나마다 호출되는 콜백입니다. 네 번째 인자는 그 콜백의 첫 번째 인자입니다. 역시 필요 없으면 0을 넘겨주면 됩니다. 마지막 인자는 오류가 발생했을 경우 할당된 오류 메세지를 가리킬 포인터입니다.

```c
sqlite3_free(err_msg);
```

할당된 오류 메세지는 **`sqlite3_free()`** 함수로 해제해줘야 합니다.

```bash
sqlite> .mode column  
sqlite> .headers on
```

프로그램을 실행하고 sqlite3 툴로 돌아가서, 우리가 원하는 대로 동작했는지 확인해 보겠습니다. 컬럼(column) 모드를 출력 모드로 사용하고, 헤더를 사용하도록 설정합니다. (차이가 궁금하다면 설정하지 않았을 때와 비교해보세요.)

```bash
sqlite> SELECT * FROM Cars;
Id          Name        Price     
----------  ----------  ----------
1           Audi        52642     
2           Mercedes    57127     
3           Skoda       9000      
4           Volvo       29000     
5           Bentley     350000    
6           Citroen     21000     
7           Hummer      41400     
8           Volkswagen  21600 
```

우리가 쿼리로 입력한 데이터들이 출력되네요!

# **마지막으로 넣은 행의 ID**

---

가끔, 마지막으로 넣은 행의 ID를 구해야하는 경우가 있습니다. 이를 위해서는, **`sqlite3_last_insert_rowid()`** 함수를 사용하면 됩니다.

```c
#include <sqlite3.h>
#include <stdio.h>

int main(void)
{
    sqlite3 *db;
    char *err_msg = 0;
    
    int rc = sqlite3_open(":memory:", &db);
    
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    char *sql = "CREATE TABLE Friends(Id INTEGER PRIMARY KEY, Name TEXT);"
    "INSERT INTO Friends(Name) VALUES ('Tom');"
    "INSERT INTO Friends(Name) VALUES ('Rebecca');"
    "INSERT INTO Friends(Name) VALUES ('Jim');"
    "INSERT INTO Friends(Name) VALUES ('Roger');"
    "INSERT INTO Friends(Name) VALUES ('Robert');";
        
    
    rc = sqlite3_exec(db, sql, 0, 0, &err_msg);
    
    if (rc != SQLITE_OK )
    {
        fprintf(stderr, "Failed to create table\n");
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }
    else
    {
        fprintf(stdout, "Table Friends created successfully\n");
    }
    
    int last_id = sqlite3_last_insert_rowid(db);
    printf("The last Id of the inserted row is %d\n", last_id);

    sqlite3_close(db);
    
    return 0;
}
```

`Friends` 테이블을 메모리에 생성했습니다. Id 컬럼은 자동으로 증가합니다.

```c
char *sql = "CREATE TABLE Friends(Id INTEGER PRIMARY KEY, Name TEXT);"
"INSERT INTO Friends(Name) VALUES ('Tom');"
"INSERT INTO Friends(Name) VALUES ('Rebecca');"
"INSERT INTO Friends(Name) VALUES ('Jim');"
"INSERT INTO Friends(Name) VALUES ('Roger');"
"INSERT INTO Friends(Name) VALUES ('Robert');";
```

SQLite 에서, `INTEGER PRIMARY KEY` 컬럼은 자동으로 증가합니다. `AUTOINCREMENT` 키워드도 있습니다. `INTEGER PRIMARY KEY AUTOINCREMENT` 처럼 같이 사용될 경우 약간 다른 알고리즘이 사용됩니다.

자동 증가 컬럼을 사용할 때는, 위에서 보이듯이 자동 증가 컬럼을 생략하고 다른 컬럼들의 이름만 명시해야합니다.

```c
int last_id = sqlite3_last_insert_rowid(db);
printf("The last Id of the inserted row is %d\n", last_id);
```

**`sqlite3_last_insert_rowid()`** 함수는 가장 최근에 성공적으로 추가한 행의 Id 값을 반환합니다. 실행해 보겠습니다.

```bash
$ ./last_row_id 
Table Friends created successfully
The last Id of the inserted row is 5
```

값이 출력되는 것을 확인할 수 있습니다.

# **데이터 받아오기**

---

우리는 `test.db` 데이터베이스에 몇 개의 데이터들을 넣었습니다. 아래 네 번째 예제 코드에서는 그 값을 어떻게 받아오는지 보겠습니다.

```c
#include <sqlite3.h>
#include <stdio.h>

int callback(void *, int, char **, char **);

int main(void)
{
    sqlite3 *db;
    char *err_msg = 0;
    
    int rc = sqlite3_open("test.db", &db);
    
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", 
                sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    char *sql = "SELECT * FROM Cars";
        
    rc = sqlite3_exec(db, sql, callback, 0, &err_msg);
    
    if (rc != SQLITE_OK )
    {
        fprintf(stderr, "Failed to select data\n");
        fprintf(stderr, "SQL error: %s\n", err_msg);

        sqlite3_free(err_msg);
        sqlite3_close(db);
        
        return 1;
    } 
    
    sqlite3_close(db);
    
    return 0;
}

int callback(
    void *NotUsed,
    int argc,
    char **argv, 
    char **azColName)
{    
    NotUsed = 0;
    
    for (int i = 0; i < argc; i++)
    {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    
    printf("\n");
    
    return 0;
}
```

**`SELECT * FROM Cars`**쿼리를 이용해서 `Cars` 테이블의 모든 행을 가져옵니다.

```c
int callback(void *, int, char **, char **);
```

이게 **`sqlite3_exec()`** 함수의 세 번째 인자로 전달하는 콜백의 시그니처입니다.

```c
int rc = sqlite3_open("test.db", &db);
```

`test.db` 데이터베이스를 엽니다.

```c
char *sql = "SELECT * FROM Cars";
```

우리가 사용할 쿼리를 정의합니다. `Cars` 테이블의 모든 행을 가져옵니다.

```c
rc = sqlite3_exec(db, sql, callback, 0, &err_msg);
```

**`sqlite3_exec()`** 함수로 쿼리를 실행합니다. 전달한 콜백으로 출력되는 각 행이 전달됩니다.

```c
int callback(
    void *NotUsed,
    int argc,
    char **argv, 
    char **azColName)
{    
    NotUsed = 0;
    
    for (int i = 0; i < argc; i++)
    {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    
    printf("\n");
    
    return 0;
}
```

콜백의 첫 번째 인자는 `sqlite3_exec()` 함수의 네 번째 인자로 넘긴 값입니다. 특별한 경우가 아니면 잘 사용되지 않습니다. 두 번째 인자는 출력되는 행의 컬럼 개수입니다. 세 번째 인자는 각 컬럼의 값입니다. 네 번째 인자는 각 컬럼의 이름입니다.

콜백 안의 코드를 보면, 모든 컬럼을 순회하며 이름과 값을 출력하는 것을 볼 수 있습니다.

```bash
$ ./select_all 
Id = 1
Name = Audi
Price = 52642

Id = 2
Name = Mercedes
Price = 57127

Id = 3
Name = Skoda
Price = 9000
...
```

프로그램 출력의 일부분입니다.

# **포맷 형태로 사용하는 쿼리**

---

(역자주: 영어로 Parameterized queries 라고 하지만, C언어 사용자들이 `printf("value: %d", i)` 같은 포맷 스트링 사용에 익숙하므로 “포맷 형태로 사용하는 쿼리”라고 표현하면 이해가 쉬울 것 같아 의역하였습니다.)

이제 다섯 번째 코드 예제에서는 포맷 형태로 사용하는 쿼리를 알아보겠습니다. “준비된 쿼리” 라고도 이야기하는 이 쿼리는 보안과 성능을 향상시킵니다. 이 쿼리를 사용할 때에는 값을 직접 적어주는 대신 물음표를 포맷 스트링으로 사용합니다.

```c
#include <sqlite3.h>
#include <stdio.h>

int main(void)
{
    sqlite3 *db;
    char *err_msg = 0;
    sqlite3_stmt *res;
    
    int rc = sqlite3_open("test.db", &db);
    
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    char *sql = "SELECT Id, Name FROM Cars WHERE Id = ?";
        
    rc = sqlite3_prepare_v2(db, sql, -1, &res, 0);
    
    if (rc == SQLITE_OK)
    {
        sqlite3_bind_int(res, 1, 3);
    }
    else
    {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    int step = sqlite3_step(res);
    
    if (step == SQLITE_ROW)
    {
        printf("%s: ", sqlite3_column_text(res, 0));
        printf("%s\n", sqlite3_column_text(res, 1));
    } 

    sqlite3_finalize(res);
    sqlite3_close(db);
    
    return 0;
}
```

위 예제 쿼리 안의 물음표(?) 문자는 런타임에 실제 값으로 변경됩니다.

```c
char *sql = "SELECT Id, Name FROM Cars WHERE Id = ?";
```

예를 들면 이 물음표는 쿼리에 Id 값을 전달하기 위해 사용됩니다.

```c
rc = sqlite3_prepare_v2(db, sql, -1, &res, 0);
```

먼저 **`sqlite3_prepare_v2()`** 함수로 준비된(prepared) 쿼리를 만들고,

```c
sqlite3_bind_int(res, 1, 3);
```

**`sqlite3_bind_int()`** 함수로 정수 값을 물음표에 연결(bind)합니다. 이러면 이후에 쿼리가 실행될 때, 물음표가 정수값 ‘3’으로 해석됩니다. 함수의 두 번째 인자는 1부터 시작하는 물음표의 번호이고, 세 번째 인자가 값입니다.

(역자주: SELECT * FROM table WHERE Id = ? AND Name = ? 처럼 물음표가 여러 개이면 맨 앞의 Id = ?의 물음표가 1번, Name = ?의 물음표가 2번 입니다.)

```c
int step = sqlite3_step(res);
```

**`sqlite3_step()`** 함수로 쿼리를 실행합니다.

```c
if (step == SQLITE_ROW)
{
    printf("%s: ", sqlite3_column_text(res, 0));
    printf("%s\n", sqlite3_column_text(res, 1));
} 
```

출력할 행이 있다면(반환 값이 SQLITE_ROW라면), **`sqlite3_column_text()`** 함수를 이용해 두 컬럼의 값을 받습니다.

(역자주: 첫 번째 인자는 쿼리 핸들, 두 번째 인자는 0부터 시작하는 컬럼 번호입니다.)

```bash
$ ./parameterized 
3: Skoda
```

예제는 Id 와 차의 이름을 출력했습니다.

(역자주: 다른 타입들에 사용하는 `sqlite3_bind*` 함수와 `sqlite3_column*` 함수들은 SQLite 공식 API 문서에서 찾아볼 수 있습니다. [bind](https://www.sqlite.org/c3ref/bind_blob.html) [column](https://www.sqlite.org/c3ref/column_blob.html))

다음 여섯 번째 코드 예제에서는 물음표 대신 이름이 있는 포맷 스트링을 사용하는 방법을 알아보겠습니다.

```c
#include <sqlite3.h>
#include <stdio.h>

int main(void)
{
    sqlite3 *db;
    char *err_msg = 0;
    sqlite3_stmt *res;
    
    int rc = sqlite3_open("test.db", &db);
    
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        
        return 1;
    }
    
    char *sql = "SELECT Id, Name FROM Cars WHERE Id = @id";
        
    rc = sqlite3_prepare_v2(db, sql, -1, &res, 0);
    
    if (rc == SQLITE_OK)
    {
        int idx = sqlite3_bind_parameter_index(res, "@id");
        int value = 4;
        sqlite3_bind_int(res, idx, value);
    }
    else
    {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    int step = sqlite3_step(res);
    
    if (step == SQLITE_ROW)
    {
        printf("%s: ", sqlite3_column_text(res, 0));
        printf("%s\n", sqlite3_column_text(res, 1));
    } 

    sqlite3_finalize(res);
    sqlite3_close(db);
    
    return 0;
}
```

이번 쿼리는 물음표 대신 아래와 같은 이름이 있는 포맷 스트링을 사용합니다.

```c
char *sql = "SELECT Id, Name FROM Cars WHERE Id = @id";
```

이름이 있는 포맷 스트링을 사용하려면 포맷 스트링의 이름 앞에 콜론(:) 혹은 골뱅이(@) 기호를 붙입니다.

```c
int idx = sqlite3_bind_parameter_index(res, "@id");
```

그리고 **`sqlite3_bind_parameter_index()`** 함수를 사용해 이름에 해당하는 포맷 스트링 번호를 얻습니다. 이후부터는 이전 예제와 똑같이 **`sqlite3_bind*`** 류의 함수를 사용해 값을 연결해주면 됩니다.

# **마치며**

---

이렇게 SQLite 데이터베이스를 C/C++ 언어로 사용하는 기본적인 방법에 대해 알아보았습니다!

저도 이전까지는 “데이터베이스” 하면 서버도 필요하고, 설치해서 계정도 생성하고, 이런 저런 어려운 세팅을 마친 뒤 포트를 열어서.. 어쩌구저쩌구… 하는 아주 복잡한 것이라고 생각했습니다. 그래서 C/C++ 프로젝트에서는 사용하기가 힘들거라고 섣불리 단정짓고 있었죠. 그런데 이번에 SQLite 데이터베이스를 사용해보며 여태까지 지레 겁먹고 있었을 뿐이라는 걸 깨달았습니다!

SQLite 는 아말감화 버전을 이용하면 별도의 라이브러리 설치나 프로젝트 세팅도 필요 없습니다. C언어 소스파일 sqlite3.c 하나와 헤더파일 sqlite3.h 하나만 압축파일에서 꺼내놓으면 끝입니다. 설치도, 계정생성도, 서버구축도 포트설정도 아무 것도 필요가 없습니다. 그저 데이터베이스를 열고, 쓰고, 닫으면 끝입니다. 와우!

프로그래밍을 하다보면 꽤 복잡한 데이터들을 디스크에 저장해야 할 때가 있습니다. 그럴 때마다 어떤 방식으로 저장할지, 오류나 프로그램 종료시에는 어떻게 대처할지 고민이 앞섰습니다. 그런데 이제는 큰 고민 없이 SQLite 데이터베이스를 사용하게 될 것 같네요!

글의 첫 부분에 언급했다시피 이 글은 2: 심화 편에서 이어집니다. 1편 내용만으로도 SQLite 데이터베이스를 사용하는데는 아무런 문제가 없습니다. 심화 편에서는 텍스트, 정수가 아닌 이미지 같은 blob 데이터를 다루는 법, 메타 데이터, 트랜잭션 같은 심화적인 내용을 다룹니다.

# **==========심화==========**

# SQLITE VIEW

모바일을 위한 요구사항을 만족시키기 위한 데이터베이스 솔루션으로 대표적인 것이 SQLite이다. SQLite는 구글의 안드로이드나 애플의 아이폰 등에서 사용되는 솔루션으로 사용자 측면과 관리자 측면에서 모두 뛰어난 접근성을 가지고 있는 데이터베이스 관리 시스템이다. SQLite에 대해서 지금까지 세 가지 이야기를 공유하였다. 본 네 번째 이야기에서는 SQLite에서 제공하는 SQL문들에 대해서 소개하도록 한다.

뷰(View)는 질의에 대한 결과 세트로 구성된 가상 테이블에 접근할 수 있는 저장 질의로 구성되어 있다. 뷰는 다른 테이블들에서 유도되었으며, 관계형 데이터베이스의 일반적인 기본 테이블과 달리 뷰는 물리적인 스키마의 형태를 가지지 않는다. 이는 데이터베이스 내의 데이터들로부터 계산되거나 병합된 가상 테이블의 형태를 가지고 있으며, 동적인 특성을 가지고 있다. 테이블 내에서 데이터가 변경되면, 뷰에서도 변경이 이루어진다.
뷰는 테이블에 비해서 다음과 같은 특성을 가지고 있다.

● 뷰는 하나 이상의 기본 테이블로부터 유도되어 생성되는 가상 테이블이다. 따라서, 뷰위에 다른 뷰를 정의할 수 있고, 정의된 기본 테이블이 삭제되면, 뷰도 자동으로 삭제된다.
● 뷰는 테이블이 포함하고 있는 데이터의 서브 세트를 나타낼 수 있다.
● 뷰는 여러 개의 테이블들이 하나의 가상 테이블로 Join될 수 있다.
● 뷰는 집합 테이블로서 동작할 수 있고, 데이터베이스 엔진은 데이터를 모으고 연산된 결과 값을 나타낼 수 있다.
● 뷰는 데이터의 복잡성을 감출 수 있다.
● 뷰는 논리적인 독립성을 제공한다.
● 뷰는 데이터 접근 제어를 통해서 외부에 대한 보안을 제공할 수 있다.
● 뷰는 테이블이 외부에 보여지는 정도를 제한할 수 있다.
● 뷰의 정의는 ALTER문을 이용하여 변경이 불가능하다.

뷰는 함수와 같은 추상성을 제공할 수 있기 때문에, 데이터베이스 사용자들은 뷰를 사용해서 추상화를 진행할 수 있다. 질의를 위해서 뷰는 다음과 같은 두 가지 방법을 사용할 수 있다.

## 쿼리

- **질의를 위한 뷰 명령어의 일반적인 형태**

```sqlite
CREATE [TEMP|TEMPORARY] VIEW [IF NOT EXISTS] [database.] view-name AS select-stmt
```

CREATE VIEW 명령어를 사용하여 사용자는 SELECT 문에 이름을 할당할 수 있다. 만약에 "CREATE" 와 "VIEW" 사이에 "TEMP" 또는 "TEMPORARY" 키워드가 존재하면 뷰는 데이터베이스가 오픈되었을 때 같이 오픈되고, 데이터베이스가 종료될 때 같이 종료된다. 만약에 데이터베이스 이름이 지정되면, 뷰는 그 이름을 가지고 생성된다. 만약에 데이터베이스 이름이 지정되지 않고, TEMP 키워드가 존재하지 않으면, VIEW는 주 데이터베이스로 생성된다. 사용자는 뷰를 삭제, 추가, 업데이트할 수 없다. 뷰는 SQLite에서는 읽기 전용이며, 삭제, 추가, 업데이트를 수행하기 위해서는 뷰에서 INSTEAD OF 트리거를 사용하도록 한다. 뷰의 삭제는 DROP VIEW 명령어를 사용하여 가능하다.

- **가상 테이블 명령어의 일반적인 형태**

```sqlite
CREATE VIRTUAL TABLE [database-name.] table-name USING module-name [ module-argument]
```

가상 테이블은 외부 저장매체 또는 연산엔진에 대한 인터페이스이며, 실제로 데이터베이스 파일에 정보를 저장하지는 않는다. 가상 테이블에 인덱스를 생성하거나 트리거를 생성할 수 없는 것을 제외하고 일반 테이블에서 할 수 있는 것들을 그대로 적용 가능하다. 가상 테이블은 읽기 전용이다.
CREATE VIRTUAL TABLE의 모듈명은 가상 테이블의 오브젝트의 이름이 사용되며, CREATE VIRTUAL TABLE의 문이 수행되기 이전에 sqlite3_create_module() 또는 sqlite3_create_module_v2()을 이용하여 SQLite 데이터베이스 연결을 통해서 등록되어 있어야만 한다. SQLite에서 가상 테이블은 DROP VIRTUAL TABLE이 아닌 일반 DROP TABLE문을 사용하여 삭제된다.

DELETE 명령어는 테이블에서 레코드들을 삭제하기 위해서 사용된다. DELETE FROM 키워드로 구성된 명령어는 삭제될 레코드의 테이블 이름을 가져야 한다. WHERE 절 없이 테이블의 모든 행은 삭제될 수 있다. 만약에 WHERE 절이 사용되면 표현식에 해당하는 행들만이 삭제된다.

- **DELETE**

```sqlite
DELETE FROM [database-name.] table-name [INDEXED BY index-name]|[NOT INDEXED] [WHERE expr]
```

DELETE문에서의 추가적인 문법 제약은 CREATE TRIGGER문을 통해서 이루어진다. 테이블 이름에서 database-name.prefix는 트리거 내에서는 허용되지 않는다. 삭제될 테이블은 트리거가 ATTACH된 테이블과 동일한 데이터베이스에 존재해야 한다. INDEXED BY와 NOT INDEXED 절은 트리거 내의 DELETE문에서는 허용되지 않으며, LIMIT 절도 트리거에서는 지원되지 않는다. WHERE가 DELETE문에서 생략되고, 삭제될 테이블이 트리거를 가지지 않으면, SQLite는 전체 테이블의 내용을 삭제하기 위해서 테이블의 각 행을 각각 살펴보지 않고 삭제 최적화를 수행한다. 버전 3.6.5 이전에서는 Truncate 동작의 최적화는 sqlite3_changes() 와 sqlite3_total_changes() 인터페이스를 의미하며, count_changes pragma는 삭제된 행들의 개수를 실제로 반환하지 않는다. 이러한 현상은 버전 3.6.5부터 수정되었다. Truncate 동작의 최적화를 비활성화하기 위해서는 컴파일 할 때는 SQLITE_OMIT_TRUNCATE_OPTIMIZATION 옵션을 사용하면 되고, 런타임 시에는 sqlite3_set_authorizer() 인터페이스를 사용하여 비활성화될 수 있다. LIMIT를 활성화하기 위해서는 컴파일 시에 SQLITE_ENABLE_UPDATE_DELETE_LIMIT를 사용하면 되며, DELETE문에 대한 문법은 ORDER BY와 LIMIT절을 사용하여 확장될 수 있다.

- **DELETE + LIMIT**

```sqlite
DELETE FROM qualified-table-name [WHERE expr] [ORDER BY ordering-term LIMIT integer [OFFSET integer]]
```

LIMIT 절은 삭제될 행의 수와 트랜잭션 수를 제한하기 위해서 사용된다.

DETACH DATABASE문은 ATTACH 문을 사용하여 형성된 추가 데이터베이스 연결을 분리하기 이위해서 사용된다. 다른 이름으로 동일한 데이터베이스 파일의 여러 번 연결할 수 있고 그 연결을 반대로 분리도 가능하다. 이 DETACH DATABASE문은 다음과 같이 사용된다.
DETACH [DATABASE] database-name

이 문장은 만약에 트랜잭션 중간에서 사용되면 실패하게 되므로 주의하도록 한다.

DROP INDEX는 CREATE INDEX문에 의해서 추가된 인덱스를 제거하기 위해서 사용된다. DROP INDEX가 사용된 이후에 인덱스는 디스크로부터 삭제되며, 복구할 수 있는 유일한 방법은 CREATE INDEX 명령어를 다시 사용하는 것이다. DROP INDEX문은 다음과 같이 사용된다.

- **DROP INDEX**

```sqlite
DROP INDEX [IF EXISTS] [database-name.] index-name
```

- **DROP TABLE**

```sqlite
DROP TABLE [IF EXISTS] [database-name.] index-name
```

DROP TABLE은 CREATE TABLE문으로 추가된 테이블을 삭제하는데 사용된다. 테이블 이름이 인자로 주어져야 한다. 삭제된 테이블은 데이터베이스 스키마와 디스크 파일에서 완전히 삭제되고 복구되지 않는다. 테이블과 관련된 모든 인덱스들과 트리거들도 역시 삭제된다. 만약에 외래 키 조건이 활성화되어 있다면, DROP TABLE 명령은 테이블이 데이터베이스 스키마에서 삭제되기 이전에 DELETE FROM 명령어를 수행한다. 테이블의 트리거는 DELETE FROM 이 수행되기 이전에 데이터베이스 스키마에서 삭제되기 때문에 트리거 동작은 안 일어난다.
DROP TRIGGER문은 다음과 같다.

- **DROP TRIGGER**

```sqlite
DROP TRIGGER [IF EXISTS] [database-name.] trigger-name
```

DROP TRIGGER문은 CREATE TRIGGER문에 의해서 생성된 트리거를 삭제한다. 트리거는 데이터베이스 스키마에서 삭제된다. 트리거는 관련된 테이블이 삭제되면 그에 따라서 같이 삭제된다.

DROP VIEW문은 다음과 같다.

- **DROP VIEW**

```sqlite
DROP VIEW [IF EXISTS] [database-name.] view-name
```

DROP VIEW문은 CREATE VIEW문에 의해서 생성된 뷰를 삭제한다. 뷰는 데이터베이스 스키마에서 삭제되지만, 테이블안의 실제 데이터는 삭제되지 않는다.

INDEXED BY는 DELETE, SELECT 또는 UPDATE 문에서 인덱스들이 제대로 사용되는지를 확인하기위해서 사용된다. INDEXED BY절은 SQLite가 읽는 테이블의 이름을 사용하며, 다음과 같은 형태로 사용된다.

- **INDEXED BY**

```sqlite
DELETE [database-name.] table-name [INDEXED BY index-name]|[NOT INDEXED]
SELECT [database-name.] table-name [INDEXED BY index-name]|[NOT INDEXED]
UPDATE [database-name.] table-name [INDEXED BY index-name]|[NOT INDEXED]
```

"INDEXED BY index-name"에서 만약에 인덱스 이름이 존재하지 않거나 질의에 의해서 사용될 수 없다면 SQL 문에서 prepare는 실패하게 된다. "NOT INDEXED"에 의해서 UNIQUE와 PRIMARY KEY 조건에 의해서 생성되는 인덱스들을 포함하여 테이블에 액세스하는 인덱스가 없음을 나타낼 수 있다. 그렇지만, "NOT INDEXED"가 지정되어 있더라도 INTEGER PRIMARY KEY에 의해서 개체들을 검색하는 것은 가능하다.
INSERT는 행을 하나 추가하기 위해서 사용된다. 이 명령어의 일반적인 형태는 다음과 같다.

- **SELECT**

```sqlite
INSERT [OR [ROLLBACK|ABORT|REPLACE|FAIL|IGNORE]]}|REPLACE INTO database-name.table-name {(column-name) VALUE (expr)}|{DEFAULT VALUE}
```

INSERT문은 다음과 같은 3가지 형태를 가진다.

1. `VALUES` 키워드를 가지고 테이블 내에 새로운 행을 하나 생성하는 형태이다. 만약에 column-list가 지정되지 않으면, 테이블 내의 칼럼 수와 동일한 값을 가지며, column-list가 지정되었다면 지정된 칼럼의 수와 일치하는 지를 확인한다.
2. `INSERT`문은 SELECT문에서 데이터를 취하는 형태이다. SELECT 결과의 칼럼 수는 테이블의 칼럼수와 일치해야 한다. 새로운 개체의 생성은 SELECT 결과로 얻어지는 각 행이 속하는 테이블에 이루어진다.
3. `DEFAULT VALUES`을 가지는 형태이다. 'INSERT ... DEFAULT VALUES'문은 테이블에 새로운 행을 하나 생성하고 기본 값으로 채운다.

테이블 명에서 선택사양으로 사용되는 "database-name." 접두어는 탑 레벨의 INSERT문에서만 사용된다. 테이블 이름은 CREATE TRIGGER 문의 INSERT문에는 사용되지 않는다. 이와 마찬가지로 INSERT문의 "DEFAULT VALUES"도 탑 레벨의 INSERT문에서는 지원되지만, 트리거 내의 INSERT에서는 적용되지 않는다.
ON CONFLICT절은 독립적인 SQL 명령어가 아니며, 표준도 아니다. ON CONFLICT 절에 대한 문법은 CREATE TABLE 명령어와 같이 사용되며, INSERT와 UPDATE 명령어에서 "ON CONFLICT"는 "OR" 대신에 사용될 수 있다. 즉, "INSERT ON CONFLICT IGNORE" 대신에 "INSERT OR IGNORE"가 사용될 수 있다. ON CONFLICT는 조건 충돌을 해소하기 위해서 사용되는 알고리즘이다. 여기서는 5가지 충돌에 대한 해결 알고리즘을 나타내었다. 기본 충돌 해결 알고리즘은 ABORT이다.

> - **`ROLLBACK`**
>
> 제약조건 위반이 발생하면, 즉각 ROLLBACK이 발생하고, 현재 트랜잭션이 종료되고, 수행하던 명령도 SQLITE_CONSTRAINT 반환 값을 가지고 중단된다. 만약에 트랜잭션이 수행되는 것이 아니라면, 이 알고리즘은 ABORT와 동일하다.
>
> - **`ABORT`**
>
> 제약조건 위반이 발생하면, 수행 명령어는 이전의 변경사항들을 모두 취소하고 SQLITE_CONSTRAINT 반환 값을 가지고 중단한다. ROLLBACK이 이루어지지 않기 때문에 트랜잭션 내에서 이전 명령어로 수행된 변경사항들은 그대로 유지된다.
>
> - **`FAIL`**
>
> 제약조건 위반이 발생하면, 수행 명령어는 SQLITE_CONSTRAINT 반환 값을 가지고 중단한다. 조건 위반이 일어나기 이전에 수행되었던 명령어의 변경사항들은 되돌려지지 않는다.
>
> - **`IGNORE`**
>
> 제약조건 위반이 발생하면, 조건위반을 포함하는 행은 추가되지 않고, 변경되지도 않는다. 그러나 명령어는 계속 진행된다. 조건 위반을 포함하는 행의 앞/뒤로 위치하는 다른 행들은 정상적으로 추가되거나 업데이트된다. IGNORE 충돌 해결 알고리즘이 사용되면, 에러는 발생하지 않는다
>
> - **`REPLACE`**
>
> UNIQUE 조건 위반이 발생하면, 이전에 존재하는 위반의 원인이 되는 행들은 현재 행을 추가하거나 업데이트하기 이전에 삭제된다. 따라서 삽입이나 업데이트는 항상 발생한다. REPLACE에 의해서 반환되는 에러는 없다. 만약에 NOT NULL 조건 위반이 발생하면, NULL 값이 칼럼 기본 값을 대치한다. 만약에 칼럼이 기본 값이 없다면, ABORT 알고리즘이 사용된다. 만약에 CHECK 조건 위반이 발생하면, IGNORE 알고리즘이 사용된다. 이 알고리즘은 INSERT 또는 UPDATE의 OR 절에서 지정되며, CREATE TABLE에서 지정된 알고리즘을 대체할 수 있다.

SELECT문은 데이터베이스에 질의하기 위해서 사용된다. SELECT의 결과는 하나 이상의 행 데이터이며, 각 행은 고정된 길이의 칼럼을 가진다. 결과 내에서 칼럼의 수는 SELECT와 FROM 키워드 사이의 표현식에 의해서 지정될 수 있다. 결과를 얻기 위해서 사용자는 자신이 원하는 표현식을 넣으면 된다. 만약에 표현식이 ‘*’이라면, 모든 테이블의 모든 칼럼이 해당하고, 테이블과 ‘.*’이 사용되면 한 테이블의 모든 칼럼을 의미한다. DISTRICT 키워드는 결과 행들의 서브 세트가 반환되며, 각 결과 행은 각기 다른 값을 가진다. NULL 값은 여러 개가 존재하더라도 각각 다른 독립적인 값으로 여겨지지 않는다. SELECT 문장에서 FROM절에서는 대상 테이블이, WHERE절에는 조인과 셀렉션 조건이 기술된다. SELECT문은 다음과 같은 형태로 사용된다. SELECT문의 가장 중요한 역할을 담당하??이 정의될 수 있다.

- **SELECT**

```sqlite
SELECT [DISTINCT|ALL] result-colum [FROM join-source]|[WHERE expr]|[GROUP BY ordering-term HAVING expr] [ORDER BY ordering-term]|[LIMIT integer OFFSET integer]
```

SELECT문에서는 다음과 같은 절들이 사용된다.

> - **`FROM`** : 데이터를 가지고 올 테이블의 이름 지정
> - **`WHERE`** : 행수준의 필터링 수행하여 원하는 값만 추출
> - **`GROUP BY`** : 그룹 지정
> - **`HAVING`** : 그룹 수준의 필터링
> - **`ORDER BY`** : 결과 정렬

SELECT 명령어를 사용하여 질의할 때, FROM 키워드 뒤에 하나 이상의 테이블을 사용할 수 있다. 만약에 콤마에 의해서 여러 개의 테이블 이름으로 구분되면, 질의는 여러 가지 테이블의 크로스 조인에 의해서 이루어진다. 크로스 조인은 곱집합을 결과로 반환하는 조인을 의미한다. FROM절의 테이블 이름 위치를 대신하여 괄호를 사용한 서브 쿼리(질의)가 이루어질 수 있다. 이 절에서는 추출하고자 하는 데이터가 저장되어 있는 테이블을 설정할 수 있다. WHERE절은 질의가 수행될 때 행의 수를 제한하기 위해서 사용될 수 있다. 그리고, WHERE절에 SELECT절이 포함된 서브 쿼리를 사용한 검색을 수행할 수 있고, 최종 검색 속성이 하나의 테이블에서 나올 수 있다.

`GROUP BY`절은 대상 테이블에 지정된 열의 이름 값에 따라서 행을 그룹으로 나눌 수 있다. 그렇지만, 그 결과가 그룹 순서대로 되지는 않는다. 또한, 이는 결과가 집단 함수를 포함하고 있을 때 유용하다.

`HAVING`절은 각 그룹에 대한 조건을 나타낸다. HAVING절은 GROUP BY절과 같이 존재한다. HAVING절은 WHERE절과 유사하지만, 앞서서 언급한 내용 이외에 다음과 같은 차이가 있다. WHERE절은 데이터가 그룹화되기 이전에 필터링되지만, HAVING절은 데이터가 그룹화한 이후에 필터링된다. WHERE절에 의해서 필터링된 행은 그룹에 포함되지 않는다. 따라서, WHERE절을 사용하여 대상을 설정하고, GROUP BY절로 그룹화시킨 이후에 HAVING절로 필터링할 수 있다. 

`ORDER BY`절은 최종 검색 결과의 정렬을 수행한다. 선택하지 않은 열을 포함하여 모든 열을 사용하며, 이 절이 반드시 필요한 것은 아니다. ORDER BY절은 정렬을 위해서 사용하는 키를 인자로서 사용한다. 만약에 ORDER BY 표현식이 정수형 K이면, K번째 칼럼에 의해서 결과는 정렬되고, 동일한 이름을 가지는 출력 칼럼의 하나이며 구분자가 표현식으로 사용되면 출력은 구분 칼럼에 의해서 정렬된다. 그 외의 경우에는 출력은 표현식 값에 의해서 정렬된다. SELECT절과 FROM절 안에 SELECT절과 FROM절이 또 다시 존재할 수 있기 때문에 중첩조회가 가능하고, WHERE절에 또 다른 SELECT절과 FROM절이 존재할 수 있다. 이 두 절은 조회를 수행하기 위해서는 반드시 필요한 절들이다.

- **UPDATE**

UPDATE문은 테이블의 선택된 행들에서 칼럼 값을 변경하기 위해서 사용한다. UPDATE문에 있는 WHERE절은 수정할 행을 선택한다. 기본 키 값이 변경되었을 때 데이터의 정의어에서 참조 무결성 조건 내에 참조 트리거 동작이 명시되어 있으면, 다른 테이블에 있는 행의 외래 키 값도 변경된다. UPDATE문에서 ‘=’의 왼쪽은 칼럼의 이름이 들어가고 오른쪽은 표현식이 사용된다. WHERE 절은 행들이 업데이트될 때의 조건을 제약하기 위해서 사용된다.
UPDATE 문은 다음과 같다.

```sqlite
UPDATE [OR ROLLBACK|ABORT|REPLACE|FAIL|IGNORE] qualified-table-name SET column-name=expr WHERE expr
```

여기서 사용되는 qualified-table-name는 다음과 같이 정의된다.

```sqlite
database.table-name [INDEXED BY index-name]|[NOT INDEXED]
```

UPDATE문에는 추가적인 문법 제약이 CREATE TRIGGER문을 통해서 이루어질 수 있다. UPDATE의 테이블 이름에서 database-name. 접두어는 트리거 내에서는 허용되지 않는다. 업데이트될 테이블은 트리거가 사용되는 테이블과 동일한 데이터베이스 내에 존재해야 한다. INDEXED BY와 NOT INDEXED절은 트리거 내의 UPDATE 문에서는 허용되지 않으며, LIMIT절은 트리거 내에서는 지원되지 않는다. 그리고, 단일 UPDATE 명령을 사용하여 여러 개의 행을 수정할 수도 있다. 칼럼의 새로운 값으로 NULL 값이나 기본 값을 명시할 수 있다. 만약에 여러 개의 테이블을 업데이트할려면 그 수만큼 UPDATE 명령이 수행되어야 한다.
지금까지 SQLite에서 지원하는 SQL문들에 대해서 간략히 살펴보았다. 이를 통해서 SQL문을 사용하는데 있어서 도움이 되길 바란다.