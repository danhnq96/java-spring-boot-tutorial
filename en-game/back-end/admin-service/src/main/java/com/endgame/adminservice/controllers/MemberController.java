package com.endgame.adminservice.controllers;

import com.endgame.adminservice.sevices.MemberService;
import com.endgame.adminservice.commons.SortData;
import com.endgame.adminservice.dto.member.ListMemberDTO;
import com.endgame.adminservice.dto.member.MemberExportExcelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin/members")
//@CrossOrigin(origins = "http://localhost:4201", allowedHeaders = "*")
public class MemberController {
    @Autowired
    MemberService memberService;

    //    API Get List Members
    @GetMapping(value = "")
    public ResponseEntity<Page<ListMemberDTO>> getListMembers(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                              @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                              @RequestParam(name = "search", required = false, defaultValue = "") String search,
                                                              @RequestParam(defaultValue = "id,asc") String[] sort) {
        Page<ListMemberDTO> lists = memberService.getListMembers(PageRequest.of(page, size, Sort.by(SortData.getOrderByParamSort(sort))), search);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    //    API Get List To Export Excel
    @GetMapping(value = "/export")
    public ResponseEntity<ArrayList<MemberExportExcelDTO>> getListEmployeesExportToExcel(
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getListMembersExportToExcel(search));
    }
}
