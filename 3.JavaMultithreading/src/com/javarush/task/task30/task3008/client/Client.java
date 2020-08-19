package com.javarush.task.task30.task3008.client;


import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        new Client ().run ();

    }

    protected Connection connection;
    private volatile boolean clientConnected;

    public synchronized void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            wait();
            if (clientConnected) {
                ConsoleHelper.writeMessage("Соединение установлено.\n" +
                        "Для выхода наберите команду 'exit'.");
            } else {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
            while (clientConnected) {
                String message = ConsoleHelper.readString();
                if ("exit".equals(message)) {
                    break;
                }
                if (shouldSendTextFromConsole()) {
                    sendTextMessage(message);
                }
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Error during SocketThread execution " + e);
        }
    }


    protected String getServerAddress() {
//        final Pattern validIpV4 = Pattern.compile("^(?:(?:^|\\.)(?:2(?:5[0-5]|[0-4]\\d)|1?\\d?\\d)){4}$|localhost");
//        ConsoleHelper.writeMessage("Enter server address");
//        String serverAddress;
//        do {
//            serverAddress = ConsoleHelper.readString();
//        } while (!serverAddress.matches(validIpV4.toString()));
//        return serverAddress;
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {

//        ConsoleHelper.writeMessage("Enter port number");
//        int portNumber;
//        do {
//            portNumber = ConsoleHelper.readInt();
//        } while (portNumber < 0 || portNumber > 65535);
//        return portNumber;
        return ConsoleHelper.readInt();

    }

    protected String getUserName() {
//        ConsoleHelper.writeMessage("Enter user name");
//        String userName;
//        userName = ConsoleHelper.readString();
//        return userName;
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            System.err.println("Error during sending message" + e);
            clientConnected = false;
        }
    }


    public class SocketThread extends Thread {
        @Override
        public void run () {
            try {
          String address =  getServerAddress();
            int port = getServerPort ();

                Socket socket = new Socket (address,port);
                Connection connection = new Connection (socket);
                Client.this.connection = connection;
                this.clientHandshake ();
                this.clientMainLoop ();
            } catch (IOException| ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message serverResponse = connection.receive();
                if (serverResponse == null || serverResponse.getType() == null) {
                    throw new IOException("Unexpected MessageType");
                }
                MessageType messageType = serverResponse.getType();
                switch (messageType) {
                    case NAME_REQUEST: {
                        Message userNameResponse = new Message(MessageType.USER_NAME, getUserName());
                        connection.send(userNameResponse);
                        break;
                    }
                    case NAME_ACCEPTED: {
                        notifyConnectionStatusChanged(true);
                        return;
                    }
                    default: {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message serverResponse = connection.receive();
                if (serverResponse == null || serverResponse.getType() == null) {
                    throw new IOException("Unexpected MessageType");
                }
                MessageType messageType = serverResponse.getType();
                switch (serverResponse.getType()) {
                    case TEXT: {
                        processIncomingMessage(serverResponse.getData());
                        break;
                    }
                    case USER_ADDED: {
                        informAboutAddingNewUser(serverResponse.getData());
                        break;
                    }
                    case USER_REMOVED: {
                        informAboutDeletingNewUser(serverResponse.getData());
                        break;
                    }
                    default: {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }


        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " connected");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " has left");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
    }
}
