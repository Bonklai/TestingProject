package ModelsEntities;

import Getters.GetBalance;

import java.sql.SQLException;
import java.util.Scanner;

public class BalanceMenu {
    Scanner  scanner = new Scanner(System.in);
    BalanceManager balanceManager = new BalanceManager();
    public void balanceMenu() throws SQLException {
        GetBalance getBalance = new GetBalance();
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("Your balance:"+getBalance.getBalance());
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.Top up your balance");
        System.out.println("2.Back");
        System.out.print("Option:");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.print("Enter card number:");
            long cardNum = scanner.nextLong();
            System.out.print("Enter card expiration date MM/YY:");
            int expirationDate = scanner.nextInt();
            System.out.print("CVV:");
            int cvv = scanner.nextInt();


            System.out.print("Enter balance:");
            int amount = scanner.nextInt();

            if (amount>0){
                balanceManager.addBalance(amount);
                System.out.println("Balance updated");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            else{
                System.out.println("OH MANN YOU WANNA DECREASE YOUR BALANCE??? ITS NOT POSSIBLE");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }

        }
        else if(choice == 2){
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
    }
}
