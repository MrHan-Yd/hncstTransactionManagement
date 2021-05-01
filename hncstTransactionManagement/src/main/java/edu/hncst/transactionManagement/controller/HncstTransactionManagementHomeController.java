package edu.hncst.transactionManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hncstXDD
 * @effect 默认打开页面
 */
@Controller
public class HncstTransactionManagementHomeController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/hncstTransactionManagement/adminIndex";
    }
}
