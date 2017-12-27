/**
 * 
 */
package com.onttp.zkmanager;

import com.blade.Blade;

/**
 * 
 * @Description: TODO(添加描述)
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年11月14日-下午2:36:07
 *
 */
public class Application {

	public static void main(String[] args) {
		Blade blade = Blade.me();
		// static
		blade.addStatics("/assets");
		// Basic Auth
		/**
		 * http.auth.username=admin http.auth.password=123456
		 */
		// blade.use(new BasicAuthMiddleware());
		// Change Server Port
		/**
		 * server.port=9001 or --server.port=9001
		 */
		// blade.listen(9001);
		blade.get("/", (req, response) -> {
			response.redirect("/zk");
		});

		blade.start(Application.class, args);
	}
}
