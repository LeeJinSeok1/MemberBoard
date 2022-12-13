package com.example.project.dto;

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
}
