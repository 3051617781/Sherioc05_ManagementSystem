import java.sql.*;
import java.util.Hashtable;
import java.util.*;


public  class DataProcessing {
	private static boolean connectToDB=false;
	
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));		
		Init();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));

	}
	public static  void Init(){}	

	//查找doc
	public static Doc searchDoc(String ID) throws SQLException {
		if (docs.containsKey(ID)) {
			Doc temp =docs.get(ID);
			return temp;
		}
		return null;
	}

	//获取所有doc
	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		Enumeration<Doc> e  = docs.elements();
		return e;
	} 

	//插入doc
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		Doc doc;		
	
		if (docs.containsKey(ID))
			return false;
		else{
			doc = new Doc(ID,creator,timestamp,description,filename);
			docs.put(ID, doc);
			return true;
		}
	} 

	//根据name查询
	public static User searchUser(String name) throws SQLException{

		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}
	
	//根据name、password查询
	public static User search(String name, String password) throws SQLException{


		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{

		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	//update
	public static boolean update(String name, String password, String role) throws SQLException{
		User user;

		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
	}
	
	//insert
	public static boolean insert(String name, String password, String role) throws SQLException{
		User user;


		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
	}
	
	//delete
	public static boolean delete(String name) throws SQLException{
						
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		
	}
	
	public void disconnectFromDB() {
		if ( connectToDB ){
			// close Statement and Connection            
			try{
       
			}finally{                                            
				connectToDB = false;              
			}                             
		} 
   }     
	
}
