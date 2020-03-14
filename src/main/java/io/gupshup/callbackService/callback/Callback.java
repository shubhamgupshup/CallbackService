package io.gupshup.callbackService.callback;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Callback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    @Column(name = "headers", length = 500)
    @ElementCollection
    private Map<String, String> headers = new HashMap<>();
    @ElementCollection
    private Map<String, String> queryParams = new HashMap<>();
    @ElementCollection
    private Map<String, String> postParams = new HashMap<>();
    private String requestType;
    private String requestPayload;
    private String postJson;
    long timeStamp;

    public Callback(){
    }

    public Callback (long id, Map<String, String> headers, long timeStamp, Map<String, String> queryParams, Map<String, String> postParams, String requestType, String postJson, String requestPayload){
        this.id = id;
        this.headers = headers;
        this.timeStamp = timeStamp;
        this.postParams = postParams;
        this.queryParams = queryParams;
        this.requestType = requestType;
        this.postJson = postJson;
        this.requestPayload = requestPayload;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = ZonedDateTime.now().toInstant().toEpochMilli();
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQueryParams() {

        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, String> getPostParams() {
        return postParams;
    }

    public void setPostParams(Map<String, String> postParams) {
        this.postParams = postParams;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public String getPostJson() {
        return postJson;
    }

    public void setPostJson(String postJson) {
        this.postJson = postJson;
    }
}
