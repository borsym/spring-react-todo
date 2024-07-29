package com.todo.Todo_app.service.impl;


import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.CommentsEntity;
import com.todo.Todo_app.model.TasksEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.repository.CommentRepository;
import com.todo.Todo_app.repository.TaskRepository;
import com.todo.Todo_app.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    // TODO not good
    @Override
    public CommentsEntity createComment(UUID id, CommentDTO commentDTO, UsersEntity user) {
        TasksEntity task = findOrThrow(taskRepository, id, "Tasks");

        CommentsEntity comment = CommentsEntity.builder()
                .task(task)
                .commentText(commentDTO.getCommentText())
                .user(user)
                .build();


        task.getComments().add(comment);
        commentRepository.save(comment);
        taskRepository.save(task);

        return comment;
    }
    @Override
    public List<CommentsEntity> getCommentsByTaskId(UUID id) {
        TasksEntity task = findOrThrow(taskRepository, id, "Tasks");
        return commentRepository.findByTask(task);
    }
    @Override
    public void deleteComment(UUID id) {
        CommentsEntity comments = findOrThrow(commentRepository, id, "Comments");
        commentRepository.delete(comments);
    }
}
