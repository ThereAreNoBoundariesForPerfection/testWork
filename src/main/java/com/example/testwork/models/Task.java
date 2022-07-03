package com.example.testwork.models;

import java.util.Objects;

public class Task {
    private Long id;
    private Long topicTaskId;
    private String name;
    private String description;
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicTaskId() {
        return topicTaskId;
    }

    public void setTopicTaskId(Long topicTaskId) {
        this.topicTaskId = topicTaskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(topicTaskId, task.topicTaskId) && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicTaskId, name, description, date);
    }
}
