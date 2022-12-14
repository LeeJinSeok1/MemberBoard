package com.example.project.entity;

import com.example.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false,unique = true)
    private String memberEmail;
    @Column(length = 10,nullable = false)
    private String memberPass;
    @Column(length = 10,nullable = false)
    private String memberName;
    @Column(length = 30)
    private String memberMobile;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime memberSaveTime;
    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<MemberFileEntity> memberFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static MemberEntity toMemberSave(MemberDTO memberDTO) {
        MemberEntity memberEntity= new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberSaveTime(memberDTO.getMemberSaveTime());
        memberEntity.setFileAttached(0);
        return memberEntity;
    }

    public static MemberEntity toMemberFileSave(MemberDTO memberDTO) {
        MemberEntity memberEntity= new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberSaveTime(memberDTO.getMemberSaveTime());
        memberEntity.setFileAttached(1);
        return memberEntity;
    }
}
