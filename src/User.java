
public abstract class User {
	private String name;
	private String password;
	private String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	//修改自己密码
	public boolean changeSelfInfo(String password){
		if (DataProcessing.update(name, password, role)){
			this.password=password;
			System.out.println("success change");
			return true;
		}else
			return false;
	}
	
	public abstract void showMenu();
	
	//下载文件
	public boolean downloadFile(String filename){
		System.out.println("downloading... ...");
		return true;
	}
	
	//文件列表
	public void showFileList(){
		System.out.println("fileList");
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
