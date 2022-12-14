package com.example.project.service;

import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MemberFileEntity;
import com.example.project.repository.MemberFileRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public MemberDTO emailCk(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity= memberRepository.findByMemberEmail(memberEmail);
        if(optionalMemberEntity.isPresent()) {
            return  MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }

    }
@Transactional
    public MemberDTO memberLogin(MemberDTO memberDTO) {
        //        email로 DB에서 조회를 하고
//        사용자가 입력한 비밀번호와 DB에서 조회한 비밀번호가 일치하는지를 판단해서
//        로그인 성공, 실패 여부를 리턴
//        단, email 조회결과가 없을 때도 실패
        Optional<MemberEntity> optionalMemberEntity= memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        //이메일로 디비에 맞는게 있는지 확인하기 위해 레파지에 이메일 컬럼 추가하고 이메일과 맞는게 있는지 optionalMemberEntity로 가져온다
        if(optionalMemberEntity.isPresent()){
            // 만약 그 값이 있다면 아래 실행
            MemberEntity memberEntity = optionalMemberEntity.get();
            // 가져온값을 memberEntity에 담아 주고
            if(memberEntity.getMemberPass().equals(memberDTO.getMemberPass()) ){
                //그담은 값의 비밀번호와 입력한 비밀번호가 맞으면 아래 실행
                MemberDTO memberDTO1 = MemberDTO.toMemberDTO(memberEntity);
                // memberEntity의 정보를 memberDTO1 에 담아준다
                //리턴값이 MemberDTO 타입이기 때문에
                return memberDTO1;
                //마지막으로 그 값을 리턴
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
@Transactional
    public MemberDTO myPage(String memberEmail) {
      MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
      MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
      return memberDTO;
    }

    public void memberUpdate(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberRepository.save(memberEntity);


    }

    public List<MemberDTO> memberList() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();

        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setId(memberEntity.getId());
            memberDTO.setMemberName(memberEntity.getMemberName());
            memberDTO.setMemberEmail(memberEntity.getMemberEmail());
            memberDTO.setMemberPass(memberEntity.getMemberPass());
            memberDTO.setMemberMobile(memberEntity.getMemberMobile());
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public void memberDelete(Long id) {
        memberRepository.deleteById(id);
    }
}
