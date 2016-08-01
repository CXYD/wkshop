package net.jeeshop.web.freemarker.view;

import freemarker.template.*;
import net.jeeshop.core.DataGetter;
import net.jeeshop.biz.global.GlobalDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dylan
 * @date 16/3/22 21:58
 * Email: dinguangx@163.com
 */
public class GlobalDataHashModel implements TemplateHashModelEx
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalDataHashModel.class);
    private final ObjectWrapper wrapper;

    public GlobalDataHashModel(
            ObjectWrapper wrapper)
    {
        this.wrapper = wrapper;
    }

    public TemplateModel get(String key) throws TemplateModelException
    {
        logger.debug("get global data, key:{}", key);
        DataGetter getter = GlobalDataManager.getData(key);
        return wrapper.wrap(getter.getData());
    }

    public boolean isEmpty()
    {
        logger.debug("check global data is empty");
        return GlobalDataManager.getAllData().isEmpty();
    }

    public int size() {
        logger.debug("check global data size");
       return GlobalDataManager.getAllData().size();
    }

    public TemplateCollectionModel keys() {
        logger.debug("get global data keys");
        return new SimpleCollection(GlobalDataManager.getAllData().keySet());
    }

    public TemplateCollectionModel values() {
        logger.debug("get global data values");
//        return new SimpleCollection(GlobalDataManager.getAllData().values(), wrapper);
        throw new IllegalArgumentException("not supported.");
    }

    public ObjectWrapper getObjectWrapper()
    {
        return wrapper;
    }
}

