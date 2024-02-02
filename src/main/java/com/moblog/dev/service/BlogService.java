package com.moblog.dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.moblog.dev.Blog;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final JdbcTemplate jdbcTemplate;

    public void addBlog(Blog blog) {
        String sql = "INSERT INTO moblog (heading, description) VALUES (?,?)";
        jdbcTemplate.update(sql, blog.getHeading(), blog.getDescription());
    }

    public List<Blog> getBlogs() {
        String sql = "SELECT * FROM moblog";

        RowMapper<Blog> rowMapper = (resultSet, rowNum) -> new Blog(
                resultSet.getInt("id"),
                resultSet.getString("heading"),
                resultSet.getString("description"));

        return jdbcTemplate.query(sql, rowMapper);
        // return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Blog>());
    }

    public Blog getBlog(int id) {
        String sql = "SELECT * FROM moblog WHERE id = ?";
        RowMapper<Blog> rowMapper = (resultSet, rowNum) -> new Blog(
            resultSet.getInt("id"),
            resultSet.getString("heading"),
            resultSet.getString("description"));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
