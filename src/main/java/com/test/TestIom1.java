package com.test;

import java.util.Date;

import org.apache.axis.transport.http.CommonsHTTPSender;

public class TestIom1 {

//	@org.junit.Test
	public void demo() throws Exception{
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "IBD104" + "\"}]";
			String _sReturn = AxisUtil.callService("http://127.0.0.1:9000/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl", "http://tempuri.org/",
				"IOM_Organ_GetOrganTreeByOrganType", "JsonString", str);
			System.out.println(str);
			System.out.println(new Date());
			System.out.println(_sReturn);
			System.out.println(new Date());
	}
//	@org.junit.Test
	public void demo1() throws Exception{
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "zzz125ee5c44c3b5c63ebf877wwwww" + "\"}]";
			String _sReturn = AxisUtil.callService("http://114.112.90.40:9506/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl", "http://tempuri.org/",
				"IOM_Organ_GetOrganTreeByOrganType", "JsonString", str);
			System.out.println(str);
			System.out.println(new Date());
			System.out.println(_sReturn);
			System.out.println(new Date());
	}
//	@org.junit.Test
	public void demo2() throws Exception{
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "IBD104" + "\"}]";
		String url="http://localhost:9000/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetOrganTreeByOrganType";
		String res=HttpRequestor.getInstance().doPost(url, str);
		System.out.println(res);
	}
	@org.junit.Test
	public void ibd() throws Exception{
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "IBD104" + "\"}]";
		String url="http://localhost:10000/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetOrganTreeByOrganType";
		TestIBDCloudId2.sendPost(url,str,false);
	}
//	@org.junit.Test
	public void cxfTest(){
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "zzz125ee5c44c3b5c63ebf877wwwww" + "\"}]";
		String url="http://www.gnetcloud.com/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl";
		CxfUtil.callService(url, "IOM_Organ_GetOrganTreeByOrganType", new Object[]{str});
	}
//	@org.junit.Test
	public void cxfTest1() {
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "IBD104" + "\"}]";
		String url="http://114.112.90.40:9360/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl";
		String res=CxfUtil.callService(url, "IOM_Organ_GetOrganTreeByOrganType", new Object[]{str});
		System.out.println(res);
	}
//	@org.junit.Test
	public void cxfTestLocal() {
		String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \""
				+ "IBD104" + "\"}]";
		String url="http://localhost:9000/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl";
		String res=CxfUtil.callService(url, "IOM_Organ_GetOrganTreeByOrganType", new Object[]{str});
		System.out.println(res);
	}
}
