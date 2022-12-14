package com.example.project.controller;

import com.example.project.dto.MemberDTO;
import com.example.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @GetMapping("/memberLogin")
    public String memberLoginPage() {
        return "memberLogin";
    }
    @PostMapping("/memberLogin")
    public String memberLogin(@ModelAttribute MemberDTO memberDTO,
                              Model model, HttpSession session){
       MemberDTO result = memberService.memberLogin(memberDTO);
        System.out.println(result);
       if(result !=null) {
           model.addAttribute("member", result);
           session.setAttribute("LoginEmail",result.getMemberEmail());

           return "redirect:boardPaging";
       }else{
           return "memberLogin";
       }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
       session.invalidate();
       return "index";
    }

    @GetMapping("/memberPage/{memberEmail}")
    public String myPage(@PathVariable String memberEmail,
                         Model model){
        System.out.println(memberEmail);
        MemberDTO result = memberService.myPage(memberEmail);
        model.addAttribute("member",result);
        return "myPage";
    }

    @PostMapping("/memberUpdate")
    public String memberUpdate(@ModelAttribute MemberDTO memberDTO,
                               Model model) {
        memberService.memberUpdate(memberDTO);

//        MemberDTO result = memberService.myPage(memberDTO.getMemberEmail());
//        model.addAttribute("member",result);
        return "index";
    }
}
