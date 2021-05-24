package com.endgame.adminservice.sevices.impls;

import com.endgame.adminservice.models.Member;
import com.endgame.adminservice.sevices.MemberService;
import com.endgame.adminservice.dto.member.ListMemberDTO;
import com.endgame.adminservice.dto.member.MemberExportExcelDTO;
import com.endgame.adminservice.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Page<ListMemberDTO> getListMembers(Pageable pageable, String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<Member> listMembers = memberRepository.getListMembers(search, pageable);
        return listMembers.map(member -> modelMapper.map(member, ListMemberDTO.class));
    }

    @Override
    public ArrayList<MemberExportExcelDTO> getListMembersExportToExcel(String search) {
        ModelMapper modelMapper = new ModelMapper();
        List<Member> listMembers = memberRepository.getListMembersExportToExcel(search);
        List<MemberExportExcelDTO> listMembersDTO = new ArrayList<>();
        listMembers.forEach(member ->{
            MemberExportExcelDTO memberDTO = modelMapper.map(member, MemberExportExcelDTO.class);
            listMembersDTO.add(memberDTO);
        });
        return (ArrayList<MemberExportExcelDTO>) listMembersDTO;
    }
}
