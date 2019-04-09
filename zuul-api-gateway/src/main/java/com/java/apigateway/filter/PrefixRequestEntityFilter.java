package com.java.apigateway.filter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PrefixRequestEntityFilter extends ZuulFilter {

	private static final String FILTER_TYPE = "pre";
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
		Set<String> keySet = requestQueryParams.keySet();
		
		for(String key : keySet) {
			System.out.println(key + " = " + requestQueryParams.get(key));
		}
		System.out.println(" Zuul PRE Filter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
