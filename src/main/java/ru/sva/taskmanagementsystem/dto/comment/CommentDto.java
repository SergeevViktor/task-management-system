package ru.sva.taskmanagementsystem.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.sva.taskmanagementsystem.dto.user.UserDto;

@Data
@Builder
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private UserDto author;
}
