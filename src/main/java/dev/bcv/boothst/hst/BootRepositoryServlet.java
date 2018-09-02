package dev.bcv.boothst.hst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.jackrabbit.api.observation.JackrabbitEvent;
import org.hippoecm.repository.HippoRepositoryFactory;
import org.hippoecm.repository.VMHippoRepository;
import org.hippoecm.repository.audit.AuditLogger;
import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.cms7.services.eventbus.GuavaHippoEventBus;
import org.onehippo.cms7.services.eventbus.HippoEventBus;
import org.onehippo.repository.RepositoryService;
import org.onehippo.repository.cluster.RepositoryClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.bcv.boothst.BootRepository;
import dev.bcv.boothst.RepoFactory;

public class BootRepositoryServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(BootRepositoryServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        BootRepository repository;
        try {
            HippoEventBus hippoEventBus = new GuavaHippoEventBus();
            HippoServiceRegistry.registerService(hippoEventBus, HippoEventBus.class);

            AuditLogger listener = new AuditLogger();
            HippoServiceRegistry.registerService(listener, HippoEventBus.class);

            repository = RepoFactory.getHippoEnterpriseRepository();

            HippoRepositoryFactory.setDefaultRepository(repository);
            HippoServiceRegistry.registerService(repository.getRepository(), RepositoryService.class);
            HippoServiceRegistry.registerService((RepositoryClusterService) event -> {
                if (!(event instanceof JackrabbitEvent)) {
                    throw new IllegalArgumentException("Event is not an instance of JackrabbitEvent");
                }
                return ((JackrabbitEvent) event).isExternal();
            }, RepositoryClusterService.class);

            VMHippoRepository.register("vm://", repository);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
