package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path= ".\\testData\\LoginData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int totalrow=xl.getRowcount("Sheet1");
		int totalcol=xl.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrow][totalcol];
		
		for(int i=1;i<=totalrow;i++)
		{
			for(int j=0;j<totalcol;j++)
			{
				logindata[i-1][j]=xl.getCellData("Sheet1", i, j);// 1,0
			}
		}
		return logindata;//returning two dimension array
	}
	
//DataProvider 2
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getRowcount("Sheet1");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}

		return apidata;

	}
}
