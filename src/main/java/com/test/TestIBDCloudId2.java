package com.test;


import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestIBDCloudId2 {
  public static StringBuilder builder=new StringBuilder(20);
 
  
  @Test
  public  void Test() throws Exception {
	String str= "{\"GII_HEAD\":{\"GZIP\":\"false\",\"SIGN\":[{\"POSITION/k\":\"\",\"RANDOMCODE/v\":\"\"}],\"SNID\":\"2015070311352418\",\"VERSION\":\"PC1.0\"},\"GII_IBD\":{\"ACTION\":\"DETAIL\",\"ISCOUNT\":\"1\",\"PAGEINDEX\":\"1\",\"PAGESIZE\":\"10\",\"REQUEST\":{\"APPLYMODE\":\"\",\"CLOUD_ID\":\"\",\"CLOUD_TYPE\":\"\",\"INDUSTRY_ID\":\"\",\"KEYWORDS\":\"\",\"MANAGER_ID\":\"\",\"STATUS\":\"0\",\"USER_ID\":\"\"}}}";
	long start=System.currentTimeMillis();
	for (int i = 0; i < 1; i++) {
//	String res=HttpRequestor.getInstance().doPost("http://192.168.1.196:8090/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_CLOUD_GetCloudBySearch", str);
	sendPost("http://localhost:9000/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_CLOUD_GetCloudBySearch",str,false);
//	JSONObject object=JSONObject.parseObject(res);
//	System.out.println(object.getString("GII_IBD"));
//	JSONArray array=JSONArray.parseArray(JSONObject.parseObject(object.getString("GII_IBD")).getString("INFOLIST"));
//	builder.delete(0, builder.length());
//	if(array.size()>0){
//		JSONObject obj=JSONObject.parseObject(array.getString(0));
//		System.out.println(builder.append(obj.getString("STATION_IP")).append(":").append(obj.getString("OUT_PORT")).toString());
//	}
	}
	long end=System.currentTimeMillis();
	System.out.println((end-start)+"毫秒");
	
}
//   @Test
   public  void test1() {
	String str= "{ \"GII_HEAD\":{ 	\"VERSION\":\"PC3.0\", 	" +
			"\"SNID\":\"20140113140645068\", 	" +
			"\"SIGN\":[  {  	\"POSITION/k\":\"\"  }, " +
			" {  	\"RANDOMCODE/v\":\"\"  } 	], " +
			"	\"CLOUD_ID\":\"1112\", 	\"GZIP\":\"false\" }, " +
			"\"GII_IBD\":{ 	\"REQUEST\":{  \"USERID\":\"\", 	\"CLOUDID\":\"\", " +
			"	\"APPRELATION\":\"\",\"MODULERELATION\":\"\", 	\"RESOURCE_TYPE\":\"\", " +
			"	\"TYPE\":\"\",\"Pid\":\"\", 	\"IsDownload\":\"\"}}}";
	JSONObject reqCloudId=JSONObject.parseObject(JSONObject.parseObject(str).getString("GII_HEAD"));
	System.out.println(reqCloudId.getString("CLOUD_ID"));
}
//@Test
public  void getAppTypeList() throws Exception{
	long start=System.currentTimeMillis();
	String cloudId="zzz125ee5c44c3b5c63ebf877wwwww";
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String result ="";
	String nameSpace = PropertiesUtil.getKeyValue("ENCRYPT_NAMESPACE_IBD");
	String url ="http://192.168.1.196:8080/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_APP_GetAppTypeByCloudId";
	String str="{\"GII_HEAD\":{\"VERSION\":\"PC2.0\",\"SNID\":\""+format.format(new Date())+"\",\"SIGN\":[{\"k\":\"\"},{\"v\":\"\"}],\"GZIP\":\"true\"," +
			"\"CLOUD_ID\":\""+cloudId+"\"},\"GII_IBD\": {\"REQUEST\": {\"CLOUDID\":\""+cloudId+"\"}}}";
	result = HttpRequestor.getInstance().doPost(url, str);
	long end=System.currentTimeMillis();
	System.out.println((end-start)+"毫秒");
	System.out.println(result);
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

public static String sendPost(String url,String data,boolean flag) throws Exception{
	URL address=new URL(url);
	String result="";
    HttpProcessor httpproc = HttpProcessorBuilder.create()
            .add(new RequestContent())
            .add(new RequestTargetHost())
            .add(new RequestConnControl())
            // 此处就是100-continue的验证的地方，http请求会分两次发送出去。确定客户端是不是接收此消息。
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
                result=EntityUtils.toString(response.getEntity());
                System.out.println(result);
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
