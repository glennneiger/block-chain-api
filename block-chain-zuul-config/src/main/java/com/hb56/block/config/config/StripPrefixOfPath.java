package com.hb56.block.config.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Been
 */
@Component
public class StripPrefixOfPath {

    private final ZuulProperties properties;

    @Autowired
    public StripPrefixOfPath(ZuulProperties properties) {
        this.properties = properties;
    }

    public String getUrl(String url) {
        if (!"".equals(properties.getPrefix())) {
            url = url.replace(properties.getPrefix(), "");
        }
        Collection<ZuulProperties.ZuulRoute> set = properties.getRoutes().values();
        for (ZuulProperties.ZuulRoute route : set) {
            String tmp = route.getPath().replace("/**", "");
            if (url.startsWith(tmp)) {
                return url.replace(tmp, "");
            }
        }
        return url;
    }
}
