package com.example.testwork.repositories;

import com.example.testwork.models.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {
    @Mock
    private ResultSet resultSet;
    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        taskMapper = new TaskMapper();
    }

    @AfterEach
    void tearDown() {
        taskMapper = null;
        resultSet = null;
    }

    @Test
    void mapRow() throws SQLException {
        Task expectedResult = createTask();

        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getLong("topic_of_tasks_id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("test");
        when(resultSet.getString("description")).thenReturn("description");
        when(resultSet.getString("date")).thenReturn("06 20, 2022 20:50:00");

        Task actualResult = taskMapper.mapRow(resultSet, 0);
        assertEquals(expectedResult, actualResult);
    }

    private Task createTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTopicTaskId(1L);
        task.setName("test");
        task.setDescription("description");
        task.setDate("06 20, 2022 20:50:00");
        return task;
    }
}