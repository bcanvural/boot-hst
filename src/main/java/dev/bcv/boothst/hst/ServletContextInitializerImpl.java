package dev.bcv.boothst.hst;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hippoecm.hst.container.HstFilter;
import org.hippoecm.hst.container.XSSUrlFilter;
import org.hippoecm.hst.site.container.HstContextLoaderListener;
import org.hippoecm.hst.site.container.session.HttpSessionEventPublisher;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class ServletContextInitializerImpl implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.err.println("------------------------------------");
        //Context params
        servletContext.setInitParameter("hst-beans-annotated-classes",
                "classpath*:org/example/**/*.class\n" +
                        "      ,classpath*:org/onehippo/**/*.class\n" +
                        "      ,classpath*:com/onehippo/**/*.class\n" +
                        "      ,classpath*:org/onehippo/forge/**/*.class");
        //Servlet Registrations START
        FilterRegistration.Dynamic filterRegistration;
        //CharacterEncodingFilter
        filterRegistration = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        //XSSUrlFilter
        filterRegistration = servletContext.addFilter("XSSUrlFilter", XSSUrlFilter.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        //HstFilter
        filterRegistration = servletContext.addFilter("HstFilter", HstFilter.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        //Servlet Registrations END
        //Listener Registrations
        servletContext.addListener(new HstContextLoaderListener());
        servletContext.addListener(new HttpSessionEventPublisher());

    }

}