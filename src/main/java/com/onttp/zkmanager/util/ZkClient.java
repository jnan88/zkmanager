/**
 * 
 */
package com.onttp.zkmanager.util;

import java.util.List;
import java.util.Map;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * @Description: Zookeeper Client 工具类
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年12月26日-下午5:37:09
 *
 */
public class ZkClient {
	private Logger				logger	= LoggerFactory.getLogger(ZkClient.class);

	private CuratorFramework	client;
	private String				connectionInfo;

	public String getConnectionInfo() {
		return connectionInfo;
	}

	public ZkClient create(String connectionInfo) {
		this.connectionInfo = connectionInfo;
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.newClient(connectionInfo, 5000, 3000, retryPolicy);
		start0(client);
		return this;
	}

	public static void start0(CuratorFramework client) {
		if (client.getState() != CuratorFrameworkState.STARTED) {
			client.start();
		}
	}

	public static void close0(CuratorFramework client) {
		if (client.getState() != CuratorFrameworkState.STOPPED) {
			client.close();
		}
	}

	public void close() {
		if (client.getState() != CuratorFrameworkState.STOPPED) {
			client.close();
		}
	}

	public String getData(String path) throws Exception {
		Stat stat = new Stat();
		byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
		String data = (null == bytes ? "" : new String(bytes));
		logger.debug("path={} , data={}", path, data);
		return data;
	}

	public List<String> getNode(String path) throws Exception {
		List<String> paths = client.getChildren().forPath(path);
		return paths;
	}

	public Map<String, String> getNodeData(String path) throws Exception {
		Map<String, String> datas = Maps.newLinkedHashMap();
		List<String> paths = client.getChildren().forPath(path);
		for (String path0 : paths) {
			String data = "";
			String cpath = path + "/" + path0;
			if ("/".equals(path)) {
				cpath = path + path0;
			}
			data = getData(cpath);
			datas.put(cpath, data);
		}
		return datas;
	}

	public List<ZkNode> getNodeList(String path) throws Exception {
		List<String> paths = client.getChildren().forPath(path);
		List<ZkNode> nodes = Lists.newLinkedList();
		for (String path0 : paths) {
			String data = "";
			String cpath = path + "/" + path0;
			if ("/".equals(path)) {
				cpath = path + path0;
			}
			data = getData(cpath);
			ZkNode node = new ZkNode();
			node.setData(data);
			node.setPath(cpath);
			node.setHasChildren("".equals(data));
			nodes.add(node);
		}
		return nodes;
	}

}
