/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.jeeshop.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller - 基类
 * 
 */
@Controller
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final String ORDER_COLUMN = "order[0][column]";
    private static final String ORDER_DIR = "order[0][dir]";

    protected void addMessage(ModelMap modelMap, String message) {
        modelMap.addAttribute("message", message);
    }
    protected void addWarning(ModelMap modelMap, String warning) {
        modelMap.addAttribute("warning", warning);
    }
    protected void addError(ModelMap modelMap, String warning) {
        modelMap.addAttribute("errorMsg", warning);
    }
    protected void addMessage(RedirectAttributes flushAttrs, String message) {
        flushAttrs.addFlashAttribute("message", message);
    }
    protected void addWarning(RedirectAttributes flushAttrs, String warning) {
        flushAttrs.addFlashAttribute("warning", warning);
    }
    protected void addError(RedirectAttributes flushAttrs, String warning) {
        flushAttrs.addFlashAttribute("errorMsg", warning);
    }

    JSONObject getRequestPage(HttpServletRequest request){
        JSONObject page = new JSONObject();

        return page;
    }

    protected String getOrderColumnData(HttpServletRequest request){
        int col = 0;
        String dir = "desc";
        if(request.getParameter(ORDER_COLUMN)!=null && StringUtils.isEmpty(request.getParameter(ORDER_COLUMN))){
            col = Integer.parseInt(request.getParameter(ORDER_COLUMN));
            dir = request.getParameter(ORDER_DIR);
        }

        String colname  = request.getParameter("columns["+col+"][data]");

        return colname+"\n"+dir;
    }

    protected String getOrderColumnName(HttpServletRequest request){
        int col = 0;
        String dir = "desc";
        if(StringUtils.isNotEmpty(request.getParameter(ORDER_COLUMN))){
            col = Integer.parseInt(request.getParameter(ORDER_COLUMN));
            dir = request.getParameter(ORDER_DIR);
        }
        String colname  = request.getParameter("columns["+col+"][name]");

        return colname+"\n"+dir;
    }

}