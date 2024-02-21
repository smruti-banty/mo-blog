package com.moblog.dev.service;

import java.util.List;

import com.moblog.dev.model.Blog;

public interface BlogService {
    void addBlog(Blog blog);

    List<Blog> getBlogs();

    Blog getBlog(int id);

    void deleteById(int id);

    void updateBlog(Blog blog);
}
