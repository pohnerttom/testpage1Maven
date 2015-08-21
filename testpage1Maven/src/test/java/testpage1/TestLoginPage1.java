package testpage1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginPage1 {

	public By inputUser = By.name("inputUser");
	public By inputPass = By.name("inputPass");
	public By submitButton = By.xpath("/html/body/div/div/div/form/button");
	public By msgTxt1 = By.xpath("/html/body/div/div[1]/div");
	public By msgTxt2 = By.xpath("/html/body/div/div[2]/div");
	public By logoffButton = By.xpath("/html/body/div/div[2]/div/a/button");
	public WebDriver driver = new FirefoxDriver();

	@Test
	public void testPage() {
		driver.get("http://www.itguy.cz/loginpage.php");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// No input typed
		driver.findElement(inputUser).sendKeys("");
		driver.findElement(inputPass).sendKeys("");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(msgTxt1).getText(), "Login failed - Username is missing");
		Assert.assertEquals(driver.findElement(msgTxt2).getText(), "Login failed - Password is missing");

		// Wrong credentials
		driver.findElement(inputUser).sendKeys("testUser");
		driver.findElement(inputPass).sendKeys("testPass");
		driver.findElement(submitButton).click();
		driver.findElement(msgTxt1).getText();
		Assert.assertEquals(driver.findElement(msgTxt1).getText(), "Login failed - Incorrect Username/Password");

		// Only user typer
		driver.findElement(inputUser).sendKeys("testUser");
		driver.findElement(submitButton).click();
		driver.findElement(msgTxt1).getText();
		Assert.assertEquals(driver.findElement(msgTxt1).getText(), "Login failed - Password is missing");

		// Only pass typed
		driver.findElement(inputPass).sendKeys("testPass");
		driver.findElement(submitButton).click();
		driver.findElement(msgTxt1).getText();
		Assert.assertEquals(driver.findElement(msgTxt1).getText(), "Login failed - Username is missing");

		// Good credential
		driver.findElement(inputUser).sendKeys("user");
		driver.findElement(inputPass).sendKeys("1234");
		driver.findElement(submitButton).click();
		driver.findElement(msgTxt1).getText();
		Assert.assertEquals(driver.findElement(msgTxt1).getText(), "Login successful");

		// Logoff
		driver.findElement(logoffButton).click();
		driver.findElement(msgTxt1).getText();
		Assert.assertEquals(driver.findElement(submitButton).getText(), "Submit");

		// Close browser
		driver.quit();
	}

}
