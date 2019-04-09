package com.java.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class EmpPreRequestFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestURI = request.getRequestURI();
		return requestURI.contains("employee");
	}

	@Override
	public Object run() throws ZuulException {
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!Exceuting Pre Filter for only Request for employee!!!!!!!!!!!!!!!!!");
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 4;
	}

}
