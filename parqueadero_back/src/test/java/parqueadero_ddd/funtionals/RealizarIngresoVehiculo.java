package parqueadero_ddd.funtionals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RealizarIngresoVehiculo {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		File file = new File("src/test/resources/");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()+"/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:4200/");
	}

	@Test
	public void testUntitledTestCase() throws Exception {
		driver.findElement(By.id("placa")).click();
		driver.findElement(By.id("placa")).clear();
		driver.findElement(By.id("placa")).sendKeys("TRO549");
		driver.findElement(By.id("tipoVehiculo")).click();
		new Select(driver.findElement(By.id("tipoVehiculo"))).selectByVisibleText("MOTO");
		driver.findElement(By.id("tipoVehiculo")).click();
		driver.findElement(By.id("cilindraje")).click();
		driver.findElement(By.id("cilindraje")).clear();
		driver.findElement(By.id("cilindraje")).sendKeys("520");
		driver.findElement(By.xpath("//button[@type='button']")).click();
		driver.findElement(By.xpath("//app-parqueadero/div/div/div/strong")).click();
		assertEquals("Placa: TRO549", driver.findElement(By.xpath("//app-parqueadero/div/div/div/strong")).getText());

	}

	@Test
	  public void testConsulta() throws Exception {
	    driver.get("http://localhost:4200/");
	    driver.findElement(By.id("TRO549")).click();
	    driver.findElement(By.id("retirarVehiculo")).click();
	    assertEquals("Retiro exitoso", driver.findElement(By.xpath("//ngb-modal-window/div/div/div[2]/strong")).getText());
	  }

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
