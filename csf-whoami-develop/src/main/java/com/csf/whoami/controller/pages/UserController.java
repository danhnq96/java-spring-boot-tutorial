/*
package com.csf.whoami.controller.pages;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csf.whoami.constant.UrlConstants;
import com.csf.whoami.dto.SearchVO;

//@Log4j2
@RestController
@RequestMapping(value = UrlConstants.URI_ADMIN)
public class UserController {

//    @Autowired
//    @Qualifier("userService")
//    private UserService userService;
//
//    @Autowired
//    @Qualifier("adminLogService")
//    private AdminLogService adminLogService;

    @Value("${databasekey}")
    private String databasekey;

    @GetMapping(value = UrlConstants.URI_LIST)
    public ModelAndView userList(@ModelAttribute("search") SearchVO search, ModelAndView model, Pageable pageable,
            HttpServletRequest request) throws Exception {
        pageable = PageRequest.of(search.getPage() <= 0 ? 0 : pageable.getPageNumber(), pageable.getPageSize());
//        Page<TbUser> userList = userService.userList(search, pageable);
//        model.addObject("list",userList);
        model.addObject("pageNumber", 0);
//        model.addObject("totalCount", userList.getTotalElements());
        model.setViewName("pages/user-management/user-list");

//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");

//        adminLogService.logSave("사용자 관리","조회",admin.getId());

        return model;
    }

    @PostMapping(value = UrlConstants.URI_LIST)
    public ModelAndView userList(@ModelAttribute("search") SearchVO search, Pageable pageable, ModelAndView model)
            throws Exception {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

//        Page<TbUser> userList = userService.userList(search, pageable);

//        model.addObject("list",userList);
        model.addObject("pageNumber", search.getPage() <= 0 ? 0 : search.getPage() - 1);
//        model.addObject("totalCount", userList.getTotalElements());
        model.setViewName("pages/user-management/user-list");

        return model;
    }

    @GetMapping(value = UrlConstants.URI_DETAIL)
    public ModelAndView userDetail(@PathVariable("id") Long id, ModelAndView model, HttpServletRequest request)
            throws Exception {

//        TbUser tbUser = userService.findById(id, databasekey);
//        model.addObject("detail",tbUser);
        model.setViewName("pages/user-management/user-detail");

//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");

//        adminLogService.logSave("사용자 관리","상세{"+id+"}",admin.getId());

        return model;
    }
}
*/
