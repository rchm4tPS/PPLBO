package com.blog.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.vo.Comment;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Serializable> {
    List<Comment> findAllByPostIdOrderByRegDateDesc(Long postId);

    // This syntax below didn't give the same result of Hibernate output.
    // List<Comment> findByPostIdAndCommentContainingOrderByRegDateDesc(Long postId, String query);

    // here, use custom query JPQL (Java Persistence Query Language) that
    // operates on entities, not on tables. We can define more complex queries
    // beyond the capabilities of Spring Data JPA naming convention.

    @Query("SELECT c FROM Comment c WHERE c.postId = :postId AND (c.comment LIKE %:query% ESCAPE '\\') ORDER BY c.regDate DESC")
    List<Comment> findByPostIdAndCommentOrderByRegDateDesc(@Param("postId") Long postId, @Param("query") String query);

    Comment findOneById(Long id);
}
