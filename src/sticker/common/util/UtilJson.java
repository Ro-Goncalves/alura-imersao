package sticker.common.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

public class UtilJson {
	
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
}