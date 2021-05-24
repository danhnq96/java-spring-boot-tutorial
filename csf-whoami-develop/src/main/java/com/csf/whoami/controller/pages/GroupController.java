package com.csf.whoami.controller.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csf.whoami.backend.service.GroupService;
import com.csf.whoami.common.domain.GroupDomain;
import com.csf.whoami.common.utilities.AuthenticationUtils;
import com.csf.whoami.constant.UrlConstants;
import com.csf.whoami.converter.AdminDomain;
import com.csf.whoami.dto.SearchVO;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(value = UrlConstants.URI_GROUP_HOME)
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * Move to Group home page.
     *
     * @param model
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping
    public ModelAndView groupList(ModelAndView model, Pageable pageable) throws Exception {
        initialGroupPage(model);
        return model;
    }

    /**
     * Move to Register page and setting list data.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping(value = UrlConstants.URI_REGISTER)
    public ModelAndView groupRegister(ModelAndView model) throws Exception {

        AdminDomain user = AuthenticationUtils.getUser();
        List<String> groupUrs = new ArrayList<>();
        if (user != null) {
            groupUrs.add(user.getName());
            groupUrs.add(user.getEmail());
        }
        model.addObject("groupUrs", groupUrs);
        model.addObject("detail", new GroupDomain());
        model.setViewName(UrlConstants.URL_GROUP_PAGE_REGISTER);
        return model;
    }

    /**
     * Regist group.
     *
     * @param groupDetail
     * @return
     * @throws Exception
     */
    @PostMapping(value = UrlConstants.URI_REGISTER)
    public Long groupInsert(@RequestBody GroupDomain groupDetail) throws Exception {
        return groupService.registerGroup(groupDetail);
    }

    /**
     * Search groups by conditions.
     *
     * @param search
     * @param model
     * @param pageable
     * @return
     * @throws Exception
     */
    @PostMapping(value = UrlConstants.URI_LIST)
    public ModelAndView groupSearch(@ModelAttribute("search") SearchVO search,
                                    ModelAndView model,
                                    Pageable pageable) throws Exception {

        pageable = PageRequest.of(search.getPage() <= 0 ? 0 : pageable.getPageNumber(), pageable.getPageSize());

        initialRadio(model);
        Page<GroupDomain> groupList = groupService.groupList(search, pageable);
        model.addObject("list", groupList);
        model.addObject("pageNumber", 0);
        model.addObject("totalCount", groupList.getTotalElements());
        model.setViewName(UrlConstants.URL_GROUP_PAGE_LIST);
//		adminLogService.logSave("관리자 관리", "조회", admin.getId());
        return model;
    }

    @GetMapping(value = UrlConstants.URI_LIST)
    public ModelAndView backToGroupList(ModelAndView model) throws Exception {
        initialGroupPage(model);
        return model;
    }

    /**
     * Show group detail information.
     *
     * @param id
     * @param model
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping(value = UrlConstants.URI_DETAIL)
    public ModelAndView groupDetail(@PathVariable String id, ModelAndView model, Pageable pageable) throws Exception {

        Long groupId = Long.parseLong(id);
        GroupDomain adminDetail = groupService.groupDetail(groupId);
        model.addObject("detail", adminDetail);
        model.setViewName(UrlConstants.URL_GROUP_PAGE_REGISTER);
//		adminLogService.logSave("관리자 관리", "상세{" + id + "}", admin.getId());
        return model;
    }

    private void initialRadio(ModelAndView model) {
        List<String> ownerRadio = new ArrayList<>();
        ownerRadio.add("owner");
        ownerRadio.add("invite");
        model.addObject("onRd", ownerRadio);
    }

    private void initialGroupPage(ModelAndView model) {
        SearchVO search = new SearchVO();
        model.addObject("search", search);
        initialRadio(model);
        model.setViewName(UrlConstants.URL_GROUP_PAGE_LIST);
    }
}
