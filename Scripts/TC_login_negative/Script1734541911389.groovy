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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://demo.firefly-iii.org/login')

WebUI.setText(findTestObject('Object Repository/OR_login_negative/Page_Login to Firefly III/input_demo_email'), 'demo@fire.com')

WebUI.setEncryptedText(findTestObject('Object Repository/OR_login_negative/Page_Login to Firefly III/input_demo_password'), 
    'PblvLzUlPsM=')

WebUI.click(findTestObject('Object Repository/OR_login_negative/Page_Login to Firefly III/button_Sign in'))

WebUI.verifyTextPresent("These credentials do not match our records.", false)

WebUI.setText(findTestObject('Object Repository/OR_login_negative/Page_Login to Firefly III/input_demo_email'), 'demo@firefly')

WebUI.setEncryptedText(findTestObject('Object Repository/OR_login_negative/Page_Login to Firefly III/input_demo_password'), 
    'KuC5M6xKe5uvO/soexOEDQ==')

WebUI.click(findTestObject('Object Repository/OR_login_negative/Page_Login to Firefly III/button_Sign in'))

WebUI.verifyTextPresent("These credentials do not match our records.", false, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

