package dominion4calcs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	private Properties props;

	public PropertiesUtils(Properties props) {
		this.props = props;
	}

	public int getInt(String key, int defaultValue) {
		if (props.containsKey(key)) {
			return Integer.parseInt(props.getProperty(key));
		} else {
			return defaultValue;
		}
	}

	public int getInt(String key) {

		return Integer.parseInt(getString(key));

	}

	public String getString(String key) {
		if (props.containsKey(key)) {
			return props.getProperty(key);
		} else {
			throw new IllegalArgumentException(key + " not found");
		}
	}

	public static Properties load(String filename) throws IOException, FileNotFoundException {
		Properties props = new Properties();
		try (FileInputStream fi = new FileInputStream(filename)) {
			props.load(fi);
		}
		return props;
	}

	public boolean getBoolean(String key) {
		String resultStr = props.getProperty(key);
		if(resultStr ==null) {
			throw new IllegalArgumentException(key+" not found");
		}
		return Boolean.parseBoolean(resultStr.trim());
		
	}
}
