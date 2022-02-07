package tests.utils;

import io.restassured.response.Response;
import tests.model.object.objectAction.ObjectActionCreator;

import java.io.*;
import java.net.ServerSocket;

public class Socket {
    ServerSocket serverSocket;

    public Socket() {
    }

    public ServerSocket createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        return serverSocket;
    }

    public java.net.Socket socket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public String checkTheOutputOfWebhookCreated(){
        java.net.Socket echoSocket;
        BufferedReader in;
        String outputMessage = null;
        try {
            echoSocket = socket(createServerSocket(8888));
            in =
                    new BufferedReader(
                            new InputStreamReader(echoSocket.getInputStream()));
            char[] buffer = new char[1000];
            int output = in.read(buffer, 0, 1000);
            outputMessage = new String(buffer, 0, output);
            in.close();
            echoSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputMessage;
    }

    public Response postObjectDefinitionObjectAction(ObjectActionCreator objectActionCreator, Integer objectId) {
        
    }
}
