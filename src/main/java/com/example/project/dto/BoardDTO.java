package com.example.project.dto;

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
}
