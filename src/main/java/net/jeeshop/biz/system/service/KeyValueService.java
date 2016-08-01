package net.jeeshop.biz.system.service;

import java.util.List;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.client.KeyValueMapper;
import net.jeeshop.biz.system.model.KeyValue;
import net.jeeshop.biz.system.model.KeyValueExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ysqin Email: 442800318@qq.com
 */
@Service
public class KeyValueService extends BaseService<KeyValue, KeyValueExample> {

	@Autowired
	private KeyValueMapper keyValueMapper;

	@Override
	protected BaseMapper<KeyValue, KeyValueExample> getMapper() {
		return keyValueMapper;
	}

	public PageBean<KeyValue> selectPageBean(final KeyValueExample params,
			PageQueryBean pageQueryBean) {
		return executePageQuery(new PageQueryExecutor<KeyValue>() {
			@Override
			public List<KeyValue> executeQuery() {
				return keyValueMapper.selectByExample(params);
			}
		}, pageQueryBean);
	}

}
