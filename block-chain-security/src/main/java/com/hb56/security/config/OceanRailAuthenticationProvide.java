package com.hb56.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;


/**
 * @author Been
 */
public class OceanRailAuthenticationProvide extends DaoAuthenticationProvider {
    @Autowired
    private AuthenticationEventPublisher eventPublisher;

    private void copyDetails(Authentication source, Authentication dest) {
        if ((dest instanceof AbstractAuthenticationToken) && (dest.getDetails() == null)) {
            AbstractAuthenticationToken token = (AbstractAuthenticationToken) dest;

            token.setDetails(source.getDetails());
        }
    }

    @SuppressWarnings("deprecation")
    private void prepareException(AuthenticationException ex, Authentication auth) {
        eventPublisher.publishAuthenticationFailure(ex, auth);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        Class<? extends Authentication> toTest = authentication.getClass();
        AuthenticationException lastException = null;
        Authentication result = null;
        try {
            result = super.authenticate(authentication);
            if (result != null) {
                copyDetails(authentication, result);
            }
        } catch (AccountStatusException | InternalAuthenticationServiceException e) {
            prepareException(e, authentication);
            throw e;
        } catch (AuthenticationException e) {
            lastException = e;
        }

        if (result != null) {
            if (result instanceof CredentialsContainer) {
                ((CredentialsContainer) result).eraseCredentials();
            }
            eventPublisher.publishAuthenticationSuccess(result);
            return result;
        }
        if (lastException == null) {
            lastException = new ProviderNotFoundException(messages.getMessage(
                    "ProviderManager.providerNotFound",
                    new Object[]{toTest.getName()},
                    "No AuthenticationProvider found for {0}"));
        }
        prepareException(lastException, authentication);
        throw lastException;
    }
}
