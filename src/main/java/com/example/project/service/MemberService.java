package com.example.project.service;

import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MemberFileEntity;
import com.example.project.repository.MemberFileRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFileRepository memberFileRepository;
    public void memberSave(MemberDTO memberDTO) throws IOException {

        if(memberDTO.getMemberFile().isEmpty()){
            MemberEntity memberEntity = MemberEntity.toMemberSave(memberDTO);
            memberRepository.save(memberEntity);
        }else{
            MultipartFile memberFile = memberDTO.getMemberFile();
            String originalFileName= memberFile.getOriginalFilename();
            String storedFileName =System.currentTimeMillis()+"_"+originalFileName;
            String savePath ="D:\\springboot_img\\" +storedFileName;
            memberFile.transferTo(new File(savePath));
            MemberEntity memberEntity = MemberEntity.toMemberFileSave(memberDTO);
            Long savedId =memberRepository.save(memberEntity).getId();

            MemberEntity entity = memberRepository.findById(savedId).get();
            MemberFileEntity memberFileEntity =
                    MemberFileEntity.toSaveMemberFileEntity(entity,originalFileName,storedFileName);

            memberFileRepository.save(memberFileEntity);
        }
    }
}
