
package jDBC;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
public class student 
{
	Connection ob=null;
	public Connection getconnect()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver"); 
		ob=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","1RV21mc096$");
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
	public int insert(String u,String n,String an,String sd,String ed)
	{
		int i=0;
		String usn,name,aname,quary;
		usn=u;
		name=n;
		aname=an;
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
			System.out.println("USN\tNAME\tA_NAME\tS_DATE\t\tD_DATE\n");
			
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
			System.out.println("");
			close();
		}
		catch(Exception e)
		{
			System.out.println("EXECEPTION:"+e.getMessage());
		}
	}
	public void asubmit()
	{
		int ch;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
		System.out.println("1:SUBMIT ASSIGNMENT ON TIME\n2:SUBMIT ASSIGNMENT BEFORE DUE TIME\n3:SUBMIT ASSIGNMENT AFTER DUE TIME\n4:GO-BACK\nSENTER YOUR CHOICE:");
		ch=sc.nextInt();
		try
		{
			ob=getconnect();
			Statement st=ob.createStatement();
			if(ch==1)
			{
				String Quary="select * from assignment where sbdate=ddate;";
				ResultSet rs=st.executeQuery(Quary);
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
				}
				close();
			}
			else if(ch==2)
			{
				String Quary="select * from assignment where sbdate<ddate;";
				ResultSet rs=st.executeQuery(Quary);
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
				}
				close();
			}
			else if(ch==3)
			{
				String Quary="select * from assignment where sbdate>ddate;";
				ResultSet rs=st.executeQuery(Quary);
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
				}
				close();
			}
			else if(ch==4)
			{
				String name;
				System.out.println("Enter Subject Name:");
				name=sc.next();
				String Quary="select * from assignment where aname='"+name+"';";
				ResultSet rs=st.executeQuery(Quary);
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
				}
				close();
			}
			else
				break;
			System.out.println();//Used For New Line
		}
			catch(Exception e)
			{
				System.out.println("EXECEPTION:"+e.getMessage());
			}
		}
	}
	public int delete(String usn)
	{
		int i=0;
		try
		{
			ob=getconnect();
			Statement st=ob.createStatement();
			String quary="delete from assignment where usn='"+usn+"'";
			i=st.executeUpdate(quary);
			close();
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}
		return i;
	}
	public static void main(String[] args)
	{
		int i=0,ch=0;
		String usn,name,aname,ed,sd;
		student a=new student();
		Scanner sc=new Scanner(System.in);
		a.getconnect();
		while(true)
		{
			System.out.println("\tASSIGNMENT DETAILS\n");
			System.out.println("1:INSERT ASSIGNMENT_DETILAS\n2:DISPALY ASSIGNMENT_DETIALS\n3:SUBMIT_DETAIL\n4:DELETE ASSIGNMENT_RECORD\nENTER YOUR CHOICE:");
			ch=sc.nextInt();
			if(ch==1)
			{
				System.out.println("ENTER STUDENT USN:");
				usn=sc.next();
				System.out.println("ENTER STUDENT NAME:");
				name=sc.next();
				System.out.println("ENTER ASSIGNMENT NAME:");
				aname=sc.next();
				System.out.println("ENTER ASSIGNMENT Submition Date in YY-MM-DD FORMET:");
				sd=sc.next();
				System.out.println("ENTER ASSIGNMENT Due Date in YY-MM-DD FORMET:");
				ed=sc.next();
				i=a.insert(usn, name, aname,sd,ed);
				if(i==1)
					System.out.println("Insertion Succesfull\n");
				else
					System.out.println("Insertion Unsuccesfull\n");
			}
			else if(ch==2)
			{
				a.display();
			}
			else if(ch==3)
			{
				a.asubmit();
			}
			else if(ch==4)
			{

				System.out.println("ENTER STUDENT USN:");
				usn=sc.next();
				i=a.delete(usn);
				if(i==1)
					System.out.println("Deletion Succesfull\n");
				else
					System.out.println("Deletion Unsuccesfull\n");
			}
			else 
				break;
		}
	}

}
