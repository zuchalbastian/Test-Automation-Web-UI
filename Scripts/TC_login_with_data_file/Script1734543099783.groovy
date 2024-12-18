// Import libraries
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

// Buka browser
WebUI.openBrowser('')

// Navigasi ke halaman login firefly
WebUI.navigateToUrl('https://demo.firefly-iii.org/login')

// Ambil data dari file Excel
def loginData = TestDataFactory.findTestData('Data Files/TD_user')

// Loop untuk setiap baris data
for (int i = 1; i <= loginData.getRowNumbers(); i++) {
	WebUI.comment("Running test case for row: " + i)
	
	// Ambil email dan password dari data file
	String email = loginData.getValue('Email Address', i)
	String password = loginData.getValue('Password', i)
	String expectedResult = loginData.getValue('Expected Result', i)
	
	// Isi email dan password
	WebUI.setText(findTestObject('Object Repository/OR_login_positive/Page_Login to Firefly III/input_demo_email'), email)
	
	WebUI.setEncryptedText(findTestObject('Object Repository/OR_login_positive/Page_Login to Firefly III/input_demo_password'), password)
	
	// Klik tombol login
	WebUI.click(findTestObject('Object Repository/OR_login_positive/Page_Login to Firefly III/button_Sign in'))
	
	// Validasi hasil berdasarkan expected result
	if (expectedResult == 'Success') {
		
		WebUI.click(findTestObject('Object Repository/OR_login_positive/Page_Home  Firefly III/span_Dashboard'))
	
		WebUI.click(findTestObject('Object Repository/OR_login_positive/Page_Home  Firefly III/a_Logout'))
		
	} else if (expectedResult.contains('Error')) {
		
		WebUI.verifyTextPresent("These credentials do not match our records.", false, FailureHandling.STOP_ON_FAILURE)
	}
	
	// Refresh untuk mencoba login dengan data berikutnya
	WebUI.refresh()
}

// Tutup browser setelah selesai
WebUI.closeBrowser()
