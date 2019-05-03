package com.cf.storage.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomMapper extends ObjectMapper{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CustomMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
