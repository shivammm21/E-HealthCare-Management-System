import java.util.Scanner;
public class Main {
    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        Processbar processbar = new Processbar();
        Scanner scanner =new Scanner(System.in);
        String pMno;
        Data data = new Data();
        int choice=0;

        String number;
        do{

            System.out.println("__________________________________________________");
            System.out.println("Welcome to E-HealthCare");
            System.out.println("[1].Create/Login Doctor");
            System.out.println("[2].Add patient");
            System.out.println("[3].History of the Patient");
            System.out.println("[4].Bill of the Patient");
            System.out.println("[5].Admin Login");
            System.out.println("[6].exit");
            System.out.print("Enter Number : ");

            try {
                choice = Integer.parseInt(scanner.next());
            }catch (NumberFormatException e){
                System.out.println("Please Enter Number !!>>>>");
            }
            processbar.run();
            clearScreen();
            switch (choice) {
                case 1:
                    do {
                        System.out.println("----------Create/Login Doctor----------");
                        System.out.println("[1].Create Doctor");
                        System.out.println("[2].Login Doctor");
                        System.out.println("[3].Exit");
                        System.out.print("Enter number : ");
                        number = scanner.next();
                        processbar.run();
                        clearScreen();
                        if (number.equals("1")) {
                            System.out.println("----------New Doctor Login----------");
                            System.out.println("Enter New ID : ");
                            int nId = scanner.nextInt();
                            System.out.print("Enter Doctor name : Dr.");
                            String dname = scanner.next().toUpperCase();
                            System.out.print("Enter Fees you take : ");
                            String fees = scanner.next();
                            System.out.print("Enter New Password : ");
                            String dpass = scanner.next();
                            processbar.run();
                            clearScreen();
                            data.createDoctor(nId, dname, dpass, fees);

                        } else if (number.equals("2")) {
                            System.out.println("----------Doctor Login----------");
                            System.out.print("Enter  Doctor ID : ");
                            int id = scanner.nextInt();
                            System.out.print("Enter Doctor Password : ");
                            String pass = scanner.next();
                            processbar.run();
                            clearScreen();
                            data.loginDoctor(id, pass);
                        } else if (number.equals("3")) {
                            System.out.println("Thank You :)");
                        } else {
                            System.out.println("Invalid>>");
                            System.out.println("Please re-enter >>!");
                        }
                    }while (Integer.parseInt(number)!=3);

                    break;
                case 2:
                    System.out.println("----------ADD Patient----------");
                    data.displayAvaiDoctor();
                    System.out.print("Enter Patient ID : ");
                    int pID = scanner.nextInt();
                    System.out.print("Enter Patient name : ");
                    String pName = scanner.next().toUpperCase();
                    do {
                        System.out.print("Enter patient Mobile Number : ");
                        pMno = scanner.next();
                        if(pMno.length()!=10){
                            System.out.println("Please enter 10 digit Mobile Number..>>!");
                        }
                    }while (pMno.length()!=10);
                    System.out.print("Enter Patient Address (City Name): ");
                    String pCity = scanner.next().toUpperCase();
                    System.out.print("Enter Patient Gender (Male/Female) :");
                    String pGender = scanner.next().toUpperCase();
                    while(true) {
                        if (pGender.equals("M") || pGender.equals("MALE")) {
                            pGender = "MALE";
                            break;
                        } else if (pGender.equals("F") || pGender.equals("FEMALE")) {
                            pGender = "FEMALE";
                            break;
                        } else {
                            System.out.println("re-enter >>");
                        }
                    }
                    System.out.print("Enter Doctor name : DR.");
                    String pDoctorname = scanner.next().toUpperCase();
                    processbar.run();
                    clearScreen();
                    data.addPatient(pID, pName, pMno, pCity,pGender,pDoctorname);

                    break;
                case 3:
                    System.out.println("----------History of the Patient----------");
                    System.out.print("Enter Patient name :");
                    String patientName = scanner.next().toUpperCase();
                    clearScreen();
                    data.patientHistory(patientName);
                    break;
                case 4:
                    System.out.println("----------Bill of the Patient----------");
                    System.out.print("Enter Patient name : ");
                    String Patientname = scanner.next().toUpperCase();
                    clearScreen();
                    data.patientBill(Patientname);

                    break;
                case 5:

                    System.out.println("----------Admin Login----------");
                       System.out.print("Enter Username : ");
                       String username = scanner.next();
                       System.out.print("Enter Password : ");
                       String password = scanner.next();
                        clearScreen();
                       if(username.equals("admin") && password.equals("1234")){
                         AdminLogin adminLogin = new AdminLogin();
                         adminLogin.logindisplay();
                       }
                       else {
                           System.out.println("Wrong");
                       }


                    break;
                case 6://exit
                    processbar.run();
                    clearScreen();
                    System.out.println("Thank You ...:)");
                    System.exit(0);

                    break;
                default:
                    System.out.println("Invalid >>>");
                    System.out.println("Re-enter>>> :)");

                    break;
            }
        }while (true);

    }
}