package rest;

import java.io.BufferedWriter;
import java.io.IOException;

public class OutputHandler {

    BufferedWriter writer;

    public OutputHandler(BufferedWriter writer){
        this.writer = writer;
    }

    public void response(Message message, MessageCollection messageCollection) {
        //Ressource in Message wird gesplittet, damit ermittelt wird, was gemacht werden muss
        String[] segments = message.getResource().split("/");

        String messageText = "";

        int nr = 0;
        if ((segments.length >=2) && segments[1].equals("messages")  ){
            if (segments.length == 3) {
                nr = Integer.parseInt(segments[2]);
            }

            if(message.getVerb().equals("POST")) {
                messageText = messageCollection.addMessage(message.getPayload());
            }
            else if(message.getVerb().equals("GET")) {
                if (nr > 0) {
                    messageText = messageCollection.specificMessage(nr);
                } else {
                    messageText = messageCollection.allMessages();
                }
            }
            else if(message.getVerb().equals("DELETE")) {
                messageText = messageCollection.deleteMessage(nr);
            }
            else if(message.getVerb().equals("PUT")) {
                messageText = messageCollection.updateMessage(nr, message.getPayload());
            }
        } else {
            messageText = "Error Message";
        }

        //Gibt er dem Client zurück
        String output = message.getVersion() + " 200 OK\n"+
                    "Server: MailServer\n"+
                    "Content-Type: text/html\n"+
                    "Content-Length: "+messageText.length()+"\n\n";
        output += messageText;

        try {
            writer.write(output);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Gibt die Informationen der Verbindung in der Console aus
        System.out.println("HTTP-Version: "+message.getVersion()+"\n" +
                "HTTP-Verb: "+message.getVerb()+"\n"+
                "Resource: "+message.getResource()+"\n"+
                "Payload: "+message.getPayload()+"\n"+
                "Message-Length: "+message.getPayload().length());
    }
}
