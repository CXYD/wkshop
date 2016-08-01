package net.jeeshop.biz.finance.paygate.alipay;

import net.jeeshop.biz.finance.paygate.alipay.model.CommonResponse;
import net.jeeshop.biz.finance.paygate.alipay.model.QueryResultModel;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class AlipayParser {
    private static final Logger logger = LoggerFactory.getLogger(AlipayParser.class);


    /**
     * 解析查询结果
     *
     * @param queryResult
     * @return
     * @throws PaygateException
     */
    public static QueryResultModel parseQueryResult(String queryResult) throws PaygateException {
        QueryResultModel model = new QueryResultModel();
        try {
            Document doc = DocumentHelper.parseText(queryResult);
            Element element = doc.getRootElement();
            // 查询结果数据
            model.is_success = element.elementTextTrim("is_success");
            model.error = element.elementTextTrim("error");
            model.sign = element.elementTextTrim("sign");
            model.sign_type = element.elementTextTrim("sign_type");
            Element requestNode = element.element("request");
            if (requestNode != null) {
                for (Element ele : (List<Element>) requestNode.elements()) {
                    model.addRequestData(ele.attributeValue("name"), ele.getTextTrim());
                }
            }
            Element responseNode = element.element("response");
            Element tradeNode;
            if (responseNode != null && (tradeNode = responseNode.element("trade")) != null) {
                for (Element ele : (List<Element>) tradeNode.elements()) {
                    model.addTradeData(ele.getName(), ele.getTextTrim());
                }
            }
        } catch (Exception e) {
            //
            logger.error("解析结果失败:{}", queryResult);
            logger.error("解析返回结果失败.", e);
            throw new PaygateException("数据解析出错", e);
        }
        return model;
    }


    public static CommonResponse parseCommonResponse(String responseText) {

        CommonResponse model = new CommonResponse();
        try {
            Document doc = DocumentHelper.parseText(responseText);
            Element rootElement = doc.getRootElement();
            model.setSuccess("T".equals(rootElement.elementTextTrim("is_success")));
            model.setErrMsg(rootElement.elementTextTrim("error"));
        } catch (Exception e) {
            logger.error("解析结果失败:{}", responseText);
            logger.error("解析返回结果失败.", e);
            throw new PaygateException("数据解析出错", e);
        }
        return model;
    }


    private static double parseDouble(String amount) {
        if (StringUtils.isBlank(amount)) {
            return 0;
        }
        return new BigDecimal(amount).doubleValue();
    }

}
