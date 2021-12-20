package tests.utils;

import lombok.extern.java.Log;
import org.apache.commons.beanutils.BeanUtils;
import tests.model.TestConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Log
public class ConfigFileReader {

	private static ConfigFileReader configFileReader;
	private Properties properties;
	private final String propertyFilePath = "src/test/resources/config/configuration.properties";

	private ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			properties.load(reader);
			reader.close();
		} catch (Exception e) {
			log.info("El archivo no existe o la ruta es errónea " + e.getMessage());
		}

	}

	public static ConfigFileReader getInstance() {
		return configFileReader == null ? configFileReader = new ConfigFileReader() : configFileReader;
	}

	public TestConfig getConfiguracion() {

		Map<String, String> map = new HashMap<String, String>();
		TestConfig bean = new TestConfig();

		for (String name : properties.stringPropertyNames()) {
			map.put(name, properties.getProperty(name));
		}

		try {
			BeanUtils.populate(bean, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bean;

	}

}