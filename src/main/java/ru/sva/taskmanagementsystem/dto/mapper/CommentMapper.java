package ru.sva.taskmanagementsystem.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.sva.taskmanagementsystem.dto.CommentDto;
import ru.sva.taskmanagementsystem.model.Comment;

@UtilityClass
public class CommentMapper {
    public CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = CommentDto.builder()
                .text(comment.getText())
                .author(UserMapper.toUserDto(comment.getAuthor()))
                .build();

        return  commentDto;
    }
}
