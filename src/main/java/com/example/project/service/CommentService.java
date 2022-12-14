package com.example.project.service;

import com.example.project.dto.CommentDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.CommentEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.CommentRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
private final CommentRepository commentRepository;
private final BoardRepository boardRepository;
private final MemberRepository memberRepository;

    public void commentSave(CommentDTO commentDTO) {

            Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
            BoardEntity boardEntity = optionalBoardEntity.get();

            Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(commentDTO.getCommentWriter());
            MemberEntity memberEntity = optionalMemberEntity.get();


            CommentEntity commentEntity = CommentEntity.toCommentSave(commentDTO, boardEntity,memberEntity);

            commentRepository.save(commentEntity);

    }
}