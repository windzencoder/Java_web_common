package com.wz.shiroweb.entity;

public class RoleResource {

	private int id;
	private int roleId;
	private int resId;
	
	public RoleResource() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}

	@Override
	public String toString() {
		return "RoleResource [id=" + id + ", roleId=" + roleId + ", resId=" + resId + "]";
	}
	
	
	
	
}
