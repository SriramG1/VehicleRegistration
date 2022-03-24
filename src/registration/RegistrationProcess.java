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
        System.out.println("Your state is          : "+array[3]+" ");
    }
    void searchOptions() throws IOException {
        boolean flag = true;
        try {
            while (flag) {
                System.out.println(" (1) -- > For search with vehicle number \n (2) --> For search with owner name \n Enter your choice : ");
                int searchChoice = in.nextByte();
                switch (searchChoice) {
                    case 1 -> {
                        flag = false;
                        searchByNumber();
                    }
                    case 2 -> {
                        flag = false;
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
        System.out.println("Enter Your name : ");
        String name=in.nextLine();
        boolean nameCheck=inputFormatChecker(name);
        if(nameCheck) {
            errorMessage();
        }
        else{
            details.setName(name);
        }
        System.out.println("Enter Your vehicle number in this format (TN 76 M 5540): ");
        details.setVehicleNumber(in.nextLine());
        boolean key=alreadyExistNumber(details.getVehicleNumber());
        if(key){
            System.out.println("\n-----Your number is already exists-----\n");
            return;
        }
        boolean check = numberFormat(details.getVehicleNumber());
        if(!check){
            System.err.println("\n-----Your number is not a vehicle number-----\n");
            return;
        }
        System.out.println("Enter your district : ");
        String district=in.nextLine();
        boolean districtCheck=inputFormatChecker(district);
        if(!districtCheck) {
            details.setDistrict(district);
        }
        else {
            errorMessage();
        }
        System.out.println("Enter your state : ");
        String state=in.nextLine();
        boolean stateCheck=inputFormatChecker(state);
        if(!stateCheck) {
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
        char more = in.nextLine().charAt(0);
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
        BufferedReader bf= new BufferedReader(file);
        System.err.println("\n-----Your details showing below-----\n");
        String line;
        boolean flag=false;
        int lines=countingLines();
        for(int i=0;i<lines;i++){
            line = bf.readLine();
            String[] array=line.split(",");
            if(array[search].equalsIgnoreCase(name))
            {
                flag=true;
                displayDetails(array);
            }
        }
        if(!flag){
            System.out.println("\nNo data found in this name...!\n");
        }
        file.close();
        bf.close();
    }
    boolean alreadyExistNumber(String number) throws IOException{
        FileReader file = new FileReader(path);
        BufferedReader bf= new BufferedReader(file);
        String line;
        boolean flag=false;
        for(int i=0;i<countingLines();i++) {
            line = bf.readLine();
            String[] array = line.split(",");
            if (array[1].equalsIgnoreCase(number)) {
                flag = true;
            }
        }
        file.close();
        bf.close();
        return flag;
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
        boolean check;
        check=number.matches("[A-Z]{2}\s[0-9]{2}\s[A-Z]\s[0-9]{4}");
        return check;
    }
    protected boolean inputFormatChecker(String detail){
        boolean flag=false;
        for(int i=0;i<detail.length();i++){
            if(detail.charAt(i)==','){
                flag=true;
                break;
            }
        }
        return flag;
    }
    void errorMessage() throws IOException{
        System.out.println("Don't add (,) symbol  with your details");
        register();
    }
}
