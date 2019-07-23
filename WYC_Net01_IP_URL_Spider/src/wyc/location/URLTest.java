package wyc.location;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL: 统一资源定位符，互联网三大基石之一（html http），区分资源 <br>
 * 1. 协议 <br>
 * 2. 域名、计算机 <br>
 * 3. 端口：默认80（http端口） <br>
 * 4. 请求资源 <br>
 * 
 * @author 王以诚
 */
public class URLTest {
	public static void main(String[] args) throws MalformedURLException {
		URL u = new URL("http://www.baidu.con:80/index.html?cansu=shsxt#aa");
		System.out.println("获取与此url关联的协议的默认端口： " + u.getDefaultPort());
		System.out.println("请求资源:" + u.getFile()); // 端口号后面的内容
		System.out.println("主机名：" + u.getHost()); // www.baidu.com
		System.out.println("路径：" + u.getPath()); // 端口号后，参数前的内容
		System.out.println("端口：" + u.getPort()); // 存在返回80.否则返回-1
		System.out.println("协议：" + u.getProtocol());
		System.out.println("参数：" + u.getQuery());
		System.out.println("锚点：" + u.getRef());
		URL u1 = new URL("http://www.abc.com/aa/");
		URL u2 = new URL(u1, "2.html"); // 相对路径构建url对象
		System.out.println(u2.toString()); // http://www.abc.com/aa/2.html
	}
}
