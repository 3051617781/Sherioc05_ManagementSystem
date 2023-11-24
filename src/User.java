import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public abstract class User {
	private String name;
	private String password;
	private String role;
	
	String uploadPath = "./data/uploadFile/";
	String downloadPath = "./data/downloadFile/";


	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	//修改自己密码
	public boolean changeSelfInfo(String password){
		try {
			if (DataProcessing.update(name, password, role)){
				this.password=password;
				System.out.println("success change");
				return true;
			}else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public abstract void showMenu();
	
	//下载文件
	public boolean downloadFile(String ID) throws IOException, SQLException{

		byte[] buffer = new byte[1024];
		Doc doc = DataProcessing.searchDoc(ID);
		if(doc == null)
			return false;
		File tempFile = new File(uploadPath + doc.getFilename());
		String filename = tempFile.getName();

		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(downloadPath + filename));

		while(true){
			int byteRead = infile.read(buffer);
			if(byteRead == -1)
				break; //EOF
			targetfile.write(buffer,0,byteRead);
		}
		infile.close();
		targetfile.close();
		
		return true;
	}
	
	//文件列表,展示所有档案文档信息
	public void showFileList() throws SQLException{
		System.out.println("fileList:");

		Enumeration<Doc> docs = DataProcessing.getAllDocs();
		Doc doc;
		while(docs.hasMoreElements()){
			doc = docs.nextElement();
			System.out.println(doc.toString());
		}
	}
	
	//退出
	public void exitSystem(){
		System.out.println("success exit! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", role=" + role + "]";
	}
	
}
