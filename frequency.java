import java.util.*;
import java.sql.*;
public class frequency {
    public static void main(String[]args)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "soham22");
        Statement st=con.createStatement();
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String str=sc.nextLine().toUpperCase(),q;

        try{
            q="drop table characterfreq;";
            System.out.println(st.executeUpdate(q));
            q="create table characterfreq(Alphabet char(1) primary key,Occurence int);";
            System.out.println(st.executeUpdate(q));
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        int freq[]=new int[26];
        Arrays.fill(freq, 0);
        for(int i=0;i<str.length();++i){
            char s=str.charAt(i);
            int j=s;
            j-=65;
            freq[j]+=1;
            //System.out.println(j+","+s);
        }
        try{
            int count=0;
            q="insert into characterfreq values(?,?);";
            for(int i=65;i<=90;++i){
                PreparedStatement ps=con.prepareStatement(q);
                char c=(char)i;
                ps.setString(1,String.valueOf(c));
                ps.setInt(2, freq[i-65]);
                count+=ps.executeUpdate();
            }    
            System.out.println(count+" times updated");
        }
        catch(Exception e){
            System.out.println(e);
        }

        q="select * from characterfreq;";
        ResultSet rs=st.executeQuery(q);
        ResultSetMetaData rsmd=rs.getMetaData();
        System.out.println("Table characterfreq");
        while(rs.next()){
            for(int i=1;i<=rsmd.getColumnCount();++i){
                System.out.print(rs.getString(i)+"\t");
            }
            System.out.println();
        }
        st.close();
        con.close();
    }
    
}
