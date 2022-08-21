package jDBC;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
public class assignment 
{
	Connection ob=null;
	public Connection getconnect()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver"); 
		System.out.println("Driver Loaded");
		ob=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","1RV21mc096$");
		System.out.println("Connection Established");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("EXECEPTION:"+e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println("EXECEPTION:"+e.getMessage());
		}
		return ob;
	}
	public int insert(String u,String n,String an,String ed)
	{
		int i=0;
		String usn,name,aname,quary;
		usn=u;
		name=n;
		aname=an;
		LocalDate sd;
		sd=LocalDate.now();
		Date sdate=Date.valueOf(sd);
		Date edate=Date.valueOf(ed);
		try
		{
			ob=getconnect();
			Statement st=ob.createStatement();
			quary="insert into assignment values('"+usn+"','"+name+"','"+aname+"','"+sdate+"','"+edate+"')";
			i=st.executeUpdate(quary);
			close();
		}
		catch(Exception e)
		{
			System.out.println("EXECEPTION:"+e.getMessage());
		}
		return i;
	}
	public void close()
	{
		try
		{
		ob.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}
	}
	public void display()
	{
		try
		{
			ob=getconnect();
			Statement st=ob.createStatement();
			ResultSet rs=st.executeQuery("select * from assignment");
			System.out.println("\n\t\tASSIGNMENT DETAILS\n");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
			close();
		}
		catch(Exception e)
		{
			System.out.println("EXECEPTION:"+e.getMessage());
		}
	}
	public static void main(String[] args)
	{
		int i=0,ch=0;
		String usn,name,aname,ed;
		assignment a=new assignment();
		Scanner sc=new Scanner(System.in);
		a.getconnect();
		while(true)
		{
			System.out.println("\t\t\t\tASSIGNMENT DETAILS\n");
			System.out.println("1:INSERT ASSIGNMENT_DETILAS\n2:DISPALY ASSIGNMENT_DETIALS\nENTER YOUR CHOICE:");
			ch=sc.nextInt();
			if(ch==1)
			{
				System.out.println("ENTER STUDENT USN:");
				usn=sc.next();
				System.out.println("ENTER STUDENT NAME:");
				name=sc.next();
				System.out.println("ENTER ASSIGNMENT NAME:");
				aname=sc.next();
				System.out.println("ENTER ASSIGNMENT Due Date in YY-MM-DD FORMET:");
				ed=sc.next();
				i=a.insert(usn, name, aname,ed);
				if(i==1)
					System.out.println("Insertion Succesfull\n");
				else
					System.out.println("Insertion Unsuccesfull\n");
			}
			else if(ch==2)
			{
				a.display();
			}
			else
				break;
		}
	}

}
