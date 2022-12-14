package com.example.project.repository;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.CommentEntity;
import com.example.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity
                                                                  boardEntity);
}
