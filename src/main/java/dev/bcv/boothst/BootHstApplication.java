package dev.bcv.boothst;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.hippoecm.repository.impl.DecoratorFactoryImpl;
import org.hippoecm.repository.impl.RepositoryDecorator;
import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.repository.RepositoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.bcv.boothst.hst.ServletContextInitializerImpl;

@SpringBootApplication
public class BootHstApplication {

    @Bean
    public Session getSession(BootRepository repository) throws RepositoryException {
        return repository.login("admin", "admin".toCharArray());
    }

    @Bean
    public BootRepository getHippoRepository() throws RepositoryException {
        BootRepository repository = RepoFactory.getHippoEnterpriseRepository();
        RepositoryDecorator repositoryDecorator = new RepositoryDecorator(new DecoratorFactoryImpl(), repository.getRepository());
        HippoServiceRegistry.registerService(repositoryDecorator, RepositoryService.class);
        return repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{BootHstApplication.class, ServletContextInitializerImpl.class}, args);
    }
}
