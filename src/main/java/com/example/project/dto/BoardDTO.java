package com.example.project.dto;

import com.example.project.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private Long id ;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private String boardPass;
    private int boardHits;
    private LocalDateTime boardSaveTime;

    private MultipartFile boardFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public BoardDTO(Long id, String boardWriter, String boardTitle,int boardHits, LocalDateTime boardSaveTime){
        this.id = id;
        this.boardWriter=boardWriter;
        this.boardTitle=boardTitle;
        this.boardHits=boardHits;
        this.boardSaveTime=boardSaveTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardSaveTime(boardEntity.getBoardSaveTime());

        if(boardEntity.getFileAttached() ==1){
            boardDTO.setFileAttached(boardEntity.getFileAttached());

            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());
        }else{
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        }
        return boardDTO;
    }
}
