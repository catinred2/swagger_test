package com.test.entity;

import io.swagger.annotations.ApiModelProperty;

public class GetInfoResult  extends BaseResult{
	private String text;
	@ApiModelProperty(value="text here",required = true)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
