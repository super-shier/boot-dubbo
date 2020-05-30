package com.li.yun.biao.job.admin.dao;

import com.li.yun.biao.job.admin.core.model.JobLogReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 1:54 下午
 * @description job log
 */
@Mapper
public interface JobLogReportDao {

    int save(JobLogReport jobLogReport);

    int update(JobLogReport jobLogReport);

    List<JobLogReport> queryLogReport(@Param("triggerDayFrom") Date triggerDayFrom,
                                      @Param("triggerDayTo") Date triggerDayTo);

    JobLogReport queryLogReportTotal();

}
