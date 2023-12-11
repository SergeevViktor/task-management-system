package ru.sva.taskmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sva.taskmanagementsystem.dto.comment.CommentCreateDto;
import ru.sva.taskmanagementsystem.dto.comment.CommentDto;
import ru.sva.taskmanagementsystem.service.CommentServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;

    @PostMapping("/create/{taskId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long taskId,
                                                    @RequestBody CommentCreateDto commentCreateDto,
                                                    Principal principal) {
        return ResponseEntity.ok().body(commentService.createComment(taskId, commentCreateDto, principal.getName()));
    }

    @DeleteMapping("/delete-comment/{taskId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long taskId) {
        return ResponseEntity.ok().body(commentService.deleteCommentById(taskId));
    }
}
