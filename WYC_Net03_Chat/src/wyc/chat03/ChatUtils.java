package wyc.chat03;

import java.io.Closeable;

/**
 * 发送接收工具类
 * @author 王以诚
 */
public class ChatUtils {
	
	/**
	 * 释放资源
	 */
	public static void close(Closeable... targets) {
		for (Closeable target : targets) {
			try {
				if (null != target) {
					target.close();
				}
			} catch (Exception e) { }
		}
	}
	
}
