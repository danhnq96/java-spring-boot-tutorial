package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT mem FROM Member mem  WHERE mem.firstName like %:search% OR mem.email like %:search% " +
            "OR mem.midName like %:search% OR mem.memberType.type like %:search% OR mem.lastName like %:search%")
    Page<Member> getListMembers(String search, Pageable pageable);

    @Query("SELECT mem FROM Member mem  WHERE mem.firstName like %:search% OR mem.email like %:search% " +
            "OR mem.midName like %:search% OR mem.memberType.type like %:search% OR mem.lastName like %:search%")
    List<Member> getListMembersExportToExcel(String search);
}
