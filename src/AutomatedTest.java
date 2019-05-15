/*
 * Question 2
 */
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomatedTest {

	
	public static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		
		//this property needs to point to path of your geckodriver. please provide
		//the path to your geckodriver in order to run this test. 
		//A copy of the file exists in this directory.
		System.setProperty("webdriver.gecko.driver", "/Users/Carlos/Downloads/geckodriver");
		driver = new FirefoxDriver();
		
		driver.get("https://www.weightwatchers.com/us/");
		
		if(driver.getTitle().contains("WW (Weight Watchers): Weight Loss & Wellness Help")) {
		    //Pass
		    System.out.println("Page title contains \"WW (Weight Watchers): Weight Loss & Wellness Help\" ");
		}
		else {
		    //Fail
		    System.out.println("Page title doesn't contains \"WW (Weight Watchers): Weight Loss & Wellness Help\" ");
		}
		
		WebElement findAStudioLink;
		findAStudioLink = driver.findElement(By.linkText("Find a Studio"));
		findAStudioLink.click();
		
		Thread.sleep(2000);
		
		if(driver.getTitle().contains("Find WW Studios & Meetings Near You | WW USA")) {
		    //Pass
		    System.out.println("Page title contains \"Find WW Studios & Meetings Near You | WW USA\" ");
		}
		else {
		    //Fail
		    System.out.println("Page title doesn't contain \"Find WW Studios & Meetings Near You | WW USA\" ");
		    //this step fails because there is an HTML element '&nbsp;' after WW that is used to insert an empty space
		}
		
		WebElement searchBox;
		searchBox = driver.findElement(By.id("meetingSearch"));
		searchBox.sendKeys("10011");
		searchBox.submit();
		
		Thread.sleep(2000);
		
		WebElement locationTitle;
		locationTitle = driver.findElement(By.className("location__name"));
		String locTitle = locationTitle.findElement(By.cssSelector("span[ng-if='!linkName'")).getText();
		System.out.println(locTitle);
		
		WebElement locationDistance;
		locationDistance = driver.findElement(By.className("location__distance"));
		String locDistance = locationDistance.getText();
		System.out.println(locDistance);
		
		WebElement listItem;
		listItem = driver.findElement(By.id("ml-1180510"));
		listItem.click();
		
		locationTitle = driver.findElement(By.className("location__name"));
		String newLocTitle = locationTitle.findElement(By.cssSelector("span[ng-if='!linkName']")).getText();
		
		if(newLocTitle.equals(locTitle)) System.out.println("Location Title Matches");
		else System.out.println("Location title is different");
		
		WebElement opHours;
		opHours = driver.findElement(By.className("meeting-hours"));
		opHours = opHours.findElement(By.className("hours-list--currentday"));
		String hourList1 = opHours.findElement(By.className("hours-list-item-hours")).getText();
		System.out.println(hourList1);
		
		printMeetings("Thu");
		
		driver.quit();
	}
	
	public static void printMeetings(String day) {
		
		HashMap<String, Integer> weekDays = new HashMap<>();
		HashMap<String, Integer> atendees = new HashMap<>();
		
		weekDays.put("Sun", 1);
		weekDays.put("Mon", 2);
		weekDays.put("Tue", 3);
		weekDays.put("Wed", 4);
		weekDays.put("Thu", 5);
		weekDays.put("Fri", 6);
		weekDays.put("Sat", 7);
		
		WebElement scheduleTable;
		scheduleTable = driver.findElement(By.className("meeting-schedule__schedule"));
		scheduleTable = scheduleTable.findElement(By.className("schedule-detailed-day"));
		String dayOfWeek = scheduleTable.findElement(By.xpath("(//div[@class='schedule-detailed-day-label'])["+weekDays.get(day)+"]")).getText();
		String apptForTheDay = scheduleTable.findElement(By.xpath("(//div[@class='schedule-detailed-day-meetings'])["+weekDays.get(day)+"]")).getText();
		System.out.println(dayOfWeek);
		String[] nameArr = apptForTheDay.split("(?=\\d)(.*)");
		
		for(int i=0; i<nameArr.length; i++) {
			if(nameArr[i].equals("")) {
				i++;
			}
			else if(atendees.containsKey(nameArr[i])) {
				atendees.put(nameArr[i], atendees.get(nameArr[i]) + 1);
			}
			else {
				atendees.put(nameArr[i], 1);
			}
		}
		
		for(String key : atendees.keySet()) {
			System.out.println(key +" "+ atendees.get(key));
		}		
	}

}
