package com.pda.itoken.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie 工具类
 */
public final class CookieUtils {
	/**
	 * 得到Cookie的值,不编码
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String cookieName){
		return getCookieValue(request,cookieName,false);
	}

	/**
	 * 得到Cookie的值
	 * @param request
	 * @param cookieName
	 * @param isDecoder
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String cookieName,boolean isDecoder){
		Cookie[] cookieList = request.getCookies();
		if (cookieList == null || cookieName == null){
			return null;
		}
		String retValue = null;
		try {
			for (int i = 0;i<cookieList.length;i++){
				if (cookieList[i].getName().equals(cookieName)){
					if (isDecoder){
						retValue = URLDecoder.decode(cookieList[i].getValue(),"UTF-8");
					}else {
						retValue = cookieList[i].getValue();
					}
					break;
				}
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 得到Cookie的值
	 * @param request
	 * @param cookieName
	 * @param encodeString
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String cookieName,String encodeString){
		Cookie[] cookieList = request.getCookies();
		if (cookieList == null || cookieName == null){
			return null;
		}
		String retValue = null;
		try {
			for (int i = 0;i<cookieList.length;i++){
				if (cookieList[i].getName().equals(cookieName)){
					retValue = URLDecoder.decode(cookieList[i].getValue(),encodeString);
					break;
				}
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 设置Cookie的值,不设置生效时间,浏览器关闭失效,不编码
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue){
		setCookie(request,response,cookieName,cookieValue,-1);
	}

	/**
	 * 设置Cookie的值,指定时间内生效,不编码
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieMaxage
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage){
		setCookie(request,response,cookieName,cookieValue,cookieMaxage,false);
	}

	/**
	 * 设置Cookie的值,不设置生效时间,编码
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param isEncode
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,boolean isEncode){
		setCookie(request,response,cookieName,cookieValue,-1,isEncode);
	}

	/**
	 * 设置Cookie的值,指定时间内生效,编码
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieMaxage
	 * @param isEncode
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){
		doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,isEncode);
	}

	/**
	 * 设置Cookie的值,指定时间内生效,指定编码
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieMaxage
	 * @param encodeString
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
		doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,encodeString);
	}

	/**
	 * 带Cookie域名删除Cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void deleteCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
		doSetCookie(request,response,cookieName,"",-1,false);
	}

	/**
	 * 设置Cookie的值,并使其在指定时间内生效
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieMaxage
	 * @param isEncode
	 */
	private static final void doSetCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){
		try {
			if (cookieValue == null){
				cookieValue = "";
			}else if (isEncode){
				cookieValue = URLEncoder.encode(cookieValue,"utf-8");
			}
			Cookie cookie = new Cookie(cookieName,cookieValue);
			if (cookieMaxage > 0){
				cookie.setMaxAge(cookieMaxage);
			}
			if (request != null){
				String domainName = getDomainName(request);
				if (!domainName.equals("localhost")){
					cookie.setDomain(domainName);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 设置Cookie的值,使其在指定时间内生效
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieMaxage
	 * @param encodeString
	 */
	private static final void doSetCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
		try {
			if (cookieValue == null){
				cookieValue = "";
			}else {
				cookieValue = URLEncoder.encode(cookieValue,encodeString);
			}
			Cookie cookie = new Cookie(cookieName,cookieValue);
			if (cookieMaxage > 0){
				cookie.setMaxAge(cookieMaxage);
			}
			if (request != null){
				String domainName = getDomainName(request);
				if (!domainName.equals("localhost")){
					cookie.setDomain(domainName);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 得到Cookie的域名
	 * @param request
	 * @return
	 */
	private static final String getDomainName(HttpServletRequest request){
		String domainName = null;
		String serverName = request.getRequestURL().toString();
		if (serverName == null || serverName.equals("")){
			domainName = "";
		}else {
			serverName = serverName.toLowerCase();
			serverName = serverName.substring(7);
			final int end = serverName.indexOf("/");
			serverName = serverName.substring(0,end);
			final String[] domains = serverName.split("\\.");
			int len = domains.length;
			if (len > 3){
				domainName = domains[len - 4] + "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
			}else if (len <= 3 && len > 1){
				domainName = "." + domains[len - 2] + "." + domains[len - 1];
			}else {
				domainName = serverName;
			}
		}
		if (domainName != null && domainName.indexOf(":") > 0){
			String[] ary = domainName.split("\\:");
			domainName = ary[0];
		}
		return domainName;
	}
}
