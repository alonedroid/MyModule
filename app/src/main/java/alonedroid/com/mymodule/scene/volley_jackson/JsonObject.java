package alonedroid.com.mymodule.scene.volley_jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonObject {
    @JsonProperty("time")
    public String publicTime;
    public List<JackForecasts> forecasts = new ArrayList<JackForecasts>();

    @JsonIgnoreProperties(ignoreUnknown=true)
    static class JackForecasts {
        public JackImage image;
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    static class JackImage {
        public String title;
        public String url;
    }
}
