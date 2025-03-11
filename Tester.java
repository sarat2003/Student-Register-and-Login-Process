package Register;
import java.util.*;
import java.sql.*;
public class Tester {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","sarat","sarat");
			PreparedStatement ps=con.prepareStatement("insert into StudentInfo02 values(?,?,?,?,?,?,?)");
            PreparedStatement ps1=con.prepareStatement("select * from StudentInfo02 where srollno=? and sname=?");
            PreparedStatement ps2=con.prepareStatement("select count(*) from StudentInfo02 where  SPERCENT >?");
            PreparedStatement ps3=con.prepareStatement("update StudentInfo02 set  SMAILID=?, SPHNO=? where SROLLNO =?");
            PreparedStatement ps4=con.prepareStatement("Delete from StudentInfo02 where SPERCENT between 30 and 60");
            PreparedStatement ps5=con.prepareStatement("select count(*) from StudentInfo02 where SPERCENT >80");
            boolean v =true;
			while(v)
			{
				System.out.println("1.Register");
				System.out.println("2.Login\n3.Exit");
				System.out.println("Entyer your choice:");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice){
				
				case 1:
					   // System.out.println("Please Register before Login.");
					    System.out.println("Enter Student-Rollno:");
					    int rollno=Integer.parseInt(sc.nextLine());
					    System.out.println("Enter  Student-name:");
					    String name=sc.nextLine();
					    System.out.println("Enter Student-percentage:");
					    double percent=Double.parseDouble(sc.nextLine());
					    System.out.println("Enter Student-Fname:");
					    String fname=sc.nextLine();
					    System.out.println("Enter Student-Lname:");
					    String lname=sc.nextLine();
					   System.out.println("Enter Student-MailId:");
					   String mid=sc.nextLine();
					   System.out.println("Enter Student-PhNo:");
					   String phno=sc.nextLine();
					   
					   ps.setInt(1, rollno);
					   ps.setString(2, name);
					   ps.setDouble(3,percent);
					   ps.setString(4, fname);
					   ps.setString(5, lname);
					   ps.setString(6, mid);
					   ps.setString(7, phno);
					   
					   int k=ps.executeUpdate();
					   if(k >0)
					   {
						   System.out.println("Register Succesfully .");
					   }
					break;
				case 2:
					System.out.println("Enter Rollno and name for login..");
					System.out.println("Enter Student-Rollno:");
					String rollno1=sc.nextLine();
					System.out.println("Enter Student-name:");
					String name1=sc.nextLine();
					ps1.setString(1, rollno1);
					ps1.setString(2, name1);
					ResultSet rs = ps1.executeQuery();
					if(rs.next())
					{
						System.out.println("Login success..");
						boolean b= true;
						while (b) {
							System.out.println("1.show Stdent percentage >60");
							System.out.println("2.Update mailid and phno based on Rollno.\n3.Delete student percent between 30 to 60\n4.find how many student got more than 80%\n5.Exit");
							System.out.println("Entyer your choice:");
							int choice1=Integer.parseInt(sc.nextLine());
							
							switch(choice1)
							{
							case 1:
								System.out.println("Enter Student-percentage:");
								double percent1=Double.parseDouble(sc.nextLine());
								ps2.setDouble(1, percent1);
								ResultSet rs1 = ps2.executeQuery();
								if(rs1.next()) {
									System.out.println("Count is : "+rs.getInt(1));
								}
								
								break;
							case 2:
								System.out.println("Enter Student-Rollno to update:");
								int rollno2=Integer.parseInt(sc.nextLine());
								
								System.out.println("Enter Update-mailid:");
								String mid2=sc.nextLine();
								System.out.println("Enter Update-phno:");
								String phno2=sc.nextLine();
								ps3.setInt(1, rollno2);
								ps3.setString(2, mid2);
								ps3.setString(3, phno2);
								
								ResultSet rs2 = ps3.executeQuery();
								if(rs2.next())
								{
									System.out.println("Update Succesfully.....");
								}
								
								
								break;
							case 3:
								 ResultSet rs3 = ps4.executeQuery();
								 if(rs3.next())
								 {
									 System.out.println("Delete Sucessfully..");
								 }
								
								break;
							case 4:
								ResultSet rs4 = ps5.executeQuery();
								if(rs4.next()) {
									System.out.println("Count is : "+rs4.getInt(1));
								}
								
								break;
							case 5:
								System.out.println("exit");
								b=false;
								
							}
							
						}
					}
					else {
						System.out.println("Invalid use id...");
					}
					
					break;
					
				case 3:
					System.out.println("exit");
					v=false;
				 
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
