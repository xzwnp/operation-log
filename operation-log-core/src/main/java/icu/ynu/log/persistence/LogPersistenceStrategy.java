package icu.ynu.log.persistence;

import icu.ynu.log.bean.LogRecordDto;

/**
 * icu.ynu.log.persistence
 *
 * @author xzwnp
 * 2023/3/2
 * 21:26
 */
public interface LogPersistenceStrategy {
	boolean supports(LogRecordDto logRecordDto);
	void doPersistence(LogRecordDto logRecordDto);
}
