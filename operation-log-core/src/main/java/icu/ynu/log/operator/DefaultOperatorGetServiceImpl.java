package icu.ynu.log.operator;


import icu.ynu.log.bean.Operator;

public class DefaultOperatorGetServiceImpl implements IOperatorGetService {

	/**
	 * 默认返回null,根据具体的业务逻辑,自行实现IOperatorGetService
	 */
	@Override
	public Operator getOperator() {
		return null;
	}
}