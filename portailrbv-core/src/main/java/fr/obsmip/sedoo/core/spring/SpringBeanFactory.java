package fr.obsmip.sedoo.core.spring;

import fr.obsmip.sedoo.core.BeanFactory;

public class SpringBeanFactory implements BeanFactory {

	@Override
	public Object getBeanByName(String beanName) 
	{
		return ApplicationContextProvider.getContext().getBean(beanName);
	}

}
