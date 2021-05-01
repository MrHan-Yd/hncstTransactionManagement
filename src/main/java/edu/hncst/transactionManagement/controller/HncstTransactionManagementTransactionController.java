package edu.hncst.transactionManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author hncstXDD
 * @effect 响应教师操作
 */
@Controller
@RequestMapping("/hncstTransactionManagementTransaction")
public class HncstTransactionManagementTransactionController {

    @RequestMapping("/transactionIndex")
    public String transactionIndex(Model model, HttpServletRequest request){
        HttpSession session =  request.getSession();

        model.addAttribute("userName", session.getAttribute("transactionName"));
        model.addAttribute("post", session.getAttribute("transactionPost"));
        return "transactionIndex";
    }

}
