package com.ah.backendreadlib.rest;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;

import com.ah.backendreadlib.bean.QueryRequestBean;


public class QueryRequestEntityFactory {
	
	/**
	 * Post メソッドのRequestEntityの作成メソッド
	 * 
	 * @param url  String リクエストエンドポイント
	 * @param body TableSyncRequestBean リクエストボディ
	 * @return RequestEntity
	 */
	public RequestEntity<QueryRequestBean> getPostEntity(String url, QueryRequestBean body) {
		HttpHeaders header = new HttpHeaders();
		RequestEntity<QueryRequestBean> restEntity = RequestEntity.post(URI.create(url)).headers(header).body(body);

		return restEntity;
	}

}
