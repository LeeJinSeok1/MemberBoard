package com.example.project.controller;

import com.example.project.dto.BoardDTO;
import com.example.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/boardSave")
    public String boardSavePage() {
        return "boardSave";
    }

    @PostMapping("/boardSave")
    public String boardSave(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.boardSave(boardDTO);
        return "redirect:boardPaging";
    }
    @GetMapping("/boardPaging")
    public String boardPaging(@PageableDefault(page =1)Pageable pageable,
                              Model model){
        Page<BoardDTO> boardDTOList = boardService.boardPaging(pageable);
        model.addAttribute("boardList",boardDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardDTOList.getTotalPages()) ? startPage + blockLimit - 1 : boardDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        System.out.println(boardDTOList);
        return "boardPaging";
    }

    @GetMapping("/boardDetail/{id}")
    public String boardDetail(@PathVariable Long id,
                              Model model){
      BoardDTO boardDTO =  boardService.boardDetail(id);
      model.addAttribute("board",boardDTO);
      return "boardDetail";
    }
    @GetMapping("/boardUpdate/{id}")
    public String boardUpdatePage(@PathVariable Long id,
                                  Model model) {
        BoardDTO boardDTO = boardService.boardDetail(id);
        model.addAttribute("board",boardDTO);
        return "boardUpdate";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(@ModelAttribute BoardDTO boardDTO){
        boardService.boardUpdate(boardDTO);
        return "redirect:boardPaging";
    }

    @Transactional
    @GetMapping("/boardDelete/{id}")
    public String boardDelete(@PathVariable Long id) {
        boardService.boardDelete(id);
        return "boardDelete";
    }

    @PostMapping("/boardSearch")
    public String search(@RequestParam("type") String type,@RequestParam("q") String q,
                         Model model){
        List<BoardDTO> searchList = boardService.search(type,q);
        model.addAttribute("boardList",searchList);
        System.out.println("searchList"+searchList);
        return "boardSearchList";

    }


}
