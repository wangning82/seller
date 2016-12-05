package com.growtogether.fuc;

import com.alibaba.fastjson.JSON;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * *所有HttpServlet的基类，会把请求中的加密字符串取出来并进行解密 (当前还尚未解密)
 * Created by admin on 2016/11/24.
 */
public class BaseServlet extends HttpServlet{
    //请求的Json
    protected com.alibaba.fastjson.JSONObject requestJson;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String securityString = req.getParameter("s");
        System.out.println("获取的加密String为" + securityString);

        requestJson = JSON.parseObject(URLDecoder.decode(securityString, "UTF8"));

        super.service(req, resp);
    }
}
