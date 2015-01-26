package bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception {

		Socket s = new Socket("www.javathinker.org", 80);
		OutputStream out = s.getOutputStream();
//		for(int i = 0 ;i < 10000; i++){
//			out.write(sendHttp("").getBytes());
//		}
		out.write(sendHttp("").getBytes());
		s.shutdownOutput();
		InputStream in = s.getInputStream();
		byte[] buffer = new byte[100];
		StringBuffer sb = new StringBuffer();
		while(-1 != in.read(buffer)){
			sb.append(new String(buffer));
		}
		
		System.out.println("Client excute result: " + sb);
		System.out.println("Client Finished");
		s.close();
	}
	private static String sendHttp(String url){
		StringBuffer sb=new StringBuffer("GET "+"/index.jsp"+" HTTP/1.1\r\n");
	    sb.append("Host: www.javathinker.org\r\n");
	    sb.append("Accept: */*\r\n");
	    sb.append("Accept-Language: zh-cn\r\n");
	    sb.append("Accept-Encoding: gzip, deflate\r\n");
	    sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\r\n");
	    sb.append("Connection: Keep-Alive\r\n\r\n");
		return sb.toString();
	}
}
