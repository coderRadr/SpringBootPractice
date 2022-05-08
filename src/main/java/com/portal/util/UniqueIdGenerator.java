package com.portal.util;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UniqueIdGenerator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String acceptHeader = httpServletRequest.getHeader(HttpHeaders.ACCEPT);
        if(StringUtils.isEmpty(acceptHeader)) {
        	httpServletRequest.setAttribute(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE);
        	httpServletResponse.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE);
        }
        
        String contentHeader = httpServletRequest.getHeader(HttpHeaders.CONTENT_TYPE);
        if(StringUtils.isEmpty(contentHeader)) {
        	httpServletRequest.setAttribute(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
        	httpServletResponse.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE);
        }
        
    	String randomId = UUID.randomUUID().toString();
        MDC.put(ConstantUtil.REQUEST_ID, randomId);
        chain.doFilter(request, response);
        MDC.clear();
    }
}
