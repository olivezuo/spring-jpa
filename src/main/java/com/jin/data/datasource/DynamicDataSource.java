package com.jin.data.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Primary
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	@Autowired
	MasterDBConfig masterDBConfig;
	
	@Autowired
	SlaveDBConfig slaveDBConfig;
		
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSource();

	}

	@PostConstruct
	public void init(){
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put("master", masterDBConfig.masterDataSource());
		targetDataSources.put("slave", slaveDBConfig.slaveDataSource());
		this.setTargetDataSources(targetDataSources);
		this.setDefaultTargetDataSource(masterDBConfig.masterDataSource());
	}
}
