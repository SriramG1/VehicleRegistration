package registration;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleRegistration extends RegistrationProcess{

    static boolean key=true;
    Scanner in = new Scanner(System.in);

    public void mainMenu() throws IOException {
        try {
            System.out.println(" \n (1) --> For search \n (2) --> For Registration \n (3) --> For Exit \n Enter Your Choice :");
            byte choice = in.nextByte();
            switch (choice) {
                case 1:
                    System.out.println(" (1) -- > For search with vehicle number \n (2) --> For search with owner name \n Enter your choice : ");
                    int searchChoice = in.nextByte();
                    switch (searchChoice) {
                        case 1:
                            searchByNumber();
                            break;
                        case 2:
                            searchByName();
                            break;
                        default:
                            System.out.println("Enter valid option...!");
                            break;
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    key = false;
                    System.err.println("---------Thanks for coming----------");
                    break;
                default:
                    System.err.println("Your option is not valid...!");
                    break;
            }
        }
        catch (InputMismatchException inputMismatchException){
            System.err.println("Enter valid option...!");
        }
    }
    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to vehicle registration...!");
        VehicleRegistration obj=new VehicleRegistration();
        while(key) {
            obj.mainMenu();
        }
    }
}
