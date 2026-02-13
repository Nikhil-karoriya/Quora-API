package com.example.QuoraAPI.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,UUID> {
    
    @Query("""
       SELECT DISTINCT q
       FROM Question q
       JOIN q.topicQuestions tq
       JOIN tq.topic t
       WHERE 
           (LOWER(q.title) LIKE LOWER(CONCAT('%', :text, '%'))
           OR LOWER(q.body) LIKE LOWER(CONCAT('%', :text, '%')))
       AND LOWER(t.name) = LOWER(:tag)
       """)
    public List<Question> findByTextAndTag(@Param("text") String text,@Param("tag") String tag);
}
