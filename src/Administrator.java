
// 管理员Administrator

public class Administrator extends User {

    Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public void showMenu() {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("1-addUser 2-delUser 3-changeUser 4-listUser ");
        switch (Main.scanner.nextLine()) {
            case "1":
                addUser();
                break;
            case "2":
                delUser();
                break;
            case "3":
                changeUserInfo();
                break;
            case "4":
                listUser();
                break;
            default:
                System.out.println("Error enter!");
                break;
        }
        // scanner.close();
    }

    // 增加
    public boolean addUser() {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("<Admin>AddUser: Enter the User name:");
        String name = Main.scanner.nextLine();
        System.out.println("<Admin>AddUser: Enter the User password:");
        String password = Main.scanner.nextLine();
        System.out.println("<Admin>AddUser: Enter the User role:");
        String role = Main.scanner.nextLine();
        // scanner.close();

        if (DataProcessing.insert(name, password, role) == true) {
            System.out.println("<Admin> Success Add!");
            return true;
        } else {
            System.out.println("<Admin> Error Add!");
            return false;
        }

    }

    // 删除
    public boolean delUser() {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("<Admin>DelUser: Enter the User name:");
        String name = Main.scanner.nextLine();
        // scanner.close();

        if (DataProcessing.delete(name) == true) {
            System.out.println("<Admin> Success Del!");
            return true;
        } else {
            System.out.println("<Admin> Error Del!");
            return false;
        }
    }

    // 查询
    public void listUser() {
        // Scanner scanner = new Scanner(System.in);
        // 查询用户并显示信息
        System.out.println("<Admin>ChangeUserInfo: Enter the user name:");
        String str = Main.scanner.nextLine();

        User user = DataProcessing.searchUser(str);
        String name = user.getName();
        String password = user.getPassword();
        String role = user.getRole();
        System.out.println(
                "<Admin>ChangeUserInfo:\n userName: " + name + " password: " + password + " role: " + role + '\n');
        // scanner.close();

    }

    // 修改用户信息
    public boolean changeUserInfo() {
        // Scanner scanner = new Scanner(System.in);
        // 查询用户并显示信息
        System.out.println("<Admin>ChangeUserInfo: Enter the user name:");
        String str = Main.scanner.nextLine();

        User user = DataProcessing.searchUser(str);
        String name = user.getName();
        String password = user.getPassword();
        String role = user.getRole();
        System.out.println(
                "<Admin>ChangeUserInfo:\n userName: " + name + " password: " + password + " role: " + password + '\n');

        // 修改用户信息
        System.out.println("<Admin>ChangeUserInfo:Enter the userName:");
        name = Main.scanner.nextLine();
        System.out.println("<Admin>ChangeUserInfo:Enter the password:");
        password = Main.scanner.nextLine();
        System.out.println("<Admin>ChangeUserInfo:Enter the role:");
        role = Main.scanner.nextLine();
        // scanner.close();

        if (DataProcessing.update(name, password, role) == true) {
            System.out.println("<Admin>ChangeUserInfo: Success Change!");
            return true;
        } else {
            System.out.println("<Admin>ChangeUserInfo: Error Change!");
            return false;
        }

    }

}
