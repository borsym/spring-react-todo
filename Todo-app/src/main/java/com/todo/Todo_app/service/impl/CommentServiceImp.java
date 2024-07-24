package com.todo.Todo_app.service.impl;


import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.Comments;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.CommentRepository;
import com.todo.Todo_app.repository.TaskRepository;
import com.todo.Todo_app.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
@Transactional
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    public CommentServiceImp(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    // TODO not good
    @Override
    public Comments createComment(UUID id, CommentDTO commentDTO, Users user) {
        Tasks task = findOrThrow(taskRepository, id, "Tasks");

        Comments comment = Comments.builder()
                .task(task)
                .commentText(commentDTO.getCommentText())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();


        task.getComments().add(comment);
        // this looks like a bit hacking for me
        commentRepository.save(comment);
        taskRepository.save(task);

        return comment;
    }
    @Override
    public List<Comments> getCommentsByTaskId(UUID id) {
        Tasks task = findOrThrow(taskRepository, id, "Tasks");
        return commentRepository.findByTask(task);
    }
    @Override
    public void deleteComment(UUID id) {
        Comments comments = findOrThrow(commentRepository, id, "Comments");
        commentRepository.delete(comments);
    }
}
