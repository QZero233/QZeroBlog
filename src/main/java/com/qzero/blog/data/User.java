package com.qzero.blog.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String username;
    /**
     * Specifically, sha256 hex-encoded all-upper-case
     */
    private String passwordHash;
    /**
     * Mark if the account is activated
     */
    private boolean activated;
    /**
     * The group that the user belongs to
     */
    private String groupId;
    /**
     * Kinda of self-description
     */
    private String saying;

    public User() {
    }

    public User(String username, String passwordHash, boolean activated, String groupId, String saying) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.activated = activated;
        this.groupId = groupId;
        this.saying = saying;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", activated=" + activated +
                ", groupId='" + groupId + '\'' +
                ", saying='" + saying + '\'' +
                '}';
    }
}
