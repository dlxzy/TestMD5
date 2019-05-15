package suanfa;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESsym {
	public byte[] initKey(){
		try {
		//密钥生成器
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		//初始化密钥生成器
		keyGenerator.init(56);
		//生成密钥
		SecretKey secretKey = keyGenerator.generateKey();
		//密钥字节数组
		return secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		}

		return null;
		}

		/**
		  * @Method: encrypt
		  * @Description: 加密算法
		  * @param data 被加密字节数组
		  * @param key  随机对称密钥
		  * @return
		  * 返回类型：byte[]
		  */
		public byte[] encrypt(byte[] data,byte[] key){
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		Cipher cipher;
		try {
		//Cipher完成加密或解密工作类
		cipher = Cipher.getInstance("DES");
		//对Cipher初始化，加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		//加密data
		return cipher.doFinal(data);
		} catch (Exception e) {
		e.printStackTrace();
		}

		return null;
		}

		/**
		  * @Method: decode
		  * @Description: TODO
		  * @param 被解密的字节数组
		  * @param key 随机对称密钥(和加密密钥务必保持一致?
		  * @return
		  * 返回类型：byte[]
		  */
		public byte[] decode(byte[] data,byte[] key){
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		Cipher cipher;
		try {
		//Cipher完成加密或解密工作类
		cipher = Cipher.getInstance("DES");
		//对Cipher初始化，解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		//解密data
		return cipher.doFinal(data);
		} catch (Exception e) {
		e.printStackTrace();
		}

		return null;
		}
	
}
