package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.MemberDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.BoardFileEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.repository.BoardFileRepository;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void boardSave(BoardDTO boardDTO) throws IOException {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(boardDTO.getBoardWriter()).get();
        if(boardDTO.getBoardFile().isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO, memberEntity);
            boardRepository.save(boardEntity);
        }else{
            MultipartFile boardFile = boardDTO.getBoardFile();
            String originalFileName = boardFile.getOriginalFilename();
            String storedFileName =System.currentTimeMillis()+"_"+originalFileName;
            String savePath = "D:\\springboot_img\\" + storedFileName;
            boardFile.transferTo(new File(savePath));
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO,memberEntity);
            Long savedId =boardRepository.save(boardEntity).getId();

            BoardEntity entity =boardRepository.findById(savedId).get();
            BoardFileEntity boardFileEntity =BoardFileEntity.toSaveBoardEntity(entity,originalFileName,storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    }

    public Page<BoardDTO> boardPaging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        final int pageLimit =3;
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit,
                Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardDTO> boardList = boardEntities.map(
                board-> new BoardDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits(),
                        board.getBoardSaveTime())
        );

        return boardList;


    }
    @Transactional
    public BoardDTO boardDetail(Long id) {
       BoardEntity boardEntity= boardRepository.findById(id).get();
       BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
       return boardDTO;
    }
}


