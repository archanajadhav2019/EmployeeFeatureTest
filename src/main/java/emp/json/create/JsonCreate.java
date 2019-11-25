package emp.json.create;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

import emp.test.exception.TestException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

//Class for JSON creation and modification methods

public class JsonCreate {

	public JSONObject getJSONObject(final File jsonFile) {
        JSONObject json = null;
        try {

            final JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            final FileReader fileReader = new FileReader(jsonFile);
            json = (JSONObject) parser.parse(fileReader);
            fileReader.close();
        } catch (IOException e) {
            throw new TestException(
                                "Exception while converting Json file to Json object" + jsonFile.toPath()
                                                    .toAbsolutePath());
        }catch(ParseException e){
        	 throw new TestException(
                     "Exception while converting Json file to Json object" + jsonFile.toPath()
                                         .toAbsolutePath());
        }
        return json;

    }
	
	public JSONObject deleteJsonByPath(final String path, final JSONObject json) {
        return JsonPath.parse(json).delete(path).json();
    }

	

}
