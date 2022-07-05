package com.example.testwork.repositories;

import com.example.testwork.models.TopicTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicTaskRepositoryImpl implements TopicTaskRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TopicTaskRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void saveTopicTask(TopicTask topicTask) {
        namedParameterJdbcTemplate.update("INSERT INTO topic_of_tasks (name) VALUES (:name);",
                new MapSqlParameterSource().addValue("name", topicTask.getName()));
    }

    @Override
    public TopicTask getTopicTask(Long id) {
        String sql = "select * from topic_of_tasks where id=?;";

        List<TopicTask> tasks = jdbcTemplate.query(sql, new TopicTaskMapper(), id);
        return DataAccessUtils.uniqueResult(tasks);

    }

    @Override
    public void updateTopicTask(TopicTask topicTask) {
        namedParameterJdbcTemplate.update("UPDATE topic_of_tasks SET name = :name WHERE id = :id;",
                new MapSqlParameterSource().addValue("name", topicTask.getName()).addValue("id", topicTask.getId()));
    }

    @Override
    public void deleteTopicTask(Long id) {
        namedParameterJdbcTemplate.update("DELETE FROM topic_of_tasks where id = :id;", new MapSqlParameterSource().addValue("id", id));
    }

    @Override
    public List<TopicTask> getAllTopicTask() {
        String sql = "SELECT * FROM topic_of_tasks";
        return jdbcTemplate.query(sql, new TopicTaskMapper());
    }


    public TopicTask getTopicTaskByName(String name) {
        String sql = "select * from topic_of_tasks where name=?;";

        List<TopicTask> tasks = jdbcTemplate.query(sql, new TopicTaskMapper(), name);
        return DataAccessUtils.uniqueResult(tasks);
    }
}
