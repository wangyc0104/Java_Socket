package wyc.location;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络爬虫的原理(基本) + 模拟浏览器
 * 
 * @author 王以诚
 */
public class SpiderTest02 {
	public static void main(String[] args) throws Exception {
		// 获取URL
		URL url = new URL("https://www.jd.com");
		// URL url = new URL("https://www.dianping.com"); // 会报403错误被拒绝
		// 下载资源
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String msg = null;
		while (null != (msg = br.readLine())) {
			System.out.println(msg);
		}
		// 分析（不管）
		// 处理（不管）
	}
}
