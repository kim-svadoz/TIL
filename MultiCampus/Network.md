# Network

## 20-04-22 수

TCP ? UDP? 프로토콜?

프로토콜 : 약속

TCP통신을 할거야 우린~ => 연결지향 : 데이터를 보내고 잘 갔는지 확인하고 제대로 안갔으면 다시 확인

네트워크프로그래밍 : 서버가 있어야 해요~  pc&pc연결 ~ => 서버와 클라이언트를 만들어야 한다.

어떤식으로 데이트를 받고 데이터를 처리해야하겠다라고 명시해놓은 pc가 있어야한다.(서버)

요청을하는pc(클라이언트)

우리는 그동안 서버역할을 하는 tomcat(웹)이나 oracle(DB)을 설치해서 사용했다.

> 자바에서 "쓰레드", "네트워크", "IO"에 대한 기본 개념은 필수이다!



  << 실습 >>

URLTest2.java

- URl객체를 생성
  - image copy
  - image폴더에 저장

---

## 20-04-27 월

### TCP통신

> 서버는 서버의 역할을 하기 위해 소켓 객채를 생성한 후 클라이언트로부터 들어오는 데이터를 받기 위해 포트를 열어놓고 대기해야 한다. ( Servet Socket )
>
> 클라이언트는 서버의 IP와 포트의 정보를 알아야 한다. ( Socket )
>
> - 소켓 끼리의 통신하는 것이다 !!

### 서버와 클라이언트가 1대1로 채팅을 할 수 있도록 구현하기

- ConsoleChatServer.java : 서버
- ConsoleChatClient.java : 클라이언트

> 클라이언트와 서버의 input/output을 쓰레드로 구현

- ClientSenderThread
- ClientReceiveThread
- ServerSenderThread
- ServerReceiveThread

```java
package single.console.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientSenderThread extends Thread{
	Socket socket;
	
	public ClientSenderThread(Socket socket) {
		super();
		this.socket = socket;
	}

	//키보드로 입력한 내용을 서버로 보내는 작업
	public void run() {
		PrintWriter out= null;
		BufferedReader keyin = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyin = new BufferedReader(new InputStreamReader(System.in));
			String sendMsg ="";//서버로 보낼 메시지
			while(true) {
				sendMsg = keyin.readLine();
				if(sendMsg ==null) break;
				out.println(sendMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//서버가 전송하는 데이터를 클라이언트에서 읽는 작업을 수행하는 쓰레드
class ClientReceiveThread extends Thread{
	Socket socket;
	
	public ClientReceiveThread(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String resMsg = "";
			while(true) {
				resMsg = in.readLine();
				if(resMsg==null) break;
				System.out.println("서버>>"+resMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//서버에서 클라이언트로 데이터를 전송하는 작업을 수행하는 쓰레드
class ServerSenderThread extends Thread{
	Socket socket;
	
	public ServerSenderThread(Socket socket) {
		super();
		this.socket = socket;
	}

	//키보드로 입력한 내용을 서버로 보내는 작업
	public void run() {
		PrintWriter out= null;
		BufferedReader keyin = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyin = new BufferedReader(new InputStreamReader(System.in));
			String sendMsg ="";//클라이언트로 보낼 메시지
			while(true) {
				sendMsg = keyin.readLine();
				if(sendMsg ==null) break;
				out.println(sendMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//서버에서 클라이언트가 전송하는 데이터를 읽는 작업을 수행하는 쓰레드
class ServerReceiveThread extends Thread{
	Socket socket;
	
	public ServerReceiveThread(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String resMsg = "";
			while(true) {
				resMsg = in.readLine();
				if(resMsg==null) break;
				System.out.println("클라이언트>>"+resMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

```

```java
public class ConsoleChatServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(12345);		
			while(true) { // 1:1 통신이므로 While 하나만 있어도 된다.
				Socket socket = server.accept();
				InetAddress clientInfo = socket.getInetAddress();
				System.out.println("접속한 클라이언트:"+clientInfo.getHostAddress());
				ServerSenderThread senderThread = new ServerSenderThread(socket);
				senderThread.start();
				new ServerReceiveThread(socket).start();
			}			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

```java
package single.console.chat;

//바이너리데이터를 읽고 쓰지 않는 경우 - 문자열
public class ConsoleChatClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("70.12.115.65", 12345);
			new ClientReceiveThread(socket).start();
			new ClientSenderThread(socket).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```



- 안드로이드에서 별도의 네트워크에 관한 모든 작업은 쓰레드로 처리해야 한다.
- **확인!!** 안드로이드에서 DB를 이용해서 작업하려면 JSON으로 데이터를 받은후 작업해야 한다!!



## 20-05-07 목

### 멀티채팅 구현하기

- 클라이언트가 접속하면, 클라이언트와 통신할 수 있는 실행흐름이 쓰레드로 돌고있음.
- 각각의 유저들이 자신들의 독립적인 실행 흐름을 구현해야 하기 때문에 User객체를 이용.
