package com.java.apigateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@Component
public class ModifyResponseBodyFilter extends ZuulFilter {
	public String filterType() {
		return "post";
	}

	public int filterOrder() {
		return 3;
	}

	public boolean shouldFilter() {
		RequestContext context = getCurrentContext();
		return context.getRequest().getParameter("service") == null;
	}

	public Object run() {
		try {
			System.out.println("!!!!!!!! BEGIN :: MOdifying Response BOdy Filter !!!!!!!!!!");
			RequestContext context = getCurrentContext();
			InputStream stream = context.getResponseDataStream();
			String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			context.setResponseBody("Modified via setResponseBody(): " + body);
			System.out.println("!!!!!!!! END :: MOdifying Response BOdy Filter !!!!!!!!!!");
		} catch (IOException e) {
			rethrowRuntimeException(e);
		}
		return null;
	}
}