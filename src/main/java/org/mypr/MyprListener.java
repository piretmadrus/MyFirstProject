package org.mypr;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

@WebListener
public class MyprListener implements ServletContextListener, HttpSessionListener{
	Logger log = Logger.getLogger(MyprListener.class);
		@Override
		public void contextInitialized(ServletContextEvent sce) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sessionCreated(HttpSessionEvent se) {
			System.out.println("Session ID: " + se.getSession().getId());
			
		}

		@Override
		public void sessionDestroyed(HttpSessionEvent se) {
			System.out.println("Session destroyed: " + se.getSession().getId());
			
		}
}
