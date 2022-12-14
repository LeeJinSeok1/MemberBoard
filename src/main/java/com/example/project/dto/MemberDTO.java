package com.example.project.dto;

import com.example.project.entity.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberSaveTime(memberEntity.getMemberSaveTime());
        memberDTO.setFileAttached(memberDTO.getFileAttached());

        if(memberEntity.getFileAttached() ==1){
            memberDTO.setFileAttached(memberEntity.getFileAttached());

            memberDTO.setOriginalFileName(memberEntity.getMemberFileEntityList().get(0).getOriginalFileName());
            memberDTO.setStoredFileName(memberEntity.getMemberFileEntityList().get(0).getStoredFileName());
        }else{
            memberDTO.setFileAttached(memberEntity.getFileAttached());
        }




        return memberDTO;
    }
}
