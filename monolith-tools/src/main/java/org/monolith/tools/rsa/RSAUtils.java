package org.monolith.tools.rsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import net.iharder.Base64;

public class RSAUtils {
	public static final String KEY_ALGORITHM = "RSA";
	private static final String PUBLIC_KEY = "publicKey";
	private static final String PRIVATE_KEY = "privateKey";
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * @Title: publicKeyToString
	 * @Description: 得到字符串型的公钥 Java
	 * @param publicKey
	 * @return String
	 */
	public static String publicKeyToString(RSAPublicKey publicKey) {
		return Base64.encodeBytes(publicKey.getEncoded());
	}

	/**
	 * @Title: privateKeyToString
	 * @Description: 得到字符串型的私钥 Java
	 * @param privateKey
	 * @return String
	 */
	public static String privateKeyToString(RSAPrivateKey privateKey) {
		return Base64.encodeBytes(privateKey.getEncoded());
	}

	/**
	 * @Title: getPublicKey
	 * @Description: 将字符串型公钥转化为PublicKey Java
	 * @param publicKey
	 * @return PublicKey
	 */
	public static PublicKey getPublicKey(String publicKey) {
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
			KeyFactory factory;
			factory = KeyFactory.getInstance(KEY_ALGORITHM);
			return factory.generatePublic(x509EncodedKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: getPrivateKey
	 * @Description: 将字符串型私钥转化为 PrivateKey Java
	 * @param privateKey
	 * @return PrivateKey
	 */
	public static PrivateKey getPrivateKey(String privateKey) {
		try {
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			return factory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: generateKeyBytes
	 * @Description: 初始化密钥对
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> generateKeyBytes() {
		KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: RSAEncode
	 * @Description: 将字符串加密
	 * @param key
	 * @param plainText
	 * @return String
	 */
	public static String RSAEncode(PublicKey key, String plainText) {
		try {
			byte[] b = plainText.getBytes("UTF-8");
			int inputLen = b.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(b, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return Base64.encodeBytes(decryptedData);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: RSADecode
	 * @Description: 将字符串解密
	 * @param key
	 * @param encodedText
	 * @return String
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IOException
	 */
	public static String RSADecode(PrivateKey key, String encodedText) throws IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {

		byte[] b = Base64.decode(encodedText);
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * @Title: getRSAPrivateKeyAsNetFormat
	 * @Description: 将Java私钥转化为.Net格式
	 * @param privateKey
	 * @return String
	 */
	public static String getRSAPrivateKeyAsNetFormat(String privateKey) {
		try {
			StringBuffer buff = new StringBuffer(1024);

			PKCS8EncodedKeySpec pvkKeySpec = new PKCS8EncodedKeySpec(getPrivateKey(privateKey).getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateCrtKey pvkKey = (RSAPrivateCrtKey) keyFactory.generatePrivate(pvkKeySpec);

			buff.append("<RSAKeyValue>");
			buff.append(
					"<Modulus>" + Base64.encodeBytes(removeMSZero(pvkKey.getModulus().toByteArray())) + "</Modulus>");
			buff.append("<Exponent>" + Base64.encodeBytes(removeMSZero(pvkKey.getPublicExponent().toByteArray()))
					+ "</Exponent>");
			buff.append("<P>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeP().toByteArray())) + "</P>");
			buff.append("<Q>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeQ().toByteArray())) + "</Q>");
			buff.append("<DP>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeExponentP().toByteArray())) + "</DP>");
			buff.append("<DQ>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeExponentQ().toByteArray())) + "</DQ>");
			buff.append("<InverseQ>" + Base64.encodeBytes(removeMSZero(pvkKey.getCrtCoefficient().toByteArray()))
					+ "</InverseQ>");
			buff.append("<D>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrivateExponent().toByteArray())) + "</D>");
			buff.append("</RSAKeyValue>");
			return buff.toString();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	/**
	 * @Title: getRSAPublicKeyAsNetFormat
	 * @Description: 将Java公钥转化为.Net格式
	 * @param encodedPublicKey
	 * @return String
	 */
	public static String getRSAPublicKeyAsNetFormat(String publicKey) {
		try {
			StringBuffer buff = new StringBuffer(1024);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKey pukKey = (RSAPublicKey) keyFactory
					.generatePublic(new X509EncodedKeySpec(getPublicKey(publicKey).getEncoded()));
			buff.append("<RSAKeyValue>");
			buff.append(
					"<Modulus>" + Base64.encodeBytes(removeMSZero(pukKey.getModulus().toByteArray())) + "</Modulus>");
			buff.append("<Exponent>" + Base64.encodeBytes(removeMSZero(pukKey.getPublicExponent().toByteArray()))
					+ "</Exponent>");
			buff.append("</RSAKeyValue>");
			return buff.toString();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	/**
	 * @Title: removeMSZero
	 * @Description: JAVA和.Net密钥转化算法
	 * @param data
	 * @return byte[]
	 */
	private static byte[] removeMSZero(byte[] data) {
		byte[] temp;
		int len = data.length;
		if (data[0] == 0) {
			temp = new byte[data.length - 1];
			System.arraycopy(data, 1, temp, 0, len - 1);
		} else
			temp = data;

		return temp;
	}

	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		Map<String, Object> map = generateKeyBytes();

		String publicKey = publicKeyToString((RSAPublicKey) map.get(PUBLIC_KEY));
		String privateKey = privateKeyToString((RSAPrivateKey) map.get(PRIVATE_KEY));

		System.out.println("生成的公钥Java--> " + publicKey);
		System.out.println("生成的公钥.Net--> " + getRSAPublicKeyAsNetFormat(publicKey));
		System.out.println("生成的私钥Java--> " + privateKey);
		System.out.println("生成的私钥.Net--> " + getRSAPrivateKeyAsNetFormat(privateKey));

		String dataText = "这是需要加密的数据,RSA加密明文长度为117字节 所以我这里采用了分段加密和分段解密,所以这个很长也无所谓哦";

		System.out.println("加密前--> " + dataText);

		String ciphertext = RSAEncode(getPublicKey(publicKey), dataText);

		System.out.println("加密后--> " + ciphertext);

		System.out.println("解密后--> " + RSADecode(getPrivateKey(privateKey), ciphertext));
	}
}