package Maven.first_maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.*;



import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
//import javax.mail.Authenticator;
/*import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;*/
/*import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;*/
import java.net.*;


public class Monitoring_FrameWork
{
	
	public static String ScriptName;
	public static String DriveName;
	public static int year;
	public static String MonthName;
	public static int monthday;
	public static String ScreenfileLocation;
	public static String PageName,ErrorInput;
	public static double tStartTime;
	public static int status;
	public static String  sPRN = "";
	public static int whatsapp=0;
	public static String sDynamicPassword = "";
	public static String sStatusTOD = "";
	public static Statement statement = null;
	public static ResultSet rs,rs1 = null;
	public static Connection connection = null;
	public static String senderEmailID,senderPassword,emailSMTPserver,emailServerPort,receiverEmailID,emailSubject,emailBody;
	public static void writefile(String intval) throws IOException
	{
		String filepath=System.getProperty("user.dir");
		File file=new File(filepath+"/Runstaus.txt");
		file.createNewFile();
		FileWriter fileWritter = new FileWriter(file,false);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(intval);
		bufferWritter.close();		
	
	}
	public static String readFile()  
	{
		String output = "1";
		try
		{
			String filepath=System.getProperty("user.dir");
			File file=new File(filepath+"/Runstaus.txt");
		    
		    FileReader reader = null;
		    try
		    {
		        reader = new FileReader(file);
		        char[] chars = new char[(int) file.length()];
		        reader.read(chars);
		        output= new String(chars);
		        reader.close();
		    } 		
		    catch (IOException e) 
		    {
		        //e.printStackTrace();
		    } 
		    finally 
		    {
		        if(reader !=null){reader.close();}
		    }
		    
		}
		catch(Exception e)
		{
			
		}
		return output;
	}
	public static void CreatePath(String ScriptName, String DriveName, String lfreq)
	{	
		Monitoring_FrameWork.ScriptName = 	ScriptName;
		Monitoring_FrameWork.DriveName = 	DriveName;
		new File(DriveName + "/" + "Logs").mkdir();
		Date now = new Date();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Monitoring_FrameWork.year=year;
		new File(DriveName + "/" + "Logs/" + year).mkdir();
		String MonthName = new SimpleDateFormat("MMMM").format(now);
		Monitoring_FrameWork.MonthName=MonthName;
		new File(DriveName + "/" + "Logs/" + year + "/" + MonthName).mkdir();
		int monthday = Calendar.getInstance().get(Calendar.DATE);
		Monitoring_FrameWork.monthday=monthday;
		new File(DriveName + "/" + "Logs/" + year + "/" + MonthName + "/" + monthday).mkdir(); 
		new File(DriveName + "/" + "Logs/" + year + "/" + MonthName + "/" + monthday + "/" + "/" + ScriptName).mkdir();
	}

	public static double StartTime()
	{
		
		double lDateTime = new Date().getTime();	
		Calendar lCDateTime = Calendar.getInstance();
		double tTimeStart=lCDateTime.getTimeInMillis();
		return  tTimeStart;	
		
	}

	public static void SaveResult( double tStartTime, String PageName, int sStatus, int iResponseTime,int availability_alert,int ResponseTime_alert) throws Exception
	{
		//sStatus=1;
		ErrorInput = "NA";
		PageName = PageName.trim();
		ScreenfileLocation = "";
		String PF = "PF";
		int availability=0;		
		String sTotaltimeRounded = "";
		Monitoring_FrameWork.PageName=PageName;
		int ScreenShotStatus = 0;
		String BandWidthValue = "";
		Date now = new Date();
		long lDateTime = new Date().getTime();	
		Calendar lCDateTime = Calendar.getInstance();
		double tTotalTimelong=(lCDateTime.getTimeInMillis()) - (tStartTime);
		double tTotalTime = tTotalTimelong/1000;		
		int iMonth = now.getMonth()+1;
		String CurrentTimeDB = Calendar.getInstance().get(Calendar.YEAR) + "-" + iMonth + "-" + now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() ;
		String tSystemTime  = now.getHours() + ":" + now.getMinutes() + ":" +" "+ now.getSeconds()+" " ;
		if (sStatus == 0 || tTotalTime > iResponseTime || ScreenShotStatus == 1)
		{
	       if (sStatus == 0 || ScreenShotStatus == 1) 
	       {
	          if (sStatus == 0)
	          {
	        	  whatsapp=1;
		         TakeScreenshots();
		         java.awt.Frame frame = new java.awt.Frame();
		         frame.setVisible(true);
		         frame.toFront();
	        	 ErrorInput=JOptionPane.showInputDialog(frame,ScriptName+" \n Enter Error Details Here for Page : " + PageName);
	        	 frame.setVisible(false);
	        	 ErrorInput=ErrorInput.replace(",", " ");
	        	 Monitoring_FrameWork.ErrorInput =  ErrorInput;
	        	 sStatus=1;
	        	 if(ErrorInput.toUpperCase()!="NE" && ErrorInput!="" && ErrorInput!=null)
	        	 {
		        	if(ErrorInput.length()>=5 && availability_alert ==0) 
		        	{
		        		 sStatus =0; 
		        	}
		        	if(ErrorInput.length()>=5 && availability_alert ==1) 
		        	{
		        		whatsapp=1;
		        	    sStatus =0;
		        	    MailSend();
		        	     //SendSMS(ErrorInput);
					    // Whatsapp(ErrorInput);
		        	    //System.out.println("sending sms");
		        	    String filePath=ScreenfileLocation;
		        	    InputStream inputStream = new FileInputStream(new File(filePath));
		        	    try
		        	    {
		        	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
		        			//Connection conn = DriverManager.getConnection("jdbc:mysql://27.106.10.158:3306/test?connectTimeout=10000", "root", "Apmosys@123");
		        			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.9:3306/test?connectTimeout=10000", "root", "Apmosys@123");
			        	    String sql = "INSERT INTO testfiledata (filedata,appname,pagename,timeval) values (?,?,?,?)";
			        	  PreparedStatement statement1 = conn.prepareStatement(sql);
			        	    statement1.setBlob(1, inputStream);
			        	    statement1.setString(2, Monitoring_FrameWork.ScriptName);
			        	    statement1.setString(3, PageName);
			        	    statement1.setString(4, CurrentTimeDB);
			        	    statement1.executeUpdate();
			        	    statement1.close();
			        	    conn.close();
		        	    }
		        	    catch(Exception e)
		        	    {
		        	    	
		        	    }
		        	 }
	        	 }
	          }
	          if (ScreenShotStatus == 1) 
	          {
	             TakeScreenshots();
	          }
	       }   
	       else
	       {
	    	   if(ResponseTime_alert ==1)
	    	   {
		    	   /*java.awt.Frame frame = new java.awt.Frame();
				   frame.setVisible(true);
				   frame.toFront();
		    	   Object[] objects = {"Response Time of the Page " + PageName + " is " + tTotalTime};
		    	   JOptionPane.showMessageDialog(null, objects,PageName,JOptionPane.PLAIN_MESSAGE);
		    	   frame.setVisible(false);*/
	    		   whatsapp=1;
	    		   //Monitoring_FrameWork.ErrorInput ="Appeared Slow in "  + tTotalTime + " Sec" ;
	    		   //TakeScreenshots();
	    		   //  MailSend();
	        	   //SendSMS1(Monitoring_FrameWork.ErrorInput);
				   //Whatsapp(Monitoring_FrameWork.ErrorInput);  
		    	}
	       }
		}			 
		try
		{
			File file = new File(Monitoring_FrameWork.DriveName + "/Logs/" + Monitoring_FrameWork.year + "/" + Monitoring_FrameWork.MonthName + "/" + Monitoring_FrameWork.monthday + "/" + Monitoring_FrameWork.ScriptName + "/" + Monitoring_FrameWork.ScriptName + ".csv");
			if(!file.exists()) 
			{
				file.createNewFile();
				FileWriter fileWritter = new FileWriter(file,true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write("Page,ResponseTime,Time,Error,Status,Error Screenshot File Location,Bandwidth" + "\n");
				bufferWritter.close();
			}
			String sStatusString = "" ;	
			if(ErrorInput.length()<5)
			{
				sStatus=1;
			}
			if (sStatus==1)
			{
				sStatusString = "PASS";
				availability = 100;
			}
			if (sStatus==0)
			{
				sStatusString = "FAIL";
				availability = 0;
			} 
			FileWriter fileWritter = new FileWriter(file,true);
		    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		    sTotaltimeRounded=String.format("%.2f", tTotalTime);
		    //System.out.println("sTotaltimeRounded---->> "+sTotaltimeRounded);
		    //System.out.println("Status save result---->> "+sStatus);
		    if (sStatus==1)
	        {	
		    	bufferWritter.write(PageName + "," + sTotaltimeRounded + "," + tSystemTime + "," + Monitoring_FrameWork.ErrorInput + "," + sStatusString + "," + ScreenfileLocation + "," + BandWidthValue + ","  + "\n");
	        }
		    if (sStatus==0)
	        {
		    	//System.out.println("ScreenfileLocation1");
		    	//System.out.println(Monitoring_FrameWork1.ScreenfileLocation); 
		    	System.out.println("ScreenfileLocation2");
		    	bufferWritter.write(PageName + "," + sTotaltimeRounded + "," + tSystemTime + "," + Monitoring_FrameWork.ErrorInput + "," + sStatusString + "," + ScreenfileLocation + "," + BandWidthValue +  "," + "\n");
		    	//System.out.println("ScreenfileLocation3");
	       }
	       bufferWritter.close();
		}
	    catch (IOException e)
		{
	    	//System.out.println("Entered catch---->> "+sStatus);
	    }

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			//connection = DriverManager.getConnection("jdbc:mysql://27.106.10.158:3306/test?connectTimeout=5000", "root", "Apmosys@123");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.0.9:3306/test?connectTimeout=10000", "root", "Apmosys@123");
			/////connection = DriverManager.getConnection("jdbc:mysql://192.168.0.115:3306/test?connectTimeout=5000", "root", "root");
    	    statement = connection.createStatement();
			 //+ "-" + iMonth + "-" + now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() ;
			String Tv = Calendar.getInstance().get(Calendar.YEAR) + "-" + iMonth + "-" + now.getDate() + " 00:00:00" ;
			rs = statement.executeQuery("SELECT * FROM  execution_data  WHERE id =(select max(id) FROM execution_data WHERE application_name='" + Monitoring_FrameWork.ScriptName + "' AND page_name='" + PageName + "')");
			//String Tv;
			while (rs.next()) 
			{
				Tv = (rs.getString("Time_Value"));
				//System.out.println("Value of Time Value ---->> " + Tv);
			}
			rs.close();            
			double Tdiff = 0;
			rs1 = statement.executeQuery("SELECT TIMESTAMPDIFF(SECOND,'" + Tv + "','" + CurrentTimeDB + "') as sTimediffval");
			while (rs1.next())
			{
		     	Tdiff=(rs1.getDouble("sTimediffval"));
			}
			rs1.close();
			String stmt = "insert into execution_data(Application_Name, Page_Name, Response_Time, Availability, Error, Time_Value,Image_Url,Location,Whatsapp) values('" + Monitoring_FrameWork.ScriptName + "','" + PageName + "'," + sTotaltimeRounded + ", " + availability + ",'" + ErrorInput + "', '" + CurrentTimeDB +"' , '" +  ScreenfileLocation + "','Mumbai'"+",'" + whatsapp+ "' "+ ")";
			//String stmt = "insert into execution_data(Application_Name, Page_Name, Response_Time, Availability, Error, Time_Value,Image_Url,TimeDiff) values('" + Monitoring_FrameWork.ScriptName + "','" + PageName + "'," + sTotaltimeRounded + ", " + availability + ",'" + ErrorInput + "', '" + CurrentTimeDB +"' , '" +  ScreenfileLocation + "'," + Tdiff + ")";
			System.out.println("Value of the query -------------->> " + stmt);
			int rs2 = statement.executeUpdate(stmt);
				connection.close();
			ErrorInput =null;
			System.out.println("Value of rs2 ---->> " + rs2);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Database Error");
		}
	////////////////////////////////////////////////////////////
		ErrorInput=null;
	}


	public static void JFrameTest()
	{
		JFrame jf = new JFrame("Test");
		jf.getContentPane().setLayout(null);
		JTextArea comp = new JTextArea();
		comp.setBounds(100,100,200,200);
		jf.getContentPane().add(comp);
		jf.getContentPane().setBackground(SystemColor.control);
		jf.setSize(500,500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void MessageBox()
	{
	    Object[] objects = {"          This is the new"};
	    JOptionPane.showMessageDialog(null, objects,"This is the title",JOptionPane.PLAIN_MESSAGE);
	}

	public static void TakeScreenshots() throws Exception
	{
		
		//var robot = new java.awt.Robot();
		//var toolkit = new java.awt.Toolkit.getDefaultToolkit();
		//var screenSize = toolkit.getScreenSize();
		//var screenRect = new java.awt.Rectangle(0, 0, screenSize.width, screenSize.height);
		//var image = robot.createScreenCapture(screenRect);	
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
			
		String file = Monitoring_FrameWork.DriveName + "/Logs/" + Monitoring_FrameWork.year + "/" + Monitoring_FrameWork.MonthName + "/" + Monitoring_FrameWork.monthday + "/" + Monitoring_FrameWork.ScriptName + "/";
		Date nowScreen = new Date();
		String ScreenShotTime = Monitoring_FrameWork.PageName + "_" + nowScreen.getHours() + "_" + nowScreen.getMinutes() + "_" + nowScreen.getSeconds();
		String ScreenshotfileLocation = file + ScreenShotTime + ".png"; 
		//File file1 = new File(ScreenshotfileLocation);
		ImageIO.write(image, "png", new File(ScreenshotfileLocation));
			
		Monitoring_FrameWork.ScreenfileLocation = ScreenshotfileLocation;
		
	}
		
   public static void CreateMessageStatus(String sMessageTfor,String tTimeValuefor,String sStatusMessage,String sProcess)
   {

		try
		{
			String sFilePath="";	
			if (sMessageTfor.equals("PRN"))
			{
				sFilePath=Monitoring_FrameWork.DriveName + "/Logs/" + Monitoring_FrameWork.year + "/" + Monitoring_FrameWork.MonthName + "/" + Monitoring_FrameWork.monthday + "/" + Monitoring_FrameWork.ScriptName + "/"  + "Message_URN.csv";
			}
			if (sMessageTfor.equals("DP"))
			{	
				sFilePath=Monitoring_FrameWork.DriveName + "/Logs/" + Monitoring_FrameWork.year + "/" + Monitoring_FrameWork.MonthName + "/" + Monitoring_FrameWork.monthday + "/" + Monitoring_FrameWork.ScriptName + "/"  + "Message_DP.csv";
			}
		
			//File file = new File(Monitoring_FrameWork1.DriveName + "/Logs/" + Monitoring_FrameWork1.year + "/" + Monitoring_FrameWork1.MonthName + "/" + Monitoring_FrameWork1.monthday + "/" + Monitoring_FrameWork1.ScriptName + "/"  + "MessageStatus.csv");
			File file = new File(sFilePath);
			
			if(!file.exists()) 
			{
				  file.createNewFile();
				  FileWriter fileWritter = new FileWriter(file,true);
				  BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				  bufferWritter.write("Start Time,End Time,Entered Code");
				  bufferWritter.close();
			}


	        String sStatusvalue = "";
			if (sStatusMessage.equals(""))
			{
				sStatusvalue="Not Received";
			}
			else
			{
				sStatusvalue="Received";
			}
		
			FileWriter fileWritter = new FileWriter(file,true);
		    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        if (sProcess.equals("Sending"))
	        {	
	        	bufferWritter.write("\n" + tTimeValuefor);
	        }
	        else
	        {
	        	bufferWritter.write("," + tTimeValuefor + "," + sStatusMessage);	
	        }
	        bufferWritter.close();
		}
	    catch (IOException e)
		{
	    //oh noes!
	    }

   }

	/////////////////////////////////////////////// Mail Sending ////////////////////////////////////////////////////
	
	
	
	public static void MailSend() throws Exception
	{
		
		//System.out.println("----------------->>"+Monitoring_FrameWork1.ScriptName);	
		//System.out.println("----------------->>"+Monitoring_FrameWork1.PageName);
		//System.out.println("----------------->>"+Monitoring_FrameWork1.ScreenfileLocation);
			
		//this.receiverEmailID=receiverEmailID;
		//this.emailSubject=emailSubject;
		//this.emailBody=emailBody;
		
			//String ReceiverEmailID = "";
		/*try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://10.9.82.71:4633/test", "test", "test");
			statement = connection.createStatement();
			String Query = "SELECT Email FROM  alerts2  WHERE Application='" + Monitoring_FrameWork.ScriptName + "'";
			System.out.println("Query ---->> " + Query);
			rs = statement.executeQuery(Query);
			
			while (rs.next())
			{
				ReceiverEmailID = (rs.getString("Email"));
			}
			rs.close();	
			System.out.println("ReceiverEmailID ---->> " + ReceiverEmailID);
		}	
		catch(Exception e)
		{
			
		}*/
	
		senderEmailID = "apmosysalert@gmail.com";//"anjani.02oct@gmail.com";
		senderPassword = "chase@123";
		emailSMTPserver = "smtp.gmail.com";
		//emailSMTPserver = "smtp.apmosys.in";//"smtp.gmail.com";
		emailServerPort = "587";//465//587;
		receiverEmailID = "sangeeta@apmosys.in,bibhu@apmosys.in,kumar@apmosys.in,alerts@apmosys.in,saching@apmosys.in,lituja.mishra@apmosys.in";//null;
		emailSubject = "" + Monitoring_FrameWork.ScriptName + " " + Monitoring_FrameWork.PageName + "  :  " + Monitoring_FrameWork.ErrorInput;
		emailBody = "<p>Dear All,</p><h3>Error Description  : "+ Monitoring_FrameWork.ErrorInput +"</h3><Br><p>Thanks & Regards,</p><p>Performance Monitoring Team,</p><p>Desk NO. 022 65646461/62<p></br>";  
		//emailBody = "Thanks & Regards <br> <h3>";
	
		Properties props = new Properties();
		props.put("mail.smtp.user",senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
	
		SecurityManager security = System.getSecurityManager();
	
		try
		{
	
			//Authenticator auth = new SMTPAuthenticator();
			//Authenticator auth = Authenticator();	
			//Session session = Session.getInstance(props, auth);	
			/*Session session = Session.getInstance(props, new Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication()
				{
					String username = senderEmailID;
					String password = senderPassword;
					return new PasswordAuthentication(username, password);
				}	
				});
			// session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailID));
			//msg.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverEmailID));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receiverEmailID));*/
	
			//************************* Code for Attachment Starts ******************************************************
			
			/*BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailBody, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String filename = Monitoring_FrameWork.ScreenfileLocation;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			messageBodyPart.setHeader("Content-ID", "image_id");
			
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<p><font size='3'>Error Screenshot Shown Below : </font>" + "<img src='cid:image_id'>", "text/html");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);*/
			
			//************************* Code for Attachment Ends ******************************************************
	
			//Transport.send(msg);
		}
		catch (Exception mex)
		{
			mex.printStackTrace();
		}
	}
	
	///////////////////////////////////// SMS Sending ////////////////////////////////////////////////////////////////////////////
	
	public static void SendSMS( String sMessage)
	{
	/*
	        String ReceiverNumber = "";
	        try
	        {
				Class.forName("com.mysql.jdbc.Driver").newInstance();			
				connection = DriverManager.getConnection("jdbc:mysql://103.224.241.221:3306/test?connectTimeout=5000", "root", "password");
	    	    statement = connection.createStatement();
				String Query = "SELECT Numbers FROM  alerts1  WHERE Application='" + Monitoring_FrameWork.ScriptName + "'";
				System.out.println("Query ---->> " + Query);
				rs = statement.executeQuery(Query);
	
				while (rs.next()) 
				{
					ReceiverNumber = (rs.getString("Numbers"));
				}
				rs.close();	
				System.out.println("ReceiverNumber ---->> " + ReceiverNumber);
	        }
	
	        catch(Exception e)
	        {
			
	        }
		
	        try 
	        {
		    	
		    	Monitoring_FrameWork.monthday=04;
		    	Monitoring_FrameWork.MonthName="09";
		    	Monitoring_FrameWork.year=2015;
		    	Monitoring_FrameWork.ScriptName="TestApp";
		    	Monitoring_FrameWork.PageName="TestPage";
		    	String Date =  Monitoring_FrameWork.monthday+"-"+ Monitoring_FrameWork.MonthName+"-"+Monitoring_FrameWork.year;
			    Date Nowsms = new Date();
			    String SmsTime =+ Nowsms.getHours() + ":" + Nowsms.getMinutes() + ":" + Nowsms.getSeconds();
			    String DateTime =Date+" "+SmsTime;
		    	sMessage="Application : "+Monitoring_FrameWork.ScriptName+" - Error : "+sMessage+" on "+Monitoring_FrameWork.PageName+" at "+DateTime;
		    	System.out.println("Entered SMS");
		        //String sMessage = "Warking Fine On TestPage";
		        sMessage = sMessage.replaceAll(" ", "%20");
		        //System.out.println(sMessage);       
		       // String requestUrl  = "http://192.168.72.165:4001/axiomhttprec/pushlistener?//userid=idecrmalerts&pwd=pa123ecrmalerts&dcode=AXISECRMALERTS&ctype=1&alert=1&msgtype=S&priority=2&sender=AxisBk&pno="+ReceiverNumber+"&msgtxt=" + sMessage;
		        String requestUrl  ="http://www.smsgatewaycenter.com/library/send_sms_2.php?UserName=bibhu&Password=mpa@123&Type=Bulk&To=3223232,779797&Mask=APMSYS&Message="
		        + sMessage;
		        requestUrl = requestUrl.replaceAll(" ", "%20");
		        System.out.println(requestUrl); 
		        URL url = new URL(requestUrl);
		        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cfm.axisb.com", 1050));
		        HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
		        System.out.println(uc.getResponseMessage());
		        System.out.println(uc.getContent());
		        uc.disconnect();  
		    } 
	    catch(Exception ex) 
	    {
	      ex.printStackTrace();
	    }
	    */
	}
	
	///////////////////////////////////// SMS Sending ////////////////////////////////////////////////////////////////////////////
	
	public static void SendSMS1( String sMessage) 
	{
	
		/*String ReceiverNumber = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			connection = DriverManager.getConnection("jdbc:mysql://103.224.241.221:3306/test?connectTimeout=5000", "root", "password");
		    
			statement = connection.createStatement();
			String Query = "SELECT Numbers FROM  alerts1  WHERE Application='" + Monitoring_FrameWork.ScriptName + "'";
			System.out.println("Query ---->> " + Query);
			rs = statement.executeQuery(Query);
	
			while (rs.next())
			{
				ReceiverNumber = (rs.getString("Numbers"));
			}
			rs.close();	
			System.out.println("ReceiverNumber ---->> " + ReceiverNumber);
		}
	
		catch(Exception e)
		{
		
		}
	
		try
		{
			
			Monitoring_FrameWork.monthday=04;
			Monitoring_FrameWork.MonthName="09";
			Monitoring_FrameWork.year=2015;
			Monitoring_FrameWork.ScriptName="TestApp";
			Monitoring_FrameWork.PageName="TestPage";
			String Date =  Monitoring_FrameWork.monthday+"-"+ Monitoring_FrameWork.MonthName+"-"+Monitoring_FrameWork.year;
			Date Nowsms = new Date();
			String SmsTime =+ Nowsms.getHours() + ":" + Nowsms.getMinutes() + ":" + Nowsms.getSeconds();
			String DateTime =Date+" "+SmsTime;
			sMessage="Application : "+Monitoring_FrameWork.ScriptName+" - Alert : "+sMessage+" on "+Monitoring_FrameWork.PageName+" at "+DateTime;
			System.out.println("Entered SMS");
			//String sMessage = "Warking Fine On TestPage";
			sMessage = sMessage.replaceAll(" ", "%20");
			//System.out.println(sMessage);       
			String requestUrl  = "http://192.168.72.165:4001/axiomhttprec/pushlistener?userid=idecrmalerts&pwd=pa123ecrmalerts&dcode=AXISECRMALERTS&ctype=1&alert=1&msgtype=S&priority=2&sender=AxisBk&pno="+ReceiverNumber+"&msgtxt=" + sMessage;
			requestUrl = requestUrl.replaceAll(" ", "%20");
			System.out.println(requestUrl); 
			URL url = new URL(requestUrl);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cfm.axisb.com", 1050));
			HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
			System.out.println(uc.getResponseMessage());
			System.out.println(uc.getContent());
			uc.disconnect();  
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		*/
	}
	////////////////////////////////////////TAskKILL////////////////////
	public static void Taskkill() 
	{
		try
		{
			String processName = "chromedriver.exe";
			String processName1 = "chromedriver.exe";
			Process p = Runtime.getRuntime().exec("tasklist");
			BufferedReader reader = new BufferedReader(new
			InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) 
			{
				if (line.contains(processName))
				{
					Runtime.getRuntime().exec("taskkill /F /IM " + processName);
				}
				if (line.contains(processName1))
				{
					Runtime.getRuntime().exec("taskkill /F /IM " + processName1);
				}
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////// Whatsapp  SMS Sending ////////////////////////////////////////////////////////////////////
	
	public static void Whatsapp(String sMessage) 
	{
		/*
	    try 
	    {
	    	//Monitoring_FrameWork.ScriptName = "App";
	    	//Monitoring_FrameWork.PageName = "Page";
	        //String sMessage = "Warking Fine On TestPage";
	    	System.out.println("Entered Whatsapp");
	        sMessage = sMessage.replaceAll(" ", "%20");
	        String Appname = Monitoring_FrameWork.ScriptName.replaceAll(" ", "%20");
	        String Pagename = Monitoring_FrameWork.PageName.replaceAll(" ", "%20");
	        System.out.println(sMessage);  
	        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cfm.axisb.com", 1050));	        
	        String requestUrl  = "http://180.149.245.142:8084/test/perfil.php?pais="+Appname+"&numero="+Pagename+"&mensaje=" + sMessage;
	        System.out.println(requestUrl);
	        URL url = new URL(requestUrl);
	        HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
	        System.out.println(uc.getResponseMessage());
	        System.out.println(uc.getContent());
	        uc.disconnect();
	        
	        String requestUrl1  = "http://180.149.245.142:8084/test/perfil1.php?pais="+Appname+"&numero="+Pagename+"&mensaje=" + sMessage;
	        System.out.println(requestUrl1);
	        URL url1 = new URL(requestUrl1);
	        HttpURLConnection uc1 = (HttpURLConnection)url1.openConnection(proxy);
	        System.out.println(uc1.getResponseMessage());
	        System.out.println(uc1.getContent());
	        uc1.disconnect(); 
	        
	        
	        String requestUrl2  = "http://180.149.245.142:8084/test/perfil2.php?pais="+Appname+"&numero="+Pagename+"&mensaje=" + sMessage;
	        System.out.println(requestUrl2);
	        URL url2 = new URL(requestUrl2);
	        HttpURLConnection uc2 = (HttpURLConnection)url2.openConnection(proxy);
	        System.out.println(uc2.getResponseMessage());
	        System.out.println(uc2.getContent());
	        uc2.disconnect(); 
	    } 
	    catch(Exception ex) 
	    {
	      ex.printStackTrace();
	    }*/ 	    
	}
	    
}
	
		
		


////  Main Class End