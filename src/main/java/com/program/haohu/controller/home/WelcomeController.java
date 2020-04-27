package com.program.haohu.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月27日 14:25
 * @Copyright:
 */
@Controller
public class WelcomeController {
    @RequestMapping("/")
    public String welcome(){
        return "redirect:/index/index";
    }
}
