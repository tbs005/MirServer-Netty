package com.zhaoxiaodan.mirserver.gameserver.entities;

import javax.persistence.*;

@Entity
public class ServerInfo{
	@Id
	@GeneratedValue
	public int id;
	@OrderColumn
	@Column(unique = true)
	public String name;
	public String ip;
	public int port;
}