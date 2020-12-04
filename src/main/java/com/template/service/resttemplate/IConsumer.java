package com.template.service.resttemplate;

import java.util.Map;

import org.springframework.http.MediaType;

import com.template.exception.ConsumerException;

public interface IConsumer {

    @SuppressWarnings("rawtypes")
	Object get(String url, Class typeReturnExpected, MediaType mediaType, Map<String, String> headers) throws ConsumerException;
    
    @SuppressWarnings("rawtypes")
	Object post(String url, Object objToSend, Class typeReturnExpected, MediaType mediaType, Map<String, String> headers) throws ConsumerException;
}
