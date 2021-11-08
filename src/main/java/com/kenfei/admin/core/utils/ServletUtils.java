package com.kenfei.admin.core.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet 工具类
 * @author fei
 * @since 2020/10/24 3:03 下午
 */
public class ServletUtils {
  /** 定义移动端请求的所有可能类型 */
  private static final String[] agent = {
    "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"
  };

  /** 获取String参数 */
  public static String getParameter(String name) {
    return getRequest().getParameter(name);
  }

  /** 获取String参数 */
  public static String getParameter(String name, String defaultValue) {
    return TypeConvertUtils.toStr(getRequest().getParameter(name), defaultValue);
  }

  /** 获取Integer参数 */
  public static Integer getParameterToInt(String name) {
    return TypeConvertUtils.toInt(getRequest().getParameter(name));
  }

  /** 获取Integer参数 */
  public static Integer getParameterToInt(String name, Integer defaultValue) {
    return TypeConvertUtils.toInt(getRequest().getParameter(name), defaultValue);
  }

  /** 获取request */
  public static HttpServletRequest getRequest() {
    return getRequestAttributes().getRequest();
  }

  /** 获取response */
  public static HttpServletResponse getResponse() {
    return getRequestAttributes().getResponse();
  }

  /** 获取session */
  public static HttpSession getSession() {
    return getRequest().getSession();
  }

  public static ServletRequestAttributes getRequestAttributes() {
    RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
    return (ServletRequestAttributes) attributes;
  }

  /**
   * 将字符串渲染到客户端
   *
   * @param response 渲染对象
   * @param string 待渲染的字符串
   * @return null
   */
  public static String renderString(HttpServletResponse response, String string) {
    try {
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      response.getWriter().print(string);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 是否是Ajax异步请求
   *
   * @param request
   */
  public static boolean isAjaxRequest(HttpServletRequest request) {
    String accept = request.getHeader("accept");
    if (accept != null && accept.indexOf("application/json") != -1) {
      return true;
    }

    String xRequestedWith = request.getHeader("X-Requested-With");
    if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
      return true;
    }

    String uri = request.getRequestURI();
    if (uri.contains(".json") || uri.contains(".xml")) {
      return true;
    }

    String ajax = request.getParameter("__ajax");
    if (ajax.contains(".json") || ajax.contains(".xml")) {
      return true;
    }

    return false;
  }
}
