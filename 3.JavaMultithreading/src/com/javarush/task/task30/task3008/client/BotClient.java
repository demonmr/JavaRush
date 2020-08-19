package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BotClient extends Client {

    protected String getUserName() {

        return String.format ("date_bot_%s",(int)(Math.random ()*100));
    }
    protected SocketThread getSocketThread() {
        return new BotSocketThread ();

    }
    protected boolean shouldSendTextFromConsole() {
        return false;
    }


    public static void main (String[] args) {
      
        new BotClient ().run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop () throws IOException, ClassNotFoundException {
            sendTextMessage ("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop ();
        }

        @Override
        protected void processIncomingMessage (String message) {
            super.processIncomingMessage(message);
            if (message!=null&&message.contains (": ")) {
                SimpleDateFormat format;
                Calendar calendar = Calendar.getInstance ();
                ConsoleHelper.writeMessage (message);
                    String[] messages = message.split (": ");
                    switch (messages[1].trim ()) {
                        case "дата": {
                            format = new SimpleDateFormat ("d.MM.YYYY");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "день": {
                            format = new SimpleDateFormat ("d");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "месяц": {
                            format = new SimpleDateFormat ("MMMM");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "год": {
                            format = new SimpleDateFormat ("YYYY");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "время": {
                            format = new SimpleDateFormat ("H:mm:ss");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "час": {
                            format = new SimpleDateFormat ("H");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "минуты": {
                            format = new SimpleDateFormat ("m");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }
                        case "секунды": {   format = new SimpleDateFormat ("s");
                            sendTextMessage (String.format ("Информация для %s: %s",messages[0],format.format (calendar.getTime ())));
                            break;
                        }

                    }
                }


        }
    }

}
