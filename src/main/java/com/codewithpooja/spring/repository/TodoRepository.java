package com.codewithpooja.spring.repository;


import com.codewithpooja.spring.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class TodoRowMapper implements RowMapper<Todo> {

        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Todo(rs.getInt("id"), rs.getString("item"));
        }
    }

    public List<Todo> findAllTodo() {
        return jdbcTemplate.query("SELECT * FROM todo;", new TodoRepository.TodoRowMapper());
    }

    public List<Todo> insertTodo(Todo todo) {
        jdbcTemplate.update("INSERT INTO todo (id,item) VALUES (?,?)",
                new Object[]{todo.getId(), todo.getItem()});
        return findAllTodo();
    }

    public Todo findByIdTodo(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM todo WHERE Id = ?;", new TodoRepository.TodoRowMapper());
    }

    public List<Todo> updateTodo(Todo todo) {
        jdbcTemplate.update("UPDATE todo SET item=?, WHERE id =?;",
                new Object[]{todo.getItem(), todo.getId()});
        return findAllTodo();
    }

    public List<Todo> deleteByIdTodo(int id) {
        jdbcTemplate.update("DELETE FROM todo WHERE id =?;",
                new Object[]{id});
        return findAllTodo();
    }
}
