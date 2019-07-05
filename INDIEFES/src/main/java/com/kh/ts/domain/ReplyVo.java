package com.kh.ts.domain;

import java.sql.Timestamp;

public class ReplyVo {
	private int reply_number;
	private int board_number;
	private String user_id;
	private String content;
	private Timestamp reg_date;
	private Timestamp late_date;
	private int like_count;
	
	public int getReply_number() {
		return reply_number;
	}
	public void setReply_number(int reply_number) {
		this.reply_number = reply_number;
	}
	public int getBoard_number() {
		return board_number;
	}
	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public Timestamp getLate_date() {
		return late_date;
	}
	public void setLate_date(Timestamp late_date) {
		this.late_date = late_date;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	@Override
	public String toString() {
		return "ReplyVo [reply_number=" + reply_number + ", board_number=" + board_number + ", user_id=" + user_id
				+ ", content=" + content + ", reg_date=" + reg_date + ", late_date=" + late_date + ", like_count="
				+ like_count + "]";
	}
	
}
