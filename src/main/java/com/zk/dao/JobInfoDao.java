package com.zk.dao;

import com.zk.domain.JobInfo;
import com.zk.domain.PageBean;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper
@Component
public interface JobInfoDao  {

    List<JobInfo> findList(@Param("job")JobInfo jobInfo, @Param("p")PageBean pageBean);

    @Insert("insert into job_info(company_name,company_addr,company_info,job_name,job_addr,job_info,salary_min,salary_max,url,time) values (#{companyName},#{companyAddr},#{companyInfo},#{jobName},#{jobAddr},#{jobInfo},#{salaryMin},#{salaryMax},#{url},#{time})")
    void save(JobInfo jobInfo);

    List<JobInfo> findAll(JobInfo jobInfo);

    @Select("select count(*) from job_info")
    int findTotal();


    @Update( "update job_info set company_addr=#{companyAddr}, company_info=#{companyInfo},job_name=#{jobName},job_addr=#{jobAddr},job_info=#{jobInfo},salary_min=#{salaryMin},salary_max=#{salaryMax} ,url=#{url},time=#{time} where company_name=#{companyName} ")
    void updateJob(JobInfo jobInfo);
}
