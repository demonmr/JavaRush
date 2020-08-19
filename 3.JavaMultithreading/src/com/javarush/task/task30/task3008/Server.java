package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
   private static Map<String, Connection> connectionMap = new ConcurrentHashMap<> ();
    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket=socket;
        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{

          Message message = new Message (MessageType.NAME_REQUEST,"Отправьте имя клиента");
          Message answer=new Message (MessageType.NAME_REQUEST);
          String name=null;

                while (name==null||name.equals ("")||connectionMap.containsKey (name)||!answer.getType ().equals (MessageType.USER_NAME))
                {
                    connection.send (message);
                    answer=connection.receive ();
                    name=answer.getData ();
                }
                        connectionMap.put (name,connection);
                        connection.send (new Message (MessageType.NAME_ACCEPTED));
                        return name;

        }
       private void notifyUsers(Connection connection, String userName) throws IOException{
           for (String name : connectionMap.keySet()) {
               if (!name.equals(userName)) {
                   connection.send(new Message(MessageType.USER_ADDED,  name));
//                    connectionMap.get(name).send(new Message(MessageType.USER_ADDED, "Добавлен новый пользователь с именем " + userName));
               }
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message message =connection.receive ();
                if (message.getType ()==MessageType.TEXT) {
                    String text = message.getData ();
                    Server.sendBroadcastMessage (new Message (MessageType.TEXT, userName + ": " + text));
                } else
                    ConsoleHelper.writeMessage ("Errors");
            }
        }
        public void run()
        {
            ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            try(Connection connection = new Connection(socket)) {
                String username =serverHandshake (connection);
                sendBroadcastMessage(new Message (MessageType.USER_ADDED,username));
                notifyUsers(connection,username);
                serverMainLoop(connection,username);
                connectionMap.remove (username);
                sendBroadcastMessage (new Message (MessageType.USER_REMOVED,username));
            }catch (IOException | ClassNotFoundException e) {
               ConsoleHelper.writeMessage ("Ошибки в программе");
            }

            
                    }
    }

    public static void sendBroadcastMessage(Message message) throws IOException {
        for (HashMap.Entry<String,Connection>map:connectionMap.entrySet ()) {
            try {
                map.getValue ().send (message);
            }catch (IOException e)
            {
                System.out.println (e.getMessage ());
            }
        }
    }


    public static void main (String[] args) {

        int port = ConsoleHelper.readInt ();
        ServerSocket serverSocket=null;
        try  {
            serverSocket = new ServerSocket (port);
            System.out.println ("Соединение установлено");
            while (true)
            {
                new Handler (serverSocket.accept ()).start ();

            }
        } catch (IOException e) {
            try {
                serverSocket.close ();
            } catch (IOException ex) {
                System.out.println (e.getMessage ());
            }
            e.printStackTrace ();
        }



    }

}
