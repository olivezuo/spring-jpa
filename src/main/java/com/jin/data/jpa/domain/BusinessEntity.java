package com.jin.data.jpa.domain;

import java.io.Serializable;

public interface BusinessEntity<T> extends Serializable{
	public T getId(); 
}
