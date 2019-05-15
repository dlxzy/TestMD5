package test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import suanfa.RSAsym;
import sun.misc.BASE64Encoder;

public class RSAsymTry {
	public static void main(String[] args){
		Map<String, byte[]> keyMap = RSAsym.generateKeyBytes();

		//还原公钥
		PublicKey publicKey = RSAsym.restorePublicKey(keyMap.get(RSAsym.PUBLIC_KEY));
		//加密
		byte[] encodedText = RSAsym.RSAEncode(publicKey, RSAsym.PLAIN_TEXT.getBytes());
		String rsaString = new BASE64Encoder().encode(encodedText);
		System.out.println("RSA encoded: " + rsaString);
		//还原私钥
		PrivateKey privateKey = RSAsym.restorePrivateKey(keyMap.get(RSAsym.PRIVATE_KEY));
		//解密刷出
		System.out.println("RSA decoded: "+ RSAsym.RSADecode(privateKey, encodedText));

		}

}
