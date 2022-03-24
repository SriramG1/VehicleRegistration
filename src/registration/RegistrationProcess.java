package registration;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RegistrationProcess{

    Scanner in = new Scanner(System.in);
    Details details = new Details();
    private static final String path="C:\\Users\\sys\\Desktop\\File\\RegistrationRecords.txt";
    public void displayDetails(String[] array){

        System.out.println("Your name is           : "+array[0]);
        System.out.println("Your vehicle number is : "+array[1]);
        System.out.println("Your district is       : "+array[2]);
        System.out.println("Your state is          : "+array[3]+"\n");
    }
    void searchOptions() throws IOException {
        boolean isValidOption = true;
        try {
            while (isValidOption) {
                System.out.println(" (1) -- > For search with vehicle number \n (2) --> For search with owner name \n Enter your choice : ");
                int searchChoice = in.nextByte();
                switch (searchChoice) {
                    case 1 -> {
                        isValidOption = false;
                        searchByNumber();
                    }
                    case 2 -> {
                        isValidOption = false;
                        searchByName();
                    }
                    default -> System.out.println("Enter valid option...!");
                }
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("-----Enter valid key-----");
        }
    }
    void getUserInput() throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Your name : ");
        String name=in.nextLine();
        boolean isValidName=inputFormatChecker(name);
        if(isValidName) {
            details.setName(name);
        }
        else{
            errorMessage();
            return;
        }
        System.out.println("Enter Your vehicle number in this format (TN 76 M 5540): ");
        details.setVehicleNumber(in.nextLine());
        boolean alreadyExist=alreadyExistNumber(details.getVehicleNumber());
        if(alreadyExist){
            System.out.println("\n-----Your number is already exists-----\n");
            return;
        }
        boolean isValidNumberFormat = numberFormat(details.getVehicleNumber());
        if(!isValidNumberFormat){
            System.err.println("\n-----Your number is not a vehicle number-----\n");
            return;
        }
        System.out.println("Enter your district : ");
        String district=in.nextLine();
        boolean isValidDistrictName=inputFormatChecker(district);
        if(isValidDistrictName) {
            details.setDistrict(district);
        }
        else {
            errorMessage();
            return;
        }
        System.out.println("Enter your state : ");
        String state=in.nextLine();
        boolean isValidStateName=inputFormatChecker(state);
        if(isValidStateName) {
            details.setState(state);
        }
        else {
            errorMessage();
        }
    }
    public void register() throws IOException {

        FileWriter write = new FileWriter(path,true);
        BufferedWriter writer = new BufferedWriter(write);
        PrintWriter printWriter = new PrintWriter(writer);
        getUserInput();
        printWriter.print(details.getName()+",");
        printWriter.print(details.getVehicleNumber()+",");
        printWriter.print(details.getDistrict()+",");
        printWriter.print(details.getState()+",");
        printWriter.println();

        System.out.println("Register successfully...! \n Enter (Yes) for more registration (No) for back to Main Menu : ");
        char more = in.next().charAt(0);
        if(more=='y'||more=='Y'){
            register();
        }
        else{
            System.out.println("Thanks For Register...!");
        }
        printWriter.flush();
        printWriter.close();
        writer.close();
        write.close();
    }

    public void searchByName() throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name for searching : ");
        String name=in.next();
        searching(name,0);
    }
    public void searchByNumber() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your number for searching : ");
        String number=in.nextLine();
        searching(number,1);
    }
    void searching(String name,int search) throws IOException{
        FileReader file = new FileReader(path);
        BufferedReader reader= new BufferedReader(file);
        System.err.println("\n-----Your details showing below-----\n");
        String line;
        boolean emptyData=true;
        int numberOfLines=countingLines();
        for(int i=0;i<numberOfLines;i++){
            line = reader.readLine();
            String[] array=line.split(",");
            if(array[search].equalsIgnoreCase(name))
            {
                emptyData=false;
                displayDetails(array);
            }
        }
        if(emptyData){
            System.out.println("\nNo data found in this name...!\n");
        }
        file.close();
        reader.close();
    }
    boolean alreadyExistNumber(String number) throws IOException{
        FileReader file = new FileReader(path);
        BufferedReader reader= new BufferedReader(file);
        String line;
        boolean isAlreadyExist=false;
        for(int i=0;i<countingLines();i++) {
            line = reader.readLine();
            String[] array = line.split(",");
            if (array[1].equalsIgnoreCase(number)) {
                isAlreadyExist = true;
            }
        }
        file.close();
        reader.close();
        return isAlreadyExist;
    }
    int countingLines() throws IOException {
        int count=0;
        FileReader read = new FileReader(path);
        BufferedReader reader = new BufferedReader(read);
        while((reader.readLine())!=null){
            count++;
        }
        read.close();
        reader.close();
        return count;
    }

    public boolean numberFormat(String number){
        boolean isValidFormat;
        isValidFormat=number.matches("[A-Z]{2}\s[0-9]{2}\s[A-Z]\s[0-9]{4}");
        return isValidFormat;
    }
    protected boolean inputFormatChecker(String detail){
        boolean inputFormat=true;
        for(int i=0;i<detail.length();i++){
            if(detail.charAt(i)==','){
                inputFormat=false;
                break;
            }
        }
        return inputFormat;
    }
    void errorMessage() throws IOException{
        System.out.println("Don't add (,) symbol  with your details");
        getUserInput();
    }
}
