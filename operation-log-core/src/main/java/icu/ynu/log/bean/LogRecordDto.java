package icu.ynu.log.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * icu.ynu.log.bean
 * 封装操作信息,便于持久化后以报表形式查看
 * @author xzwnp
 * 2023/3/1
 * 21:00
 */
@Data
@Accessors(chain = true)
public class LogRecordDto {
	private static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 业务ID
	 */
	private String bizId;

	/**
	 * 业务类型
	 * 可选
	 */
	private String bizType;

	/**
	 * 日志内容
	 * 可选
	 * 解析SpEL表达式后生成
	 */
	String content;


	/**
	 * 操作人ID
	 * 如果为空,会采取默认的方式尝试获取操作人
	 */
	private String operatorId;

	/**
	 * 操作人姓名/昵称
	 */
	private String operatorName;

	/**
	 * 所有参数
	 */
	private Map<String, Object> parameterMap;
	/**
	 * 返回值
	 */
	private Object returnValue;
	/**
	 * 耗时（单位：毫秒）
	 */
	private Long timeCost;

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 异常信息
	 */
	private Throwable exception;

	@Override
	public String toString() {
		String errMessage = exception == null?null:exception.getMessage();
		return "{" +
			"业务id:'" + bizId + '\'' +
			", 业务类型:'" + bizType + '\'' +
			", 操作描述:'" + content + '\'' +
			", 操作员编号:'" + operatorId + '\'' +
			", 操作员姓名:'" + operatorName + '\'' +
			", 操作结果状态=" + (success?"成功":"失败") +
			", 耗时=" + timeCost +
			"ms, success=" + success +
			", 错误信息=" + errMessage +
			'}';
	}
}
