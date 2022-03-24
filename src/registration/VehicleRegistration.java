package registration;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleRegistration extends RegistrationProcess{

    private static boolean starter=true;
    private final Scanner in = new Scanner(System.in);

    public void mainMenu() throws IOException {
        try {
            System.out.println(" \n (1) --> For search \n (2) --> For Registration \n (3) --> For Exit \n Enter Your Choice :");
            byte choice = in.nextByte();
            switch (choice) {
                case 1 -> searchOptions();
                case 2 -> register();
                case 3 -> {
                    starter = false;
                    System.err.println("---------Thanks for coming----------");
                }
                default -> System.err.println("Your option is not valid...!");
            }
        }
        catch (InputMismatchException inputMismatchException){
            System.err.println("Enter valid option...!");
        }
    }
    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to vehicle registration...!");
        VehicleRegistration obj=new VehicleRegistration();
        while(starter) {
            obj.mainMenu();
        }
    }
}
