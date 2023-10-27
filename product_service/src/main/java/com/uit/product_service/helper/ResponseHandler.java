package com.uit.product_service.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static  <T> ResponseEntity<Object> getResponse(T content, HttpStatus status){
        Map<String,Object> map=new HashMap<>();
        map.put("content",content);
        map.put("status", status.value());
        return new ResponseEntity<>( map,status);

    }

    public static  ResponseEntity<Object> getResponse(HttpStatus status,String error){
        Map<String,Object> map=new HashMap<>();
        map.put("error",error);
        map.put("status",status);
        return new ResponseEntity<>(map,status);
    }
}
