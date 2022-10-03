package com.pda.itoken.common.constants;

/**
 * 状态码常量
 */
public enum HttpStatusConstants {
	// 枚举类型
	BAD_GATEWAY(502,"从上游服务器接收到无效响应");

	// 状态
	private int status;

	// 消息内容
	private String content;

	/**
	 * 构造器
	 * @param status
	 * @param content
	 */
	private HttpStatusConstants(int status,String content){
		this.status = status;
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public String getContent() {
		return content;
	}
}
