package jsonParsingUtil;

import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParsingUtility {

	// JSON parsing
	public static String getValueByJpath(JSONObject jsonresponse, String jpath) {

		Object obj = jsonresponse;
		for (String s : jpath.split("/")) {
			if (!s.isEmpty()) {
				if (!(s.contains("[") || s.contains("]"))) {
					obj = ((JSONObject) obj).get(s);
				} else if (s.contains("[") || s.contains("]")) {
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				}
			}

		}
		return obj.toString();
	}

}
