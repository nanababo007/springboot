package com.kdhppo.smplcms.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCtl {

    //메인 대시보드 페이지.
    @GetMapping({"/", "/main/index.do"})
    public String index(Model model) {
        return "main/index";
    }

}