package com.unicom.test.microservice.producer;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hash工具类
 * 
 * @author huangjl
 *
 */
public final class HashUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HashUtil.class);

	/**
	 * 静态类不可构造
	 */
	private HashUtil() {
	}

	/**
	 * 字节转16进制字符串
	 * 
	 * @param src
	 *            待转换的字节组
	 * @return 16进制字符串
	 */
	public static String byte2hex(final byte[] src) {
		final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] str = new char[src.length * 2];
		for (int i = 0; i < src.length; i++) {
			final byte b = src[i];
			str[i * 2] = hexDigits[b >>> 4 & 0xf];
			str[i * 2 + 1] = hexDigits[b & 0xf];
		}
		return new String(str);
	}

	/**
	 * 获得hash
	 * 
	 * @param is
	 *            输入数据流
	 * @param algorithm
	 *            hash方式
	 * @return hash
	 */
	public static byte[] getHash(final InputStream is, String algorithm) {
		try {
			final MessageDigest digest = MessageDigest.getInstance(algorithm);
			final byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				digest.update(buffer, 0, len);
			}
			return digest.digest();
		} catch (IOException | NoSuchAlgorithmException e) {
			LOG.error("转换" + algorithm + "失败", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				LOG.error("转换" + algorithm + "关闭数据流失败", e);
			}
		}
		return new byte[0];
	}

	/**
	 * 获得字节组的md5
	 * 
	 * @param source
	 *            待转换的字节组
	 * @return 已转换的字节流
	 */
	public static byte[] getMD5(final byte[] source) {
		byte[] result = new byte[0];
		try {
			final MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(source);
			result = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			LOG.error("转换md5失败", e);
		}
		return result;
	}

	/**
	 * 
	 * @param source
	 *            待转换的字节组
	 * @return 已转换的16进制字符串
	 */
	public static String getMD5String32(final byte[] source) {
		return byte2hex(getMD5(source));
	}

	/**
	 * 
	 * @param source
	 *            待转换的字节组
	 * @return 已转换的16进制字符串
	 */
	public static String getMD5String16(final byte[] source) {
		return byte2hex(getMD5(source)).substring(8, 24);
	}

	/**
	 * 获得输入流的md5
	 * 
	 * @param is
	 *            输入流
	 * @return 已转换的字节流
	 */
	public static byte[] getMD5(final InputStream is) {
		return getHash(is, "MD5");
	}

	/**
	 * 获得字节组的sha1
	 * 
	 * @param source
	 *            待转换的字节组
	 * @return 已转换的字节流
	 */
	public static byte[] getSHA1(final byte[] source) {
		try {
			final MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			sha1.update(source);
			return sha1.digest();
		} catch (Exception e) {
			LOG.error("转换sha1失败", e);
		}
		return new byte[0];
	}

	/**
	 * 获得输入流的sha1
	 * 
	 * @param is
	 *            输入流
	 * @return 已转换的字节流
	 */
	public static byte[] getSHA1(final InputStream is) {
		return getHash(is, "SHA-1");
	}

	/**
	 * 获得输入流的sha256
	 * 
	 * @param is
	 *            输入流
	 * @return 已转换的字节流
	 */
	public static byte[] getSHA256(final InputStream is) {
		return getHash(is, "SHA-256");
	}

	/**
	 * 获得输入流的crc32
	 * 
	 * @param is
	 *            输入流
	 * @return 数据校验码
	 */
	public static long getCRC32(final InputStream is) {
		final CRC32 crc32 = new CRC32();
		try {
			final byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				crc32.update(buffer, 0, len);
			}
			return crc32.getValue();
		} catch (IOException e) {
			LOG.error("转换crc32失败", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				LOG.error("转换crc32关闭数据流失败", e);
			}
		}
		return -1;
	}

}
