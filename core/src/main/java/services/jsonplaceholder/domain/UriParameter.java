package services.jsonplaceholder.domain;

public class UriParameter {
    private String key;
    private String value;

    public UriParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public UriParameter(String value) {
        this.value = value;
    }

    public String getFormattedParameter(){
        if(key == null){
            return value;
        }
        return String.format("%s=%s", key, value);
    }
}
