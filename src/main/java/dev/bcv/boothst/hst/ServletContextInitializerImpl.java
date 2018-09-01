package dev.bcv.boothst.hst;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.hippoecm.hst.container.HstFilter;
import org.hippoecm.hst.container.XSSUrlFilter;
import org.hippoecm.hst.security.servlet.LoginServlet;
import org.hippoecm.hst.servlet.BinariesServlet;
import org.hippoecm.hst.servlet.HstFreemarkerServlet;
import org.hippoecm.hst.servlet.HstPingServlet;
import org.hippoecm.hst.site.container.HstContextLoaderListener;
import org.hippoecm.hst.site.container.session.HttpSessionEventPublisher;
import org.onehippo.cms7.utilities.servlet.ResourceServlet;
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

        //Servlet registrations

        ServletRegistration.Dynamic binariesServlet = servletContext.addServlet("BinariesServlet", BinariesServlet.class);
        binariesServlet.addMapping("/binaries/*");

        ServletRegistration.Dynamic freemarkerServlet = servletContext.addServlet("freemarker", HstFreemarkerServlet.class);
        freemarkerServlet.setInitParameter("TemplatePath", "/");
        freemarkerServlet.setInitParameter("ContentType", "text/html; charset=UTF-8");
        freemarkerServlet.addMapping("*.ftl");
        freemarkerServlet.setLoadOnStartup(200);

        ServletRegistration.Dynamic templateComposerResourceServlet = servletContext.addServlet("TemplateComposerResourceServlet", ResourceServlet.class);
        templateComposerResourceServlet.setInitParameter("jarPathPrefix", "/META-INF/hst/pagecomposer");
        templateComposerResourceServlet.addMapping("/hst/pagecomposer/sources/*");

        ServletRegistration.Dynamic loginServlet = servletContext.addServlet("LoginServlet", LoginServlet.class);
        loginServlet.addMapping("/login/*");

        ServletRegistration.Dynamic securityResourceServlet = servletContext.addServlet("SecurityResourceServlet", ResourceServlet.class);
        securityResourceServlet.setInitParameter("jarPathPrefix", "/META-INF/hst/security");
        securityResourceServlet.addMapping("/login/hst/security/*");

        ServletRegistration.Dynamic hstResourceServlet = servletContext.addServlet("HstResourceServlet", ResourceServlet.class);
        hstResourceServlet.setInitParameter("jarPathPrefix", "/META-INF/web-resources");
        hstResourceServlet.addMapping("/resources/*");


        ServletRegistration.Dynamic pingServlet = servletContext.addServlet("PingServlet", HstPingServlet.class);
        pingServlet.addMapping("/ping/*");
        
    }

}