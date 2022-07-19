package sticker.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UtilProperties {
	private String apiKey;
	
	public void setApiKey(String apiKey) {

		//Casa
		File classPath = new File(".");;		
		String configPath = classPath.getAbsolutePath() + "/config/";
						
		Map<String, String> properties;
		try {
			properties = getParametrosIntegracao(configPath + "config");
			this.apiKey = properties.get(apiKey);	
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	private Map<String, String> getParametrosIntegracao(String nomeArquivoParam) throws Exception {
		if (!nomeArquivoParam.endsWith(".properties")) {
			nomeArquivoParam += ".properties";
		}

		FileInputStream arquivo = new FileInputStream(nomeArquivoParam);
		Properties properties = new Properties();
		properties.load(arquivo);

		Set<Object> keySet = properties.keySet();
		Iterator<Object> iterator = keySet.iterator();
		Map<String, String> parametroMap = new HashMap<String, String>();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			parametroMap.put(key, properties.getProperty(key));
		}
		return parametroMap;
	}

}
