package wyc.chat04;

import java.io.Closeable;

/**
 * 聊天室工具类
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
