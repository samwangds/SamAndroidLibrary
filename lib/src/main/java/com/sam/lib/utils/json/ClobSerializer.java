/**
 * @(#)ClobSerializer.java 2014-2-14
 * 
 * Copyright 2000-2011 by ChinanetCenter Corporation.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ChinanetCenter Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with ChinanetCenter.
 * 
 */

package com.sam.lib.utils.json;

import com.sam.lib.utils.logger.Logger;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * JSON的Clob序列化类
 * 
 * @date 2014年2月14日
 * @version 1.0
 * @since 2.2
 */
public class ClobSerializer extends JsonSerializer<Clob> {

	@Override
	public void serialize(Clob value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		try {
			jgen.writeString(clobStringConversion(value));
		}
		catch (SQLException e) {
			Logger.e("Error while serialize clob: ", e);
			jgen.writeNull();
		}
	}

	public Class<Clob> handledType() {
		return Clob.class;
	}

	private static String clobStringConversion(Clob clob) throws IOException, SQLException {
		if (clob == null)
			return "";

		StringBuffer buffer = new StringBuffer();
		String str;

		BufferedReader bufferedReader = new BufferedReader(clob.getCharacterStream());

		while ((str = bufferedReader.readLine()) != null)
			buffer.append(str);

		return buffer.toString();
	}
}
