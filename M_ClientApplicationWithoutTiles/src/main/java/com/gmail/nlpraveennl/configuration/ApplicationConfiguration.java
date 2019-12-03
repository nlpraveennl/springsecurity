package com.gmail.nlpraveennl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.gmail.nlpraveennl"})
public class ApplicationConfiguration implements WebMvcConfigurer
{

	@Bean
	public ViewResolver configureViewResolver()
	{
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/jsp/");
		viewResolve.setSuffix(".jsp");

		return viewResolve;
	}

	@Bean
	public ThemeSource themeSource()
	{
		ResourceBundleThemeSource source = new ResourceBundleThemeSource();
		source.setBasenamePrefix("theme-");
		return source;
	}
	
	@Bean
	public ThemeResolver themeResolver()
	{
		CookieThemeResolver resolver = new CookieThemeResolver();
		resolver.setCookieMaxAge(2400);
		resolver.setCookieName("mythemecookie-java");
		resolver.setDefaultThemeName("blue");
		return resolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver()
	{
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("mylocalecookie-java");
		return localeResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
		
		ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
		themeInterceptor.setParamName("mytheme");
		registry.addInterceptor(themeInterceptor);
	}
}