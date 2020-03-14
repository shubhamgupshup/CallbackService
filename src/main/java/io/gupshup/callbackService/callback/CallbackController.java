package io.gupshup.callbackService.callback;

import io.gupshup.callbackService.CallbackServiceApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
public class CallbackController {

    private static final Logger LOGGER = LogManager.getLogger(CallbackServiceApplication.class);

    @Autowired
    private CallbackService callbackService;

//    @RequestMapping(method = RequestMethod.GET, value = "/v1/api/callbackService")
//    public List<Callback> getAllCallbacks(){
//    return callbackService.getAllCallbacks();
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "v1/api/callbackService/{id}")
//    @ResponseBody
//    public Optional<Callback> getCallback(@PathVariable long id, HttpServletRequest httpServletRequest) {
//        Callback response = new Callback();
//
//
//        return callbackService.getCallback(id);
//
//
//    }

    @RequestMapping(value = "v1/api/callbackService")
    public Callback storeCallback(HttpServletRequest httpRequest) {
        LOGGER.debug("-----------------Start------------------", httpRequest);

        Callback response = new Callback();
        response.setRequestType(httpRequest.getMethod());
        Enumeration<String> headers = httpRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            response.getHeaders().put(headerName, httpRequest.getHeader(headerName));
        }


        Enumeration<String> params = httpRequest.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
                response.getPostParams().put(paramName, httpRequest.getParameter(paramName));
        }

        String queryString = httpRequest.getQueryString();
        if(queryString != null && !queryString.equalsIgnoreCase("")) {
            String[] parameters = queryString.split("&");

            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                String values = response.getPostParams().get(keyValuePair[0]);
                response.getPostParams().remove(keyValuePair[0]);
                response.getQueryParams().put(keyValuePair[0], values);
            }
        }

        response.setPostJson(extractPostRequestBody(httpRequest));
        response.setTimeStamp(System.currentTimeMillis());
        callbackService.addCallback(response);



        return response;
    }


    static String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    @GetMapping(value = "v1/api/callbackService/all")
    public List<Callback> getAllCallback(){
        return callbackService.getAllCallbacks();
    }

    @GetMapping(value = "v1/api/callbackService/{id}")
    public Optional<Callback> getCallback(@PathVariable long id){
        return callbackService.getCallback(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "v1/api/callbackService/{id}")
    public String deleteCallback(@PathVariable long id){
        callbackService.deleteCallback(id);
        return "success "+id;
    }

}
