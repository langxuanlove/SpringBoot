package com.test;


import java.net.Socket;
import java.net.URL;
import java.util.concurrent.Future;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Consts;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.pool.BasicConnFactory;
import org.apache.http.impl.pool.BasicConnPool;
import org.apache.http.impl.pool.BasicPoolEntry;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class TestEvent {

	//事件注册
//	@Test
	public void registerEvent(){
		String url="http://localhost:8000/GnetIBUS/eventMq/registerEvent/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":" +
				"{\"IBUS\":{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD123\",\"CLOUD_NAME\":\"木叶集团\",\"PASSWORD\":\"123456\",\"ENCRYPTION\":\"0\",\"SUMMARY\":\"第一次出书啊\",\"EVENT_INFO\":[{\"EVENT_NAME\":\"demo_2016_09_27\",\"EVENT_TYPE\":\"Queue\",\"EVENT_DESC\":\"队列模式\"},{\"EVENT_NAME\":\"demo12_2016_09_27\",\"EVENT_TYPE\":\"Topic\",\"EVENT_DESC\":\"主题模式\"}]}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
//			resabc1=sendPost(url, str, false);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Test //发送事件消息
	public void sendEventMsg(){
		String url="http://localhost:8000/GnetIBUS/eventMq/sendEventMsg/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":" +
				"{\"IBUS\":{\"USER_ID\":\"\",\"CLOUD_ID\":\"IBD123\"," +
				"\"EVENT_NAME\":\"demo12_2016_09_27\",\"SPONSORS_ID\":\"e70f5e02600c40e6bd74d74276aba2a0\",\"EVENT_TYPE\":\"Topic\",\"EVENT_DATA\":\"第一次出书啊\"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	@Test	//事件消息确认接口.
	public void confirmMessageByMsgID(){
		String url="http://localhost:8000/GnetIBUS/eventMq/confirmMessageByMsgID/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":" +
				"{\"IBUS\":{\"MSG_ID\":\"123\",\"CLOUD_ID\":\"IBD123\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//事件订阅
//	@Test
	public void registerSubscriber(){
		String url="http://localhost:8000/GnetIBUS/eventMq/registerSubscriber/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\",\"USER_NAME\":\"大野木\",\"CLOUD_NAME\":\"凯英\"," +
				"\"SUB_EVENT_INFO\":[{\"EVENT_ID\":\"123\",\"EVENT_NAME\":\"demo\",\"EVENT_TYPE\":\"Queue\",\"EVENT_DESC\":\"队列模式\"},{\"EVENT_ID\":\"1234\",\"EVENT_NAME\":\"demo12\",\"EVENT_TYPE\":\"Topic\",\"EVENT_DESC\":\"主题模式\"}]}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
//			resabc1=sendPost(url, str, false);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	检验发布者信息是否存在
//	@Test
	public void checkPublisherInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/checkPublisherInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 我的所有发布事件，按条件获取事件信息
//	@Test
	public void getEventInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/getEventInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200\",\"CLOUD_ID\":\"IBD104\",\"KEY\":\"\",\"INDEX\":\"0\",\"PAGE_SIZE\":\"2\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 按获取所有的事件信息
		@Test // TYPE=0按发布者搜索，type=1按事件名称进行搜索
		public void getAllEventInfo(){
//			String url="http://localhost:8000/GnetIBUS/eventMq/getAllEventInfo/";
			String url="http://114.112.90.41:10032/GnetIBUS/eventMq/getAllEventInfo/";
			String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
					"{\"USER_ID\":\"200\",\"CLOUD_ID\":\"IBD104\",\"KEY\":\"水门\",\"TYPE\":\"0\",\"INDEX\":\"0\",\"PAGE_SIZE\":\"10\"" +
					"}}}";
			// 
			String resabc1;
			try {
				resabc1 = HttpRequestor.getInstance().doPost(url, str);
				System.out.println(resabc1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
//	@Test
	// 更新发布者信息
	public void updatePublisherInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/updatePublisherInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\",\"SUMMARY\":\"11更新事件发布者信息.\",\"ENCRYPTION\":\"1\",\"PASSWORD\":\"654321\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void updateEventInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/updateEventInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD101\",\"EVENT_INFO\":[{\"EVENT_NAME\":\"刷机测试\",\"EVENT_ID\":\"4a2128a7e59641e5b214bacf186fe765\"}]" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void deleteEventInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/deleteEventInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200869\",\"CLOUD_ID\":\"IBD104\",\"EVENT_ID\":\"dc8c07300b3f4001a267f87d5b1618a1\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	getEventSubInfo
//	@Test
	public void getEventSubInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/getEventSubInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200869\",\"CLOUD_ID\":\"IBD104\",\"EVENT_ID\":\"4a2128a7e59641e5b214bacf186fe765\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test //取消订阅的事件
	public void deleteSubInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/deleteSubInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200869\",\"CLOUD_ID\":\"IBD102\",\"EVENT_ID\":\"4a2128a7e59641e5b214bacf186fe765\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test //更新订阅者基本信息
	public void updateSubscriberInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/updateSubscriberInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\",\"SUMMARY\":\"更新订阅者的测试\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	@Test //获取发布者的基本信息
	public void getPublishInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/getPublishInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test //获取订阅者的基本信息
	public void getSubscriberInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/getSubscriberInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void res(){
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost("http://localhost:8000/GnetIBUS/eventMq/eventInfo","");
//			resabc1=sendPost(url, str, false);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	@Test
	public void json_zuqiu(){
		try {
			String resabc1 = HttpRequestor.getInstance().doPost("http://qball.me/cgi-bin/league_find","");
//			resabc1=sendPost(url, str, false);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Test  // 获取我的所有订阅事件
	public void getSubInfo(){
		String url="http://localhost:8000/GnetIBUS/eventMq/getSubInfo/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\",\"KEY\":\"bb\",\"INDEX\":\"0\",\"PAGE_SIZE\":\"5\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test //更新配置文件
	public void getUpdateProperties(){
		String url="http://localhost:8000/GnetIBUS/eventMq/getUpdateProperties/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	@Test //验证事件名称是否存在
	public void checkEventName(){
		String url="http://localhost:8000/GnetIBUS/eventMq/checkEventName/";
		String str="psJson={\"GMSG_HEAD\":{\"Version\":\"1.0\",\"DeviceType\":\"PC\",\"DriveID\":\"fasfewqiuy66ewq\",\"GroupID\":\"IBD104\",\"ETP_ID\":\"hger5465qsd\",\"SNID\":\"20120309195611068001\",\"Sign\":\"N_key\",\"Command\":\"S018\"},\"GMSG_IBD\":{\"IBUS\":" +
				"{\"USER_ID\":\"200866\",\"CLOUD_ID\":\"IBD104\",\"EVENT_NAME\":\"demo133\"" +
				"}}}";
		String resabc1;
		try {
			resabc1 = HttpRequestor.getInstance().doPost(url, str);
			System.out.println(resabc1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static String sendPost(String url,String data,boolean flag) throws Exception{
		URL address=new URL(url);
		String result="";
	    HttpProcessor httpproc = HttpProcessorBuilder.create()
	            .add(new RequestContent())
	            .add(new RequestTargetHost())
	            .add(new RequestConnControl())
	            .add(new RequestExpectContinue(true)).build();
	        HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
	        HttpCoreContext coreContext = HttpCoreContext.create();
	        HttpHost host = new HttpHost(address.getHost(), address.getPort());
	        coreContext.setTargetHost(host);
	        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
	        ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;
	        try {
	            HttpEntity requestBodies = new StringEntity(data,Consts.UTF_8);
	                if (!conn.isOpen()) {
	                    Socket socket = new Socket(host.getHostName(), host.getPort());
	                    socket.setKeepAlive(true);
	                    conn.bind(socket);
	                }
	                BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST",address.getPath());
	                request.setEntity(requestBodies);
	                System.out.println(">> Request URI: " + request.getRequestLine().getUri());
	                httpexecutor.preProcess(request, httpproc, coreContext);
	                HttpResponse response = httpexecutor.execute(request, conn, coreContext);
	                httpexecutor.postProcess(response, httpproc, coreContext);
	                System.out.println("<< Response: " + response.getStatusLine());
	                result=EntityUtils.toString(response.getEntity(),"UTF-8");
	                if (!connStrategy.keepAlive(response, coreContext)) {
	                    conn.close();
	                } else {
	                    System.out.println("Connection kept alive...");
	                }
	        } finally {
	            conn.close();
	        }
	        return result;
	    }

}
