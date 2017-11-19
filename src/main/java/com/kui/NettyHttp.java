package com.kui;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class NettyHttp {
	    
	    public void start(int port) throws Exception {
	        EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
	                    .childHandler(new ChannelInitializer<SocketChannel>() {
	                                @Override
	                                public void initChannel(SocketChannel ch) throws Exception {
	                                    // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
	                                    ch.pipeline().addLast(new HttpResponseEncoder());
	                                    // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
	                                    ch.pipeline().addLast(new HttpRequestDecoder());
	                                    // 此类针对的100-continue进行了处理，虽然客户端是两次发送请求，一次是请求头，一次是body体，但是
	                                    // 此类已经将两次的内容聚合到一起发给HttpServerInboundHandler了。由于返回的响应的有100和200的
	                                    // 所以返回的时候不能进行聚合返回，个人理解，
//	                                    ch.pipeline().addLast("aggregator", new HttpObjectAggregator(1048576));
	                                    ch.pipeline().addLast(new HttpServerInboundHandler());
	                                }
					}).option(ChannelOption.SO_BACKLOG, 128) 
	                    .childOption(ChannelOption.SO_KEEPALIVE, true);
	            ChannelFuture f = b.bind(port).sync();
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
	        }
	    }

	    public static void main(String[] args) throws Exception {
	    	NettyHttp server = new NettyHttp();
	       System.out.println("Http Server listening on 10000 ...");
	        server.start(10000);
	    }
}
