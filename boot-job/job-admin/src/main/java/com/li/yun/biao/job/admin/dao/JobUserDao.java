package com.li.yun.biao.job.admin.dao;

import com.li.yun.biao.job.admin.core.model.JobUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 1:54 下午
 * @description
 */
@Mapper
public interface JobUserDao {

    List<JobUser> pageList(@Param("offset") int offset,
                           @Param("pagesize") int pagesize,
                           @Param("username") String username,
                           @Param("role") int role);

    int pageListCount(@Param("offset") int offset,
                      @Param("pagesize") int pagesize,
                      @Param("username") String username,
                      @Param("role") int role);

    JobUser loadByUserName(@Param("username") String username);

    int save(JobUser jobUser);

    int update(JobUser jobUser);

    int delete(@Param("id") int id);

}
