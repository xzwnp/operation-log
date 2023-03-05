package icu.ynu.log.autoconfigure;

import icu.ynu.log.evaluation.CachedSpelExpressionParser;
import icu.ynu.log.operator.DefaultOperatorGetServiceImpl;
import icu.ynu.log.operator.IOperatorGetService;
import icu.ynu.log.persistence.FileLogPersistenceStrategy;
import icu.ynu.log.persistence.LogPersistenceStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@ComponentScan(basePackages = "icu.ynu.log") //不加这个扫描不到包下的Component
@EnableConfigurationProperties(LogRecordProperties.class)
public class LogRecordAutoConfiguration {


    @Bean
    ExpressionParser expressionParser() {
        return new CachedSpelExpressionParser();
    }

    @Bean
    @ConditionalOnMissingBean(IOperatorGetService.class)
    IOperatorGetService defaultOperatorGetService() {
        return new DefaultOperatorGetServiceImpl();
    }

    @Bean
    ParameterNameDiscoverer parameterNameDiscoverer() {
        return new DefaultParameterNameDiscoverer();
    }

    @Bean
    @ConditionalOnProperty(name = "log-record.persistence.file.enabled", havingValue = "true")
    LogPersistenceStrategy fileLogPersistenceStrategy() {
        return new FileLogPersistenceStrategy();
    }

    @Bean(name = "logExecutor")
    ThreadPoolExecutor logExecutor() {
        return new ThreadPoolExecutor(1, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
    }


}