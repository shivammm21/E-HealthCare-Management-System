import java.sql.*;
import java.util.Scanner;

public class Data {
    Connection con;
    Scanner scanner = new Scanner(System.in);
    Processbar processbar = new Processbar();
    void Connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","root");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void createDoctor(int id, String Name, String password, String fees){
        try {
            Connection();
            PreparedStatement psmt = this.con.prepareStatement("insert into doctor values(?,?,?,?)");
            psmt.setInt(1,id);
            psmt.setString(2,Name);
            psmt.setString(3,password);
            psmt.setString(4,fees);
            psmt.executeUpdate();
            System.out.println("Added Successfully :)");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public void loginDoctor(int id, String Password){
        try {
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet drs = smt.executeQuery("select * from doctor");

            while (drs.next()){
                if(id==drs.getInt(1) && Password.equals(drs.getString(3))){
                    int choice;
                    do {
                        System.out.println("Welcome DR." + drs.getString(2));
                        System.out.println("[1].View Patient");
                        System.out.println("[2].Edit Patient");
                        System.out.println("[3].Exit");
                        System.out.print("Enter Number : ");
                        choice = scanner.nextInt();
                        if (choice == 1) {
                            System.out.println("----------View Patient----------");
                            String doctorName = drs.getString(2);
                            LoginDoctor loginDoctor = new LoginDoctor();
                            loginDoctor.viewPatient(doctorName);
                            System.out.println("--------------------------------");
                        } else if (choice == 2) {
                            System.out.println("----------Edit Patient----------");
                            System.out.println("--------------------------------");
                        } else {
                            System.out.println("Invalid>>");
                            System.out.println("re-enter :(");
                        }
                    }while (choice!=3);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void displayAvaiDoctor(){
        try {
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from doctor");
            System.out.println("Available Doctor>>");
            while (rs.next()){
                System.out.println(rs.getInt(1)+". DR."+rs.getString(2));
            }
            System.out.println("___________________");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void addPatient(int pID, String pName, String pNumber, String pCity, String pGender, String pDoctor){
        try{
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from doctor");

            while (rs.next()){
                if(pDoctor.equals(rs.getString(2))){
                    PreparedStatement psmt = this.con.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?)");
                    psmt.setInt(1,rs.getInt(1));
                    psmt.setInt(2,pID);
                    psmt.setString(3,pName);
                    psmt.setString(4,pNumber);
                    psmt.setString(5,pCity);
                    psmt.setString(6,pGender);
                    psmt.setString(7,"DR."+pDoctor);
                    psmt.setString(8, rs.getString(4));

                    psmt.executeUpdate();

                    System.out.println("Patient Added Successfully :)");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public void patientHistory(String patientName){
        try {

            Connection();
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from patient");
            while (rs.next()){
                if(patientName.equals(rs.getString(3))){
                    System.out.println("------------------------------");
                    System.out.println("Patient ID : "+rs.getInt(2));
                    System.out.println("Patient Name : "+rs.getString(3));
                    System.out.println("Patient Contact Number : "+rs.getString(4));
                    System.out.println("City : "+rs.getString(5));
                    System.out.println("Patient Gender : "+rs.getString(6));
                    System.out.println("Appoint Doctor : "+rs.getString(7));
                    System.out.println("------------------------------");

                }
            }

        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }
    public void patientBill(String patientName){
        try{
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet rs = smt.executeQuery("select * from patient");
            while (rs.next()){
                if(patientName.equals(rs.getString(3))){
                    System.out.print("how many days he/she Stayed ? : ");
                    int days = scanner.nextInt();
                    processbar.run();
                    int stayfees = days*350;
                    int bedfees = days*200;
                    int serviceCharge = days*100;
                    int doctorCharge;

                    if(days>0) {
                        doctorCharge = days* Integer.parseInt(rs.getString(8));
                    }else {
                        doctorCharge = Integer.parseInt(rs.getString(8));
                    }
                    double totalPayment = stayfees+bedfees+serviceCharge+doctorCharge;
                    System.out.println(days+"days stayed per days=350 : "+stayfees);
                    System.out.println("Bed charger : "+bedfees);
                    System.out.println("Service Charges : "+serviceCharge);
                    System.out.println("Doctor Charges : "+doctorCharge);
                    System.out.println("______Total Payment : "+totalPayment);
                    System.out.println("-----------------------------------------------");
                }
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}