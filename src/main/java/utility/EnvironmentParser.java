package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class EnvironmentParser {

	private static Logger logger = Logger.getLogger(EnvironmentParser.class.getName());
	private static FileInputStream fiStream = null;
	private static Properties propFile = new Properties();
	public static final String envPropFileName = "C:\\ToolsQA\\SeleniumMavenProject\\src\\main\\resources\\Enviornment.properties";
	
	public EnvironmentParser(String fileName) throws Exception
	{
		try
		{
			logger.info("reading environment properties file");
			fiStream = new FileInputStream(fileName);
			propFile.load(fiStream);
			
		}catch(FileNotFoundException e)
		{
			logger.error("Environment prop file is not found", e);
		}catch(IOException e)
		{
			logger.error("Environment prop file is not loaded", e);
		}
	}
	
	public String getEnvironmentVar(String envVar)
	{
		
		String getPropertyValues = propFile.getProperty(envVar);
		
		String EnvironmentVarValues = getPropertyValues.toString();
		
		logger.info("Environment var values :" + EnvironmentVarValues);
		
		return EnvironmentVarValues;
		
	}
	

}
