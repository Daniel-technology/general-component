package com.general.component.mvc.core.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * web上下文
 *
 * @author littlesnail
 * @create 2023−04-17 10:38 AM
 * @since 0.6.0
 */
public abstract class WebContext implements ApplicationContextAware {

    /**
     * dispatcherServlet
     */
    public static final String DEFAULT_DISPATCHER_SERVLET_BEAN_NAME = "dispatcherServlet";
    /**
     * applicationContext
     */
    private static ApplicationContext applicationContext;

    public static ApplicationContext getAppContext() {
        Assert.isTrue(WebContext.isInit(), "No initialization running in the spring environment");
        return applicationContext;
    }

    public static boolean isInit() {
        return applicationContext != null;
    }

    public static <T> T getBean(Class<T> requiredType) {
        Assert.isTrue(WebContext.isInit(), "No initialization running in the spring environment");
        return applicationContext.getBean(requiredType);
    }

    public static Object getBean(String name) {
        Assert.isTrue(WebContext.isInit(), "No initialization running in the spring environment");
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        Assert.isTrue(WebContext.isInit(), "No initialization running in the spring environment");
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * ContextHolder
     */
    public static class ContextHolder implements ApplicationContextAware {
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            if (WebContext.applicationContext == null) {
                WebContext.applicationContext = applicationContext;
            }
        }
    }

    public static DispatcherServlet getDispatcherServlet() {
        return WebContext.getBean(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME, DispatcherServlet.class);
    }
}
