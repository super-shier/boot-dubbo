package com.li.yun.biao.job.admin.dao;

import com.li.yun.biao.job.admin.core.model.JobLogGlue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 1:54 下午
 * @description job log for glue
 */
@Mapper
public interface JobLogGlueDao {

    int save(JobLogGlue jobLogGlue);

    List<JobLogGlue> findByJobId(@Param("jobId") int jobId);

    int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

    int deleteByJobId(@Param("jobId") int jobId);

}
