package com.csf.whoami.controller.pages;

import javax.servlet.http.HttpServletRequest;

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

import com.csf.whoami.constant.UrlConstants;
import com.csf.whoami.controller.api.AbstractController;
import com.csf.whoami.dto.AdminInfo;
import com.csf.whoami.dto.SearchVO;

//@Log4j2
@RestController
@RequestMapping(value = UrlConstants.URI_ADMIN)
public class AdminController extends AbstractController {

//    @Autowired
//    @Qualifier("adminService")
//    private AdminService adminService;
//
//    @Autowired
//    @Qualifier("adminLogService")
//    private AdminLogService adminLogService;
//
//    @Autowired
//    @Qualifier("adminRepository")
//    private AdminRepository adminRepository;

    @GetMapping(value = UrlConstants.URI_LIST)
    public ModelAndView adminList(@ModelAttribute("search") SearchVO search, ModelAndView model, Pageable pageable, HttpServletRequest request) throws Exception {
//        pageable = PageRequest.of(
//                search.getPage() <= 0 ? 0 : pageable.getPageNumber(), pageable.getPageSize()
//        );
//        Page<AdminInfo> adminList = adminService.adminList(search,pageable);
//        model.addObject("list",adminList);
//        model.addObject("pageNumber", 0);
//        model.addObject("totalCount", adminList.getTotalElements());
//        model.setViewName("pages/admin-management/admin-list");
//
//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");
//
//        adminLogService.logSave("관리자 관리","조회",admin.getId());
        return model;
    }

    @PostMapping(value = UrlConstants.URI_LIST)
    public ModelAndView adminList(@ModelAttribute("search") SearchVO search, Pageable pageable, ModelAndView model) throws Exception {

        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize()
        );

//        Page<AdminInfo> adminList = adminService.adminList(search,pageable);

//        model.addObject("list",adminList);
        model.addObject("pageNumber", search.getPage() <= 0 ? 0 : search.getPage() - 1);
//        model.addObject("totalCount", adminList.getTotalElements());
        model.setViewName("pages/admin-management/admin-list");

        return model;
    }

    @GetMapping(value = UrlConstants.URI_REGISTER)
    public ModelAndView adminInsert(ModelAndView model, HttpServletRequest request) throws Exception {
        model.setViewName("pages/admin-management/admin-create");

//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");

//        adminLogService.logSave("관리자 관리","등록",admin.getId());

        return model;
    }

    @PostMapping(value = UrlConstants.URI_REGISTER)
    public int adminInsert(@RequestBody AdminInfo info, HttpServletRequest request) throws Exception {
        /*
         result
         0 : 정상
         1 : 비밀번호 다름
         2 : 중복된 ID
         */
//        int result = adminService.adminInsert(info);

//        if(result == 0){
//
//            HttpSession session = request.getSession();
//            TbAdmin admin = (TbAdmin) session.getAttribute("admin");
//
//            adminLogService.logSave("관리자 관리","등록{"+info.getName()+"}",admin.getId());
//        }

        return 0;
    }

    @GetMapping(value = UrlConstants.URI_DETAIL)
    public ModelAndView adminDetail(@PathVariable Long id, ModelAndView model, HttpServletRequest request) throws Exception {

//        AdminInfo adminDetail = adminService.adminDetail(id);

//        model.addObject("detail",adminDetail);
        model.setViewName("pages/admin-management/admin-detail");

//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");

//        adminLogService.logSave("관리자 관리","상세{"+id+"}",admin.getId());

        return model;
    }

    @PostMapping(value = UrlConstants.URI_PASSWORD_RESET)
    public void adminResetPassword(@PathVariable Long id, HttpServletRequest request) throws Exception {

//        adminService.resetPassword(id);

//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");

//        adminLogService.logSave("관리자 관리","비밀번호 초기화{"+id+"}",admin.getId());
    }

    @PostMapping(value = UrlConstants.URI_UPDATE)
    public void adminUpdate(@RequestBody AdminInfo info, HttpServletRequest request) throws Exception {

//        TbAdmin tbAdmin = adminService.adminFindById(info.getId());
//        tbAdmin.setEmail(info.getEmail());
//        tbAdmin.setUsername(info.getUsername());

//        HttpSession session = request.getSession();
//        TbAdmin admin = (TbAdmin) session.getAttribute("admin");

//        adminLogService.logSave("관리자 관리","수정{"+info.getId()+"}",admin.getId());
//
//        adminService.adminUpdate(tbAdmin,info);
    }

    @GetMapping(value = UrlConstants.URI_ADMIN_START)
    public ModelAndView getAdminStart(ModelAndView model, HttpServletRequest request) throws Exception {
        model.setViewName("layout/start");
        return model;
    }
}
