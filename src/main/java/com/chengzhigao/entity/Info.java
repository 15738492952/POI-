package com.chengzhigao.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Info {
	private Integer id;
	private String name;
	private int age;
	private int gid;
	private Date todate;
}
