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

/**
 * Filter bean used to add unique-id txt for Request
 */
@Component
public class UniqueIdGenerator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String randomId = UUID.randomUUID().toString();
        MDC.put(ConstantUtil.REQUEST_ID, randomId);
        chain.doFilter(request, response);
        MDC.clear();
    }
}
