package fr.obsmip.sedoo.core.parameter.parameterprovider;

import javax.servlet.ServletContext;

import fr.obsmip.sedoo.core.servlet.ServletContextProvider;

public class WebParameterProvider implements ReadableParameterProvider{

	public WebParameterProvider() {
	}
	

	
	@Override
	public String getParameter(String input) 
	{
		ServletContext context = ServletContextProvider.getContext();
		String initParameter = context.getInitParameter(input);
		return initParameter;
	}

}
