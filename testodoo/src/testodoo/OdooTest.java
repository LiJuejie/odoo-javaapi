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

public class OdooTest {  
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

		//登录
		int uid = (int)authClient.execute(
				authStartConfig, "authenticate", configList);

		System.out.println(uid);
	}
}  
          
		/*
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();  
        XmlRpcClient client = new XmlRpcClient();  
        config.setServerURL(new URL(String.format("%s/xmlrpc/2/object", URL)));  
        client.setConfig(config);  
        // 查找客户
        List<Object> partners = Arrays.asList((Object[])client.execute("execute_kw", Arrays.asList(  
                DB, USERID, PASS,  
                "res.partner", "name_get",  
                Arrays.asList(Arrays.asList(1)
            ))));

        if(partners!=null&&partners.size()>0){// partners式: [[id, name]]  
            Object[] partner = (Object[])partners.get(0);  
            if(partner.length==2)  
            System.out.println("客户名: "+partner[1]);  
        	List<Object> customers = Arrays.asList((Object[]) client.execute(
    				"execute_kw", Arrays.asList(
    						DB, USERID, PASS, "res.partner", "search_read",
    						Arrays.asList(Arrays.asList(
    								// 设置查询条件
    								// Arrays.asList("is_company", "=", true),
    								Arrays.asList("customer", "=", false))),
    						new HashMap() {
    							{// 查询name字段, 限定最多返回100条记录
    								put("fields", Arrays.asList("name"));
    								put("limit", 100);
    							}
    						})));
    		
    		System.out.println("**********客户列表**********");
    		for (int i=0;i<customers.size();i++){
    			Map customer = (Map)customers.get(i);
    			String name = (String)customer.get("name");
    			System.out.println(name);
    		}
    		System.out.println("****************************");
		*/
