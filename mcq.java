import java.sql.*;
import java.util.*;
public class mcq {
    public static void main(String[]args)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","soham22");
        Statement st=con.createStatement();

        Scanner sc=new Scanner(System.in);
        String q,ques;
        try{
            //q="drop table mcqq;";
           // System.out.println(st.executeUpdate(q));
            q="create table mcqq(Question varchar(200),A int,C1 varchar(200),C2 varchar(200),C3 varchar(200));";
            System.out.println(st.executeUpdate(q));
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        try{
            int count=0;
            q="insert into mcqq values(?,?,?,?,?);";
            while(count>=0){
                
                System.out.println("Enter Question//press ex to exit");
                ques="Q."+sc.nextLine();
                if(ques.equals("Q.ex")){
                    break;
                }
                PreparedStatement ps=con.prepareStatement(q);
                System.out.println("Enter Three Choices");
                String c1,c2,c3;
                c1="1."+sc.nextLine();
                c2="2."+sc.nextLine();
                c3="3."+sc.nextLine();
                System.out.println("Enter the answer");
                int ans=sc.nextInt();
                ps.setString(1, ques);
                ps.setInt(2, ans);
                ps.setString(3, c1);
                ps.setString(4, c2);
                ps.setString(5, c3);
                count+=ps.executeUpdate();
                System.out.println(count);    
            }
            System.out.println(count+"\tquestions set");
        }
        catch(Exception e){
            System.out.println(e);
        }

        try{
            q="select * from mcqq;";
            ResultSet rs=st.executeQuery(q);
            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("Answer the following mcq's");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            while(rs.next()){
                for(int i=1;i<=rsm.getColumnCount();++i){
                    if(i!=2){
                        System.out.println(rs.getString(i));
                    }
                }
                System.out.print("Your Answer:\t");
                int c=sc.nextInt();
                if(c==rs.getInt(2))
                    System.out.println("Correct Choice");
                else
                    System.out.println("Wrong Choice");
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        st.close();
        con.close();
    }
}
