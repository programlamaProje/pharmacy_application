/*Muhammed Baki Caki - 13.12.2021
 * BIL359 Assignment Test Automation Project 
 * */
package eczane_Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseSetup {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		// set desired capabilities
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("deviceName", "Pixel_11.0");
		caps.setCapability("udid", "emulator-5554");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "11.0");
		caps.setCapability("appPackage", "com.example.roomexample");
		caps.setCapability("appActivity", ".LoginActivity");
		caps.setCapability("noReset", "false");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void test() {	//tum test metotlari burdan cagriliyor.
		testLogin();
		test_ilacEkle();
		test_dusukStok();
		test_ilacAra();
		test_ilacListesi();

	}

	public void testLogin() {	//giris ekraný ezcane ismi ve resmi duzenleme
		
		// isim duzenleme
		MobileElement more_options = (MobileElement) driver.findElementByAccessibilityId("More options");
		more_options.click();
		MobileElement isim_duzenle = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")));
		isim_duzenle.click();
		MobileElement eczane_isim = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.roomexample:id/eczIsim")));
		eczane_isim.sendKeys("Esen");
		MobileElement kaydet = (MobileElement) driver.findElementById("com.example.roomexample:id/login_button");
		kaydet.click();

		// fotograf ekleme
		wait.until(ExpectedConditions.elementToBeClickable(more_options)).click();
		MobileElement resmi_duzenle = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")));
		resmi_duzenle.click();
		MobileElement profil_resmi = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//android.widget.LinearLayout[@content-desc=\"eczane.jpg, 51.69 kB, 9:55 AM\"]/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView[2]")));
		profil_resmi.click();
		MobileElement giris_butonu = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.roomexample:id/login_button")));
		giris_butonu.click();
	}

	public void test_ilacEkle() { //ilac ekleme sayfasý - sirasiyla 2 firma, 2 kategori ve 3 ilac ekleniypr 
		
		String firma1 = "Bayer";
		String firma2 = "Novartis";

		String ilac1 = "Minoset";// bayer agrý kesici
		String ilac2 = "Cataflam"; // novartis agrý kesici
		String ilac3 = "Sirdalud"; // novartis kas gevsetici
	

		MobileElement yeni_ilac_ekle = (MobileElement) wait.until(
				ExpectedConditions.elementToBeClickable(By.id("com.example.roomexample:id/nav_menu_add_medicine_btn")));
		yeni_ilac_ekle.click();

		// ---- Firma Ekle ----
		//1-Bayer
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // implicit wait
		MobileElement more_options = (MobileElement) driver.findElementByAccessibilityId("More options");
		more_options.click();
		MobileElement firma_ekle = (MobileElement) driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
		firma_ekle.click();
		MobileElement firma_ismi = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText")));
		firma_ismi.sendKeys(firma1);
		MobileElement ok_button = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_button.click();
		
		//2-Novartis
		MobileElement more_options2 = (MobileElement) driver.findElementByAccessibilityId("More options");
		more_options2.click();
		MobileElement firma_ekle2 = (MobileElement) driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
		firma_ekle2.click();
		MobileElement firma_ismi2 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText")));
		firma_ismi2.sendKeys(firma2);
		MobileElement ok_button2 = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_button2.click();

		// -----Kategori Ekle----
		//1 - Agri kesici
		MobileElement more_options3 = (MobileElement) driver.findElementByAccessibilityId("More options");
		more_options3.click();
		MobileElement kategori_ekle = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")));
		kategori_ekle.click();
		MobileElement kategori_ismi = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText")));
		kategori_ismi.sendKeys("Aðrý kesici");
		MobileElement ok_button3 = (MobileElement) driver.findElementById("android:id/button1");
		ok_button3.click();
		
		//2 - Kas gevsetici
		MobileElement more_options4 = (MobileElement) driver.findElementByAccessibilityId("More options");
		more_options4.click();
		MobileElement kategori_ekle2 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")));
		kategori_ekle2.click();
		MobileElement kategori_ismi2 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText")));
		kategori_ismi2.sendKeys("Kas Gevsetici");
		MobileElement ok_button4 = (MobileElement) driver.findElementById("android:id/button1");
		ok_button4.click();

		
		// ilac1'i ekleme - stok deger sinirlari testi(0<x<200) 
		MobileElement ilac_ismi = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineName");
		ilac_ismi.sendKeys(ilac1);
		MobileElement firmasec = (MobileElement) driver.findElementById("com.example.roomexample:id/inputCompany");
		firmasec.click();
		MobileElement sec_firma1 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]")));
		sec_firma1.click();
		MobileElement stok = (MobileElement) driver.findElementById("com.example.roomexample:id/inputStock");
		stok.sendKeys("-1");
		MobileElement kategorisec = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineType");
		kategorisec.click();
		MobileElement sec_kategori1 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		sec_kategori1.click();
		MobileElement konum = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineLocation");
		konum.sendKeys("Raf 1 Sira 1");
		MobileElement listele_butonu = (MobileElement) driver
				.findElementById("com.example.roomexample:id/addMedicineButton");
		listele_butonu.click();
		wait.until(ExpectedConditions.elementToBeClickable(stok)).sendKeys("300");
		listele_butonu.click();
		wait.until(ExpectedConditions.elementToBeClickable(stok)).sendKeys("15");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		listele_butonu.click();

		// ilac3 ekleme
		MobileElement ilac_ismi3 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineName");
		ilac_ismi3.sendKeys(ilac3);
		MobileElement firmasec3 = (MobileElement) driver.findElementById("com.example.roomexample:id/inputCompany");
		firmasec3.click();
		MobileElement sec_firma3 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		sec_firma3.click();
		MobileElement stok3 = (MobileElement) driver.findElementById("com.example.roomexample:id/inputStock");
		stok3.sendKeys("50");
		MobileElement kategorisec3 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineType");
		kategorisec3.click();
		MobileElement sec_kategori3 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]")));
		sec_kategori3.click();
		MobileElement konum3 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineLocation");
		konum3.sendKeys("Raf 2 Sira 1");
		MobileElement listele_butonu3 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/addMedicineButton");
		listele_butonu3.click();

		// ilac2 ekleme
		MobileElement ilac_ismi2 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineName");
		ilac_ismi2.sendKeys(ilac2);
		MobileElement firmasec2 = (MobileElement) driver.findElementById("com.example.roomexample:id/inputCompany");
		firmasec2.click();
		MobileElement sec_firma2 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		sec_firma2.click();
		MobileElement stok2 = (MobileElement) driver.findElementById("com.example.roomexample:id/inputStock");
		stok2.sendKeys("100");
		MobileElement kategorisec2 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineType");
		kategorisec2.click();
		MobileElement sec_kategori2 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		sec_kategori2.click();
		MobileElement konum2 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/inputMedicineLocation");
		konum2.sendKeys("Raf 1 Sira 2");
		MobileElement listele_butonu2 = (MobileElement) driver
				.findElementById("com.example.roomexample:id/addMedicineButton");
		listele_butonu2.click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back();	//ana sayfaya don

	}

	public void test_dusukStok() { // dusuk stoklu olan tek veriyi(minoset - 15) kontrol et

		MobileElement dusuk_stok_butonu = (MobileElement) wait.until(
				ExpectedConditions.elementToBeClickable(By.id("com.example.roomexample:id/nav_menu_low_on_stock_btn")));
		dusuk_stok_butonu.click();
		MobileElement dusuk_stok_isim = (MobileElement) driver.findElementById("com.example.roomexample:id/lowOnStockMedicineName");
		MobileElement dusuk_stok_deger = (MobileElement) driver.findElementById("com.example.roomexample:id/medicineStock");

		String result1 = dusuk_stok_isim.getText();
		String result2 = dusuk_stok_deger.getText();
		String expected1 = "Minoset";
		String expected2 = "15";

		assertEquals(result1, expected1);
		assertEquals(result2, expected2);

		driver.navigate().back();	//ana sayfaya don
	}

	public void test_ilacAra() { // tek ilaci ara ve konumunu kontrol et
		MobileElement ilac_ara_buton = (MobileElement) wait.until(ExpectedConditions
				.elementToBeClickable(By.id("com.example.roomexample:id/nav_menu_search_medicine_btn")));
		ilac_ara_buton.click();
		MobileElement arama_kutucugu = (MobileElement) driver.findElementById("com.example.roomexample:id/searchInput");
		arama_kutucugu.sendKeys("Cataflam");
		MobileElement arama_ikonu = (MobileElement) driver.findElementById("com.example.roomexample:id/searchButton");
		arama_ikonu.click();

		MobileElement konum = (MobileElement) driver.findElementById("com.example.roomexample:id/searchOutputMedicineLocation");// arama sonucu

		String result = konum.getText();
		String expected = "Raf 1 Sira 2";

		assertEquals(result, expected);

		driver.navigate().back();
	}

	public void test_ilacListesi() { // farklý turlere gore listele
		MobileElement ilac_listesi_butonu = (MobileElement) driver
				.findElementById("com.example.roomexample:id/nav_menu_medicine_list_btn");
		ilac_listesi_butonu.click();
		MobileElement siralama_türü = (MobileElement) driver.findElementById("android:id/text1");
		siralama_türü.click();
		MobileElement listele_ilac_türü = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		listele_ilac_türü.click();
		MobileElement ilac_listesi_butonu2 = (MobileElement) driver.findElementById("android:id/text1");
		ilac_listesi_butonu2.click();
		MobileElement listele_firma = (MobileElement) driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
		listele_firma.click();
		driver.navigate().back();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
