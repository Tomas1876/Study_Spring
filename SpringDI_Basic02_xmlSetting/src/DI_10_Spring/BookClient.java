package DI_10_Spring;

import java.util.Properties;

public class BookClient {
	
	//Properties는 특수한 해쉬맴으로 key와 value가 모두 String이다
	private Properties config;
	public void setConfig(Properties config) {
		this.config = config;
	}
	
	//일반함수 
	public void connect() {
		String server = config.getProperty("server");
		String timeout = config.getProperty("connectiontimeout");
		
		System.out.println("server : " + server);
		System.out.println("timeout : " + timeout);
	}
	
}
