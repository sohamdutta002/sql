import java.sql.*;
import java.util.*;
public class mcq{
    public static void main(String [] args)throws Exception{
        try{
            //For creating connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","soham22");
            Statement st=con.createStatement();
            String q;
            ResultSet s;
            //Scanner sc=new Scanner(System.in);
            ResultSetMetaData rs;
            try{
                q="create table s(roll int primary key,name varchar(100));";
                System.out.println(st.executeUpdate(q));
            }
            catch(Exception e){
                System.out.println(e);
            }
            q="insert into s values(?,?)";
            int count=0;
            //s=st.executeQuery(q);
            try{
                for(int i=0;i<10;i++){
                    PreparedStatement ps=con.prepareStatement(q);
                    StringBuilder sb=new StringBuilder(10);
                    String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ "+" abcdefghijklmnopqrstuvwxyz";
                    for(int j=0;j<10;++j){
                        int index=(int)(alpha.length()*Math.random());
                        sb.append(alpha.charAt(index));
                    }
                    //String ns=UUID.randomUUID().toString().substring(0,5);
                    ps.setInt(1, i);
                    ps.setString(2, sb.toString());
                    count+=ps.executeUpdate();
                }
                System.out.println(count+" rows updated");
            }
            catch(Exception e){
                System.out.println(e);
            }
            q="select * from s;";
            s=st.executeQuery(q);
            rs=s.getMetaData();    
            while(s.next()){
                for(int i=1;i<=rs.getColumnCount();i++){
                    System.out.print(s.getString(i)+",");
                }
                System.out.println();
            }
            st.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
    }
}}