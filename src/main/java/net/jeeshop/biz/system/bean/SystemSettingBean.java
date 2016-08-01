package net.jeeshop.biz.system.bean;

import com.google.common.collect.Lists;
import net.jeeshop.biz.system.model.SystemSetting;
import net.jeeshop.core.ManageContainer;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author dinguangx@163.com
 * @date 2015-12-24 22:24
 */
public class SystemSettingBean extends SystemSetting {

    private List<String> imagesList = Lists.newArrayList();// 图集列表，页面显示

    public List<String> getImagesList() {
        if (StringUtils.isNotBlank(this.getImages())) {
            String[] images = this.getImages().split(ManageContainer.product_images_spider);

            for (String image : images) {
                if (StringUtils.isNotBlank(image)) {
                    imagesList.add(image);
                }
            }
        }
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        //TODO
    }

}
