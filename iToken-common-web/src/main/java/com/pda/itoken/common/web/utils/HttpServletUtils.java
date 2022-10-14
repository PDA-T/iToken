package com.pda.itoken.common.web.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author PDA
 * @Date 2022/10/14 21:48
 * @Description Http工具类
 * @since version-1.0
 */
public class HttpServletUtils {

	/**
	 * @author PDA
	 * @Date 2022/10/14 21:49
	 * @Description 获取完整请求路径,带请求参数
	 * @Param [request]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	public static String getFullPath(HttpServletRequest request){
		StringBuffer uri = request.getRequestURL();
		String url = uri.toString();
		// 获取所有参数,返回 Map 集合
		Map<String,String[]> map = request.getParameterMap();
		Set<Map.Entry<String,String[]>> entry = map.entrySet();
		Iterator<Map.Entry<String,String[]>> iterable = entry.iterator();

		// 遍历集合
		StringBuffer sb = new StringBuffer();
		while (iterable.hasNext()){
			Map.Entry<String,String[]> item = iterable.next();
			// 请求名
			String key = item.getKey();
			// 请求值
			for (String value:item.getValue()){
				// 拼接每个请求参数 key = value&
				sb.append(key + "=" + value + "&");
			}
		}
		String string = sb.toString();
		if (StringUtils.isNotBlank(string)){
			// 拼接 URL,URL?key=value&key=value& 最后一个去掉&
			url = url + "?" + string.substring(0,string.lastIndexOf("&"));
		}
		return url;
	}
}
