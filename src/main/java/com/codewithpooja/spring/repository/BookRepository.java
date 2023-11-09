package com.codewithpooja.spring.repository;

import com.codewithpooja.spring.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow (ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"));
        }
        }
        public List<Book> findAll() {
            return jdbcTemplate.query("SELECT * FROM book;", new BookRowMapper());
        }

    public Book findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE Id = ?;", new BookRowMapper());
    }

    public List<Book> insert(Book book){
        jdbcTemplate.update("INSERT INTO book (id,name, author) VALUES (?,?,?)",
                new Object[]{book.getId(), book.getName(), book.getAuthor()});
        return findAll();
    }

    public List<Book> update(Book book){
        jdbcTemplate.update("UPDATE book SET name=? , author =? WHERE id =?;",
                new Object[]{book.getName(), book.getAuthor(), book.getId()});
        return findAll();
    }

    public List<Book> deleteById(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id =?;",
                new Object[]{id});
        return findAll();
    }
}
