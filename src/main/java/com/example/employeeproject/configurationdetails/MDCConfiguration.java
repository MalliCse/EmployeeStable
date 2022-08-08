package com.example.employeeproject.configurationdetails;

import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Component
public class MDCConfiguration implements Filter{
	
	 private static final Logger logger = LoggerFactory.getLogger(MDCConfiguration.class);

	 public String UNIQUES_ID = "req.unique";
	 
	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        //do nothing
	    }

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        String uniqueId = UUID.randomUUID().toString();
	        uniqueId = StringUtils.replace(uniqueId,"-","");
	        MDC.put("CorrelationId", uniqueId);
	       ((HttpServletResponse)servletResponse).setHeader("Request-ID",uniqueId);
	        logger.info("unique id generated {}", uniqueId );
	        filterChain.doFilter(servletRequest,servletResponse);
	    }

	    @Override
	    public void destroy() {
	    	MDC.clear();
	    }
	
	

}
