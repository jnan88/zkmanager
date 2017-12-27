/**
 * 
 */
package com.onttp.zkmanager.hooks;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.WebContext;
import com.blade.mvc.handler.DefaultExceptionHandler;
import com.blade.mvc.ui.RestResponse;
import com.onttp.zkmanager.exceptions.AppEx;

/**
 * 
 * @Description: TODO(添加描述)
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年11月14日-下午3:03:17
 *
 */
@Bean
public class GolbalExceptionHandler extends DefaultExceptionHandler {

	@Override
	public void handle(Exception e) {
		if (e instanceof AppEx) {
			AppEx validateException = (AppEx) e;
			String msg = validateException.getMessage();
			WebContext.response().json(RestResponse.fail(msg));
		} else {
			super.handle(e);
		}
	}

}
