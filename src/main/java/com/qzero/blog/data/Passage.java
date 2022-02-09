package com.qzero.blog.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Passage {

    @Id
    private String passageId;
    private String passageTitle;
    private Long postDate;
    @Transient
    private String content;

    public Passage() {
    }

    public Passage(String passageId, String passageTitle, Long postDate) {
        this.passageId = passageId;
        this.passageTitle = passageTitle;
        this.postDate = postDate;
    }

    public Passage(String passageId, String passageTitle, Long postDate, String content) {
        this.passageId = passageId;
        this.passageTitle = passageTitle;
        this.postDate = postDate;
        this.content = content;
    }

    public String getPassageId() {
        return passageId;
    }

    public void setPassageId(String passageId) {
        this.passageId = passageId;
    }

    public String getPassageTitle() {
        return passageTitle;
    }

    public void setPassageTitle(String passageTitle) {
        this.passageTitle = passageTitle;
    }

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "passageId='" + passageId + '\'' +
                ", passageTitle='" + passageTitle + '\'' +
                ", postDate=" + postDate +
                ", content='" + content + '\'' +
                '}';
    }
}
