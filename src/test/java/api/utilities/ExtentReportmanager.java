package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportmanager implements ITestListener {
	//responsible for UI
	public ExtentSparkReporter sparkrep;
	//specify common info in report
	public ExtentReports extent;
	//creating the entry of test pass failure etc......
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext textcontext)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timestamp+".html";
		
		sparkrep =new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkrep.config().setDocumentTitle("RestAssured Automation");
		sparkrep.config().setReportName("Pet store API");
		sparkrep.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkrep);
		extent.setSystemInfo("Application", "Pest store api");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("user", "Rahul D");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext textcontext)
	{
		extent.flush();
	}
	
	
}
