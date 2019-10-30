package com.zk.controller;

import com.zk.domain.JobInfo;
import com.zk.domain.Msg;
import com.zk.domain.PageBean;
import com.zk.service.impl.JobInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.Page;

import java.util.List;

@Controller
@RequestMapping("/")
public class JobController {
    @Autowired
    private JobInfoServiceImpl jobInfoService;

    @GetMapping("/all")
    @ResponseBody
    public Object getAll(@RequestParam String page,@RequestParam String limit){
        JobInfo jobInfo=new JobInfo();
         Msg msg=new Msg();
        int p = Integer.parseInt(page);
        int l=Integer.parseInt(limit);
        PageBean pageBean = new PageBean();
        pageBean.setPage(p);
        pageBean.setLimit(l);
        List<JobInfo> all = jobInfoService.findAll(jobInfo);
        int total = jobInfoService.findTotal();
        List<JobInfo> list = jobInfoService.findList(jobInfo,pageBean);

//        model.addAttribute("list",list);
        msg.setData(list);

        msg.setCount(total);
        return msg;
    }


}
