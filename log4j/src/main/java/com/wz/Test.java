package com.wz;

import org.apache.log4j.Logger;

public class Test {

	//获取类的实例
	public static Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		logger.info("Info信息");
		logger.debug("debug信息");
		logger.error("error信息");
		logger.warn("warn信息");
		logger.fatal("fatal信息");
		
		logger.error("error信息-异常", new IllegalArgumentException("非法参数"));
	}

}
