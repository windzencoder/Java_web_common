package com.wz.shiro.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class TestEncode {

	@Test
	public void testEncode(){
		//202cb962ac59075b964b07152d234b70
		System.out.println(new Md5Hash("123").toHex());
		//ICy5YqxZB1uWSwcVLSNLcA==
		System.out.println(new Md5Hash("123").toBase64());
	}
	
}
