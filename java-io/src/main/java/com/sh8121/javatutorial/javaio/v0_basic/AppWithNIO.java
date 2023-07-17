package com.sh8121.javatutorial.javaio.v0_basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class AppWithNIO {

    public static void main(String[] args) throws IOException {
        var address = new InetSocketAddress("localhost", 8080);
        var socketChannel = SocketChannel.open(address);

        socketChannel.write(StandardCharsets.UTF_8.encode(CharBuffer.wrap("GET /hello?name=A HTTP/1.0\r\n\r\n")));

        var byteBuffer = ByteBuffer.allocate(8192);
        var charsetDecoder = StandardCharsets.UTF_8.newDecoder();
        var charBuffer = CharBuffer.allocate(8192);
        var store = new StringBuilder();

        while (socketChannel.read(byteBuffer) != -1 || byteBuffer.position() > 0) {
            byteBuffer.flip();
            charsetDecoder.decode(byteBuffer, charBuffer, true);
            charBuffer.flip();
            store.append(charBuffer);
            charBuffer.clear();
            byteBuffer.compact();
        }

        System.out.println(store);
    }
}
