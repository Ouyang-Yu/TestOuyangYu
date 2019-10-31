package com.example.recyclerview.chat;

/**
 * @author Ouyang Yu
 * @date 2019/10/28 16:54
 */
public class MsgItem {
	public static final int RECEIVE=0;
	public static final int SEND = 1;
	String content;
	private int type;

	public MsgItem(String content, int type) {
		this.content = content;
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
