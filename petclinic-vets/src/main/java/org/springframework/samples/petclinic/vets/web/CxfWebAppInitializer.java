package org.springframework.samples.petclinic.vets.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Slf4j
public class CxfWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        log.info("Starting: " + CxfWebAppInitializer.class.getCanonicalName());

        container.setInitParameter("contextConfigLocation", "classpath:webapp-context.xml");
        container.addListener(ContextLoaderListener.class);

        ServletRegistration.Dynamic apiServlet = container.addServlet("CXFServlet", new CXFServlet());
        apiServlet.setLoadOnStartup(1);
        apiServlet.addMapping("/*");
    }
}
