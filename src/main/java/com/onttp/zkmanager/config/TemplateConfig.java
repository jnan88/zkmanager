/**
 * 
 */
package com.onttp.zkmanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.Blade;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Order;
import com.onttp.zkmanager.util.ZkClient;
import com.onttp.zkmanager.util.ZkUtil;

/**
 * 
 * @Description: BeanProcessor项目启动的时候加载一些配置
 * @Author qizai
 * @Version: 0.0.1
 * @CreateAt 2017年11月14日-下午3:15:01
 *
 */
@Order(1)
@Bean
public class TemplateConfig implements BeanProcessor {
	private Logger logger = LoggerFactory.getLogger(TemplateConfig.class);

	@Override
	public void processor(Blade blade) {
		// Base.open("jdbc:mysql://127.0.0.1:3306/app", "root", "123456");
		ZkUtil.setZkClient(new ZkClient().create("127.0.0.1:2181"));
		// 项目启动的时候加载一些 配置
		// blade.templateEngine(templateEngine);

		blade.templateEngine(new JetbrickTemplateEngine());
		logger.info("Blade initialize TemplateEngine for " + blade.templateEngine().getClass().getSimpleName());

	}

}
