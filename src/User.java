
public abstract class User {
	private String name;
	private String password;
	private String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password){
		if (DataProcessing.update(name, password, role)){
			this.password=password;
			System.out.println("�޸ĳɹ�");
			return true;
		}else
			return false;
	}
	
	public abstract void showMenu();
	
	public boolean downloadFile(String filename){
		System.out.println("Success download file");
		return true;
	}
	
	public void showFileList(){
		System.out.println("Show FileList");
	}
	
	public void exitSystem(){
		System.out.println("Success Exit!");
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
	

}
