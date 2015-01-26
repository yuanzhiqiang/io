package bio;

import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.List;

public class CopyOfTestSelector {
	private static final int MAXSIZE = 65535;

	public static final void main(String argc[]) {
		Selector[] sels = new Selector[MAXSIZE];
		Selector[] sels2 = new Selector[MAXSIZE];
		ServerSocket[] ssa = new ServerSocket[MAXSIZE];
		int i = 0;
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				List list = new ArrayList();
//				File file = new File(
//						"/Users/yuanzq/Desktop/work/liequ/io/src/bio/file.txt");
//				FileInputStream fis = null;
//				int i = 0;
//				try {
//					for (i = 0; i < 65535; i++) {
//						fis = new FileInputStream(file);
//						list.add(fis);
//						Thread.sleep(50);
//					}
//				} catch (Exception e) {
//					System.out.println(i);
//					e.printStackTrace();
//				}
//			}
//
//		}).start();
		try {
			for (i = 0; i < MAXSIZE; ++i) {
				sels[i] = Selector.open();
//				ssa[i] = new ServerSocket(5000+i);
				Thread.sleep(5);
			}
		} catch (Exception ex) {
			System.out.println("main:" + i);
			throw new RuntimeException(ex);
		}
	}
}