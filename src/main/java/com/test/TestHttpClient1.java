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



public class TestHttpClient1 {
	public static final String str="[{\"IOM_Version\":\"Web3.0\"},{\"organType\":\"0\",\"serviceId\":\"zzz125ee5c44c3b5c63ebf877wwwww\",\"searchValue\":\"\"}]";
public static void main(String[] args) throws Exception {
	long start=System.currentTimeMillis();
	String result="";
	for (int i = 0; i < 3;i++) {
		String iomServer="http://192.168.4.54:8090/Gnet_Ibd_Iom/services/IOM_CommonRest/";
//		String iomServer="http://114.112.90.40:9030/Gnet_Ibd_Iom/services/IOM_CommonRest/";
//		 result=HttpUtil.sendPostRequest(iomServer+"IOM_Organ_GetOrganTreeByOrganType", str,false,null,null);
		System.out.println(sendPost("http://192.168.4.54:8090/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetOrganTreeByOrganType",str,false));
//		System.out.println(result);
//		asyncSendPost("http://192.168.1.196:8080/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetOrganTreeByOrganType",str);
	}
	long end=System.currentTimeMillis();
	System.out.println((end-start)+"毫秒");
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
            HttpEntity requestBodies = new StringEntity(str,Consts.UTF_8);
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


public static String asyncSendPost(String address, String data)throws Exception {
		final URL url = new URL(address);
		String result = "";
		final HttpProcessor httpproc = HttpProcessorBuilder.create()
				.add(new RequestContent()).add(new RequestTargetHost())
				.add(new RequestConnControl())
				.add(new RequestExpectContinue(true)).build();
		final HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
		final BasicConnPool pool = new BasicConnPool(new BasicConnFactory());
		pool.setDefaultMaxPerRoute(2);
		pool.setMaxTotal(2);
		HttpHost targets = new HttpHost(url.getHost(), url.getPort());
		ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;
		Future<BasicPoolEntry> future = pool.lease(targets, null);
		boolean reusable = false;
		BasicPoolEntry entry = future.get();
		HttpEntity[] requestBodies = { new StringEntity(str, Consts.UTF_8), };
		HttpClientConnection conn = entry.getConnection();
		HttpCoreContext coreContext = HttpCoreContext.create();
		coreContext.setTargetHost(targets);
		BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST", url.getPath());
		request.setEntity(requestBodies[0]);
		System.out.println(">> Request URI: "+ request.getRequestLine().getUri());
		httpexecutor.preProcess(request, httpproc, coreContext);
		HttpResponse response = httpexecutor.execute(request, conn, coreContext);
		httpexecutor.postProcess(response, httpproc, coreContext);
		System.out.println("<< Response: " + response.getStatusLine());
		result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		reusable = connStrategy.keepAlive(response, coreContext);
		if (reusable) {
            System.out.println("Connection kept alive...");
        }
        pool.release(entry, reusable);
		return result;
	}
}
