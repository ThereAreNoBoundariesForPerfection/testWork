package com.example.testwork.repositories;

import com.example.testwork.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TaskRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void saveTask(Task task) {
        namedParameterJdbcTemplate.update("INSERT INTO tasks (topic_of_tasks_id, name, description, date) VALUES (:topic_of_tasks_id, :name, :description, :date);",
                new MapSqlParameterSource().addValue("topic_of_tasks_id", task.getTopicTaskId()).addValue("name", task.getName()).addValue("description",
                        task.getDescription()).addValue("date", task.getDate()));
    }

    @Override
    public Task getTask(Long id) {
        String sql = "select * from tasks where id=?;";

        List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper(), id);
        return DataAccessUtils.uniqueResult(tasks);
    }

    @Override
    public void updateTask(Task task) {
        namedParameterJdbcTemplate.update("UPDATE tasks SET topic_of_tasks_id = :topic_of_tasks_id, name = :name, description = :description, date = :date WHERE id = :id",
                new MapSqlParameterSource().addValue("topic_of_tasks_id", task.getTopicTaskId()).addValue("name", task.getName()).addValue("description", task.getDescription())
                        .addValue("date", task.getDate()).addValue("id", task.getId()));
    }

    @Override
    public void deleteTask(Long id) {
        namedParameterJdbcTemplate.update("DELETE FROM tasks where id = :id;", new MapSqlParameterSource().addValue("id", id));
    }

    @Override
    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, new TaskMapper());
    }

    public Task getTaskByName(String name) {
        String sql = "select * from tasks where name=?;";
        List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper(), name);
        return DataAccessUtils.uniqueResult(tasks);
    }

    public List<Task> getAllTasksDesk() {
        String sql = "SELECT * FROM tasks ORDER BY id DESC";
        return jdbcTemplate.query(sql, new TaskMapper());
    }
}
