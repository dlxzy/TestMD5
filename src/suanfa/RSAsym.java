package suanfa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAsym {
	public static final String KEY_ALGORITHM = "RSA";
	/** 貌似默认是RSA/NONE/PKCS1Padding，未验证 */
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";


	/** RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024 */
	public static final int KEY_SIZE = 1024;


	public static final String PLAIN_TEXT = "hello world!";


	/**
	* @Method: generateKeyBytes
	* @Description: 首先产生一个公钥和私钥对，使用HashMap保存起来
	* @return 返回类型：Map<String,byte[]>
	*/
	public static Map<String, byte[]> generateKeyBytes() {


	try {
	// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
	KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
	// 初始化密钥对生成器，密钥大小为KEY_SIZE位  
	keyPairGenerator.initialize(KEY_SIZE);
	// 生成一个密钥对，保存在keyPair中 
	KeyPair keyPair = keyPairGenerator.generateKeyPair();

	// 得到公钥  
	RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
	// 得到私钥  
	RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
	//使用HashMap保存起来
	Map<String, byte[]> keyMap = new HashMap<String, byte[]>();
	keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
	keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
	return keyMap;
	} catch (NoSuchAlgorithmException e) {
	e.printStackTrace();
	}
	return null;
	}


	/**
	* 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
	* 
	* @param keyBytes
	* @return
	*/
	public static PublicKey restorePublicKey(byte[] keyBytes) {
	X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

	try {
	KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
	PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
	return publicKey;
	} catch (Exception e) {
	e.printStackTrace();
	}
	return null;
	}


	/**
	* 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
	* 
	* @param keyBytes
	* @return
	*/
	public static PrivateKey restorePrivateKey(byte[] keyBytes) {
	PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
	try {
	KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
	PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
	return privateKey;
	} catch (Exception e) {
	e.printStackTrace();
	}
	return null;
	}


	/**
	* 加密，三步走。
	* 
	* @param key
	* @param plainText
	* @return
	*/
	public static byte[] RSAEncode(PublicKey key, byte[] plainText) {

	try {
	Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
	cipher.init(Cipher.ENCRYPT_MODE, key);
	return cipher.doFinal(plainText);
	} catch (Exception e) {
	e.printStackTrace();
	}

	return null;
	}

	/**
	* 解密，三步走。
	* 
	* @param key
	* @param plainText
	* @return
	*/
	public static String RSADecode(PrivateKey key, byte[] encodedText) {

	try {
	Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
	cipher.init(Cipher.DECRYPT_MODE, key);
	return new String(cipher.doFinal(encodedText));
	} catch (Exception e) {
	e.printStackTrace();
	}
	return null;
	}

}
