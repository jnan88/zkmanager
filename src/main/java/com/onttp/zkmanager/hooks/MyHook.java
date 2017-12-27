/**
 * 
 */
package com.onttp.zkmanager.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.hook.Signature;
import com.blade.mvc.hook.WebHook;
import com.blade.mvc.http.Request;

/**
 * 
 * @Description: WebHook拦截一个路由的前、后可以做一些自定义的动作的接口，用户可以使用该接口完成一些登录拦截、日志记录、权限验证等操作。
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年11月14日-下午3:17:35
 *
 */
@Bean
public class MyHook implements WebHook {
	private Logger log = LoggerFactory.getLogger(MyHook.class);

	@Override
	public boolean before(Signature signature) {
		Request request = signature.request();
		String uri = request.uri();
		log.info("{}\t{}", request.method(), uri);
		return true;
	}

}
