package com.example.testwork.repositories;

import com.example.testwork.models.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setTopicTaskId(rs.getLong("topic_of_tasks_id"));
        task.setName(rs.getString("name"));
        task.setDescription(rs.getString("description"));
        task.setDate(rs.getString("date"));
        return task;
    }
}
