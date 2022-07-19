package sticker.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UtilJson {
	private String absPath = new File("").getAbsolutePath();
	
	@SuppressWarnings("unchecked")
	public void salvarVoto(String Usuario, String title, String rating) {		
		JSONObject jo = new JSONObject();	
		String classPath = UtilJson.class.getClassLoader().getResource("").getPath();
		String votoPath = classPath + "../base-dados/" ;
		
		jo.put("title", title);
		jo.put("usuario", Usuario);
		jo.put("rating", rating);
					
		try {
			PrintWriter pw = new PrintWriter(votoPath + "Filmes.json");
			pw.write(jo.toJSONString());	          
	        pw.flush();
	        pw.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	public JSONArray getArquivoJson(String arquivo) {
		JSONArray jsResult = new JSONArray();
		String pathJsonFile = this.absPath + File.separator + "base-dados" + File.separator + arquivo + ".json";
		
		try {
			Object obj = new JSONParser().parse(new FileReader(pathJsonFile));
			JSONObject jo = (JSONObject) obj;
			
			jsResult = (JSONArray) jo.get("results");
						
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return jsResult;
	}
}