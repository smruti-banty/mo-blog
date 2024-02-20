package com.moblog.dev.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import com.moblog.dev.model.Blog;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogServiceClientImpl implements BlogService {
    private final JdbcClient jdbcClient;
    @Value("${prefix}")
    private String prefix;

    @Override
    public void addBlog(Blog blog) {
        var heading = prefix + " " + blog.getHeading();
        var description = blog.getDescription();

        String sql = "INSERT INTO moblog (heading, description) VALUES (:h,:d)";
        jdbcClient.sql(sql)
                .param("h", heading)
                .param("d", description)
                .update();
        // jdbcClient.sql(sql).params(List.of(heading, description)).update();
        // jdbcClient.sql(sql).params(Map.of("h",heading, "d", description)).update();
    }

    @Override
    public Blog getBlog(int id) {
        String sql = "SELECT * FROM moblog WHERE id = ?";
        var optional = jdbcClient.sql(sql)
                .param(id)
                .query(Blog.class).optional();

        return optional.orElseThrow();
    }

    @Override
    public List<Blog> getBlogs() {
        String sql = "SELECT * FROM moblog";
        return jdbcClient.sql(sql).query(Blog.class).list();
    }

}
