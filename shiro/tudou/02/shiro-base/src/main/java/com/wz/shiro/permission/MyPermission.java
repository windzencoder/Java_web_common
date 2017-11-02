package com.wz.shiro.permission;

import org.apache.shiro.authz.Permission;

public class MyPermission implements Permission{

	private String resouceId;//资源
	private String operator;//操作
	private String instance;//实例
	
	
	
	public String getResouceId() {
		return resouceId;
	}

	public void setResouceId(String resouceId) {
		this.resouceId = resouceId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public  MyPermission() {
	}
	
	public  MyPermission(String permissionStr) {
		String[] strs = permissionStr.split("\\+");
		if (strs.length > 1) {
			this.setResouceId(strs[1]);
		}
		if (this.getResouceId() == null || "".equals(this.getResouceId())) {
			this.setResouceId("*");
		}
		if (strs.length > 2) {
			this.setOperator(strs[2]);
		}
		if (strs.length > 3) {
			this.setInstance(strs[3]);
		}
		if (this.getInstance() == null || "".equals(this.getInstance())) {
			this.setInstance("*");
		}
		System.out.println(this);
	}
	
	@Override
	public boolean implies(Permission p) {
		if(!(p instanceof MyPermission)) {  
            return false;  
        }  
		MyPermission other = (MyPermission) p;  
        if(!("*".equals(this.resouceId) || this.resouceId.equals(other.resouceId))) {  
            return false;  
        }  
        if(!("*".equals(this.operator) || this.operator.equals(other.operator))) {  
            return false;  
        }  
        if(!("*".equals(this.instance) || this.instance.equals(other.instance))) {  
            return false;  
        }  
        return true; 
	}

	@Override
	public String toString() {
		return "MyPermission [resouceId=" + resouceId + ", operator=" + operator + ", instance=" + instance + "]";
	}
	

}
