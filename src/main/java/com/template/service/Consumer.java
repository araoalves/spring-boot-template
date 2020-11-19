package com.template.service;

import java.util.Map;

import org.springframework.http.MediaType;

import com.template.exception.ConsumerException;

public interface Consumer {

    @SuppressWarnings("rawtypes")
	Object get(String url, Class typeReturnExpected, MediaType mediaType, Map<String, String> headers) throws ConsumerException;
    
    @SuppressWarnings("rawtypes")
	Object post2(String url, Object objToSend, Class typeReturnExpected, MediaType mediaType, Map<String, String> headers) throws ConsumerException;
}
