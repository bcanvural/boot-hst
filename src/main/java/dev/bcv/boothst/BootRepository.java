package dev.bcv.boothst;

import javax.jcr.RepositoryException;

import org.apache.jackrabbit.core.config.RepositoryConfig;

import com.onehippo.repository.HippoEnterpriseRepository;

public class BootRepository extends HippoEnterpriseRepository {

    public BootRepository(final String repositoryDirectory, final String repositoryConfig) throws RepositoryException {
        super(repositoryDirectory, repositoryConfig);
        super.initialize();
    }

    public RepositoryConfig createRepositoryConfig() throws RepositoryException {
        return super.createRepositoryConfig();
    }
}