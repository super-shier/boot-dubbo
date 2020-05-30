package com.li.yun.biao.job.admin.dao;

import com.li.yun.biao.job.admin.core.model.JobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 1:54 下午
 * @description
 */
@Mapper
public interface JobGroupDao {

    List<JobGroup> findAll();

    List<JobGroup> findByAddressType(@Param("addressType") int addressType);

    int save(JobGroup jobGroup);

    int update(JobGroup jobGroup);

    int remove(@Param("id") int id);

    JobGroup load(@Param("id") int id);

    List<JobGroup> pageList(@Param("offset") int offset,
                            @Param("pagesize") int pagesize,
                            @Param("appname") String appname,
                            @Param("title") String title);

    int pageListCount(@Param("offset") int offset,
                      @Param("pagesize") int pagesize,
                      @Param("appname") String appname,
                      @Param("title") String title);

}
