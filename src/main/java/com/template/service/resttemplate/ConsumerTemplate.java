package com.template.service.resttemplate;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.template.exception.ConsumerException;
import com.thoughtworks.xstream.XStream;


@Service
public class ConsumerTemplate implements IConsumer {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Object get(String url, Class typeReturnExpected, MediaType mediaType, Map<String, String> map) throws ConsumerException {
        String forObject = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(getHeaders(map)), String.class).getBody();
        logger.info("Consumindo -> "+url+"("+mediaType+") - REST TEMPLATE ");
        if(mediaType.equals(MediaType.APPLICATION_JSON))
            return new Gson().fromJson(forObject,typeReturnExpected);

        if(mediaType.equals(MediaType.APPLICATION_XML))
            return new XStream().fromXML(forObject);

       throw new ConsumerException("Eer... Problemas ao identificar o MediaType");
    }

    private MultiValueMap<String, String> getPostHeaders(Map<String,String> header){
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
        for(Map.Entry<String, String> s: header.entrySet()){
        	headers.set(s.getKey(),s.getValue());
        }
        return headers;
    }

    private HttpHeaders getHeaders(Map<String,String> headers){
        HttpHeaders restHeaders = new HttpHeaders();

        for(Map.Entry<String, String> s: headers.entrySet()){
            restHeaders.set(s.getKey(),s.getValue());
        }
        return restHeaders;
    }
    
    
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object post(String url, Object objToSend, Class typeReturnExpected, MediaType mediaType, Map<String,String> headers) throws ConsumerException {
        try {
            String forObject = (String) restTemplate.postForEntity(url, new HttpEntity(objToSend, getPostHeaders(headers)), String.class).getBody();

            if (mediaType.equals(MediaType.APPLICATION_JSON))
                return new Gson().fromJson(forObject, typeReturnExpected);

            if (mediaType.equals(MediaType.APPLICATION_XML))
                return new XStream().fromXML(forObject);

            throw new ConsumerException("Problemas ao identificar o MediaType");
        }catch (HttpStatusCodeException ex){
            throw new ConsumerException(ex, ex.getMessage());
        }
    }
    
}
