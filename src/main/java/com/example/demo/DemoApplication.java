package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest; 
import java.util.Enumeration;

@RestController
public class HeaderController {

    @GetMapping("/headers")
    public String getHeaders(HttpServletRequest request) {
        StringBuilder headersInfo = new StringBuilder("<h1>Received HTTP Headers:</h1><ul>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersInfo.append("<li>").append(headerName).append(": ").append(headerValue).append("</li>");
        }
        
        String xForwardedFor = request.getHeader("Forwarded-For");
        if (xForwardedFor != null) {
            headersInfo.append("<li>").append("Forwarded-For").append(": ").append(xForwardedFor).append("</li>");
        } else {
            headersInfo.append("<li>").append("Forwarded-For").append(": ").append("Not Present").append("</li>");
        }
        headersInfo.append("</ul>");
        return headersInfo.toString();
    }
}