/**
 * 
 */
package com.onttp.zkmanager.controller;

import java.util.List;
import java.util.Optional;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.onttp.zkmanager.util.LayuiPage;
import com.onttp.zkmanager.util.ZkNode;
import com.onttp.zkmanager.util.ZkUtil;

/**
 * 
 * @Description: TODO(添加描述)
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年11月14日-下午2:40:49
 *
 */
@Path
public class ZkController {

	@GetRoute("zk")
	public String zk(Request request, Response response) {
		Optional<String> pathReq = request.query("path");
		try {
			request.attribute("zkserver", ZkUtil.getZkClient().getConnectionInfo());
			request.attribute("nodeList", ZkUtil.getZkClient().getNodeList(pathReq.orElse("/")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "zk-manager.jetx";
	}

	@GetRoute("zk/server")
	public void zkUpdate(Request request, Response response) {
		Optional<String> pathReq = request.query("zkserver");
		String connectionInfo = pathReq.orElse("");
		if (!"".equals(connectionInfo)) {
			ZkUtil.getZkClient().close();
			ZkUtil.getZkClient().create(connectionInfo);
		}
		response.redirect("/zk");
	}

	@GetRoute("api/zk")
	@JSON
	public LayuiPage api(Request request, Response response) throws Exception {
		Optional<String> pathReq = request.query("path");
		List<ZkNode> paths = ZkUtil.getZkClient().getNodeList(pathReq.orElse("/"));
		return new LayuiPage(paths, paths.size());
	}

}
