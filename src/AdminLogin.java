import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminLogin {
    Connection con;
    Scanner scanner = new Scanner(System.in);
    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    void Connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","root");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void logindisplay(){
        try {
            Connection();
            int choice;
            do{
                System.out.println("[1].Show all Patient");
                System.out.println("[2].Show all Doctor");
                System.out.println("[3].Remove Patient");
                System.out.println("[4].Remove Doctor");
                System.out.println("[5].Exit");
                System.out.print("Enter number : ");
                choice = scanner.nextInt();
                clearScreen();
                if(choice==1){
                    System.out.println("----------Show All Patient----------");
                    showAllPatient();
                    System.out.println("------------------------------------");
                } else if (choice == 2) {
                    System.out.println("-----------Show All Doctor----------");
                    showAllDoctor();
                    System.out.println("------------------------------------");
                } else if (choice == 3) {
                    System.out.println("-----------Remove Patient-----------");
                    System.out.print("Enter Patient ID : ");
                    int patient_id = scanner.nextInt();
                    deletePatient(patient_id);
                    System.out.println("------------------------------------");
                } else if (choice == 4) {
                    System.out.println("------------Remove Doctor------------");
                    System.out.print("Enter Patient ID : ");
                    int doctor_id = scanner.nextInt();
                    deleteDoctor(doctor_id);
                    System.out.println("-------------------------------------");
                } else if (choice == 5) {
                    System.out.println("Thank You :)");
                    break;
                }else {
                    System.out.println("Invalid number");
                    System.out.println("re-enter");
                }
            }while (true);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void showAllPatient(){
        try{
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from Patient");
            while (rs.next()){
                System.out.print(rs.getInt(2)+" "+rs.getString(3)+" ");
                System.out.print(rs.getString(4)+" "+rs.getString(5)+" ");
                System.out.print(rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8));
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void showAllDoctor(){
        try{
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from doctor");
            while (rs.next()){
                System.out.print(rs.getInt(1)+" DR."+rs.getString(2)+" "+rs.getString(4));
                System.out.println();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void deletePatient(int id){
        try {
            String choice;
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from patient");
            while (rs.next()){
                if(rs.getInt(2)==id){
                    System.out.println("Patient ID : "+rs.getInt(2));
                    System.out.println("Patient Name : "+rs.getString(3));
                    System.out.println("Patient Contact Number : "+rs.getString(4));
                    System.out.println("Patient City : "+rs.getString(5));
                    System.out.println("Patient Gender : "+rs.getString(6));
                    System.out.println("Appointment Doctor : "+rs.getString(7));
                }
            }
            System.out.print("Are you sure you want to delete ? (y/n) : ");
            choice = scanner.next().toLowerCase();
            if(choice.equals("y") || choice.equals("yes")){
                smt.executeUpdate("delete from patient where patient_id="+id);
                System.out.println("Removed Successfully>>");
            }else if(choice.equals("n") || choice.equals("no")) {
                System.out.println("Not Removed record");
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void deleteDoctor(int id){
        try {
            String choice;
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from doctor");
            while (rs.next()){
                if(rs.getInt(1)==id){
                    System.out.println("Doctor ID : "+rs.getInt(1));
                    System.out.println("Doctor Name : DR."+rs.getString(2));
                    System.out.println("Doctor Fees : "+rs.getString(3));
                }
            }
            System.out.print("Are you sure you want to delete ? (y/n) : ");
            choice = scanner.next().toLowerCase();
            if(choice.equals("y") || choice.equals("yes")){
                smt.executeUpdate("delete from doctor where doctorid="+id);
                System.out.println("Removed Successfully>>");
            }else if(choice.equals("n") || choice.equals("no")) {
                System.out.println("Not Removed record");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
