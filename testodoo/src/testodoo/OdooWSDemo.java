package testodoo;
import com.debortoliwines.openerp.api.FilterCollection;
import com.debortoliwines.openerp.api.ObjectAdapter;
import com.debortoliwines.openerp.api.Row;
import com.debortoliwines.openerp.api.RowCollection;
import com.debortoliwines.openerp.api.Session;

public class OdooWSDemo {  
    public static final String IP = "172.16.0.129";
    public static final int HOST = 8069;
    public static final String DB = "linxerp";
    public static final int USERID = 1;  
    public static final String USERNAME = "admin";  
    public static final String PASS = "R0ck9odoo";
	private static final String Row = null;
      
	public static void main(String[] args) throws Exception{  
		Session openERPSession = new Session(IP, HOST, DB, USERNAME, PASS);
		try {
		    openERPSession.startSession();
		    /*
		    ObjectAdapter partnerAd = openERPSession.getObjectAdapter("res.partner");
		    System.out.println(partnerAd);
		    FilterCollection filters = new FilterCollection();
			filters.add("customer","=",true);

			RowCollection partners = partnerAd.searchAndReadObject(filters, new String[]{"name","email"});
			System.out.println(partners);
			for (Row row : partners){
			    System.out.println("Row ID: " + row.getID());
			    System.out.println("Name:" + row.get("name"));
			    System.out.println("Email:" + row.get("email"));
			}
			*/
			//获取所有用户
		    ObjectAdapter userAd = openERPSession.getObjectAdapter("res.users");
		    FilterCollection user_filters = new FilterCollection();
		    user_filters.add("active","=",true);
		    user_filters.add("login","=","jjli@linx-info.com");
		    RowCollection users = userAd.searchAndReadObject(user_filters,new String[]{"name","login","partner_id"});
		    //获取用户信息
		    for (Row row : users){
		    	//System.out.println("Row ID:" + row.getID());
		    	//System.out.println("Name:" + row.get("name"));
		    	//System.out.println("login:" + row.get("login"));
		    	//System.out.println("partner_id:" + ((Object[])row.get("partner_id"))[1]);
		    	//获取部门和职位
			    ObjectAdapter hrAd = openERPSession.getObjectAdapter("hr.employee");
			    FilterCollection hrfilters = new FilterCollection();
			    hrfilters.add("user_id","=",row.getID());
			    RowCollection hrs = hrAd.searchAndReadObject(hrfilters, new String[]{"name","department_id","job_id"});
			    for (Row hrrow : hrs){
			    	System.out.println("Row ID:" + hrrow.getID());
			    	System.out.println("Name:" + hrrow.get("name"));
			    	System.out.println("Department:" + ((Object[])hrrow.get("department_id"))[1].toString());
			    	System.out.println("Job:" + ((Object[])hrrow.get("job_id"))[1].toString());
			    }
		    }
		    ObjectAdapter partitleAd = openERPSession.getObjectAdapter("res.partner.title");
		    FilterCollection ptfilters = new FilterCollection();
		    ptfilters.add("name","=","女士");
		    RowCollection partitles = partitleAd.searchAndReadObject(ptfilters, new String[]{"name"});
		    for (Row titlerow : partitles){
		    	System.out.println("partitle:" + titlerow.get("name"));
		    	System.out.println("partitle:" + titlerow.getID());
			    ObjectAdapter parAd = openERPSession.getObjectAdapter("res.partner");
			    Row newpar = parAd.getNewRow(new String[]{"name","title"});
			    newpar.put("name", "test1");
				//newpar.putMany2ManyValue("title", new Object[]{"3"},true);    many2many
			    //newpar.put("title",new Object[]{"value"})   one2many
			    newpar.put("title",titlerow.getID());
			    parAd.createObject(newpar);
			    //System.out.println("New Row ID:" + newhr.getID());
		    }   
		} catch (Exception e) {
		    System.out.println("Error while reading data from server:\n\n" + e.getMessage());
		}
	}
}  