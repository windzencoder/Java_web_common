package com.wz.shiro.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class TestEncode {

	@Test
	public void testEncode(){
		System.out.println(new Md5Hash("123").toHex());
		System.out.println(new Md5Hash("123").toBase64());
	}
	
}
