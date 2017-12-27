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
 * @CreateAt 2017年12月26日-下午7:28:55
 *
 */
@Data
public class ZkNode {
	private int	id;
	private String	path;
	private boolean	hasChildren;
	private String	data;
}
