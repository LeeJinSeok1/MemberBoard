package com.example.project.controller;

import com.example.project.dto.MemberDTO;
import com.example.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/memberSave")
    public String memberSavePage() {
        return "memberSave";
    }
    @PostMapping("/memberSave")
    public String memberSave(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.memberSave(memberDTO);
        return "index";
    }
    @PostMapping("/emailCk")
    public @ResponseBody String emailCk(@RequestParam("memberEmail") String memberEmail) {
       MemberDTO memberDTO = memberService.emailCk(memberEmail);
       if(memberDTO == null) {
           return "ok";
       }else{
           return "no";
       }
    }
}
