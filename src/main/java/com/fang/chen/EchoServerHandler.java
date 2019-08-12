package com.fang.chen;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

@ChannelHandler.Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    int i = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

        ByteBuf buf = msg.content();
//        String response = buf.toString(CharsetUtil.UTF_8);

//        System.out.println("Server received message " + response);

        ctx.write(new DatagramPacket(buf.copy(), msg.sender()));

//        byte[] bytes = new byte[buf.readableBytes()];
//        int readerIndex = buf.readerIndex();
//        buf.getBytes(readerIndex, bytes);

//        System.out.println(Arrays.toString(bytes));
//        if (i == 0) {
//            System.out.println("Client port: " + msg.sender().getPort());
//            i++;
//        }


//        System.out.println("Server sending response " + response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
        ctx.close();
    }
}
