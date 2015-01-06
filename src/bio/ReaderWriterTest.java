package bio;

import java.io.FileReader;
import java.io.IOException;

public class ReaderWriterTest {

	
	
	public static void main(String[] args) throws IOException {
		
		FileReader reader = new FileReader("/Users/yuanzq/Desktop/work/liequ/io/src/bio/file.txt");
		char[] result = new char[100];
		reader.read(result);
		System.out.println(new String(result));
	}
}
