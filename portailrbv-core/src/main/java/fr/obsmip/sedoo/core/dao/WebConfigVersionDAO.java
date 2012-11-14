package fr.obsmip.sedoo.core.dao;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import fr.obsmip.sedoo.core.servlet.ServletContextProvider;

public class WebConfigVersionDAO implements VersionDAO{

	private static final String INFORMATION_NON_DISPONIBLE = "Information non disponible";
	private static final String DEVELOPEMENT_MODE = "Mode développement - version inaccessible";
	private static final String DEFAULT_KEY = "${version}";

	@Override
	public String getVersion() {
		ServletContext context = ServletContextProvider.getContext();
		String initParameter = context.getInitParameter("version");
		if (StringUtils.isEmpty(initParameter))
		{
			return INFORMATION_NON_DISPONIBLE;
		}
		if (initParameter.compareToIgnoreCase(DEFAULT_KEY)==0)
		{
			return DEVELOPEMENT_MODE;
		}
		else
		{
			return initParameter.trim();
		}
	}
	
	
	
	

}
