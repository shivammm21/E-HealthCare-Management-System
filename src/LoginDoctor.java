import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDoctor {
    Connection con;

    void Connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","root");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void viewPatient(String doctorName){
        try{
            Connection();
            Statement smt = this.con.createStatement();
            ResultSet prs = smt.executeQuery("select * from patient");
            while (prs.next()){
                if(prs.getString(7).equals("DR."+doctorName)){
                    System.out.print(prs.getInt(2)+" "+ prs.getString(3));
                    System.out.print(" "+prs.getString(4)+" "+ prs.getString(5));
                    System.out.print(" "+ prs.getString(6)+" "+ prs.getString(8));
                    System.out.println();
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
