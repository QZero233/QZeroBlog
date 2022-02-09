package com.qzero.blog;

public class RedirectionTarget {

    private String targetUrl;
    private String targetName;
    private String message;

    public RedirectionTarget() {
    }

    public RedirectionTarget(String targetUrl, String targetName, String message) {
        this.targetUrl = targetUrl;
        this.targetName = targetName;
        this.message = message;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RedirectionTarget{" +
                "targetUrl='" + targetUrl + '\'' +
                ", targetName='" + targetName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
