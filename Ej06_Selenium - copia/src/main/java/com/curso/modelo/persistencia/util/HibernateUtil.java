package com.curso.modelo.persistencia.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sf;
	
	public static SessionFactory getSF(){
		
		if(sf == null){
		    Configuration configuration = new Configuration();
		    configuration.configure("com/curso/cfg/hibernate.cfg.xml");
		    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sf = configuration.buildSessionFactory(serviceRegistry);				
		}
		
		return sf;		
	}	
}

