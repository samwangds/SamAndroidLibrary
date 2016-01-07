/**
 * Project Name:mpserver
 * File Name:JsonUtil.java
 * Package Name:com.ptnetwork.mpserver.util
 * Date:2012-8-18上午11:38:42
 * Copyright (c) 2012, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.sam.lib.utils.json;


import android.text.TextUtils;

import com.sam.lib.utils.logger.Logger;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;


/**
 * ClassName:JsonUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-18 上午11:38:42 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class JsonUtil {
	private static ObjectMapper mapper;
	private static SimpleModule simpleModule;

	static {
		mapper = new ObjectMapper();
		//设置输出时包含属性的风格,使用输出全部属性到Json字符串
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.ALWAYS);
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        //添加换行符的兼容处理
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

		//设置CLOB类型等序列化工具
		simpleModule = new SimpleModule("SimpleModule", new Version(1, 0, 0, null));
		simpleModule.addSerializer(new ClobSerializer());
		mapper.registerModule(simpleModule);
	}

	/**
	 * 将json 转换为 Java Object<p>
	 * 如果JSON字符串为Null或"null"字符串, 返回Null.<br>
	 * 如果JSON字符串为"[]", 返回空集合.<br>
	 * 如果转换出现异常，返回Null
	 * <p>
	 * 如需读取集合如List/Map, 且不是List&lt;String>这种简单类型时使用如下语句,
	 *
	 * @param jsonString 输入的json字符串
	 * @param clazz 需要转换得到的类型
	 * @return 转换后的Java对象
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		}
		catch (IOException e) {
			Logger.e("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 将json 转换为简单的 Java List/Map Object<p>
	 * 如果JSON字符串为Null或"null"字符串, 返回Null.<br>
	 * 如果JSON字符串为"[]", 返回空集合.<br>
	 * 如果转换出现异常，返回Null
	 * <p>
	 * example:<pre>
	 * List&lt;Bean> result = JsonUtil.fromJson(source,new JsonTypeReference&lt;List&lt;Bean>>(){
	 * });</pre>
	 *
	 * @param jsonString 输入的json字符串
	 * @param type 需要转换得到的结果类型。使用 new JsonTypeReference构造
	 * @return 转换后的Java对象
	 */
	public static <T> T fromJson(String jsonString, TypeReference<T> type) {
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, type);
		}
		catch (IOException e) {
			Logger.e("parse json string error:" + jsonString, e);
			return null;
		}
	}


	/**
	 * 将java对象转换为json字符串。
	 * <p>
	 * 如果对象为Null, 返回"null".<br>
	 * 如果集合为空集合, 返回"[]".<br>
	 * 如果转换出错，返回空null
	 *
	 * @param object 需要转换的java object
	 * @return json string
	 */
	public static String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		}
		catch (IOException e) {
			Logger.e("write to json string error:" + object, e);
			return null;
		}
	}


	public static String objToString(Object obj){
		if(obj != null && obj instanceof String ){
			return  (String) obj;
		}
		return null;
	}
	public static long objToLong(Object obj){
		if(obj != null ){
			if(obj instanceof Long){
				return  (long) obj;
			}else if(obj instanceof String){
				return Long.parseLong((String) obj);
			}
		}

		return 0;
	}
	public static int objToInt(Object obj){
			if(obj != null ){
				if(obj instanceof Integer){
					return  (int) obj;
				}else if(obj instanceof String){
					return Integer.parseInt((String) obj);
				}
			}
		return 0;
	}


	public static boolean objToBoolean(Object obj){
		if(obj != null  ){
			if( obj instanceof Boolean){
				return  (Boolean) obj;
			}else if( obj instanceof String){
				return  !"0".equals(obj);
			}else if( obj instanceof Integer){
				return  Integer.valueOf(1) == obj;
			}
		}
		return false;
	}

}

