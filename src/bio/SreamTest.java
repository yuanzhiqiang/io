package bio;

import java.io.File;
import java.io.FileInputStream;

public class SreamTest {

	
	public static void main(String[] args) throws Exception{
		File file = new File("/Users/yuanzq/Desktop/work/liequ/io/src/bio/file.txt");
		FileInputStream fis = new FileInputStream(file);
		int b ;
		while((b = fis.read()) != -1){
			System.out.println((byte)b);
		}
		fis.close();
	}
	
	
}
