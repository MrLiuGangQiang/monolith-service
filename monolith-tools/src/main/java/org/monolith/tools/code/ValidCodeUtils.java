package org.monolith.tools.code;

import java.util.Random;

/**
 * @ClassName ValidCodeUtils
 * @author <font color="red"><b>LiuGangQiang</b></font>
 * @date 2016年1月31日 上午10:41:21
 * @version 1.0
 * @description 生成验证码
 */
public class ValidCodeUtils {

	private static char[] numbers = "0123456789".toCharArray();

	private static char[] words = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

	private static final int MIN_LEN = 4;

	private static final int MAX_LEN = 8;

	/**
	 * @param len
	 * @return {@link String}
	 * @date 2016年1月31日 上午10:42:43
	 * @version 1.0
	 * @description 返回数字
	 */
	public static String generateNumber(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = numbers[random.nextInt(numbers.length)];
		}
		return new String(cs);
	}

	/**
	 * @param len
	 * @return {@link String}
	 * @version 1.0
	 * @date 2016年1月31日 上午10:45:18
	 * @description 返回字符数字混合型验证码
	 */
	public static String generateCode(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = words[random.nextInt(words.length)];
		}
		return new String(cs);
	}

	/**
	 * @param len
	 * @return {@link Int}
	 * @version 1.0
	 * @date 2016年1月31日 上午10:46:11
	 * @description 限制验证码长度
	 */
	private static int limitLen(int len) {
		if (len < MIN_LEN) {
			return MIN_LEN;
		} else if (len > MAX_LEN) {
			return MAX_LEN;
		} else {
			return len;
		}
	}
}
