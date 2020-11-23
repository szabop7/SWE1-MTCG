import java.io.BufferedReader;
import java.io.IOException;

public class MessageProcesser {

    private BufferedReader b_reader;

    private Message message = null;

    public MessageProcesser(BufferedReader b_reader){ this.b_reader = b_reader; }

    public Message process() {
        message = null;
        try {
            //Erstellen des Headers
            message = readHeader(b_reader);
            //Erstellen des Payloads
            if (message != null){
                String payload="";
                int contentLength = message.getContentLength();

                StringBuilder builder = new StringBuilder(10000);
                //Nachricht wird Stelle für Stelle durchgegangen und die Nachricht dann zusammengefügt
                for (int i = 0; b_reader.ready()==true; i++) {

                    builder.append((char)b_reader.read());

                    if( i >= contentLength )
                    {
                        break;
                    }
                }
                //Zusammengefügter String wird mit setPayload übergeben
                message.setPayload(builder.toString());
                return message;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Message readHeader(BufferedReader reader) throws IOException {
        Message message = new Message();
        String input;
        // segments[0]=verb; segments[1]=resource; segments[2]=version
        input = reader.readLine();
        String[] segments = input.split(" ");
        if (segments.length == 3){
            message.setVerb(segments[0]);
            message.setResource(segments[1]);
            message.setVersion(segments[2]);
        } else {
            return null;
        }
        //Weitere Informationen aus dem Header speichern
        while ( (input = reader.readLine()) != null ) {
            //Weiter Informationen vom Header ausgegeben
            System.out.println(input);
            if (input.isBlank())
                break;
            //Informationen werden in einer HashMap gespeichert
            segments = input.split(" ",2);
            if (segments.length == 2) {
                message.addHeaderValues(segments[0].toLowerCase(),segments[1]);
            }
        }
        return message;
    }

}