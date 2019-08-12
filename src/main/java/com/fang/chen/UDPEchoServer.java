package com.fang.chen;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class UDPEchoServer {

    public static void main(String[] args) throws Exception {

        new UDPEchoServer().start();

    }

    public void start() throws Exception {
        final EchoServerHandler handler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channel(NioDatagramChannel.class)
                    .localAddress(new InetSocketAddress(12345))
                    .handler(new ChannelInitializer<DatagramChannel>() {
                        @Override
                        protected void initChannel(DatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    })
//                    .option(ChannelOption.SO_RCVBUF, 1024)
//                    .option(ChannelOption.SO_SNDBUF, 1024)
                    ;
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }


    }

}
