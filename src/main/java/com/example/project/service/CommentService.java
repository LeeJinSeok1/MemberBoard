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

import java.util.ArrayList;
import java.util.List;
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

    public List<CommentDTO> commentList(Long boardId) {
        Optional<BoardEntity> optionalCommentEntity =boardRepository.findById(boardId);
        BoardEntity boardEntity=optionalCommentEntity.get();

        List<CommentEntity> commentEntityList =
                commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

       List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList){
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(commentEntity.getId());
            commentDTO.setCommentWriter(commentEntity.getCommentWriter());
            commentDTO.setCommentContents(commentEntity.getCommentContents());
            commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
            commentDTO.setMemberId(commentEntity.getMemberEntity().getId());
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}