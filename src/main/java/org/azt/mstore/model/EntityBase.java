package org.azt.mstore.model;

import java.util.Date;

public class EntityBase {

	protected Date createTime;
	
	protected int isBackend;
	
	protected int isValid;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public int getIsBackend() {
		return this.isBackend;
	}

	public void setIsBackend(int isBackend) {
		this.isBackend = isBackend;
	}
	
	public int getIsValid(){
		return this.isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	
}
