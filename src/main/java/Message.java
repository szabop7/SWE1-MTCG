
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Message {
    @Setter
    @Getter
    private String verb;
    @Setter
    @Getter
    private String version;
    @Setter
    @Getter
    private String resource;
    @Setter
    @Getter
    private Map<String,String> data;
    @Setter
    @Getter
    private String payload;

    public Message(){
        data = new HashMap<>();
    }

    public void addHeaderValues(String key, String value){
        data.put(key,value);
    }

    public int getContentLength(){
        if (data != null && data.containsKey("content-length:")){
            return Integer.parseInt(data.get("content-length:"));
        }
        return 0;
    }
}