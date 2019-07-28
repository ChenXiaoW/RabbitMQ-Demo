package com.chenw.directprovider.model;

import java.io.Serializable;

/**
 * @author: ChenWei
 * @description: 消息实体
 * @create: 2019/7/24 - 21:58
 **/

public class LogMsg implements Serializable  {


	private static final long serialVersionUID = 3349138030214066909L;
	private Integer id;

	private String content;
	/** 消息唯一id */
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LogMsg() {
	}

	public LogMsg(Integer id, String content, String message) {
		this.id = id;
		this.content = content;
		this.message = message;
	}
}
