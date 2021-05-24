package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTypeRepository extends JpaRepository<MemberType,Integer> {
}
