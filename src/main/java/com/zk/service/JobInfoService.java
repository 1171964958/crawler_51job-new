package com.zk.service;


import com.zk.domain.JobInfo;
import com.zk.domain.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobInfoService {
    void  save(JobInfo jobInfo);

    List<JobInfo> findAll(JobInfo jobInfo);
    List<JobInfo> findList(JobInfo jobInfo, PageBean pageBean);
    int findTotal();
}
