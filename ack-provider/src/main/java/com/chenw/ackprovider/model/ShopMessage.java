package com.chenw.ackprovider.model;

import java.io.Serializable;

/**
 * @author chenw
 * @title: ShopMessage
 * @description: 商品信息实体类
 * @date 2019/7/25 16:17
 */
public class ShopMessage implements Serializable {

	private static final long serialVersionUID = -479607351927125502L;
	private Integer id;

	private String name;

	private String messageId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
