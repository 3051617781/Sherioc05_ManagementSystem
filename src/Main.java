import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        
        User user = null;
        while(user == null){
            System.out.println("welcome");
            System.out.println("enter the userName:");  String name = scanner.nextLine();   
            System.out.println("enter the password:");  String password = scanner.nextLine();
            try {
                if((user = DataProcessing.search(name, password)) !=null){
                    System.out.println("success login!");
                }else{
                    System.out.println("error log!");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                user = null;
            }
        }

        while(true){
            user.showMenu();
        }
        //scanner.close();
    }
    
}
