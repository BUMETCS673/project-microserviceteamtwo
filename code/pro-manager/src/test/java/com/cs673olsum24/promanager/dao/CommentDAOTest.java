package com.cs673olsum24.promanager.dao;

import com.cs673olsum24.promanager.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentDAOTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CommentDAO commentDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private Comment comment;

    @BeforeEach
    void setUp() {
        commentDAO = new CommentDAO(namedParameterJdbcTemplate);
        commentDAO.setEntityManager(entityManager);

        comment = new Comment();
        comment.setProjectId("proj_001");
        comment.setComments("This is a sample comment");
        comment.setUserId(2);
        comment.setCreatedOn(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void testGetAllComments() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("proj_001");
        assertThat(comments).isNotEmpty();
        assertThat(comments.get(0)).containsKeys("comment_id", "project_id", "comments", "user_id", "created_on");
    }

    @Test
    void testAddComment() {
        commentDAO.addComment(comment);

        List<Map<String, Object>> comments = commentDAO.getAllComments("proj_001");
        assertThat(comments).isNotEmpty();
        assertThat(comments.get(comments.size() - 1)).containsEntry("comments", "This is a sample comment");
    }

   
    @Test
    void testEditComment() {
        List<Map<String, Object>> commentsBeforeEdit = commentDAO.getAllComments("proj_001");
        int commentId = (int) commentsBeforeEdit.get(0).get("comment_id");

        comment.setId(commentId);
        comment.setComments("Updated comment");

        commentDAO.editComment(comment);

        List<Map<String, Object>> commentsAfterEdit = commentDAO.getAllComments("proj_001");
        assertThat(commentsAfterEdit).isNotEmpty();
        assertThat(commentsAfterEdit.get(0)).containsEntry("comments", "Updated comment");
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }

        @Bean
        CommentDAO commentDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            return new CommentDAO(namedParameterJdbcTemplate);
        }
    }
}
