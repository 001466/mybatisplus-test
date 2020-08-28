/*
 *      Copyright (c) 2018-2028, DreamLu All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: DreamLu 卢春梦 (596392912@qq.com)
 */
package org.ec.test.utils;

import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Supplier;

/**
 * 工具包集合，工具类快捷方式
 *
 * @author L.cm
 */
public class Func {

	/**
	 * 断言，必须不能为 null
	 * <blockquote><pre>
	 * public Foo(Bar bar) {
	 *     this.bar = $.requireNotNull(bar);
	 * }
	 * </pre></blockquote>
	 *
	 * @param obj the object reference to check for nullity
	 * @param <T> the type of the reference
	 * @return {@code obj} if not {@code null}
	 * @throws NullPointerException if {@code obj} is {@code null}
	 */
	public static <T> T requireNotNull(T obj) {
		return Objects.requireNonNull(obj);
	}

	/**
	 * 断言，必须不能为 null
	 * <blockquote><pre>
	 * public Foo(Bar bar, Baz baz) {
	 *     this.bar = $.requireNotNull(bar, "bar must not be null");
	 *     this.baz = $.requireNotNull(baz, "baz must not be null");
	 * }
	 * </pre></blockquote>
	 *
	 * @param obj     the object reference to check for nullity
	 * @param message detail message to be used in the event that a {@code
	 *                NullPointerException} is thrown
	 * @param <T>     the type of the reference
	 * @return {@code obj} if not {@code null}
	 * @throws NullPointerException if {@code obj} is {@code null}
	 */
	public static <T> T requireNotNull(T obj, String message) {
		return Objects.requireNonNull(obj, message);
	}

	/**
	 * 断言，必须不能为 null
	 * <blockquote><pre>
	 * public Foo(Bar bar, Baz baz) {
	 *     this.bar = $.requireNotNull(bar, () -> "bar must not be null");
	 * }
	 * </pre></blockquote>
	 *
	 * @param obj             the object reference to check for nullity
	 * @param messageSupplier supplier of the detail message to be
	 *                        used in the event that a {@code NullPointerException} is thrown
	 * @param <T>             the type of the reference
	 * @return {@code obj} if not {@code null}
	 * @throws NullPointerException if {@code obj} is {@code null}
	 */
	public static <T> T requireNotNull(T obj, Supplier<String> messageSupplier) {
		return Objects.requireNonNull(obj, messageSupplier);
	}

	/**
	 * 判断对象是否为null
	 * <p>
	 * This method exists to be used as a
	 * {@link java.util.function.Predicate}, {@code filter($::isNull)}
	 * </p>
	 *
	 * @param obj a reference to be checked against {@code null}
	 * @return {@code true} if the provided reference is {@code null} otherwise
	 * {@code false}
	 * @see java.util.function.Predicate
	 */
	public static boolean isNull(@Nullable Object obj) {
		return Objects.isNull(obj);
	}

	/**
	 * 判断对象是否 not null
	 * <p>
	 * This method exists to be used as a
	 * {@link java.util.function.Predicate}, {@code filter($::notNull)}
	 * </p>
	 *
	 * @param obj a reference to be checked against {@code null}
	 * @return {@code true} if the provided reference is non-{@code null}
	 * otherwise {@code false}
	 * @see java.util.function.Predicate
	 */
	public static boolean notNull(@Nullable Object obj) {
		return Objects.nonNull(obj);
	}



	/**
	 * 判断是否为空字符串
	 * <pre class="code">
	 * $.isBlank(null)		= true
	 * $.isBlank("")		= true
	 * $.isBlank(" ")		= true
	 * $.isBlank("12345")	= false
	 * $.isBlank(" 12345 ")	= false
	 * </pre>
	 *
	 * @param cs the {@code CharSequence} to check (may be {@code null})
	 * @return {@code true} if the {@code CharSequence} is not {@code null},
	 * its length is greater than 0, and it does not contain whitespace only
	 * @see Character#isWhitespace
	 */
	public static boolean isBlank(@Nullable final CharSequence cs) {
		return StringUtil.isBlank(cs);
	}

	/**
	 * 判断不为空字符串
	 * <pre>
	 * $.isNotBlank(null)	= false
	 * $.isNotBlank("")		= false
	 * $.isNotBlank(" ")	= false
	 * $.isNotBlank("bob")	= true
	 * $.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is
	 * not empty and not null and not whitespace
	 * @see Character#isWhitespace
	 */
	public static boolean isNotBlank(@Nullable final CharSequence cs) {
		return StringUtil.isNotBlank(cs);
	}

	/**
	 * 判断是否有任意一个 空字符串
	 *
	 * @param css CharSequence
	 * @return boolean
	 */
	public static boolean isAnyBlank(final CharSequence... css) {
		return StringUtil.isAnyBlank(css);
	}

	/**
	 * 判断是否全为非空字符串
	 *
	 * @param css CharSequence
	 * @return boolean
	 */
	public static boolean isNoneBlank(final CharSequence... css) {
		return StringUtil.isNoneBlank(css);
	}

	/**
	 * 判断对象是数组
	 *
	 * @param obj the object to check
	 * @return 是否数组
	 */
	public static boolean isArray(@Nullable Object obj) {
		return ObjectUtil.isArray(obj);
	}

	/**
	 * 判断空对象 object、map、list、set、字符串、数组
	 *
	 * @param obj the object to check
	 * @return 数组是否为空
	 */
	public static boolean isEmpty(@Nullable Object obj) {
		return ObjectUtil.isEmpty(obj);
	}

	/**
	 * 对象不为空 object、map、list、set、字符串、数组
	 *
	 * @param obj the object to check
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(@Nullable Object obj) {
		return !ObjectUtil.isEmpty(obj);
	}

	/**
	 * 判断数组为空
	 *
	 * @param array the array to check
	 * @return 数组是否为空
	 */
	public static boolean isEmpty(@Nullable Object[] array) {
		return ObjectUtil.isEmpty(array);
	}

	/**
	 * 判断数组不为空
	 *
	 * @param array 数组
	 * @return 数组是否不为空
	 */
	public static boolean isNotEmpty(@Nullable Object[] array) {
		return ObjectUtil.isNotEmpty(array);
	}

	/**
	 * 对象组中是否存在 Empty Object
	 *
	 * @param os 对象组
	 * @return boolean
	 */
	public static boolean hasEmpty(Object... os) {
		for (Object o : os) {
			if (isEmpty(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将字符串中特定模式的字符转换成map中对应的值
	 * <p>
	 * use: format("my name is ${name}, and i like ${like}!", {"name":"L.cm", "like": "Java"})
	 *
	 * @param message 需要转换的字符串
	 * @param params  转换所需的键值对集合
	 * @return 转换后的字符串
	 */
	public static String format(@Nullable String message, @Nullable Map<String, Object> params) {
		return StringUtil.format(message, params);
	}

	/**
	 * 同 log 格式的 format 规则
	 * <p>
	 * use: format("my name is {}, and i like {}!", "L.cm", "Java")
	 *
	 * @param message   需要转换的字符串
	 * @param arguments 需要替换的变量
	 * @return 转换后的字符串
	 */
	public static String format(@Nullable String message, @Nullable Object... arguments) {
		return StringUtil.format(message, arguments);
	}

	/**
	 * 比较两个对象是否相等。<br>
	 * 相同的条件有两个，满足其一即可：<br>
	 *
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return 是否相等
	 */
	public static boolean equals(Object obj1, Object obj2) {
		return Objects.equals(obj1, obj2);
	}

	/**
	 * 安全的 equals
	 *
	 * @param o1 first Object to compare
	 * @param o2 second Object to compare
	 * @return whether the given objects are equal
	 * @see Object#equals(Object)
	 * @see Arrays#equals
	 */
	public static boolean equalsSafe(@Nullable Object o1, @Nullable Object o2) {
		return ObjectUtil.nullSafeEquals(o1, o2);
	}









	/**
	 * 强转string,并去掉多余空格
	 *
	 * @param str 字符串
	 * @return String
	 */
	public static String toStr(Object str) {
		return toStr(str, "");
	}

	/**
	 * 强转string,并去掉多余空格
	 *
	 * @param str          字符串
	 * @param defaultValue 默认值
	 * @return String
	 */
	public static String toStr(Object str, String defaultValue) {
		if (null == str) {
			return defaultValue;
		}
		return String.valueOf(str);
	}

	/**
	 * 判断一个字符串是否是数字
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {boolean}
	 */
	public static boolean isNumeric(final CharSequence cs) {
		return StringUtil.isNumeric(cs);
	}






	/**
	 * <p>Convert a <code>String</code> to an <code>Double</code>, returning a
	 * default value if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, the default value is returned.</p>
	 *
	 * <pre>
	 *   $.toDouble(null, 1) = 1.0
	 *   $.toDouble("", 1)   = 1.0
	 *   $.toDouble("1", 0)  = 1.0
	 * </pre>
	 *
	 * @param value the string to convert, may be null
	 * @return the int represented by the string, or the default if conversion fails
	 */







	/**
	 * <p>Convert a <code>String</code> to an <code>Boolean</code>, returning a
	 * default value if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, the default value is returned.</p>
	 *
	 * <pre>
	 *   $.toBoolean("true", true)  = true
	 *   $.toBoolean("false")   	= false
	 *   $.toBoolean("", false)  	= false
	 * </pre>
	 *
	 * @param value the string to convert, may be null
	 * @return the int represented by the string, or the default if conversion fails
	 */
	public static Boolean toBoolean(Object value) {
		return toBoolean(value, null);
	}

	/**
	 * <p>Convert a <code>String</code> to an <code>Boolean</code>, returning a
	 * default value if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, the default value is returned.</p>
	 *
	 * <pre>
	 *   $.toBoolean("true", true)  = true
	 *   $.toBoolean("false")   	= false
	 *   $.toBoolean("", false)  	= false
	 * </pre>
	 *
	 * @param value        the string to convert, may be null
	 * @param defaultValue the default value
	 * @return the int represented by the string, or the default if conversion fails
	 */
	public static Boolean toBoolean(Object value, Boolean defaultValue) {
		if (value != null) {
			String val = String.valueOf(value);
			val = val.toLowerCase().trim();
			return Boolean.parseBoolean(val);
		}
		return defaultValue;
	}

	/**
	 * 转换为Integer数组<br>
	 *
	 * @param str 被转换的值
	 * @return 结果
	 */




	/**
	 * 转换为Integer集合<br>
	 *
	 * @param str 结果被转换的值
	 * @return 结果
	 */

	/**
	 * 转换为Integer集合<br>
	 *
	 * @param split 分隔符
	 * @param str   被转换的值
	 * @return 结果
	 */

	/**
	 * 转换为String数组<br>
	 *
	 * @param str 被转换的值
	 * @return 结果
	 */
	public static String[] toStrArray(String str) {
		return toStrArray(",", str);
	}

	/**
	 * 转换为String数组<br>
	 *
	 * @param split 分隔符
	 * @param str   被转换的值
	 * @return 结果
	 */
	public static String[] toStrArray(String split, String str) {
		if (isBlank(str)) {
			return new String[]{};
		}
		return str.split(split);
	}

	/**
	 * 转换为String集合<br>
	 *
	 * @param str 结果被转换的值
	 * @return 结果
	 */
	public static List<String> toStrList(String str) {
		return Arrays.asList(toStrArray(str));
	}

	/**
	 * 转换为String集合<br>
	 *
	 * @param split 分隔符
	 * @param str   被转换的值
	 * @return 结果
	 */
	public static List<String> toStrList(String split, String str) {
		return Arrays.asList(toStrArray(split, str));
	}

	/**
	 * 将 long 转短字符串 为 62 进制
	 *
	 * @param num 数字
	 * @return 短字符串
	 */

	/**
	 * 将集合拼接成字符串，默认使用`,`拼接
	 *
	 * @param coll the {@code Collection} to convert
	 * @return the delimited {@code String}
	 */
	public static String join(Collection<?> coll) {
		return StringUtil.join(coll);
	}

	/**
	 * 将集合拼接成字符串，默认指定分隔符
	 *
	 * @param coll  the {@code Collection} to convert
	 * @param delim the delimiter to use (typically a ",")
	 * @return the delimited {@code String}
	 */
	public static String join(Collection<?> coll, String delim) {
		return StringUtil.join(coll, delim);
	}

	/**
	 * 将数组拼接成字符串，默认使用`,`拼接
	 *
	 * @param arr the array to display
	 * @return the delimited {@code String}
	 */
	public static String join(Object[] arr) {
		return StringUtil.join(arr);
	}

	/**
	 * 将数组拼接成字符串，默认指定分隔符
	 *
	 * @param arr   the array to display
	 * @param delim the delimiter to use (typically a ",")
	 * @return the delimited {@code String}
	 */
	public static String join(Object[] arr, String delim) {
		return StringUtil.join(arr, delim);
	}


	/**
	 * 分割 字符串
	 *
	 * @param str       字符串
	 * @param delimiter 分割符
	 * @return 字符串数组
	 */
	public static String[] split(@Nullable String str, @Nullable String delimiter) {
		return StringUtil.delimitedListToStringArray(str, delimiter);
	}

	/**
	 * 分割 字符串 删除常见 空白符
	 *
	 * @param str       字符串
	 * @param delimiter 分割符
	 * @return 字符串数组
	 */
	public static String[] splitTrim(@Nullable String str, @Nullable String delimiter) {
		return StringUtil.delimitedListToStringArray(str, delimiter, " \t\n\n\f");
	}

	/**
	 * 字符串是否符合指定的 表达式
	 *
	 * <p>
	 * pattern styles: "xxx*", "*xxx", "*xxx*" and "xxx*yyy"
	 * </p>
	 *
	 * @param pattern 表达式
	 * @param str     字符串
	 * @return 是否匹配
	 */
	public static boolean simpleMatch(@Nullable String pattern, @Nullable String str) {
		return PatternMatchUtils.simpleMatch(pattern, str);
	}

	/**
	 * 字符串是否符合指定的 表达式
	 *
	 * <p>
	 * pattern styles: "xxx*", "*xxx", "*xxx*" and "xxx*yyy"
	 * </p>
	 *
	 * @param patterns 表达式 数组
	 * @param str      字符串
	 * @return 是否匹配
	 */
	public static boolean simpleMatch(@Nullable String[] patterns, String str) {
		return PatternMatchUtils.simpleMatch(patterns, str);
	}

	/**
	 * 生成uuid
	 *
	 * @return UUID
	 */
	public static String randomUUID() {
		return StringUtil.randomUUID();
	}

	/**
	 * 转义HTML用于安全过滤
	 *
	 * @param html html
	 * @return {String}
	 */

	/**
	 * 随机数生成
	 *
	 * @param count 字符长度
	 * @return 随机数
	 */

	/**
	 * 随机数生成
	 *
	 * @param count      字符长度
	 * @param randomType 随机数类别
	 * @return 随机数
	 */

	/**
	 * 字符串序列化成 md5
	 *
	 * @param data Data to digest
	 * @return MD5 digest as a hex string
	 */

	/**
	 * 数组序列化成 md5
	 *
	 * @param bytes the bytes to calculate the digest over
	 * @return md5 digest string
	 */


	/**
	 * sha1Hex
	 *
	 * @param data Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha1Hex
	 *
	 * @param bytes Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * SHA224Hex
	 *
	 * @param data Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * SHA224Hex
	 *
	 * @param bytes Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha256Hex
	 *
	 * @param data Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha256Hex
	 *
	 * @param bytes Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha384Hex
	 *
	 * @param data Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha384Hex
	 *
	 * @param bytes Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha512Hex
	 *
	 * @param data Data to digest
	 * @return digest as a hex string
	 */

	/**
	 * sha512Hex
	 *
	 * @param bytes Data to digest
	 * @return digest as a hex string
	 */


	/**
	 * hmacMd5 Hex
	 *
	 * @param bytes Data to digest
	 * @param key   key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha1 Hex
	 *
	 * @param data Data to digest
	 * @param key  key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha1 Hex
	 *
	 * @param bytes Data to digest
	 * @param key   key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha224 Hex
	 *
	 * @param data Data to digest
	 * @param key  key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha224 Hex
	 *
	 * @param bytes Data to digest
	 * @param key   key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha256 Hex
	 *
	 * @param data Data to digest
	 * @param key  key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha256 Hex
	 *
	 * @param bytes Data to digest
	 * @param key   key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha384 Hex
	 *
	 * @param data Data to digest
	 * @param key  key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha384 Hex
	 *
	 * @param bytes Data to digest
	 * @param key   key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha512 Hex
	 *
	 * @param data Data to digest
	 * @param key  key
	 * @return digest as a hex string
	 */

	/**
	 * hmacSha512 Hex
	 *
	 * @param bytes Data to digest
	 * @param key   key
	 * @return digest as a hex string
	 */

	/**
	 * byte 数组序列化成 hex
	 *
	 * @param bytes bytes to encode
	 * @return MD5 digest as a hex string
	 */
	public static String encodeHex(byte[] bytes) {
		return DatatypeConverter.printHexBinary(bytes);
	}

	/**
	 * 字符串反序列化成 hex
	 *
	 * @param hexString String to decode
	 * @return MD5 digest as a hex string
	 */
	public static byte[] decodeHex(final String hexString) {
		return DatatypeConverter.parseHexBinary(hexString);
	}

	/**
	 * Base64编码
	 *
	 * @param value 字符串
	 * @return {String}
	 */

	/**
	 * Base64编码
	 *
	 * @param value   字符串
	 * @param charset 字符集
	 * @return {String}
	 */

	/**
	 * Base64编码为URL安全
	 *
	 * @param value 字符串
	 * @return {String}
	 */



	/**
	 * Base64解码
	 *
	 * @param value 字符串
	 * @return {String}
	 */

	/**
	 * Base64解码
	 *
	 * @param value   字符串
	 * @param charset 字符集
	 * @return {String}
	 */

	/**
	 * Base64URL安全解码
	 *
	 * @param value 字符串
	 * @return {String}
	 */



	/**
	 * 关闭 Closeable
	 *
	 * @param closeable 自动关闭
	 */

	/**
	 * InputStream to String utf-8
	 *
	 * @param input the <code>InputStream</code> to read from
	 * @return the requested String
	 * @throws NullPointerException if the input is null
	 */

	/**
	 * InputStream to String
	 *
	 * @param input   the <code>InputStream</code> to read from
	 * @param charset the <code>Charset</code>
	 * @return the requested String
	 * @throws NullPointerException if the input is null
	 */


	/**
	 * InputStream to bytes 数组
	 *
	 * @param input InputStream
	 * @return the requested byte array
	 */

	/**
	 * 读取文件为字符串
	 *
	 * @param file the file to read, must not be {@code null}
	 * @return the file contents, never {@code null}
	 */

	/**
	 * 读取文件为字符串
	 *
	 * @param file     the file to read, must not be {@code null}
	 * @param encoding the encoding to use, {@code null} means platform default
	 * @return the file contents, never {@code null}
	 */

	/**
	 * 读取文件为 byte 数组
	 *
	 * @param file the file to read, must not be {@code null}
	 * @return the file contents, never {@code null}
	 */

	/**
	 * 将对象序列化成json字符串
	 *
	 * @param object javaBean
	 * @return jsonString json字符串
	 */

	/**
	 * 将对象序列化成 json byte 数组
	 *
	 * @param object javaBean
	 * @return jsonString json字符串
	 */

	/**
	 * 将json字符串转成 JsonNode
	 *
	 * @param jsonString jsonString
	 * @return jsonString json字符串
	 */

	/**
	 * 将json字符串转成 JsonNode
	 *
	 * @param in InputStream
	 * @return jsonString json字符串
	 */

	/**
	 * 将json字符串转成 JsonNode
	 *
	 * @param content content
	 * @return jsonString json字符串
	 */

	/**
	 * 将json字符串转成 JsonNode
	 *
	 * @param jsonParser JsonParser
	 * @return jsonString json字符串
	 */

	/**
	 * 将json byte 数组反序列化成对象
	 *
	 * @param bytes     json bytes
	 * @param valueType class
	 * @param <T>       T 泛型标记
	 * @return Bean
	 */

	/**
	 * 将json反序列化成对象
	 *
	 * @param jsonString jsonString
	 * @param valueType  class
	 * @param <T>        T 泛型标记
	 * @return Bean
	 */

	/**
	 * 将json反序列化成对象
	 *
	 * @param in        InputStream
	 * @param valueType class
	 * @param <T>       T 泛型标记
	 * @return Bean
	 */

	/**
	 * 将json反序列化成对象
	 *
	 * @param bytes         bytes
	 * @param typeReference 泛型类型
	 * @param <T>           T 泛型标记
	 * @return Bean
	 */


	/**
	 * 将json反序列化成对象
	 *
	 * @param jsonString    jsonString
	 * @param typeReference 泛型类型
	 * @param <T>           T 泛型标记
	 * @return Bean
	 */


	/**
	 * 将json反序列化成对象
	 *
	 * @param in            InputStream
	 * @param typeReference 泛型类型
	 * @param <T>           T 泛型标记
	 * @return Bean
	 */


	/**
	 * url 编码
	 *
	 * @param source the String to be encoded
	 * @return the encoded String
	 */

	/**
	 * url 编码
	 *
	 * @param source  the String to be encoded
	 * @param charset the character encoding to encode to
	 * @return the encoded String
	 */

	/**
	 * url 解码
	 *
	 * @param source the encoded String
	 * @return the decoded value
	 * @throws IllegalArgumentException when the given source contains invalid encoded sequences
	 * @see StringUtils#uriDecode(String, Charset)
	 * @see java.net.URLDecoder#decode(String, String)
	 */

	/**
	 * url 解码
	 *
	 * @param source  the encoded String
	 * @param charset the character encoding to use
	 * @return the decoded value
	 * @throws IllegalArgumentException when the given source contains invalid encoded sequences
	 * @see StringUtils#uriDecode(String, Charset)
	 * @see java.net.URLDecoder#decode(String, String)
	 */
	public static String urlDecode(String source, Charset charset) {
		return StringUtils.uriDecode(source, charset);
	}

	/**
	 * 日期时间格式化
	 *
	 * @param date 时间
	 * @return 格式化后的时间
	 */

	/**
	 * 日期格式化
	 *
	 * @param date 时间
	 * @return 格式化后的时间
	 */

	/**
	 * 时间格式化
	 *
	 * @param date 时间
	 * @return 格式化后的时间
	 */

	/**
	 * 对象格式化 支持数字，date，java8时间
	 *
	 * @param object  格式化对象
	 * @param pattern 表达式
	 * @return 格式化后的字符串
	 */


	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr 时间字符串
	 * @param pattern 表达式
	 * @return 时间
	 */

	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr 时间字符串
	 * @param format  ConcurrentDateFormat
	 * @return 时间
	 */

	/**
	 * 日期时间格式化
	 *
	 * @param temporal 时间
	 * @return 格式化后的时间
	 */

	/**
	 * 日期时间格式化
	 *
	 * @param temporal 时间
	 * @return 格式化后的时间
	 */

	/**
	 * 时间格式化
	 *
	 * @param temporal 时间
	 * @return 格式化后的时间
	 */

	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr   时间字符串
	 * @param formatter DateTimeFormatter
	 * @return 时间
	 */


	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr 时间字符串
	 * @return 时间
	 */

	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr   时间字符串
	 * @param formatter DateTimeFormatter
	 * @return 时间
	 */


	/**
	 * 将字符串转换为日期
	 *
	 * @param dateStr 时间字符串
	 * @return 时间
	 */

	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr   时间字符串
	 * @param formatter DateTimeFormatter
	 * @return 时间
	 */


	/**
	 * 将字符串转换为时间
	 *
	 * @param dateStr 时间字符串
	 * @return 时间
	 */

	/**
	 * 时间比较
	 *
	 * @param startInclusive the start instant, inclusive, not null
	 * @param endExclusive   the end instant, exclusive, not null
	 * @return a {@code Duration}, not null
	 */
	public static Duration between(Temporal startInclusive, Temporal endExclusive) {
		return Duration.between(startInclusive, endExclusive);
	}

	/**
	 * 比较2个 时间差
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return 时间间隔
	 */

	/**
	 * 对象类型转换
	 *
	 * @param source     the source object
	 * @param targetType the target type
	 * @param <T>        泛型标记
	 * @return the converted value
	 * @throws IllegalArgumentException if targetType is {@code null},
	 *                                  or sourceType is {@code null} but source is not {@code null}
	 */


	/**
	 * 对象类型转换
	 *
	 * @param source     the source object
	 * @param sourceType the source type
	 * @param targetType the target type
	 * @param <T>        泛型标记
	 * @return the converted value
	 * @throws IllegalArgumentException if targetType is {@code null},
	 *                                  or sourceType is {@code null} but source is not {@code null}
	 */


	/**
	 * 对象类型转换
	 *
	 * @param source     the source object
	 * @param targetType the target type
	 * @param <T>        泛型标记
	 * @return the converted value
	 * @throws IllegalArgumentException if targetType is {@code null},
	 *                                  or sourceType is {@code null} but source is not {@code null}
	 */


	/**
	 * 获取方法参数信息
	 *
	 * @param constructor    构造器
	 * @param parameterIndex 参数序号
	 * @return {MethodParameter}
	 */


	/**
	 * 获取方法参数信息
	 *
	 * @param method         方法
	 * @param parameterIndex 参数序号
	 * @return {MethodParameter}
	 */


	/**
	 * 获取Annotation注解
	 *
	 * @param annotatedElement AnnotatedElement
	 * @param annotationType   注解类
	 * @param <A>              泛型标记
	 * @return {Annotation}
	 */
	@Nullable
	public static <A extends Annotation> A getAnnotation(AnnotatedElement annotatedElement, Class<A> annotationType) {
		return AnnotatedElementUtils.findMergedAnnotation(annotatedElement, annotationType);
	}







	/**
	 * 实例化对象
	 *
	 * @param clazz 类
	 * @param <T>   泛型标记
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) {
		return (T) BeanUtil.instantiateClass(clazz);
	}

	/**
	 * 实例化对象
	 *
	 * @param clazzStr 类名
	 * @param <T>      泛型标记
	 * @return 对象
	 */
	public static <T> T newInstance(String clazzStr) {
		return BeanUtil.newInstance(clazzStr);
	}

	/**
	 * 获取Bean的属性
	 *
	 * @param bean         bean
	 * @param propertyName 属性名
	 * @return 属性值
	 */
	@Nullable
	public static Object getProperty(@Nullable Object bean, String propertyName) {
		return BeanUtil.getProperty(bean, propertyName);
	}

	/**
	 * 设置Bean属性
	 *
	 * @param bean         bean
	 * @param propertyName 属性名
	 * @param value        属性值
	 */
	public static void setProperty(Object bean, String propertyName, Object value) {
		BeanUtil.setProperty(bean, propertyName, value);
	}






	/**
	 * 拷贝对象，source 对象属性做非 null 判断
	 *
	 * <p>
	 * 支持 map bean copy
	 * </p>
	 *
	 * @param source     源对象
	 * @param targetBean 需要赋值的对象
	 */


	/**
	 * 拷贝对象，并对不同类型属性进行转换
	 *
	 * @param source 源对象
	 * @param clazz  类名
	 * @param <T>    泛型标记
	 * @return T
	 */


	/**
	 * 拷贝列表对象
	 *
	 * <p>
	 * 支持 map bean copy
	 * </p>
	 *
	 * @param sourceList  源列表
	 * @param targetClazz 转换成的类型
	 * @param <T>         泛型标记
	 * @return T
	 */


	/**
	 * 拷贝列表对象，并对不同类型属性进行转换
	 *
	 * <p>
	 * 支持 map bean copy
	 * </p>
	 *
	 * @param sourceList  源对象列表
	 * @param targetClazz 转换成的类
	 * @param <T>         泛型标记
	 * @return List
	 */


	/**
	 * 拷贝对象，扩展 Spring 的拷贝方法
	 *
	 * @param source the source bean
	 * @param clazz  the target bean class
	 * @param <T>    泛型标记
	 * @return T
	 * @throws BeansException if the copying failed
	 */
	@Nullable
	public static <T> T copyProperties(@Nullable Object source, Class<T> clazz) throws BeansException {
		return BeanUtil.copyProperties(source, clazz);
	}

	/**
	 * 拷贝列表对象，扩展 Spring 的拷贝方法
	 *
	 * @param sourceList  the source list bean
	 * @param targetClazz the target bean class
	 * @param <T>         泛型标记
	 * @return List
	 * @throws BeansException if the copying failed
	 */
	public static <T> List<T> copyProperties(@Nullable Collection<?> sourceList, Class<T> targetClazz) throws BeansException {
		return BeanUtil.copyProperties(sourceList, targetClazz);
	}

	/**
	 * 将对象装成map形式
	 *
	 * @param bean 源对象
	 * @return {Map}
	 */

	/**
	 * 将map 转为 bean
	 *
	 * @param beanMap   map
	 * @param valueType 对象类型
	 * @param <T>       泛型标记
	 * @return {T}
	 */


}
