package fr.obsmip.sedoo.core.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextProvider implements ServletContextListener {

	private static ServletContext context;
	
	public void contextDestroyed(ServletContextEvent e) {
		
	}

	public void contextInitialized(ServletContextEvent e) {
		 context = e.getServletContext();
	}
	
	public static ServletContext getContext() {
		return context;
	}

}
