package com.sh8121.javatutorial.javaio.v0_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class AppWithIO {

    public static void main(String[] args) throws IOException {
        var socket = new Socket("localhost", 8080);
        var outputStream = socket.getOutputStream();
        var printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        printWriter.print("GET /hello?name=A HTTP/1.0\r\n\r\n");
        printWriter.flush();

        var inputStream = socket.getInputStream();
        var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        var store = new StringBuilder();

        for (var line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            store.append(line);
            store.append(System.lineSeparator());
        }

        System.out.println(store);
    }
}
