package hr.fina.eracun.application_config.verzija.app;
/*
 * By FINA Development 2005.07.25
 */
import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Klasa koja služi za dohvat trenutne verzije
 * 
 * 
 * @version $Revision: 1.1 $ - $Date: 2019/09/25 06:07:05 $ - $Author: bkocman $
 * @author ibarovic
 */
public class Verzija {
	public static final String PROJECT_NAME = "project-name";
	public static final String PROJECT_DESCRIPTION = "project-description";
	public static final String VERSION_STRIPES = "ver-stripes";
	public static final String TIMESTAMP = "timestamp";
	public static final String REPOSITORY_PATH = "repository-path";
	public static final String CMS_URL = "cms-url";
	public static final String CMS_FTP_SERVER = "cms-ftp-server";
	public static final String CMS_FTP_REMOTE_DIR = "cms-ftp-remote-dir";

	private static final Resource cResource;
	private static final Properties cProperties;
	
	static{
		
		cResource = new ClassPathResource("verzija.properties");
		cProperties = new Properties();
		try {
			cProperties.load(cResource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] pArgs) {
		System.out.println(getFullVerzija());
	}

	/**
	 * Metoda vra�a podatak pod key-em iz verzija.properties datoteke
	 * @param pKey - koristiti stati�ke varijable
	 * @return String
	 */
	public static String get(String pKey) {
		return cProperties.getProperty(pKey);
	}
	
	public static String getVersionDots(){
		return get(VERSION_STRIPES).replace("-", ".");
	}
	
	/**
	* Metoda vraca String prezentaciju verzije sa imenom projekta, verzijom i timestamp-om
	* @return String
	*/
	public static String getFullVerzija() {
		return get(PROJECT_NAME) + " " + getVersionDots() + " " + get(TIMESTAMP);
	}
	
	/**
	* Metoda vraca String prezentaciju verzije samo sa imenom projekta te verzijom
	* @return String
	*/
	public static String getShortVerzija() {
		return get(PROJECT_NAME) + " " + getVersionDots();
	}
}
