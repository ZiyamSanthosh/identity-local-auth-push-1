package org.wso2.carbon.identity.local.auth.push.authenticator.impl;

import org.wso2.carbon.identity.application.authentication.framework.store.SessionDataStore;
import org.wso2.carbon.identity.local.auth.push.authenticator.PushAuthContextManager;
import org.wso2.carbon.identity.local.auth.push.authenticator.cache.PushAuthContextCache;
import org.wso2.carbon.identity.local.auth.push.authenticator.cache.PushAuthContextCacheEntry;
import org.wso2.carbon.identity.local.auth.push.authenticator.cache.PushAuthContextCacheKey;
import org.wso2.carbon.identity.local.auth.push.authenticator.model.PushAuthContext;

import static org.wso2.carbon.identity.local.auth.push.authenticator.constant.AuthenticatorConstants.PUSH_AUTH_CONTEXT_CACHE;

/**
 * This class implements the {@link PushAuthContextManager} interface.
 */
public class PushAuthContextManagerImpl implements PushAuthContextManager {

    @Override
    public void storeContext(String key, PushAuthContext context) {

        PushAuthContextCacheEntry entry = new PushAuthContextCacheEntry(context);
        PushAuthContextCache.getInstance().addToCache(new PushAuthContextCacheKey(key), entry);
        storeToSessionStore(key, entry);
    }

    @Override
    public PushAuthContext getContext(String key) {

        PushAuthContextCacheEntry cacheEntry = PushAuthContextCache.getInstance()
                .getValueFromCache(new PushAuthContextCacheKey(key));
        if (cacheEntry != null) {
            return cacheEntry.getPushAuthContext();
        } else {
            return getFromSessionStore(key).getPushAuthContext();
        }
    }

    @Override
    public void clearContext(String key) {

        PushAuthContextCache.getInstance().clearCacheEntry(new PushAuthContextCacheKey(key));
        clearFromSessionStore(key);
    }

    /**
     * Store push authentication context in session store.
     *
     * @param id            Unique key for identifying the push auth context for the session.
     * @param entry         Push auth context cache entry.
     */
    private void storeToSessionStore(String id, PushAuthContextCacheEntry entry) {

        SessionDataStore.getInstance().storeSessionData(id, PUSH_AUTH_CONTEXT_CACHE, entry);
    }

    /**
     * Get push authentication context from session store.
     *
     * @param key           Unique key for identifying the push auth context for the session.
     * @return              Push Auth context stored under unique key.
     */
    private PushAuthContextCacheEntry getFromSessionStore(String key) {

        return (PushAuthContextCacheEntry) SessionDataStore.getInstance().getSessionData(key, PUSH_AUTH_CONTEXT_CACHE);
    }

    /**
     * Clear push authentication context from session store.
     *
     * @param key           Unique key for identifying the push auth context for the session.
     */
    private void clearFromSessionStore(String key) {

        SessionDataStore.getInstance().clearSessionData(key, PUSH_AUTH_CONTEXT_CACHE);
    }
}