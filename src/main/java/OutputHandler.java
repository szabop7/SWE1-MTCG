import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

public class OutputHandler {

    BufferedWriter writer;
    String username="";
    User u=new User();

    public OutputHandler(BufferedWriter writer){
        this.writer = writer;
    }

    public void response(Message message, MessageCollection messageCollection) {
        //Ressource in Message wird gesplittet, damit ermittelt wird, was gemacht werden muss
        String[] segments = message.getResource().split("/");
        String messageText = "";

        //Gibt er dem Client zurück
        String output = message.getVersion() + " 200 OK\n"+
                "Server: MailServer\n"+
                "Conuhvuvuvuvztent-Type: text/html\n"+
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



        if(message.getVerb().equals("POST")) {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Connection conn= new Connection() {
                @Override
                public <T> T unwrap(Class<T> iface) throws SQLException {
                    return null;
                }

                @Override
                public boolean isWrapperFor(Class<?> iface) throws SQLException {
                    return false;
                }

                @Override
                public Statement createStatement() throws SQLException {
                    return null;
                }

                @Override
                public PreparedStatement prepareStatement(String sql) throws SQLException {
                    return null;
                }

                @Override
                public CallableStatement prepareCall(String sql) throws SQLException {
                    return null;
                }

                @Override
                public String nativeSQL(String sql) throws SQLException {
                    return null;
                }

                @Override
                public void setAutoCommit(boolean autoCommit) throws SQLException {

                }

                @Override
                public boolean getAutoCommit() throws SQLException {
                    return false;
                }

                @Override
                public void commit() throws SQLException {

                }

                @Override
                public void rollback() throws SQLException {

                }

                @Override
                public void close() throws SQLException {

                }

                @Override
                public boolean isClosed() throws SQLException {
                    return false;
                }

                @Override
                public DatabaseMetaData getMetaData() throws SQLException {
                    return null;
                }

                @Override
                public void setReadOnly(boolean readOnly) throws SQLException {

                }

                @Override
                public boolean isReadOnly() throws SQLException {
                    return false;
                }

                @Override
                public void setCatalog(String catalog) throws SQLException {

                }

                @Override
                public String getCatalog() throws SQLException {
                    return null;
                }

                @Override
                public void setTransactionIsolation(int level) throws SQLException {

                }

                @Override
                public int getTransactionIsolation() throws SQLException {
                    return 0;
                }

                @Override
                public SQLWarning getWarnings() throws SQLException {
                    return null;
                }

                @Override
                public void clearWarnings() throws SQLException {

                }

                @Override
                public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
                    return null;
                }

                @Override
                public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
                    return null;
                }

                @Override
                public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
                    return null;
                }

                @Override
                public Map<String, Class<?>> getTypeMap() throws SQLException {
                    return null;
                }

                @Override
                public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

                }

                @Override
                public void setHoldability(int holdability) throws SQLException {

                }

                @Override
                public int getHoldability() throws SQLException {
                    return 0;
                }

                @Override
                public Savepoint setSavepoint() throws SQLException {
                    return null;
                }

                @Override
                public Savepoint setSavepoint(String name) throws SQLException {
                    return null;
                }

                @Override
                public void rollback(Savepoint savepoint) throws SQLException {

                }

                @Override
                public void releaseSavepoint(Savepoint savepoint) throws SQLException {

                }

                @Override
                public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
                    return null;
                }

                @Override
                public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
                    return null;
                }

                @Override
                public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
                    return null;
                }

                @Override
                public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
                    return null;
                }

                @Override
                public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
                    return null;
                }

                @Override
                public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
                    return null;
                }

                @Override
                public Clob createClob() throws SQLException {
                    return null;
                }

                @Override
                public Blob createBlob() throws SQLException {
                    return null;
                }

                @Override
                public NClob createNClob() throws SQLException {
                    return null;
                }

                @Override
                public SQLXML createSQLXML() throws SQLException {
                    return null;
                }

                @Override
                public boolean isValid(int timeout) throws SQLException {
                    return false;
                }

                @Override
                public void setClientInfo(String name, String value) throws SQLClientInfoException {

                }

                @Override
                public void setClientInfo(Properties properties) throws SQLClientInfoException {

                }

                @Override
                public String getClientInfo(String name) throws SQLException {
                    return null;
                }

                @Override
                public Properties getClientInfo() throws SQLException {
                    return null;
                }

                @Override
                public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
                    return null;
                }

                @Override
                public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
                    return null;
                }

                @Override
                public void setSchema(String schema) throws SQLException {

                }

                @Override
                public String getSchema() throws SQLException {
                    return null;
                }

                @Override
                public void abort(Executor executor) throws SQLException {

                }

                @Override
                public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

                }

                @Override
                public int getNetworkTimeout() throws SQLException {
                    return 0;
                }
            };
            try {
                conn = DriverManager.getConnection(url,"postgres","12345");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            String password = "";
            boolean admin = false;
            int coins = 0;
            if(message.getResource().equals("/sessions")||message.getResource().equals("/users")) {

                Map<String, Object> map = new Map<String, Object>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean containsKey(Object key) {
                        return false;
                    }

                    @Override
                    public boolean containsValue(Object value) {
                        return false;
                    }

                    @Override
                    public Object get(Object key) {
                        return null;
                    }

                    @Nullable
                    @Override
                    public Object put(String key, Object value) {
                        return null;
                    }

                    @Override
                    public Object remove(Object key) {
                        return null;
                    }

                    @Override
                    public void putAll(@NotNull Map<? extends String, ?> m) {

                    }

                    @Override
                    public void clear() {

                    }

                    @NotNull
                    @Override
                    public Set<String> keySet() {
                        return null;
                    }

                    @NotNull
                    @Override
                    public Collection<Object> values() {
                        return null;
                    }

                    @NotNull
                    @Override
                    public Set<Entry<String, Object>> entrySet() {
                        return null;
                    }
                };
                messageText = message.getPayload();
                ObjectMapper mapper = new ObjectMapper();
                try {
                    map = mapper.readValue(messageText, Map.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                username = map.get("Username").toString();
                password = map.get("Password").toString();
            }
            if(message.getResource().equals("/users")) {
                String SQL = "INSERT INTO public.\"User\"(admin,password,username,coins,active,token,elo) "
                        + "VALUES(?,?,?,?,?,?,?)";


                try (PreparedStatement pstmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)) {
                    if (username.equals("admin")) {
                        admin = true;
                    }
                    pstmt.setBoolean(1, admin);
                    pstmt.setString(2, password);
                    pstmt.setString(3, username);
                    pstmt.setInt(4, 20);
                    pstmt.setBoolean(5, false);
                    pstmt.setString(6, "Basic "+username+"-mtcgToken");
                    pstmt.setInt(7, 100);

                    pstmt.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(message.getResource().equals("/sessions")) {
                boolean usergefunden=false;
                String sql = "SELECT username, password, coins FROM public.\"User\"\n"
                        + "WHERE username='"+username+"' and password='"+password+"'";
                Statement stmt =null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs=stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    while (rs.next()) {
                        usergefunden=true;
                        for (int i = 1; i <= columnsNumber; i++) {

                            //if (i > 1) System.out.print(",  ");
                            String columnValue = rs.getString(i);
                            //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            if(i==3){
                                coins=Integer.parseInt(columnValue);
                            }
                        }
                        System.out.println("");
                    }
                    if(usergefunden==false){
                        username="";
                        password="";
                        coins=0;
                        System.out.println("Anmeldung fehlgeschlagen, User nicht gefunden!");
                    }
                    else {
                        if (username.equals("admin")) {
                            u.setAdmin(true);
                        }
                        else{
                            u.setAdmin(false);
                        }
                        u.setUsername(username);
                        u.setPassword(password);
                        u.setCoins(coins);
                        System.out.println("Folgender User ist angemeldet: \nName: "+u.getUsername()+", Passwort: "+u.getPassword()+", Coins: "+u.getCoins());

                        String sql2 = "UPDATE public.\"User\"\n"
                                + "SET active=true\n"
                                + "WHERE username='"+username+"' and password='"+password+"'";
                        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                        pstmt2.executeUpdate();

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            else if(message.getResource().equals("/packages")) {
                username=MessageProcesser.auth;
                String isAdmin="";
                String isActive="f";
                String[] cardIDs=new String[5];
                boolean error=false;

                String sql2="SELECT active FROM public.\"User\"\n"
                        + "WHERE username='"+username+"' AND active=true";
                Statement stmt2 =null;
                try {
                    stmt2 = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                    ResultSetMetaData rsmd2 = rs2.getMetaData();
                    int columnsNumber2 = rsmd2.getColumnCount();

                    while (rs2.next()) {
                        for (int i = 1; i <= columnsNumber2; i++) {
                            String columnValue2 = rs2.getString(i);
                            isActive = columnValue2;
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                String sql = "SELECT admin FROM public.\"User\"\n"
                        + "WHERE username='"+username+"'";
                Statement stmt =null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs=stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    while (rs.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            String columnValue = rs.getString(i);
                            isAdmin=columnValue;
                        }
                    }
                    if(isActive.equals("t")==false){
                        System.out.println("User existiert nicht, oder ist gerade nicht angemeldet!");
                    }
                    else if(isAdmin.equals("t")==false){
                        System.out.println("Der angegebene User ist kein Admin!");
                    }
                    else{
                        String id="";
                        String name="";
                        String damage="";
                        double damage2=0;

                        Map<String, Object> map = new Map<String, Object>() {
                            @Override
                            public int size() {
                                return 0;
                            }

                            @Override
                            public boolean isEmpty() {
                                return false;
                            }

                            @Override
                            public boolean containsKey(Object key) {
                                return false;
                            }

                            @Override
                            public boolean containsValue(Object value) {
                                return false;
                            }

                            @Override
                            public Object get(Object key) {
                                return null;
                            }

                            @Nullable
                            @Override
                            public Object put(String key, Object value) {
                                return null;
                            }

                            @Override
                            public Object remove(Object key) {
                                return null;
                            }

                            @Override
                            public void putAll(@NotNull Map<? extends String, ?> m) {

                            }

                            @Override
                            public void clear() {

                            }

                            @NotNull
                            @Override
                            public Set<String> keySet() {
                                return null;
                            }

                            @NotNull
                            @Override
                            public Collection<Object> values() {
                                return null;
                            }

                            @NotNull
                            @Override
                            public Set<Entry<String, Object>> entrySet() {
                                return null;
                            }
                        };
                        messageText = message.getPayload();
                        ObjectMapper mapper = new ObjectMapper();
                        Object[] myObjects = mapper.readValue(messageText, Object[].class);
                        for(int i=0; i<myObjects.length; i++){
                            String[] parts=myObjects[i].toString().split(" ");
                            for( int j=0; j<parts.length;j++){
                                String[] parts2=parts[j].split("");
                                if(j==0){
                                    for( int k=4; k<parts2.length-1;k++){
                                        id+=parts2[k];
                                    }
                                    System.out.print(id);
                                }
                                else if(j==1){
                                    for( int k=5; k<parts2.length-1;k++){
                                        name+=parts2[k];
                                    }
                                    System.out.print(", "+name);
                                }
                                else if(j==2){
                                    for( int k=7; k<parts2.length-1;k++){
                                        damage+=parts2[k];
                                    }
                                    damage2=Double.parseDouble(damage);
                                    System.out.print(", "+damage2+"\n");
                                }
                            }
                            String type="";
                            String rarity="";
                            String description="";
                            String monstertype="";
                            if(name.equals("WaterGoblin")){
                                type="water";
                                rarity="common";
                                description="The Cheeky One";
                                monstertype="goblin";
                            }
                            else if(name.equals("Dragon")){
                                type="fire";
                                rarity="rare";
                                description="The Firespitter";
                                monstertype="dragon";
                            }
                            else if(name.equals("WaterSpell")){
                                type="water";
                                rarity="common";
                                description="The Raging Waters";
                                monstertype="spell";
                            }
                            else if(name.equals("Ork")){
                                type="normal";
                                rarity="common";
                                description="The Molester";
                                monstertype="ork";
                            }
                            else if(name.equals("RegularSpell")){
                                type="normal";
                                rarity="rare";
                                description="The Ancient Magic";
                                monstertype="spell";
                            }
                            else if(name.equals("FireSpell")){
                                type="fire";
                                rarity="common";
                                description="The Firerain";
                                monstertype="spell";
                            }
                            else if(name.equals("Knight")){
                                type="normal";
                                rarity="common";
                                description="The Victorious";
                                monstertype="knight";
                            }
                            else if(name.equals("FireElf")){
                                type="fire";
                                rarity="rare";
                                description="The Fallen Elf";
                                monstertype="fireelf";
                            }
                            /*
                            boolean kartegefunden=false;
                            String sql3 = "SELECT id FROM public.\"Cards\"\n"
                                    + "WHERE id='"+id+"'";
                            Statement stmt3 =null;
                            try {
                                stmt3 = conn.createStatement();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            try {
                                ResultSet rs3 = stmt3.executeQuery(sql3);
                                while (rs3.next()) {
                                    kartegefunden = true;
                                }
                                if(kartegefunden==true){
                                    System.out.println("Die Karte mit der ID: "+id+" existiert schon!");
                                    break;
                                }*/
                                String sql4 = "INSERT INTO public.\"Cards\"(type,rarity, description, damage, monstertype, id, name) "
                                        + "VALUES(?,?,?,?,?,?,?)";


                                try (PreparedStatement pstmt4 = conn.prepareStatement(sql4,
                                        Statement.RETURN_GENERATED_KEYS)) {
                                    pstmt4.setString(1, type);
                                    pstmt4.setString(2, rarity);
                                    pstmt4.setString(3, description);
                                    pstmt4.setDouble(4, damage2);
                                    pstmt4.setString(5, monstertype);
                                    pstmt4.setString(6, id);
                                    pstmt4.setString(7, name);

                                    pstmt4.executeUpdate();
                                    cardIDs[i]=id;
                                } catch (SQLException ex) {
                                    System.out.println("ERROR: Die ID "+id+" exisitiert bereits!");
                                    error=true;
                                    break;
                                }
                            id="";
                            name="";
                            damage="";
                            damage2=0;
                            }
                        if(error==false){
                            String sql5 = "INSERT INTO public.\"Packages\"(card_1, card_2, card_3, card_4, card_5) "
                                    + "VALUES(?,?,?,?,?)";
                            try (PreparedStatement pstmt5 = conn.prepareStatement(sql5,
                                    Statement.RETURN_GENERATED_KEYS)) {
                                pstmt5.setString(1, cardIDs[0]);
                                pstmt5.setString(2, cardIDs[1]);
                                pstmt5.setString(3, cardIDs[2]);
                                pstmt5.setString(4, cardIDs[3]);
                                pstmt5.setString(5, cardIDs[4]);

                                pstmt5.executeUpdate();
                            }
                            catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        }
                        }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            else if (message.getResource().equals("/transactions/packages")){
                username=MessageProcesser.auth;
                String[] cards=new String[5];

                String sql = "SELECT * FROM public.\"Packages\"";
                Statement stmt =null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    if(columnsNumber==0){
                        System.out.println("Es sind keine Packages vorhanden, bitte welche hinzufügen");
                    }
                    else {
                        Random r = new Random();
                        int random = r.nextInt(columnsNumber);
                        int counter =0;

                        while (rs.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if(random==counter){
                                    cards[i-1]=rs.getString(i);
                                }

                            }
                            counter ++;
                        }

                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }


            }

        }
        /*
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
        }*/



    }
}
