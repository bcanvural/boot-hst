package dev.bcv.boothst;

import javax.jcr.Credentials;
import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.onehippo.repository.RepositoryService;

public class RepositoryServiceImpl implements RepositoryService {
    @Override
    public String[] getDescriptorKeys() {
        return new String[0];
    }

    @Override
    public boolean isStandardDescriptor(final String key) {
        return false;
    }

    @Override
    public boolean isSingleValueDescriptor(final String key) {
        return false;
    }

    @Override
    public Value getDescriptorValue(final String key) {
        return null;
    }

    @Override
    public Value[] getDescriptorValues(final String key) {
        return new Value[0];
    }

    @Override
    public String getDescriptor(final String key) {
        return null;
    }

    @Override
    public Session login(final Credentials credentials, final String workspaceName) throws LoginException, NoSuchWorkspaceException, RepositoryException {
        return null;
    }

    @Override
    public Session login(final Credentials credentials) throws LoginException, RepositoryException {
        return null;
    }

    @Override
    public Session login(final String workspaceName) throws LoginException, NoSuchWorkspaceException, RepositoryException {
        return null;
    }

    @Override
    public Session login() throws LoginException, RepositoryException {
        return null;
    }
}
