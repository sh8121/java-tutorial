package com.sh8121.javatutorial.javaio.v1_nio_basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class AppNonBlockingMode {

    public static void main(String[] args) throws IOException, InterruptedException {
        var socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
        socketChannel.configureBlocking(false);
        var writeByteBuffer = StandardCharsets.UTF_8.encode("GET /hello?name=A HTTP/1.0\r\n\r\n");
        socketChannel.write(writeByteBuffer);

        var readByteBuffer = ByteBuffer.allocate(64);
        var stringBuilder = new StringBuilder();

        while (true) {
            System.out.printf("Before Read At %s\n", LocalDateTime.now());
            int bytes = socketChannel.read(readByteBuffer);
            System.out.printf("Read %d Bytes\n", bytes);
            System.out.printf("After Read At %s\n\n", LocalDateTime.now());
            if (bytes == 0) {
                Thread.sleep(200);
                continue;
            } else if (bytes == -1) {
                break;
            }

            readByteBuffer.flip();
            stringBuilder.append(StandardCharsets.UTF_8.decode(readByteBuffer));
            readByteBuffer.clear();
        }

        System.out.println(stringBuilder);
        socketChannel.close();
    }
}
