package com.csf.whoami.controller.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csf.whoami.backend.service.MenuService;
import com.csf.whoami.constant.UrlConstants;
import com.csf.whoami.controller.api.AbstractController;
import com.csf.whoami.converter.MenuDomain;

import io.swagger.annotations.Api;

@RestController
@Api
public class MenuController extends AbstractController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = UrlConstants.URI_ADMIN_MENU)
    public ModelAndView getAdminMenu(ModelAndView model, HttpServletRequest request) throws Exception {
        List<MenuDomain> listMenu = menuService.getMenus();

        model.addObject("list", listMenu);
        model.setViewName("layout/menu");
        return model;
    }
}
