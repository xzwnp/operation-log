package icu.ynu.log.function;

/**
 * icu.ynu.log.function
 *
 * @author xzwnp
 * 2023/3/1
 * 19:44
 */
public interface IFunctionService {
	String apply(String functionName, String value);

	boolean beforeFunction(String functionName);
}
