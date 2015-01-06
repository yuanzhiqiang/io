package bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;

public class FileDescriptorTest {

	
	public static void main(String[] args) throws Exception {
//		FileDescriptor fd = FileDescriptor.out;
//		FileOutputStream fos = new FileOutputStream(fd);
//		fos.write("hello,print".getBytes());
		writeReadTest();
	}
	
	
	public static void readWriteTest() throws Exception{
		File file = new File("/Users/yuanzq/Desktop/work/liequ/io/src/bio/file.txt");
		FileInputStream fis = new FileInputStream(file);
		FileInputStream fis1 = new FileInputStream(fis.getFD());
		FileOutputStream fos = new FileOutputStream(fis.getFD());
		int b ;
		while((b = fis.read()) != -1){
			System.out.println((byte)b);
		}
		while((b = fis1.read()) != -1){
			System.out.println((byte)b);
		}
		fos.write("testreadwrite".getBytes());
		fis.close();
		fis1.close();
		fos.close();
	}
	
	public static void writeReadTest() throws Exception{
		File file = new File("/Users/yuanzq/Desktop/work/liequ/io/src/bio/file.txt");
		FileOutputStream fos = new FileOutputStream(file);
		FileInputStream fis = new FileInputStream(fos.getFD());
		FileInputStream fis1 = new FileInputStream(fos.getFD());
		int b ;
		while((b = fis.read()) != -1){
			System.out.println((byte)b);
		}
		while((b = fis1.read()) != -1){
			System.out.println((byte)b);
		}
		fos.write("testreadwrite".getBytes());
		fis.close();
		fis1.close();
		fos.close();
	}
	
	public static void SocketFileFileDescriptorTest() throws Exception{
		ServerSocket ss = new ServerSocket(8080);
		ss.accept();
	}
}
