package com.example.project.controller;

import com.example.project.dto.CommentDTO;
import com.example.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/commentSave")
    public @ResponseBody CommentDTO commentSave(@ModelAttribute CommentDTO commentDTO) {
        commentService.commentSave(commentDTO);
        return null;
    }
}
