package com.java.apigateway.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ModifyResponseDataStreamFilter extends ZuulFilter {

	private static final String FILTER_TYPE = "post";

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("Exceuting Post FIlter BEGIN");
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			InputStream responseDataStream = ctx.getResponseDataStream();
			String body = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
			body = "Modified via setResponseDataStream(): " + body;
			ctx.setResponseDataStream(new ByteArrayInputStream(body.getBytes("UTF-8")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exceuting Post FIlter END");
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
