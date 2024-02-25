package Applications;

import Helpers.CheckDelivery;
import ModelsEntities.PrintDeliveryData;
import Repositories.DeliveryRepository;

import java.sql.SQLException;
import java.util.Scanner;

import ModelsEntities.User;


public class DeliveryApplication extends User {
    private CheckDelivery checkDelivery = CheckDelivery.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private PrintDeliveryData printDeliveryData = new PrintDeliveryData();
    private final DeliveryRepository deliveryRepository = new DeliveryRepository();


    public void startApp() throws SQLException {
        if(!checkDelivery.checkDelivery()){
            System.out.println("Please register delivery");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            deliveryRepository.reg();
        }
        else{
            printDeliveryData.printDeliveryData();
            System.out.print("You have already registered your delivery. Do you want edit? y/n:");
            String edit = scanner.next();
            if(edit.equals("y")){
                deliveryRepository.reg();
                System.out.println("Your data has been updated");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            else{
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
        }
    }

}
