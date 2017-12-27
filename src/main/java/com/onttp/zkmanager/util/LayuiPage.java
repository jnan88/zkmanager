/**
 * 
 */
package com.onttp.zkmanager.util;

import lombok.Data;

/**
 * 
 * @Description: TODO(添加描述)
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年12月26日-下午7:34:27
 *
 */
@Data
public class LayuiPage {
	private int		code;
	private String	msg = "";
	private int		count;
	private Object	data;

	/**
	 * @param data
	 */
	public LayuiPage() {
		super();
	}

	public LayuiPage(Object data) {
		super();
		this.data = data;
	}
	public LayuiPage(Object data,int count) {
		super();
		this.data = data;
		this.count = count;
	}

}
