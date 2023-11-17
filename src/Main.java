import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        
        User user = null;
        while(user == null){
            System.out.println("enter the userName:");  String name = scanner.nextLine();   
            System.out.println("enter the password:");  String password = scanner.nextLine();
            user = DataProcessing.search(name, password);
        }

            user.showMenu();
        
        scanner.close();
    }
    
}
