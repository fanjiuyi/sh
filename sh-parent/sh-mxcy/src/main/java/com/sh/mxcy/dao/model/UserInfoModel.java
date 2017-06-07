package com.sh.mxcy.dao.model;

import java.io.Serializable;
import java.util.Date;


public class UserInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户信息模型
	 */
	private int id;
	private String username;
	private String loginname;
	private String loginpwd;
	private String type;
	private String status;
	private Date create_time;
	private String is_del;
	private String remarks;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getIs_del() {
		return is_del;
	}

	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "UserInfoModel{" +
				"id=" + id +
				", username='" + username + '\'' +
				", loginname='" + loginname + '\'' +
				", loginpwd='" + loginpwd + '\'' +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
				", create_time=" + create_time +
				", is_del='" + is_del + '\'' +
				", remarks='" + remarks + '\'' +
				'}';
	}
}
