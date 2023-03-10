package cn.rg.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	//同一个用户同一个uid
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 生成随机码
	 * @return
	 */
	public static String getCode(){
		return getId();
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());
		System.out.println(getId());


		System.out.println(getCode());
	}
}
