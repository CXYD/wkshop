package net.jeeshop.biz.shop.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class CustomPage extends BaseModel implements Serializable {
    /** custom_page.pageid */
    private Integer pageid;

    /** custom_page.update_teim */
    private Date updateTeim;

    /** custom_page.title */
    private String title;

    /** custom_page.dsp */
    private String dsp;

    /** custom_page.img */
    private String img;

    /** custom_page.isIndex */
    private String isindex;

    /** custom_page.khid */
    private String khid;

    /** custom_page.pageContent */
    private String pagecontent;

    private static final long serialVersionUID = 1L;

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Date getUpdateTeim() {
        return updateTeim;
    }

    public void setUpdateTeim(Date updateTeim) {
        this.updateTeim = updateTeim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp == null ? null : dsp.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getIsindex() {
        return isindex;
    }

    public void setIsindex(String isindex) {
        this.isindex = isindex == null ? null : isindex.trim();
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid == null ? null : khid.trim();
    }

    public String getPagecontent() {
        return pagecontent;
    }

    public void setPagecontent(String pagecontent) {
        this.pagecontent = pagecontent == null ? null : pagecontent.trim();
    }

    @Override
    public String toString() {
        return "CustomPage{" +
                "pageid=" + pageid +
                ", updateTeim=" + updateTeim +
                ", title='" + title + '\'' +
                ", dsp='" + dsp + '\'' +
                ", img='" + img + '\'' +
                ", isindex='" + isindex + '\'' +
                ", khid='" + khid + '\'' +
                ", pagecontent='" + pagecontent + '\'' +
                '}';
    }
}