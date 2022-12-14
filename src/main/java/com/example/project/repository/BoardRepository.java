package com.example.project.repository;

import com.example.project.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository  extends JpaRepository<BoardEntity,Long> {
    // 작성자 검색
    // select * from board_table where board_writer like '%q%' order by id desc;
    List<BoardEntity> findByBoardWriterContainingOrderByIdDesc(String q);

    // select * from board_table where board_title like '%q%' order by id desc;
    List<BoardEntity> findByBoardTitleContainingOrderByIdDesc(String q);


}
