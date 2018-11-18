import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
	private JSONParser jsonParser;
	private Vector<Shape> v;
	private BufferedWriter bfw;
	private BufferedReader bfr;
	private String tmp;
	private Shape s;

	public JsonParser() {
		jsonParser = new JSONParser();

	}

	public void openReader() {
		try {
			bfr = new BufferedReader(new FileReader("test.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openWriter() {
		try {
			bfw = new BufferedWriter(new FileWriter("test.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save(Vector<Shape> v) {
		this.v = v;

		StringBuffer sb = new StringBuffer("");

		sb.append("{\"");
		sb.append("Shape"); // className
		sb.append("\":[");

		for (int i = 0; i < v.size(); i++) {
			sb.append("{\"");
			sb.append("getShape");
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(v.get(i).getShape());
			sb.append("\"");

			sb.append(",\"");
			sb.append("sx");
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(v.get(i).getSx());
			sb.append("\"");

			sb.append(",\"");
			sb.append("sy");
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(v.get(i).getSy());
			sb.append("\"");

			sb.append(",\"");
			sb.append("ex");
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(v.get(i).getEx());
			sb.append("\"");

			sb.append(",\"");
			sb.append("ey");
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(v.get(i).getEy());
			sb.append("\"");

			sb.append("}");
			if (!(i == v.size() - 1)) {
				sb.append(",");
			}
		}
		
		sb.append("]}");
		String result = sb.toString();
		System.out.println(result);

		try {
			bfw.write(result);
			bfw.newLine();
			bfw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<Shape> load() {
		try {
			tmp = bfr.readLine();
			System.out.println(tmp);
			bfr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject jsonObject = null;
		JSONArray groupInfoArray = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(tmp);

			groupInfoArray = (JSONArray) jsonObject.get("Shape");
			v = new Vector<Shape>();
			for (int i = 0; i < groupInfoArray.size(); i++) {
				JSONObject groupObject = (JSONObject) groupInfoArray.get(i);

				s = new Shape();
				s.setting(Integer.parseInt(groupObject.get("sx").toString()),
						Integer.parseInt(groupObject.get("sy").toString()),
						Integer.parseInt(groupObject.get("ex").toString()),
						Integer.parseInt(groupObject.get("ey").toString()), groupObject.get("getShape").toString());
				v.add(s);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}

}
