package ru.sva.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sva.taskmanagementsystem.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
