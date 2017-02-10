package com.appium.szzc2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AppLibs{

public WebElement findElement(AndroidDriver driver,String localstyle,String value)  {
	WebElement result = null;
	try{
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	switch(localstyle)
	{
	case "id": 
		result = driver.findElement(By.id(value));
		break;
	case "name": 
		result = driver.findElement(By.name(value));
		break;
	case "xpath": 
		result = driver.findElement(By.xpath(value));
		break;
	case "class": 
		result = driver.findElement(By.className(value));
		break;
		
	}
	}catch(Exception e){


			}

	return result;
}

public List<WebElement> findElements(AndroidDriver driver,String localstyle,String value){
	
	List<WebElement> result = null;
	switch(localstyle)
	{
	case "id": 
		result = driver.findElements(By.id(value));
		break;
	case "name": 
		result = driver.findElements(By.name(value));
		break;
	case "xpath": 
		result = driver.findElements(By.xpath(value));
		break;
	}
	return result;
}

public void execute(AndroidDriver driver,WebElement element,String exemethod,String value) 
{
	try{
	switch(exemethod)
	{
	case "click": 
		element.click();
		break;
	case "sendKeys": 
		element.sendKeys(value);
		break;
	case "swipe":
		swipefuc(driver,value);
		break;
	case "waitelement":
		waitelement(driver,value);
		break;
		
	}
	}catch(Exception e){
		
	}
}

public void swipefuc(AndroidDriver driver,String value){
	try{
		Thread.sleep(3000);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	driver.swipe(500,600,80,600,5000);
}

public String checktext(AndroidDriver driver,String localstyle,String localvalue,String checkname){
	//WebElement check_element = driver.findElement(By.id(elementID));
	
	List<WebElement> check_elements = findElements(driver,localvalue,localvalue);
	int i=0;
	for(;i<check_elements.size();i++){
	Assert.assertTrue(check_elements.get(i).getAttribute("text").contains(checkname));
	System.out.println("The text is "+check_elements.get(i).getAttribute("text"));
	}
	return check_elements.get(i-1).getAttribute("text");//返回最后一条
}

public WebElement element(AndroidDriver driver,int y) throws IOException{
	WebElement result = findElement(driver,Xlsfile.readxls("szzc_object",2, y),Xlsfile.readxls("szzc_object",3, y));
	return result;
}

public WebElement element(AndroidDriver driver,int y,String value) throws IOException{
	WebElement result = findElement(driver,Xlsfile.readxls("szzc_object",2, y),value);
	return result;
}

public void filterset(AndroidDriver driver,String sub1,String sub2){
WebElement filter_sex = driver.findElement(By.id("com.yougou:id/textView_sift_"+sub1));
filter_sex.click();
WebElement filter_sex_man = driver.findElement(By.name(sub2));
filter_sex_man.click();
WebElement filter_sex_select = driver.findElement(By.id("com.yougou:id/popup_ok"));
filter_sex_select.click();
}

public void waitelement(final AndroidDriver driver,final String str){
	System.out.println("Wait_Element START!!! "+"str is: "+str);
	WebDriverWait wait = new WebDriverWait(driver, 60);
	WebElement e= wait.until(new ExpectedCondition<WebElement>() {
		// @Override
		public WebElement apply(WebDriver d) {

		WebElement d2 = null;
		try {
			d2 = element(driver, Xlsfile.search(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wait_Element Finished!!!");
		return d2;

		}

	});	
}

}
