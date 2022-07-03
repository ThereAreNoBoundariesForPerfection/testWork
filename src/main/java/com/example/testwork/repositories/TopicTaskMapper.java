package com.example.testwork.repositories;

import com.example.testwork.models.TopicTask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicTaskMapper implements RowMapper<TopicTask> {

    @Override
    public TopicTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        TopicTask topicTask = new TopicTask();
        topicTask.setId(rs.getLong("id"));
        topicTask.setName(rs.getString("name"));

        return topicTask;
    }
}
