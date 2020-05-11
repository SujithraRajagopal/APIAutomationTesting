package loadProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadConfigProperties {

	public Properties prop;
	public FileInputStream ip;
	
	public int response_code_200= 200;
	public int response_code_201= 201;
	

	public LoadConfigProperties() {

		prop = new Properties();

		try {
			ip = new FileInputStream("C:\\Users\\Sam\\git\\APIAutomationTesting\\RestClientFramework\\src\\main\\java\\configs\\config.properties");

			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
