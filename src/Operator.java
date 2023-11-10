
//文件管理者Operator

public class Operator extends User{

    Operator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public void showMenu() {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("1-uploadFile 2-downloadFile ");
        switch (Main.scanner.nextLine()) {
            case "1":uploadFile();break;
            case "2":downloadFile("filename");break;
            default:
                System.out.println("Error enter!");
                break;
        }
        // scanner.close();
    }
    
    public boolean uploadFile(){
        System.out.println("<Operator>: Success upload!");
		return true;
    }
}
