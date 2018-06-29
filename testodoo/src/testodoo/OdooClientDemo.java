package testodoo;
import java.net.URL;  
import java.util.ArrayList;
import java.util.Arrays;  
import java.util.Collections;
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;
  
import org.apache.xmlrpc.client.XmlRpcClient;  
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;  

import com.debortoliwines.openerp.api.ObjectAdapter;
import com.debortoliwines.openerp.api.Session;

public class OdooWSDemo {  
    public static final String URL = "http://172.16.0.129:8069";
    public static final String DB = "linxerp";
    public static final int USERID = 1;  
    public static final String USERNAME = "admin";  
    public static final String PASS = "R0ck9odoo";
      
	public static void main(String[] args) throws Exception{  
		final XmlRpcClient authClient = new XmlRpcClient();
		final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl();

		authStartConfig.setServerURL(
				new URL(String.format("%s/xmlrpc/2/common", URL)));

		List configList = new ArrayList();//数组
		Map paramMap = new HashMap();//字典

		configList.add(DB);
		configList.add(USERNAME);
		configList.add(PASS);
		configList.add(paramMap);

		//登录返回用户的uid
		int uid = (int)authClient.execute(
				authStartConfig, "authenticate", configList);

		System.out.println(uid);
		//根据邮箱地址查询用户的uid然后进行创建等操作
		
	}
}  
