package com.zk.service.impl;

import com.zk.dao.JobInfoDao;
import com.zk.domain.JobInfo;
import com.zk.domain.PageBean;
import com.zk.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoDao jobInfoDao;


    @Transactional
    public void save(JobInfo jobInfo) {
        JobInfo param = new JobInfo();
        //根据时间和url来判断是否更新和保存信息
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        List<JobInfo> all = this.findAll(param);

        if(all.size()==0){
            jobInfoDao.updateJob(jobInfo);
        }


        jobInfoDao.save(jobInfo);
    }

    @Override
    public List<JobInfo> findAll(JobInfo jobInfo) {
        Example e= Example.of(jobInfo);
        List list = jobInfoDao.findAll(jobInfo);
        return list;

    }

    public List<JobInfo> findList(JobInfo jobInfo, PageBean pageBean){
        return jobInfoDao.findList(jobInfo,pageBean);
    }

    @Override
    public int findTotal() {
        return jobInfoDao.findTotal();
    }

//    @Override
//    public List<JobInfo> findAll(JobInfo jobInfo) {
//        return null;
//    }
}
