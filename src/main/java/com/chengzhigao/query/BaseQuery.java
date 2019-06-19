package com.chengzhigao.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseQuery {
	private int page;
	private int count;
	
	public int getPage() {
		return (page-1) * count;
	}
}
