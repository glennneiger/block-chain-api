package com.hb56.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Been
 */
@Component
@ConfigurationProperties(prefix = "lock-user")
public class LockUserSettings {
    private String keyLock;
    private String keyLocked;
    private int failCount;
    private long lockHours;
    private long lockedHours;

    public String getKeyLock() {
        return keyLock;
    }

    public void setKeyLock(String keyLock) {
        this.keyLock = keyLock;
    }

    public String getKeyLocked() {
        return keyLocked;
    }

    public void setKeyLocked(String keyLocked) {
        this.keyLocked = keyLocked;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public long getLockHours() {
        return lockHours;
    }

    public void setLockHours(long lockHours) {
        this.lockHours = lockHours;
    }

    public long getLockedHours() {
        return lockedHours;
    }

    public void setLockedHours(long lockedHours) {
        this.lockedHours = lockedHours;
    }
}
