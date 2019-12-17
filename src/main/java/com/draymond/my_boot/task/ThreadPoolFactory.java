package com.draymond.my_boot.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/17 18:02
 */
@Configuration
public class ThreadPoolFactory {
    private Log logger = LogFactory.getLog(getClass());
    @Value("${thread-pool-factory.corePoolSize}")
    private int corePoolSize;
    @Value("${thread-pool-factory.maxPoolSize}")
    private int maxPoolSize;
    @Value("${thread-pool-factory.queueCapacity}")
    private int queueCapacity;
    @Value("${thread-pool-factory.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        logger.info("创建线程池");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //加入此头后此线程池成为系统线程池
        threadPoolTaskExecutor.setThreadNamePrefix("Anno-Executor");
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setRejectedExecutionHandler((r, executor) -> {
        });
        return threadPoolTaskExecutor;
    }

}