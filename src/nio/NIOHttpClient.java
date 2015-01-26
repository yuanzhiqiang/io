package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOHttpClient {

	public static void main(String[] args) {
		System.out
				.println(getDomain("http://api.customer.liequ.idc/memberlq/api/customer/updateUserInfo.aj"));
		System.out
				.println(getPath("http://api.customer.liequ.idc/memberlq/api/customer/updateUserInfo.aj"));
	}

	private static void nioHttprocess(String url) {
		try {
			SocketChannel sc = SocketChannel.open();
			sc.configureBlocking(false);
			Selector selector = Selector.open();
			if (sc.connect(new InetSocketAddress(getDomain(url), 80))) {
				doWrite(sc, url);
				ByteBuffer att = ByteBuffer.allocate(2 * 1024);
				sc.register(selector, SelectionKey.OP_READ, att);
			} else {
				sc.register(selector, SelectionKey.OP_CONNECT);
			}
			while (true) {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iter = keys.iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					iter.remove();
					if (key.isValid()) {
						if (key.isReadable()) {
							ByteBuffer readBuffer = ByteBuffer.allocate(1024);
							int readBytes = sc.read(readBuffer);
							if (readBytes > 0) {
								readBuffer.flip();
								byte[] bytes = new byte[readBuffer.remaining()];
								readBuffer.get(bytes);
								String body = new String(bytes, "UTF-8");
								System.out.println(body);
							} else if (readBytes < 0) {
								// 对端链路关闭
								key.cancel();
								sc.close();
								break;
							}
						}
						if (key.isConnectable()) {
							if (sc.finishConnect()) {
								ByteBuffer att = ByteBuffer.allocate(2 * 1024);
								sc.register(selector, SelectionKey.OP_READ, att);
								doWrite(sc, url);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void doWrite(SocketChannel sc, String url)
			throws IOException {
		String requestString = sendGet(url);
		ByteBuffer buffer = ByteBuffer
				.allocate(requestString.getBytes().length);
		buffer.put(requestString.getBytes());
		buffer.flip();
		sc.write(buffer);
	}

	private static String sendGet(String url) {
		StringBuffer sb = new StringBuffer("GET " + getPath(url)
				+ " HTTP/1.1\r\n");
		sb.append("Host: " + getDomain(url) + "\r\n");
		sb.append("Accept: */*\r\n");
		sb.append("Accept-Language: zh-cn\r\n");
		sb.append("Accept-Encoding: gzip, deflate\r\n");
		sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\r\n");
		sb.append("Connection: Keep-Alive\r\n\r\n");
		return sb.toString();
	}

	private static String getDomain(String url) {
		String parse = url.replace("http://", "");
		return parse.substring(0, parse.indexOf("/"));
	}

	private static String getPath(String url) {
		String parse = url.replace("http://", "");
		return parse.substring(parse.indexOf("/"),
				parse.indexOf("?") == -1 ? parse.length() : parse.indexOf("?"));
	}

	// private static String getParam(String url){
	// String parse = url.replace("http://", "");
	// return parse.substring(parse.indexOf("?"), parse.length());
	// }

}
