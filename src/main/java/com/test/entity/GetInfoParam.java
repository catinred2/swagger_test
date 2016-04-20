package com.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetInfoParam  {
	@JsonProperty("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
