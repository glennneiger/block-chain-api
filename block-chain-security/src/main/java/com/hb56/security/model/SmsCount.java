package com.hb56.security.model;

import java.io.Serializable;

/**
 * @author Been
 */
public class SmsCount implements Serializable {
    private String from = "9920000001";
    private String to;
    private int version = 1;
    private String message;
    private static final long serialVersionUID = 1L;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
