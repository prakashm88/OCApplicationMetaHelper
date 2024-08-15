package com.itechgenie.apps.openshift.helper.controllers;

import java.net.InetAddress;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class ItgAllRequestCatcher {
	
	@GetMapping(value = "/**")
    public Map<String, Object> getHeaders(@RequestParam(value = "env", defaultValue = "NonePassed") String env, HttpServletRequest request) {
		log.info("Inside getHeader:" );
        Map<String, Object> returnMap = new HashMap<String, Object>();

        Date sTime;
        String sDtime;

        try {
            String hostName = InetAddress.getLocalHost().getHostName() + " with IP=" + InetAddress.getLocalHost().getHostAddress() + " ";
            returnMap.put("hostName", hostName);

            sTime = new java.util.Date();
            sDtime = sTime.toString();

            returnMap.put("dateTome", sDtime);
            returnMap.put("serverName", request.getServerName());
            returnMap.put("requestUrl", request.getRequestURL());
            returnMap.put("requestMethod", request.getMethod());

            Map<String, Object> headerMap = new HashMap<String, Object>();

            try {
                Enumeration enumeration = request.getHeaderNames();
                while (enumeration.hasMoreElements()) {
                    String name = (String)enumeration.nextElement();
                    String value = request.getHeader(name);
                    headerMap.put(name, value);
                }
            } catch (Exception e1) {
                returnMap.put("exception.e1", e1.getMessage());
            }


            returnMap.put("headerMap", headerMap);

            Map<String, Object> cookieMap = new HashMap<String, Object>();

            try {
                Cookie[] arr1 = request.getCookies();
                for (int i = 0; i < arr1.length; i++) {
                    String cookiename = arr1[i].getName();
                    String cookievalue = arr1[i].getValue();
                    cookieMap.put(cookiename, cookievalue);
                }
            } catch (Exception e2) {
                returnMap.put("exception.e2", e2.getMessage());
            }


            returnMap.put("cookieMap", cookieMap);

        } catch (Exception e) {
            returnMap.put("exception", e.getMessage());
        }
        log.info("Inside getHeader - returning: " + returnMap );
        return returnMap;
    }


}
