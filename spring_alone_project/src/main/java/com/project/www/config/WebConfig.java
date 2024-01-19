package com.project.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true);
		
		return new Filter[] {encoding};
	}
	
	protected void customizeRegistration(Dynamic registration){
		//그 외 기타 사용자 설정
		//사용자 지정 익셉션 설정을 할 것인지 처리 throwExceptionIfNoHandlerFound
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
		String uploadLocation = "C:\\_myProject\\_java\\_fileUpload";
		int maxFileSize = 1024 * 1024 * 20;
		int maxRequestSize = maxFileSize * 2;
		int fileSizeThreshold = maxFileSize;
		
		MultipartConfigElement multiConfig = 
				new MultipartConfigElement(uploadLocation, maxFileSize, maxRequestSize, fileSizeThreshold);
		registration.setMultipartConfig(multiConfig);
	}
	
}
