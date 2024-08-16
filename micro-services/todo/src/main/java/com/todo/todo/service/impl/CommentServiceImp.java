package com.todo.todo.service.impl;


import com.todo.todo.api.exception.EntityNotFoundException;
import com.todo.todo.dto.CommentDTO;
import com.todo.todo.model.CommentsEntity;
import com.todo.todo.model.TasksEntity;
import com.todo.todo.model.UsersEntity;
import com.todo.todo.repository.CommentRepository;
import com.todo.todo.repository.TaskRepository;
import com.todo.todo.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    // TODO not good
    @Override
    public CommentsEntity createComment(UUID id, CommentDTO commentDTO, UsersEntity user) {
        TasksEntity task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task"));

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
        TasksEntity task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task"));
        return commentRepository.findByTask(task);
    }

    @Override
    public void deleteComment(UUID id) {
        CommentsEntity comments = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment"));
        commentRepository.delete(comments);
    }
}
