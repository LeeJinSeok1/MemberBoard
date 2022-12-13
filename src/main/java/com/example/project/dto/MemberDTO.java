package com.example.project.dto;

import com.example.project.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPass;
    private String memberMobile;
    private String memberName;
    private LocalDateTime memberSaveTime;

    private MultipartFile memberFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberDTO.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPass(memberEntity.getMemberPass());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberSaveTime(memberEntity.getMemberSaveTime());
        return memberDTO;
    }
}
