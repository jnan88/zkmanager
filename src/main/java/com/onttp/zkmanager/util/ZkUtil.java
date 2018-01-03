/**
 * 
 */
package com.onttp.zkmanager.util;

import java.util.List;

import org.apache.curator.shaded.com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Description: Zk缓存
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年12月27日-上午10:43:35
 *
 */
public class ZkUtil {
	@Getter
	@Setter
	private static ZkClient zkClient;

	public static void close() {
		if (null != zkClient) {
			zkClient.close();
		}
	}

	public static void create(String connectionInfo) {
		close();
		zkClient = new ZkClient();
		zkClient.create(connectionInfo);
	}

	public static String getConnectionInfo() {
		return null == zkClient ? "" : zkClient.getConnectionInfo();
	}

	public static List<ZkNode> getNodes(String path) {
		List<ZkNode> nodes = Lists.newArrayList();
		if (null == zkClient) {
			return nodes;
		}
		try {
			nodes = zkClient.getNodeList(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodes;
	}
}
