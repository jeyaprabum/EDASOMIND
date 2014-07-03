package com.maximilian_boehm.schemavalidator.access.struct;

public interface SVSchema {
	
	public void addCondition(SVSchemaCondition condition);

	
	public void setName(String sName);
	public String getName();

}
