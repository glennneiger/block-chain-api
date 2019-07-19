package com.hb56.resource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Been
 */
@ConfigurationProperties(prefix = "security-config")
public class SecuritySettings {
    private String revokeTokenUri = "/logout";
    private String permit = "/favicon.ico";
    private String authenticate = "/";
    private String resourceId = "oauth2-resource";
    private String basic;
    private String accessExpression = "true";

    public String getRevokeTokenUri() {
        return revokeTokenUri;
    }

    public void setRevokeTokenUri(String revokeTokenUri) {
        this.revokeTokenUri = revokeTokenUri;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getAccessExpression() {
        return accessExpression;
    }

    public void setAccessExpression(String accessExpression) {
        this.accessExpression = accessExpression;
    }
}
