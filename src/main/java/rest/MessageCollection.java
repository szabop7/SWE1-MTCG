package rest;

import java.util.ArrayList;
import java.util.List;

public class MessageCollection
{
    private static MessageCollection m_collection = null;

    //statische Variable, da die Collection für alle Instanzen gleichbleibt
    private static ArrayList<String> messages = new ArrayList<>();

    //kreiert eine Instance
    public static MessageCollection getInstance()
    {
        if (m_collection == null) {
            m_collection = new MessageCollection();
        }
        return m_collection;
    }

    public static String updateMessage(int nr, String s){
        if (nr-1 < messages.size() && nr > 0){
            messages.set(nr-1,s);
            return "Nachricht wurde aktualisiert!";
        }
        return "Mit der ID "+nr+" wurde keine Nachricht gefunden!";
    }

    public static String specificMessage(int nr){
        if (nr-1 < messages.size() && nr > 0){
            return "Nachricht "+(nr)+": "+messages.get(nr-1);
        }
        return "Mit der ID "+nr+" wurde keine Nachricht gefunden!";
    }

    public static String addMessage(String s){
        if(s.equals(""))
            return "Nachricht darf nicht leer sein!";
        messages.add(s);
        return ""+messages.size();
    }

    public static String allMessages(){
        if (messages.size() <= 0){
            return "Keine Nachrichten vorhanden";
        }
        String msg = "";
        for (int i = 0; i < messages.size(); i++){
            msg += "Nachricht "+(i+1)+": "+messages.get(i);
            msg += "\n";

        }
        return msg;
    }


    public static String deleteMessage(int nr){
        if (nr-1 < messages.size() && nr > 0){
            messages.remove(nr-1);
            return "Nachricht wurde gelöscht!";
        }
        return "Mit der ID "+nr+" wurde keine Nachricht gefunden!";
    }
}
