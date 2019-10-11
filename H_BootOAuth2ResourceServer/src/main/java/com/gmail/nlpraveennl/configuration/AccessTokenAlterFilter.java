package com.gmail.nlpraveennl.configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenAlterFilter implements Filter
{

	Logger OUT = LoggerFactory.getLogger(AccessTokenAlterFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		OUT.info("[[[[[[[[[[[[STARTED]]]]]]]]]]]]]]");

		CharResponseWrapper wrappedResponse = new CharResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, wrappedResponse);
		byte[] bytes = wrappedResponse.getByteArray();
		String out = new String(bytes);
		OUT.info("Response String: {}", out);
		
		response.getOutputStream().write(out.getBytes());
		
		OUT.info("[[[[[[[[[[[[ENDED]]]]]]]]]]]]]]");
	}

	private static class ByteArrayServletStream extends ServletOutputStream
	{
		ByteArrayOutputStream baos;

		ByteArrayServletStream(ByteArrayOutputStream baos)
		{
			this.baos = baos;
		}

		public void write(int param) throws IOException
		{
			baos.write(param);
		}

		@Override
		public boolean isReady()
		{
			return false;
		}

		@Override
		public void setWriteListener(WriteListener listener)
		{}
	}

	private static class ByteArrayPrintWriter
	{
		private ByteArrayOutputStream	baos	= new ByteArrayOutputStream();
		private PrintWriter				pw		= new PrintWriter(baos);
		private ServletOutputStream		sos		= new ByteArrayServletStream(baos);

		public PrintWriter getWriter()
		{
			return pw;
		}

		public ServletOutputStream getStream()
		{
			return sos;
		}

		byte[] toByteArray()
		{
			return baos.toByteArray();
		}
	}

	public class CharResponseWrapper extends HttpServletResponseWrapper
	{
		private ByteArrayPrintWriter	output;
		private boolean					usingWriter;

		public CharResponseWrapper(HttpServletResponse response)
		{
			super(response);
			usingWriter = false;
			output = new ByteArrayPrintWriter();
		}

		public byte[] getByteArray()
		{
			return output.toByteArray();
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException
		{
			if (usingWriter)
			{
				super.getOutputStream();
			}
			usingWriter = true;
			return output.getStream();
		}

		@Override
		public PrintWriter getWriter() throws IOException
		{
			if (usingWriter)
			{
				super.getWriter();
			}
			usingWriter = true;
			return output.getWriter();
		}

		public String toString()
		{
			return output.toString();
		}
	}
}
