package com.ql.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ql.blog.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
