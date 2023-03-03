package icu.ynu.log.persistence;

import icu.ynu.log.bean.LogRecordDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * icu.ynu.log.persistence
 * 基于日志文件的持久化策略
 *
 * @author xzwnp
 * 2023/3/2
 * 21:27
 */
public class FileLogPersistenceStrategy implements LogPersistenceStrategy {
	private final Logger log = LoggerFactory.getLogger("operationLog");


	@Override
	public boolean supports(LogRecordDto logRecordDto) {
		return true;
	}

	@Override
	public void doPersistence(LogRecordDto logRecordDto) {
		MDC.put("userId", logRecordDto.getOperatorId());
		//输出到文件,而不是控制台
		log.info(logRecordDto.toString());
	}
}
