package com.oncloud6.atd.hibernate;

import com.oncloud6.atd.domain.Gebruiker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateConnector {

    private SessionFactory factory;

    public HibernateConnector() {
	   buildSessionFactory();
    }

    private void buildSessionFactory() {
	   try {
		  factory = new AnnotationConfiguration().configure().
				//hier moet je de Annotated klasse toevoegen via addAnnotatedClass(classname)
				addAnnotatedClass(Gebruiker.class).
				buildSessionFactory();
	   } catch (Throwable ex) {
		  System.err.println("Failed to create sessionFactory object." + ex);
		  throw new ExceptionInInitializerError(ex);
	   }

    }

    public SessionFactory getSessionFactory() {
	   return factory;
    }
}
