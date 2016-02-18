package com.zhaoxiaodan.mirserver.core.network.encoder;

import com.zhaoxiaodan.mirserver.core.network.Request;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.ByteOrder;
import java.util.List;

/**
 * Created by liangwei on 16/2/17.
 */
public class RequestEncoder extends MessageToMessageEncoder<Request> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Request msg, List<Object> out) throws Exception {

		ByteBuf buf = Unpooled.buffer().order(ByteOrder.LITTLE_ENDIAN);

		buf.writeByte(msg.cmdIndex + '0');
		buf.writeInt(msg.header.recog);
		buf.writeShort(msg.header.type);
		buf.writeShort(msg.header.p1);
		buf.writeShort(msg.header.p2);
		buf.writeShort(msg.header.p3);
		if (msg.body != null && !msg.body.isEmpty()) {
			buf.writeBytes(msg.body.getBytes());
			buf.writeByte('\0');
		}

		out.add(buf);
	}
}
