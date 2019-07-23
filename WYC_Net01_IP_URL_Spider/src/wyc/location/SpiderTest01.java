package wyc.location;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络爬虫的原理(基本)
 * 
 * @author 王以诚
 */
public class SpiderTest01 {
	public static void main(String[] args) throws Exception {
		// 获取URL
		URL url = new URL("https://www.jd.com");
		// URL url = new URL("https://www.dianping.com"); // 会报403错误被拒绝
		// 下载资源
		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String msg = null;
		while (null != (msg = br.readLine())) {
			System.out.println(msg);
		}
		// 分析（不管）
		// 处理（不管）
	}
}
