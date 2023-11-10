
// 普通用户Browser

public class Browser extends User{

    Browser(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public void showMenu() {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("1-showFileList 2-downloadFile 3-changeSelfInfo");
        switch (Main.scanner.nextLine()) {
            case "1":showFileList();break;
            case "2":downloadFile("getName()");break;
            case "3":changeSelfInfo("getPassword()");break;
            default:
                System.out.println("Error enter!");
                break;
        }
        // scanner.close();
    }

    
}
