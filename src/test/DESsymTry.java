package test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import suanfa.DESsym;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESsymTry {
	public static void main(String[] args){
		DESsym desUtils = new DESsym();
		byte[] key = desUtils.initKey();
		System.out.println("key:"+key);
		String originData = "123456sadsadasdas1233455";
		System.out.println("原始数据:"+originData);
		try {
		// 将原始数据转化为字符串
		byte[] arrayOrigin = originData.getBytes("utf-8");
		// 对原始数据进行加密
		byte[] encryption = desUtils.encrypt(arrayOrigin, key);
		// 通过BASE64Encoder转化处理
		String encryptionStr = new BASE64Encoder().encode(encryption);
		System.out.println("经过加密之后的字符串:"+encryptionStr);

		try {
		// 使用BASE64Decoder进行转化处理
		byte[] decoceOrigin = new BASE64Decoder().decodeBuffer(encryptionStr);
		// 使用DES进行解密操作
		byte[] decode = desUtils.decode(decoceOrigin, key);
		// 把字节数组转化为字符串
		String decodeStr = new String(decode,"utf-8");
		System.out.println("对加密字符串进行解密:"+decodeStr);
		} catch (IOException e) {
		e.printStackTrace();
		} 
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		}
		}
	
}
