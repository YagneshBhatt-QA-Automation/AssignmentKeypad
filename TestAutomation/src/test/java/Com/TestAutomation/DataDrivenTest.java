package Com.TestAutomation;
/*************************
Description : This class is driver script, Reading Excel file through JXL and also initiate Extent report 
CreatedBy & date: Yagnesh Bhatt
Function Added : DTOTestCases
*************************/
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.testng.annotations.Test;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Com.utilDTO.Utility;

public class DataDrivenTest extends Utility{
static ExtentTest test;
static ExtentReports report;
static Workbook myFirstWbook = null;
static WritableWorkbook wwbCopy;
static WritableSheet shSheet;
static WritableSheet wshTemp;
@BeforeClass
public static void startTest()
{
	// Adding Timestamp adding in File name of CopyFile
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());                             
    System.out.println(timestamp.getTime());
	report = new ExtentReports(System.getProperty("user.dir") + "\\Result\\ExtentReportResults"+ timestamp.getTime() +".html");	
}

@SuppressWarnings("static-access")
@Test
public void DTOTestCases() throws BiffException, IOException, RowsExceededException, WriteException
{		
	 
	// Adding Timestamp adding in File name of CopyFile
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());                        
    
    // Existing Base TestCases File
	String FilePath = System.getProperty("user.dir") + "\\Data\\Test.xls";
	System.out.println(FilePath);
	FileInputStream fs = new FileInputStream(FilePath);
	Workbook wb = Workbook.getWorkbook(fs);
	//****************Write in cell***********
	wwbCopy = myFirstWbook.createWorkbook(new File(System.getProperty("user.dir") + "\\Result\\Testcopy"+timestamp.getTime()+".xls"), wb);
	shSheet = wwbCopy.getSheet(0);
	// TO get the access to the sheet
	Sheet sh = wb.getSheet("Sheet1");
	// To get the number of rows present in sheet
	int totalNoOfRows = sh.getRows();
	// To get the number of columns present in sheet
	
	for (int row = 1; row < totalNoOfRows; row++) {		
		test = report.startTest("Test Case Number " + sh.getCell(0, row).getContents());
		test.log(LogStatus.INFO, "Test Phone Number : " + sh.getCell(1, row).getContents());
		
		List<String> combos = getKeypadCombination(sh.getCell(1, row).getContents(),timestamp.getTime(), sh.getCell(0, row).getContents());
		int Actual = combos.size();
		int Expected = Integer.parseInt(sh.getCell(2, row).getContents());		
		if (Expected == Actual)
		{	
			test.log(LogStatus.INFO, "Possible Combination in number : " + sh.getCell(2, row).getContents());
			test.log(LogStatus.PASS, "combination passed based on test case");
			String PATH = System.getProperty("user.dir") + "\\Result\\"+timestamp.getTime();
			PATH = PATH + "\\TestCase" + sh.getCell(0, row).getContents() + ".txt";
			test.log(LogStatus.INFO, "Artifact File Name : <a href='file:"+ PATH +"'>TestCase"+sh.getCell(0, row).getContents()+".txt</a>");
			//setValueIntoCell("sheet1", 3, row, "PASS");
			
		    Label labTemp = new Label(3, row, "PASS");
		    try {shSheet.addCell(labTemp);}catch (Exception e){e.printStackTrace();}
			
		}
		else
		{
			test.log(LogStatus.FAIL, "combination Failed based on test case");
			test.log(LogStatus.FAIL, "Expected : " + Expected + " Actual : "+ Actual);
			test.log(LogStatus.INFO, "<a href='file:\\C:\\Users\\anbajaj\\Desktop\\test.xml'>"+sh.getCell(2, row).getContents()+"</a>");

			
		    Label labTemp = new Label(3, row, "FAIL");
		    try {shSheet.addCell(labTemp);}catch (Exception e){e.printStackTrace();}
		}
	}
}

@AfterClass
public static void endTest() throws WriteException, IOException
{
	//Extent report end and flush it
	report.endTest(test);
	report.flush();
	try{
	//Excel report write and close	
	wwbCopy.write();	
    wwbCopy.close();
	}
	catch(Exception e)
	{e.printStackTrace();}
}
}