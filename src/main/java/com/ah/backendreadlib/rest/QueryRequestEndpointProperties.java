package com.ah.backendreadlib.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

public class QueryRequestEndpointProperties {
	
	@Autowired
	Environment env;
	
	private String url;
	
	@PostConstruct
	public void init() {
	//querylib内のyamlからリクエスト先URLの取得
	this.url = env.getProperty("query.url");
	}

}
