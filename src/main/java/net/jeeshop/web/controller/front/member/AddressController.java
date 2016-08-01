package net.jeeshop.web.controller.front.member;

import com.google.common.collect.Lists;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.model.MemberAddress;
import net.jeeshop.biz.member.service.MemberAddressService;
import net.jeeshop.biz.system.bean.AreaItem;
import net.jeeshop.biz.system.service.AreaService;
import net.jeeshop.web.controller.front.FrontBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dylan
 * @date 16/3/2 22:01
 * Email: dinguangx@163.com
 */
@Controller
@RequestMapping("member")
public class AddressController extends FrontBaseController {
    @Autowired
    MemberAddressService memberAddressService;

    @Autowired
    AreaService areaService;

    @RequestMapping({"address", "addressList"})
    public String address(ModelMap modelMap) {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return page_toLoginRedirect;
        }
        List<MemberAddress> addressList = memberAddressService.selectMemberAddress(currentMember.getId());
        modelMap.addAttribute("addressList", addressList);
        return "member/address";
    }

    @RequestMapping("addressAdd")
    public String addressAdd(@ModelAttribute("address")MemberAddress address, ModelMap modelMap) {
        beforeToEdit(address, modelMap);
        return "member/addressEdit";
    }

    private void beforeToEdit(MemberAddress address, ModelMap modelMap) {
        List<AreaItem> provinces = areaService.loadAreasByPid(0l, false);
        modelMap.addAttribute("provinces", provinces);

        List<AreaItem> cities = Lists.newArrayList();
        //市
        if (StringUtils.isNotBlank(address.getProvince())) {
            long provinceId = -1l;
            for (AreaItem item : provinces) {
                if (item.getAreaCode().equals(address.getProvince())) {
                    provinceId = item.getId();
                    break;
                }
            }
            cities = areaService.loadAreasByPid(Long.valueOf(provinceId), false);
        }
        modelMap.addAttribute("cities", cities);
        //区县
        if (StringUtils.isNotBlank(address.getCity())) {
            long cityId = -1l;
            for (AreaItem item : cities) {
                if (item.getAreaCode().equals(address.getCity())) {
                    cityId = item.getId();
                    break;
                }
            }
            modelMap.addAttribute("areas", areaService.loadAreasByPid(Long.valueOf(cityId), false));
        } else {
            modelMap.addAttribute("areas", new ArrayList<Area>());
        }
    }

    @RequestMapping("addressEdit")
    public String addressEdit(Long id, ModelMap modelMap) {
        MemberAddress address = memberAddressService.selectById(id);
        if(address == null) {
            return "redirect:address";
        }
        beforeToEdit(address, modelMap);
        modelMap.addAttribute("address", address);
        return "member/addressEdit";
    }

    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public String saveAddress(MemberAddress address, ModelMap modelMap) {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return page_toLoginRedirect;
        }
        address.setMemberId(currentMember.getId());
        if(address.getId() == null) {
            memberAddressService.insert(address);
        } else {
            memberAddressService.update(address);
        }
        return "redirect:addressList";
    }
}
