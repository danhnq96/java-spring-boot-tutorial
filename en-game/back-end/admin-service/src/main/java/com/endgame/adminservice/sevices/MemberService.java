package com.endgame.adminservice.sevices;

import com.endgame.adminservice.dto.member.ListMemberDTO;
import com.endgame.adminservice.dto.member.MemberExportExcelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface MemberService {

    Page<ListMemberDTO> getListMembers(Pageable pageable, String search);

    ArrayList<MemberExportExcelDTO> getListMembersExportToExcel(String search);
}
