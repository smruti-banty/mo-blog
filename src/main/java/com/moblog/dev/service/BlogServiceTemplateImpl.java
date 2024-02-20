package com.moblog.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.moblog.dev.model.Blog;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class BlogServiceTemplateImpl implements BlogService{
    private final JdbcTemplate jdbcTemplate;

    @Value("${prefix}")
    private String prefix;

    public void addBlog(Blog blog) {
        var heading = prefix + " " + blog.getHeading();

        String sql = "INSERT INTO moblog (heading, description) VALUES (?,?)";
        jdbcTemplate.update(sql, heading, blog.getDescription());
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
