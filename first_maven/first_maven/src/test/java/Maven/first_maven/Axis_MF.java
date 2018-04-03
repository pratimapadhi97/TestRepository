package Maven.first_maven;

import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.sikuli.script.Screen;

//import com.asprise.ocr.Ocr;

public class Axis_MF {

	static int status=0;
	static double starttime;
	static String parent;
	static int i=1;
	static int deftime=30;
	static String Otp_Input;
	static int availability_alert=1;
	static int ResponseTime_alert=1;
	static String filepath =System.getProperty("user.dir");
	static int defintime=90;
	static int maxwait=90;
	//static String imagepath="/home/apmosysmon09/workspace/Axis MF/";
	
	public static void test(String nextstep) throws Exception
	{
		
		UIManager UI=new UIManager();
		java.awt.Frame frame = new java.awt.Frame();
		Thread.sleep(2000);
		//WebDriver driver=new FirefoxDriver();
		frame.setVisible(false);
		frame.toFront();
		UI.put("OptionPane.background", Color.white);
		UI.put("Panel.background", Color.CYAN);
        
		//Screen deb=new Screen();
		String imagepath="/home/apmo8/Pictures/";

		System.setProperty("webdriver.chrome.driver", "/home/apmosys/Desktop/Driver/chromedriver");
       DesiredCapabilities cap=new DesiredCapabilities();
     
		WebDriver driver=new ChromeDriver(cap);
		Actions action=new Actions(driver);
		//Screen sikuli=new Screen();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,maxwait);
		Monitoring_FrameWork.writefile(nextstep);

		try
		{
			
			starttime=Monitoring_FrameWork.StartTime();
			//driver.get("https://www.axismf.com/");
			driver.get("https://www.axismf.com");
			//driver.findElement(By.tagName("html")).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("investMenu"))));
			WebElement home=driver.findElement(By.className("investMenu"));
			//WebElement home=driver.findElement(By.id("Home_box_Menu"));
			//if(driver.findElement(By.className("investMenu")).isDisplayed())
			if(home!=null)
			{
				status=1;
			}
			else
			{
				status=0;
			}	

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Home Page", status, maxwait, availability_alert, ResponseTime_alert);
		}
		try
		{
			status=0;
			WebElement investwus=driver.findElement(By.className("investMenu"));
			action.moveToElement(investwus).build().perform();
			driver.manage().deleteAllCookies();
			starttime=Monitoring_FrameWork.StartTime();
			driver.get("https://www.axismf.com/Online/ExistingInvestor.aspx");
			//driver.findElement(By.linkText("EXISTING INVESTOR")).click();
			//	driver.get("https://www.axismf.com/Online/ContinueToLogin.aspx");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnLogin")));
			if(driver.findElement(By.name("btnLogin")).isDisplayed())
			{
				status=1;
			}
			else

			{
				status=0;
			}			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Login Page", status, maxwait, availability_alert, ResponseTime_alert);
		}
		/*try
		{
			starttime=0;		

			driver.findElement(By.className("new_website_btn")).click();
			starttime=Monitoring_FrameWork.StartTime();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
			if(driver.findElement(By.id("txtUserName")).isDisplayed())
			{
				status=1;
			}
			else
			{
				status=0;
			}			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Login Page", status, maxwait, availability_alert, ResponseTime_alert);
		}*/
		try
		{
			status=0;
			driver.findElement(By.id("txtUserName")).clear();
			//Thread.sleep(2000);
			
			
		   
	//driver.findElement(By.id("txtUserName")).sendKeys("aniruddhupadhyay@gmail.com");//DONT USE THIS ID (ID N PWD CHANGED)
	       driver.findElement(By.id("txtUserName")).sendKeys("apmosysreport@gmail.com");//("apmosysreport@gmail.com")//sanin123in2001@gmail.com
	     		//+ "");
			driver.findElement(By.id("txtPassword")).clear();
	    
		   
       
		//driver.findElement(By.id("txtPassword")).sendKeys("");
	     driver.findElement(By.id("txtPassword")).sendKeys("chase@123");//("chase@123")//sat@1903
			driver.findElement(By.id("txt_Captcha")).clear();
			//deb.find(imagepath+"cancel.png").click();
			//Thread.sleep(2000);
			String captcha=JOptionPane.showInputDialog(frame,"Enter CAPTCHA");
			//String captcha=Captcha.reCap(driver).trim();
			Thread.sleep(2000);

			//String f=JOptionPane.showInputDialog(parentComponent, message)
			//String captcha = driver.findElement(By.id("Image1")).getText();
			//System.out.println(captcha);
	        driver.findElement(By.xpath(".//*[@id='txt_Captcha']")).sendKeys(captcha);
			////////////////////////////////////////////////////////////////////////////////////////////
			/*try
			{
			WebElement imageUrl=driver.findElement(By.xpath("//*[@id='Image1']"));
			 System.out.println("Image source path : \n"+ imageUrl);
			 Actions action1 = new Actions(driver).contextClick(imageUrl);
				action1.build().perform();
				Robot robot=new Robot();
				Thread.sleep(500);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(500);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(500);
				robot.keyPress(KeyEvent.VK_ENTER); 
				robot.keyRelease(KeyEvent.VK_ENTER);
				//Thread.sleep(1000);
				//sikuli.find(imagepath+"SaveAs.png").click();
				Thread.sleep(2000);
				if(sikuli.exists(imagepath+"Captcha.png")!=null)
				{
				sikuli.find(imagepath+"Captcha.png").click();
				Thread.sleep(2000);

				}
				if(sikuli.exists(imagepath+"captcha (1)")!=null)
				{@
					sikuli.find(imagepath+"captcha (1)").click();
					Thread.sleep(2000);
				}
				sikuli.find(imagepath+"Save.png").click();
				Thread.sleep(1000);
				sikuli.find(imagepath+"Replace.png").click();
				 Ocr.setUp(); // one time setup
				    Ocr ocr = new Ocr(); // create a new OCR engine
				    ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
				    String captcha1 = ocr.recognize(new File[] {new File("C:\\Users\\pc 22\\Downloads\\captcha.gif")},Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
				    driver.findElement(By.id("txt_Captcha")).sendKeys(captcha);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}*/
			/*System.out.println("capthca is="+captcha);
			driver.findElement(By.id("txt_Captcha")).sendKeys(captcha);
			starttime=Monitoring_FrameWork.StartTime();
			driver.findElement(By.id("btnLogin")).click();*/
	      

			WebElement element=driver.findElement(By.id("btnLogin"));
			element.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dv_KYC_1']/a")));
			if(driver.findElement(By.xpath("//*[@id='dv_KYC_1']/a")).isDisplayed())
			{
				status=1;
			}
			else
			{
				status=0;
			}
		}	

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Status is  "+status);
			Monitoring_FrameWork.SaveResult(starttime, "After Login Page", status, maxwait, availability_alert, ResponseTime_alert);
		}

		
		try
		{
			status=0;
			starttime=Monitoring_FrameWork.StartTime();
			driver.findElement(By.xpath("//*[@id='dv_KYC_1']/a")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("dvLoading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt_DistributorARN")));
		//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='ancNoFolio']/img")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("dvLoading")));
			if(driver.findElement(By.id("txt_DistributorARN")).isDisplayed())
			
				
				
			{
				status=1;
			}
			else
			{
				status=0;
			}
		}	

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Folio & Distributor Details Page", status, maxwait, availability_alert, ResponseTime_alert);
		}
		try
		{	
			status=0;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("dvLoading")));
			
			/*Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ancNoFolio']/img")));
			Thread.sleep(30000);
			driver.findElement(By.xpath("//*[@id='ancNoFolio']/img")).click();////*[@id="ancNoFolio"]/img*/
			
			Thread.sleep(2000);
			WebElement elmnt=driver.findElement(By.xpath("//*[@id='div_Step1_Folio_Broker_Details']/a"));
			
			Thread.sleep(10000);
			
			WebElement elmnt2=driver.findElement(By.xpath("//*[@id='div_Direct']/a/img"));
			/*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elmnt2);
			Thread.sleep(500); */

			elmnt2.click();
			
			starttime=Monitoring_FrameWork.StartTime();
			driver.findElement(By.xpath("//*[@id='div_Step1_Folio_Broker_Details']/a")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("findSolCat")));
			if(driver.findElement(By.id("findSolCat")).isDisplayed())
			{
				status=1;
			}
			else
			{
				status=0;
			}
		}	

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Scheme Details Page", status, maxwait, availability_alert, ResponseTime_alert);
		}

/*try
		{	
			status=0;
			driver.findElement(By.xpath("//*[@id='Div4']/div/div/div[1]/div/div[1]/div[1]/div/ins")).click();
			//Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='Div4']/div/div/div[1]/div/div[2]/div[2]/div/ins")).click();
			//Thread.sleep(1000);
			starttime=Monitoring_FrameWork.StartTime();
			driver.findElement(By.linkText("SUBMIT")).click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='div_Step2_SchemeDetails']/div[1]/div")));
			//if(driver.findElement(By.xpath("//*[@id='div_Step2_SchemeDetails']/div[1]/div")).isDisplayed())
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("findSolCat")));
			if(driver.findElement(By.id("findSolCat")).isDisplayed())
			{
				status=1;
			}
			else
			{
				status=0;
			}
		}	

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Scheme Details Page", status, maxwait, availability_alert, ResponseTime_alert);
		}
*/
try
{
	status=0;
	WebElement fundtp=driver.findElement(By.id("selectMe"));
	Select fundsel=new Select(fundtp);
	fundsel.selectByIndex(2);
	//Thread.sleep(1000);

	WebElement element = driver.findElement(By.id("findSolCat"));
	starttime=Monitoring_FrameWork.StartTime();
	executor.executeScript("arguments[0].click();", element);			
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='PlanDrp_0']")));//*[@id="PlanDrp_0"]
	//if(driver.findElement(By.xpath("//*[@id='PlanDrp_0']")).isDisplayed())
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PlanDrp_1")));
	//if(driver.findElement(By.id("PlanDrp_1")).isDisplayed())
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ancPurchase_1")));
	if(driver.findElement(By.id("ancPurchase_1")).isDisplayed())
		
	{
		status=1;
	}
	else
	{
		status=0;
	}
	}	
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		Monitoring_FrameWork.SaveResult(starttime, "Mutual Fund Result Page", status, maxwait, availability_alert, ResponseTime_alert);
	}


		try
		{	
			status=0;
			WebElement fundtp=driver.findElement(By.id("PlanDrp_1"));
			Select fundsel=new Select(fundtp);
			fundsel.selectByIndex(1);
			//Thread.sleep(1000);
			WebElement element = driver.findElement(By.id("ancPurchase_1"));
			executor = (JavascriptExecutor)driver;
			//Thread.sleep(1000);
			executor.executeScript("arguments[0].click();", element);
			/*status=0;
			driver.findElement(By.xpath("//*[@id='PlanDrp_1']")).click();
			new Select(driver.findElement(By.xpath("//*[@id='PlanDrp_1']"))).selectByIndex(0);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='ancPurchase_1']")).click();*/
			
			driver.findElement(By.id("ss_txtAmt_Lumsum")).clear();
			driver.findElement(By.id("ss_txtAmt_Lumsum")).sendKeys("100");
		//	Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Invest")));
			starttime=Monitoring_FrameWork.StartTime();
			driver.findElement(By.linkText("Invest")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btn_MakePayment_L']")));//*[@id="btn_MakePayment_L"]
			if(driver.findElement(By.xpath("//*[@id='btn_MakePayment_L']")).isDisplayed())
			{
				status=1;
			}
			else
			{
				status=0;
			}
		}	

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Select Payment Option Page", status, maxwait, availability_alert, ResponseTime_alert);
		}

		/*try
		{	
			status=0;
			new Select(driver.findElement(By.id("drpGender_AP1"))).selectByIndex(1);
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("drpMarital_Status_AP1"))).selectByIndex(1);
			Thread.sleep(1000);
			
			driver.findElement(By.id("txt_AP1_Pan_number")).clear();
		    driver.findElement(By.id("txt_AP1_Pan_number")).sendKeys("BASPP8297H");
			new Select(driver.findElement(By.id("drp_AP1_Nationality"))).selectByVisibleText("INDIA");
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("drpOccupation_AP1"))).selectByIndex(1);
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("drpCategory_AP1"))).selectByIndex(2);
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("drpCountry_of_birth_AP1"))).selectByVisibleText("INDIA");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='txt_AP1_City_of_Birth']")).sendKeys("MUMBAI");
			Thread.sleep(1000);
			driver.findElement(By.id("txt_AP1_adhar_number")).sendKeys("1111111111111111");
			Thread.sleep(1000);
			//driver.findElement(By.xpath("//*[@id='txt_AP1_Correspondence_Address1']")).sendKeys("abcd");
			driver.findElement(By.xpath("//*[@id='txt_AP1_Correspondence_Address2']")).sendKeys("dr");
			driver.findElement(By.xpath("//*[@id='txt_AP1_Correspondence_Address3']")).sendKeys("cr");

         //  Thread.sleep(2000);
			new Select(driver.findElement(By.id("drpCountry_of_Res_AP1"))).selectByVisibleText("INDIA");
			//Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='txt_AP1_Correspondence_Address1']")).sendKeys("Raju");
			driver.findElement(By.xpath("//*[@id='txt_AP1_State']")).sendKeys("MAHARASHTRA");     
			driver.findElement(By.xpath("//*[@id='txt_AP1_City']")).sendKeys("MUMBAI");
			driver.findElement(By.xpath("//*[@id='txt_AP1_Pin_Code']")).sendKeys("400001");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='chk_Permanent_1_Address']")).click();
			
          // Thread.sleep(2000);
			new Select(driver.findElement(By.id("drpPermanent_Country_of_birth_AP1"))).selectByVisibleText("INDIA");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='txt_AP1_Permanent_Address2']")).sendKeys("wr");
			driver.findElement(By.xpath("//*[@id='txt_AP1_Permanent_Address3']")).sendKeys("yu");

            Thread.sleep(2000);
			//driver.findElement(By.id("//*[@id='div_AP1_Gross_Annual_income']/div[3]/a/img")).click();
			Thread.sleep(2000);

			driver.findElement(By.id("txt_AP1_City_of_Birth")).clear();
			driver.findElement(By.id("txt_AP1_City_of_Birth")).sendKeys("PATANA");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='txt_AP1_Correspondence_Address2']")).sendKeys("dr");
			driver.findElement(By.xpath("//*[@id='txt_AP1_Correspondence_Address3']")).sendKeys("cr");

Thread.sleep(3000);
			WebElement el2=driver.findElement(By.xpath("//*[@id='div_AP1_Gross_Annual_income']/div[2]/a/img"));
			executor.executeScript("arguments[0].click();",el2);
			//if(driver.findElement(By.xpath("//*[@id='div_AP1_Gross_Annual_income']/div[1]")).isDisplayed())
			//driver.findElement(By.xpath("//*[@id='div_AP1_Gross_Annual_income']/div[3]/a/img")).click();

//Thread.sleep(2000);
			driver.findElement(By.id("txt_Nominee_1_Name")).clear();
			driver.findElement(By.id("txt_Nominee_1_Name")).sendKeys("KOLKATA");
			driver.findElement(By.id("txt_Nominee_1_Date_of_birth")).click();
			Thread.sleep(2000);
			WebElement cal=driver.findElement(By.id("ui-datepicker-div"));
			cal.findElement(By.linkText("1")).click();
			//Thread.sleep(2000);
			driver.findElement(By.id("txt_Nominee_1_Gaurdian_name")).clear();
			driver.findElement(By.id("txt_Nominee_1_Gaurdian_name")).sendKeys("Bengol");
		//	Thread.sleep(2000);
			driver.findElement(By.id("txt_Nominee_1_Address")).clear();
			driver.findElement(By.id("txt_Nominee_1_Address")).sendKeys("Bengol");
			driver.findElement(By.id("txt_Nominee_1_Allocation")).clear();
			driver.findElement(By.id("txt_Nominee_1_Allocation")).sendKeys("100");
			//Thread.sleep(2000);
			driver.findElement(By.id("txt_IFSC_Code")).clear();
			driver.findElement(By.id("txt_IFSC_Code")).sendKeys("UBIN");
			//Thread.sleep(5000);
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
		//	Thread.sleep(2000);
			driver.findElement(By.id("txt_BankAccNo")).clear();
			driver.findElement(By.id("txt_BankAccNo")).sendKeys("1111111111111111");

			new Select(driver.findElement(By.id("drp_BankAccType"))).selectByIndex(1);
			driver.findElement(By.id("txt_Bank_Pin")).clear();
			driver.findElement(By.id("txt_Bank_Pin")).sendKeys("111111");
		//	Thread.sleep(1000);
			//open upload window
			driver.findElement(By.id("file_upload")).sendKeys(filepath+"open.png");
			//
//			StringSelection ss = new StringSelection("/home/apmosysmon09/workspace/Loop/Images");
//			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			//imitate mouse event like enter , ctrl+c, ctrl+V
//			Robot robot = new Robot();
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_V);
//			robot.keyRelease(KeyEvent.VK_V);
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.keyRelease(KeyEvent.VK_ENTER);
			/////////////////////////////////End/////////////////////

			//sikuli.find("/home/apmosysmon09/Music/browser.png").click();
			//deb.find(imagepath+"browser.png").click();
			
			deb.wait("/home/apmosys/Pictures/select.png",defintime);
			deb.click("/home/apmosys/Pictures/select.png");
			Thread.sleep(1000);
			//sikuli.find("/home/apmosysmon09/Music/captcha.png").click();
			//deb.click("/home/apmo8/Pictures/screen.png");
			//Thread.sleep(2000);
		    //sikuli.find("/home/apmosysmon09/Music/open.png").click();
			deb.click("/home/apmosys/Desktop/Web_Monitoring/Webmonitoring/open.png");
		//	
			starttime=Monitoring_FrameWork.StartTime();
		    driver.findElement(By.xpath("//*[@id='InvestorPlan']/a")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_MakePayment_L")));
			if(driver.findElement(By.id("btn_MakePayment_L")).isDisplayed())
			{
				status=1;
			}
			else
			{
				status=0;
			}
		}	

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Select Payment Option Page", status, maxwait, availability_alert, ResponseTime_alert);
		}*/
		try
		{
			status=0;
			new Select(driver.findElement(By.id("drpNetBanks"))).selectByIndex(1);
		//	Thread.sleep(2000);
			WebElement elmnt=driver.findElement(By.xpath("//*[@id='div_Net_TC_Lumpsum']/div[1]/div[1]/div/ins"));
			executor.executeScript("arguments[0].click();", elmnt);	
		//	Thread.sleep(2000);
			WebElement elmnt1=driver.findElement(By.xpath("//*[@id='btn_MakePayment_L']"));
			starttime=Monitoring_FrameWork.StartTime();
			executor.executeScript("arguments[0].click();", elmnt1);	
			//wait.until(ExpectedConditions.visibilityOfElementLoThread.sleep(2000);cated(By.xpath(".//*[@id='username']")));
			//if(driver.findElement(By.xpath("Thread.sleep(2000);.//*[@id='username']")).isDisplayed())

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='AuthenticationFG.USER_PRINCIPAL']")));
			if(driver.findElement(By.xpath("//*[@id='AuthenticationFG.USER_PRINCIPAL']")).isDisplayed())////*[@id='AuthenticationFG.USER_PRINCIPAL']
			{
				status=1;
			}
			else
			{
				status=0;
			}

		}

catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Monitoring_FrameWork.SaveResult(starttime, "Payment Gateway Page", status, maxwait, availability_alert, ResponseTime_alert);
		}
		driver.close();
	}	

	public static void main(String args[]) throws Exception
	{
		
			while(true)
			{
				Axis_MF.test("1");
			}				
		
		


	}
}
