package com.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class GetInfoParam  {
	@ApiModelProperty(value="用户id",required = true)
	private Long id;
	@ApiModelProperty(value="用户name",required = true)
	private String name;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
