package com.jin.data.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TargetDataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(TargetDataSourceAspect.class);
			
	@Around("@annotation(targetDataSource)")
	public Object advice(ProceedingJoinPoint point, TargetDataSource targetDataSource) {
		try {
			DynamicDataSourceHolder.putDataSource(targetDataSource.value());
			Object result = point.proceed();
			return result;
		} catch (Throwable e) {
			logger.error(e.getMessage());
			return null;
		} finally {
			logger.info("Restore the datasource to default value");
			DynamicDataSourceHolder.clearDataSource();
		}
	}
}
