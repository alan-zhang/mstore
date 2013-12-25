package org.azt.mstore.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String encrypt(String value) {
		char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] b = value.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(b);
			byte[] b2 = md.digest();
			char str[] = new char[b2.length << 1];
			int len = 0;

			for (int i = 0; i < b2.length; i++) {
				byte val = b2[i];
				str[len++] = hexChar[(val >>> 4) & 0xf];
				str[len++] = hexChar[val & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
