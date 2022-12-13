package com.example.project.repository;

import com.example.project.entity.MemberFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFileRepository extends JpaRepository<MemberFileEntity,Long> {
}
