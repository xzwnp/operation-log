package icu.ynu.log;

import icu.ynu.log.evaluation.CachedSpelExpressionParser;
import icu.ynu.log.operator.DefaultOperatorGetServiceImpl;
import icu.ynu.log.operator.IOperatorGetService;
import icu.ynu.log.persistence.FileLogPersistenceStrategy;
import icu.ynu.log.persistence.LogPersistenceStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;

@Configuration
@Slf4j
@ComponentScan(basePackages = "icu.ynu.log") //不加这个扫描不到包下的Component
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
	LogPersistenceStrategy fileLogPersistenceStrategy() {
		return new FileLogPersistenceStrategy();
	}

}