package com.qzero.blog.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Token {

    @Id
    private String tokenId;
    /**
     * The username of the user who has the token
     */
    private String username;
    /**
     * Mark when the token will be expired
     * Negative or 0 means permanent valid
     */
    private long expiredTime;

    public Token() {
    }

    public Token(String tokenId, String username, long expiredTime) {
        this.tokenId = tokenId;
        this.username = username;
        this.expiredTime = expiredTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId='" + tokenId + '\'' +
                ", username='" + username + '\'' +
                ", expiredTime=" + expiredTime +
                '}';
    }
}
