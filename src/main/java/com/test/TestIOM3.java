package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestIOM3 {
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		String resabc1="";
//		for (int i = 0; i < 1; i++) {
			String url ="http://192.168.1.196:8100/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetOrganTreeByOrganType";
			String str="[{\"IOM_Version\":\"Web3.0\"},{\"organType\":\"0\",\"serviceId\":\"zzz125ee5c44c3b5c63ebf877wwwww\",\"searchValue\":\"\"}]";
			System.out.println(str.getBytes().length);
			resabc1=HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
			JSONArray object=JSONArray.parseArray(str);
			System.out.println(object);
			JSONObject jsonObject=JSONObject.parseObject(object.get(1).toString());
			System.out.println(jsonObject.get("serviceId"));
//		}
		long end=System.currentTimeMillis();
		System.out.println((end-start)+"毫秒");
	}

	@org.junit.Test
	public void IomTree() throws Exception{
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "IBD104" + "\"}]";
		String url="http://localhost:9000/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetOrganTreeByOrganType";
		String res=HttpRequestor.getInstance().doPost(url, str);
		System.out.println(res);
	}
}
