package org.example;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class OMCTest {
	
@Test
public static void SearchSKU() throws InterruptedException, IOException{
	
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		String date1= dateFormat.format(new Date());
		//String reportPath = "C:\\Users\\cychau\\git\\ACMM Testing\\ACMM testing\\Report\\"+date1+"report.html";
		System.out.println("start here ");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\cychau\\Katalon Studio\\SeleniumToKatalon\\Include\\resources\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.get("https://alprosit.qragoracloud.com/alpro-sit/login");
		
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		
		System.out.println("Window ID : "+winHandleBefore);
		// Perform the click operation that opens new window
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("OPR");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("QRRA1888");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[@id='login']")).click();
		Thread.sleep(6000);
		
		Set<String> handles =  driver.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(winHandleBefore))
		          {
		          driver.switchTo().window(windowHandle);
					/* <!--Perform your operation here for new window--> */

		          System.out.println("Window ID : "+windowHandle);
				    
					driver.findElement(By.xpath("//div[@id='side_menu']/div[2]/div/div[4]")).click();
					Thread.sleep(4000);
					driver.findElement(By.xpath("//div[@id='agr_master_page']/div/div/div/div/div/div/div[2]/div")).click();
					Thread.sleep(4000);
					driver.findElement(By.xpath("//table[@id='result']/div/div/div[2]/a")).click();
					Thread.sleep(4000);
					driver.findElement(By.xpath("//input[@id='search_sku']")).sendKeys("100032416");
					Thread.sleep(4000);
					driver.findElement(By.xpath("//a[@id='inquiry_search']/i")).click();
					Thread.sleep(4000);

					String date2= dateFormat.format(new Date());
					
					File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					String image = "C:\\Users\\cychau\\Katalon Studio\\SeleniumToKatalon\\Reports\\"+date2+"screenshot.png";
					File saveImage = new File(image);
					FileUtils.copyFile(src, saveImage);
					
		          driver.close(); //closing child window
		         driver.switchTo().window(winHandleBefore); //ctrl to parent window
		         driver.quit();
		         System.out.println("Done sent email ");
		          }
		       }
	
}


}
