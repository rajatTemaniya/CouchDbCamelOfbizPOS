package pojo;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper mapper;
    static {
           mapper = new ObjectMapper();
    }
    public static <T>T convertJsonToJava(String jsonString,Class<T> cls) {
        T result =null;
        try {
            result = mapper.readValue(jsonString,cls);
        } catch (JsonParseException e) {
            System.out.println("'JsonUtil' Exception occure while converting JSON to JavaObject 1");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("'JsonUtil' Exception occure while converting JSON to JavaObject 2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("'JsonUtil' Exception occure while converting JSON to JavaObject 3");
            e.printStackTrace();
        }
        return result;
   }
}
