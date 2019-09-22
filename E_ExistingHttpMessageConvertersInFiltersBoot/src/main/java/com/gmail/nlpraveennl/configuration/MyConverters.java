package com.gmail.nlpraveennl.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;

@Component
public class MyConverters
{
	private List<HttpMessageConverter<?>> converters;
	
	public MyConverters()
	{
		this.converters = new ArrayList<HttpMessageConverter<?>>();
		
		converters.add(createXmlHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	public List<HttpMessageConverter<?>> getConverters()
	{
		return converters;
	}
	
	private HttpMessageConverter<Object> createXmlHttpMessageConverter()
	{
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		return xmlConverter;
	}

}
