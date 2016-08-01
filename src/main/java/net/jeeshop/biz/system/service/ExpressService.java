package net.jeeshop.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.client.ExpressMapper;
import net.jeeshop.biz.system.model.Express;
import net.jeeshop.biz.system.model.ExpressExample;

/**
 *	配送方式管理Service
 *
 * @author Leolione
 * @email leolione@outlook.com
 * @since V1.0
 */
@Service
public class ExpressService extends BaseService<Express, ExpressExample> {
	
	@Autowired
	private ExpressMapper expressMapper;
	
	@Override
	protected BaseMapper<Express, ExpressExample> getMapper() {
		return expressMapper;
	}
	
    /**
     * 根据code查找配送方式
     * @param expressCode
     * @return
     */
    public Express selectByCode(String expressCode) {
    	ExpressExample example = new ExpressExample();
    	ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andExpressCodeEqualTo(expressCode);
        return selectUniqueByExample(example);
    }
    
}
