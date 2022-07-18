package sticker._static.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Filmes {
	
	@SuppressWarnings("unchecked")
	public JSONArray getJsonTopFilmes() {
		JSONArray joArray = new JSONArray();
		JSONObject jo = new JSONObject();		
		
		jo.put("title", "Filme um");
		jo.put("image", "linkimagemum.com");
		jo.put("imDbRating", "9.2");		
		joArray.add(jo);
		
		jo.put("title", "Filme dois");
		jo.put("image", "linkimagemdois.com");
		jo.put("imDbRating", "9.0");
		joArray.add(jo);
		
		jo.put("title", "Filme três");
		jo.put("image", "linkimagemtres.com");
		jo.put("imDbRating", "9.0");
		joArray.add(jo);
		
		return joArray;
		//Escrevendo o Json em um arquivo		
//		try {
//			PrintWriter pw = new PrintWriter("Filmes.json");
//			pw.write(jo.toJSONString());	          
//	        pw.flush();
//	        pw.close();
//		} catch (FileNotFoundException e) {			
//			e.printStackTrace();
//		}
	}
}
