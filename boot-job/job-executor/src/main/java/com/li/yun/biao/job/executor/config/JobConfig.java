package com.li.yun.biao.job.executor.config;

import com.li.yun.biao.job.core.executor.impl.JobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 1:54 下午
 * @description job config
 */
@Configuration
public class JobConfig {
    private Logger logger = LoggerFactory.getLogger(JobConfig.class);

    @Value("${job.admin.addresses}")
    private String adminAddresses;

    @Value("${job.accessToken}")
    private String accessToken;

    @Value("${job.executor.appname}")
    private String appname;

    @Value("${job.executor.address}")
    private String address;

    @Value("${job.executor.ip}")
    private String ip;

    @Value("${job.executor.port}")
    private int port;

    @Value("${log.home}")
    private String logPath;

    @Value("${job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean
    public JobSpringExecutor jobExecutor() {
        logger.info(">>>>>>>>>>> job config init.");
        JobSpringExecutor jobSpringExecutor = new JobSpringExecutor();
        jobSpringExecutor.setAdminAddresses(adminAddresses);
        jobSpringExecutor.setAppname(appname);
        jobSpringExecutor.setAddress(address);
        jobSpringExecutor.setIp(ip);
        jobSpringExecutor.setPort(port);
        jobSpringExecutor.setAccessToken(accessToken);
        jobSpringExecutor.setLogPath(logPath);
        jobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return jobSpringExecutor;
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */


}