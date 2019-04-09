package com.java.apigateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AddParaminHeaderPreFilter extends ZuulFilter {
	
	private static final String FILTER_TYPE = "pre";
	
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String requestURI = ctx.getRequest().getRequestURI();
		System.out.println("shouldFilter() requestURI " + requestURI);		
		return requestURI.contains("employee");
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("Begin :: run() method");
		RequestContext ctx = RequestContext.getCurrentContext();
		String requestURI = ctx.getRequest().getRequestURI();
		System.out.println("run() requestURI " + requestURI);
		if(requestURI.contains("employee")) {
			ctx.addZuulRequestHeader("isLogEnabled", Boolean.toString(true));
		}
		
		System.out.println("End :: run() method");
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 2;
	}
}
