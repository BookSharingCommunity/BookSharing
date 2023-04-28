package com.booksharing.apisystem.requests;

public class NewMessageRequest {
    private String username;
    private String content;

    private long threadId;

    public NewMessageRequest(String username, String content, long threadId) {
        this.username = username;
        this.content = content;
        this.threadId = threadId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }
}
