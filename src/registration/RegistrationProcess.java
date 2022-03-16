package registration;

import java.io.*;
import java.util.Scanner;

public class RegistrationProcess{

    Scanner in = new Scanner(System.in);
    public void displayDetails(String[] array){

        System.out.println("Your name is           : "+array[0]);
        System.out.println("Your vehicle number is : "+array[1]);
        System.out.println("Your district is       : "+array[2]);
        System.out.println("Your state is          : "+array[3]+" ");
    }

    public void register() throws IOException {

        FileWriter write = new FileWriter("C:\\Users\\sys\\Desktop\\File\\RegistrationRecords.txt",true);
        BufferedWriter writer = new BufferedWriter(write);
        PrintWriter printWriter = new PrintWriter(writer);

        Details details = new Details();

        System.out.println("Enter Your name : ");
        details.setName(in.nextLine());
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
        details.setDistrict(in.nextLine());
        System.out.println("Enter your state : ");
        details.setState(in.nextLine());

        printWriter.print(details.getName()+",");
        printWriter.print(details.getVehicleNumber()+",");
        printWriter.print(details.getDistrict()+",");
        printWriter.print(details.getState()+",");
        printWriter.println();

        System.out.println("Register successfully...! \n Enter (Yes) for more registration (No) for back to Main Menu : ");
        char more = in.nextLine().charAt(0);
        if(more==89||more==121){
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
        System.out.println("Enter your name for searching : ");
        String name=in.nextLine();

        FileReader file = new FileReader("C:\\Users\\sys\\Desktop\\File\\RegistrationRecords.txt");
        BufferedReader bf= new BufferedReader(file);

        System.err.println("\n-----Your details showing below-----\n");
        String line;
        boolean flag=false;
        int lines=countingLines();
        for(int i=0;i<lines;i++){
            line = bf.readLine();
            String[] array=line.split(",");
            if(array[0].equalsIgnoreCase(name))
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
    public void searchByNumber() throws IOException {
        System.out.println("Enter your number for searching : ");
        String number=in.nextLine();

        FileReader file = new FileReader("C:\\Users\\sys\\Desktop\\File\\RegistrationRecords.txt");
        BufferedReader bf= new BufferedReader(file);
        System.err.println("\n-----Your details showing below-----\n");
        String line;
        boolean flag=false;
        for(int i=0;i<countingLines();i++){
            line = bf.readLine();
            String[] array=line.split(",");
            if(array[1].equalsIgnoreCase(number))
            {
                flag=true;
                displayDetails(array);
            }
        }
        if(!flag){
            System.out.println("No data found in this number...!");
        }
        file.close();
        bf.close();
    }
    boolean alreadyExistNumber(String number) throws IOException{
        FileReader file = new FileReader("C:\\Users\\sys\\Desktop\\File\\RegistrationRecords.txt");
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
        FileReader read = new FileReader("C:\\Users\\sys\\Desktop\\File\\RegistrationRecords.txt");
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
}
