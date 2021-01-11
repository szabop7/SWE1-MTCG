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

    private static String fighter1="";
    private static String fighter2="";
    private static String[] deck1=new String[4];
    private static String[] deck2=new String[4];
    private boolean working = false;
    Object lock=new Object();
    BufferedWriter writer;
    String username="";
    User u=new User();

    public OutputHandler(BufferedWriter writer){
        this.writer = writer;
    }

    public void response(Message message, MessageCollection messageCollection) {
        String messageText = "";
        String status="200 OK";


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
                    messageText="Hinzugefuegt: "+username+" "+password;
                } catch (SQLException ex) {
                    status="400 Error";
                    messageText="Dieser Username ist bereits vorhanden!";
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
                        status="400 Error";
                        messageText="Anmeldung fehlgeschlagen, User nicht gefunden!";
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
                        messageText="Folgender User ist angemeldet: \nName: "+u.getUsername()+", Passwort: "+u.getPassword()+", Coins: "+u.getCoins();

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
                username=MessageProcesser.getAuth();
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
                        status="400 Error";
                        messageText="User existiert nicht, oder ist gerade nicht angemeldet!";
                    }
                    else if(isAdmin.equals("t")==false){
                        status="400 Error";
                        messageText="Der angegebene User ist kein Admin!";
                    }
                    else{
                        String id="";
                        String name="";
                        String damage="";
                        double damage2=0;

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
                                    status="400 Error";
                                    messageText="ERROR: Die ID "+id+" exisitiert bereits!";
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
            else if (message.getResource().equals("/transactions/packages")) {
                username = MessageProcesser.getAuth();
                String isActive = "false";
                String sql2 = "SELECT active FROM public.\"User\"\n"
                        + "WHERE username='" + username + "' AND active=true";
                Statement stmt2 = null;
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isActive.equals("t") == false) {
                    status="400 Error";
                    messageText="Der angegebene User existiert entweder nicht oder ist nicht angemeldet!";
                } else {
                    String[] cards = new String[5];

                    String sql = "SELECT * FROM public.\"Packages\"";
                    ResultSet result = null;
                    int rows = 0;
                    try {
                        Statement statement = conn.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        result = statement.executeQuery(sql);
                        result.last();
                        rows = result.getRow();
                        result.beforeFirst();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    Random r = new Random();
                    if(rows==0) {
                        status="400 Error";
                        messageText="Es sind leider keine Packages mehr vorhanden!";
                    }
                    else {
                        int randomRow = r.nextInt(rows) + 1;
                        try {
                            ResultSetMetaData rsmd = result.getMetaData();
                            int columnsNumber = rsmd.getColumnCount();
                            while (result.next()) {
                                if (result.getRow() == randomRow) {
                                    for (int i = 1; i <= columnsNumber; i++) {
                                        cards[i - 1] = result.getString(i);
                                    }
                                }
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        coins = 0;

                        sql = "SELECT coins FROM public.\"User\"\n"
                                + "WHERE username='" + username + "'";
                        Statement stmt = null;
                        try {
                            stmt = conn.createStatement();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            ResultSet rs = stmt.executeQuery(sql);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int columnsNumber = rsmd.getColumnCount();

                            while (rs.next()) {
                                coins = Integer.parseInt(rs.getString(1));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (coins < 5) {
                            status="400 Error";
                            messageText="Der User hat leider zu wenige Coins!";
                        } else {
                            coins -= 5;

                            sql = "UPDATE public.\"User\"\n"
                                    + "SET coins=" + coins + "\n"
                                    + "WHERE username='" + username + "'";
                            PreparedStatement pstmt2 = null;
                            try {
                                pstmt2 = conn.prepareStatement(sql);
                                pstmt2.executeUpdate();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            sql = "INSERT INTO public.\"user_cards\"(username, cardname) "
                                    + "VALUES(?,?)";
                            for (int i = 0; i < cards.length; i++) {
                                try (PreparedStatement pstmt = conn.prepareStatement(sql,
                                        Statement.RETURN_GENERATED_KEYS)) {
                                    pstmt.setString(1, username);
                                    pstmt.setString(2, cards[i]);

                                    pstmt.executeUpdate();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            sql = "DELETE FROM public.\"Packages\""
                                    + "WHERE card_1='" + cards[0] + "'";
                            PreparedStatement pstmt = null;
                            try {
                                pstmt = conn.prepareStatement(sql);
                                pstmt.executeUpdate();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }


                        }
                    }

                }
            }
            else if(message.getResource().equals("/battles")) {

                username=MessageProcesser.getAuth();
                if(fighter1.equals("")){
                    fighter1=username;
                    String sql = "SELECT card_1, card_2, card_3, card_4 FROM public.\"Deck\"\n"
                            + "WHERE username='" + fighter1 + "'";
                    Statement stmt = null;
                    try {
                        stmt = conn.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ResultSet rs = stmt.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                 deck1[i-1] = rs.getString(i);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized(lock){
                        while (true) {
                            try { lock.wait(); }
                            catch (InterruptedException e) {
                                break;
                            }
                        }
                    }
                }
                else{

                    fighter2=username;
                    String sql = "SELECT card_1, card_2, card_3, card_4 FROM public.\"Deck\"\n"
                            + "WHERE username='" + fighter2 + "'";
                    Statement stmt = null;
                    try {
                        stmt = conn.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ResultSet rs = stmt.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                deck2[i-1]=rs.getString(i);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (lock){
                        messageText+="\n"+battle(deck1, deck2, fighter1, fighter2);
                        lock.notifyAll();
                    }

                }
            }

        }
        else if(message.getVerb().equals("GET")){
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

            if (message.getResource().equals("/cards")) {

                username = MessageProcesser.getAuth();
                if(username.equals("")||username.equals(null)){
                    messageText="Es wurde kein Token angegeben!";
                }
                else {
                    String isActive = "false";
                    String sql = "SELECT active FROM public.\"User\"\n"
                            + "WHERE username='" + username + "' AND active=true";
                    Statement stmt = null;
                    try {
                        stmt = conn.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ResultSet rs = stmt.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                String columnValue2 = rs.getString(i);
                                isActive = columnValue2;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (isActive.equals("t") == false) {
                        status = "400 Error";
                        messageText = "Der angegebene User existiert entweder nicht oder ist nicht angemeldet!";

                    } else {
                        ArrayList<String> cards = new ArrayList<>();

                        sql = "SELECT cardname FROM public.\"user_cards\"\n"
                                + "WHERE username='" + username + "'";
                        stmt = null;
                        try {
                            stmt = conn.createStatement();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            ResultSet rs = stmt.executeQuery(sql);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int columnsNumber = rsmd.getColumnCount();

                            while (rs.next()) {
                                for (int i = 1; i <= columnsNumber; i++) {
                                    cards.add(rs.getString(i));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (cards.size() == 0) {
                            status = "400 Error";
                            messageText = "Der User besitzt noch keine Karten, bitte Packages kaufen!";
                        } else {
                            messageText = "Der User besitzt folgende Karten(" + cards.size() + "):\n";
                            for (int i = 0; i < cards.size(); i++) {
                                sql = "SELECT name, description, damage FROM public.\"Cards\"\n"
                                        + "WHERE id='" + cards.get(i) + "'";
                                stmt = null;
                                try {
                                    stmt = conn.createStatement();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                try {
                                    ResultSet rs = stmt.executeQuery(sql);
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int columnsNumber = rsmd.getColumnCount();

                                    while (rs.next()) {
                                        for (int j = 1; j <= columnsNumber; j++) {
                                            if (j == 1) {
                                                messageText += "Name: " + rs.getString(j) + " - ";
                                            } else if (j == 2) {
                                                messageText += rs.getString(j);
                                            } else if (j == 3) {
                                                messageText += ", Damage: " + rs.getString(j) + "\n";
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            username="";
                        }
                    }
                }

            }
            else if(message.getResource().equals("/deck")){
                username=MessageProcesser.getAuth();
                if(username.equals("")==false&&username.equals(null)==false)
                {
                    String[] cards=new String [4];
                    boolean gefunden = false;
                    String sql = "SELECT card_1,card_2,card_3,card_4 FROM public.\"Deck\"\n"
                            + "WHERE username='" + username + "'";
                    Statement stmt = null;
                    try {
                        stmt = conn.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        ResultSet rs = stmt.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();

                        while (rs.next()) {
                            gefunden = true;
                            for(int i=1;i<=columnsNumber;i++){
                                cards[i-1]=rs.getString(i);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(gefunden==true){
                        messageText="";
                        for(int i=0; i<cards.length; i++) {
                            sql = "SELECT name,description,damage FROM public.\"Cards\"\n"
                                    + "WHERE id='" + cards[i] + "'";
                            try {
                                stmt = conn.createStatement();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            try {
                                ResultSet rs = stmt.executeQuery(sql);
                                ResultSetMetaData rsmd = rs.getMetaData();
                                int columnsNumber = rsmd.getColumnCount();

                                while (rs.next()) {
                                    for (int j = 1; j <= columnsNumber; j++) {
                                        if(j==1){
                                            messageText+="Name: "+rs.getString(j);
                                        }
                                        else if(j==2){
                                            messageText+=" - "+rs.getString(j);
                                        }
                                        else if(j==3){
                                            messageText+=", Damage: "+rs.getString(j)+"\n";
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        status = "400 Error";
                        messageText="Dieser User hat noch kein Deck konfiguriert!";
                    }
                }
                else{
                    status = "400 Error";
                    messageText="Kein Token angegeben!";
                }
            }
            else if(message.getResource().equals("/score")){
                String name1="";
                String name2="";
                String name3="";
                int elo1=0;
                int elo2=0;
                int elo3=0;
                String sql = "SELECT username, elo FROM public.\"User\"";
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    int counter=0;
                    while (rs.next()) {
                        for(int i=1; i<=columnsNumber;i++){
                            if(counter==0) {
                                if (i == 1)
                                    name1 = rs.getString(i);
                                else
                                    elo1 = Integer.parseInt(rs.getString(i));
                            }
                            else if(counter==1) {
                                if (i == 1)
                                    name2 = rs.getString(i);
                                else
                                    elo2 = Integer.parseInt(rs.getString(i));
                            }
                            else if(counter==2) {
                                if (i == 1)
                                    name3 = rs.getString(i);
                                else
                                    elo3 = Integer.parseInt(rs.getString(i));
                            }
                        }
                        counter++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(elo1>=elo2&&elo1>=elo3){
                    if(elo2>=elo3){
                        messageText+="1: "+name1+" "+elo1+"\n";
                        messageText+="2: "+name2+" "+elo2+"\n";
                        messageText+="3: "+name3+" "+elo3;
                    }
                    else{
                        messageText+="1: "+name1+" "+elo1+"\n";
                        messageText+="2: "+name3+" "+elo3+"\n";
                        messageText+="3: "+name2+" "+elo2;
                    }

                }else if(elo2>=elo1&&elo2>=elo3){
                    if(elo1>=elo3) {
                        messageText += "1: " + name2 + " " + elo2 + "\n";
                        messageText += "2: " + name1 + " " + elo1 + "\n";
                        messageText += "3: " + name3 + " " + elo3;
                    }
                    else{
                        messageText += "1: " + name2 + " " + elo2 + "\n";
                        messageText += "2: " + name3 + " " + elo3 + "\n";
                        messageText += "3: " + name1 + " " + elo1;
                    }
                }
                else if(elo3>=elo1&&elo3>=elo2){
                    if(elo1>=elo2) {
                        messageText += "1: " + name3 + " " + elo3 + "\n";
                        messageText += "2: " + name1 + " " + elo1 + "\n";
                        messageText += "3: " + name2 + " " + elo2;
                    }
                    else{
                        messageText += "1: " + name3 + " " + elo3 + "\n";
                        messageText += "2: " + name2 + " " + elo2 + "\n";
                        messageText += "3: " + name1 + " " + elo1;
                    }
                }
            }

        }
        else if(message.getVerb().equals("PUT")){
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

            if (message.getResource().equals("/deck")) {
                messageText="";
                username=MessageProcesser.getAuth();
                if(username.equals("")==false&&username.equals(null)==false){
                    String [] deck=new String[4];
                    String input = message.getPayload();
                    ObjectMapper mapper = new ObjectMapper();
                    Object[] myObjects = new Object[0];
                    try {
                        myObjects = mapper.readValue(input, Object[].class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    for(int i=0; i<myObjects.length; i++){
                        deck[i]=myObjects[i].toString();
                        }
                    boolean b=false;
                    try{
                        deck[3].equals(null);

                    }
                    catch(Exception e){
                        b=true;
                        status = "400 Error";
                        messageText="Zu wenige Karten angegeben!";
                    }
                    if(b==false){
                        String sql = "";
                        for (int i = 0; i < deck.length; i++) {
                            boolean gefunden = false;
                            sql = "SELECT username FROM public.\"user_cards\"\n"
                                    + "WHERE cardname='" + deck[i] + "' and username='" + username + "'";
                            Statement stmt = null;
                            try {
                                stmt = conn.createStatement();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            try {
                                ResultSet rs = stmt.executeQuery(sql);
                                ResultSetMetaData rsmd = rs.getMetaData();
                                int columnsNumber = rsmd.getColumnCount();

                                while (rs.next()) {
                                    gefunden = true;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (gefunden == false) {
                                status = "400 Error";
                                messageText = "Die Karte " + deck[i] + " besitzt der angegebene User nicht!";
                            }

                        }
                        if (messageText.equals("")) {
                            boolean gefunden = false;
                            sql = "SELECT username FROM public.\"Deck\"\n"
                                    + "WHERE username='" + username + "' and card_1='" + deck[0] + "' and card_2='" + deck[1] + "' and card_3='" + deck[2] + "' and card_4='" + deck[3] + "'";
                            Statement stmt = null;
                            try {
                                stmt = conn.createStatement();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            try {
                                ResultSet rs = stmt.executeQuery(sql);
                                ResultSetMetaData rsmd = rs.getMetaData();
                                int columnsNumber = rsmd.getColumnCount();

                                while (rs.next()) {
                                    gefunden = true;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (gefunden == true) {
                                status = "400 Error";
                                messageText = "Dieses Deck existiert schon!";
                            } else {
                                sql = "DELETE FROM public.\"Deck\""
                                        + "WHERE username='" + username + "'";
                                try {
                                    PreparedStatement pstmt2 = conn.prepareStatement(sql);
                                    pstmt2.executeUpdate();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                                sql = "INSERT INTO public.\"Deck\"(username, card_1, card_2, card_3,card_4) "
                                        + "VALUES(?,?,?,?,?)";
                                PreparedStatement pstmt = null;
                                try {
                                    pstmt = conn.prepareStatement(sql,
                                            Statement.RETURN_GENERATED_KEYS);
                                    pstmt.setString(1, username);
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                for (int i = 0; i < deck.length; i++) {
                                    try {
                                        pstmt.setString(i + 2, deck[i]);

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                }
                                try {
                                    pstmt.executeUpdate();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }
                        }
                    }

                }
                else{
                    status="400 Error";
                    messageText="Bitte einen gueltigen Token angeben!";
                }
            }
        }

        //Ressource in Message wird gesplittet, damit ermittelt wird, was gemacht werden muss
        String[] segments = message.getResource().split("/");
        //Gibt er dem Client zurück
        String output = message.getVersion() + " "+status+"\n"+
                "Server: MTCG_Server\n"+
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

    }
    public String battle(String [] deck1, String [] deck2, String player1, String player2){
        ArrayList<Card>fighter1=new ArrayList<>();
        ArrayList<Card>fighter2=new ArrayList<>();

        String name="";
        String description="";
        String element="";
        String monstertype="";
        int damage=0;

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

        String output="";
        boolean ende=false;
        for(int i=0; i<deck1.length; i++){
            String sql = "SELECT name, description, type, monstertype, damage FROM public.\"Cards\"\n"
                    + "WHERE id='" + deck1[i] + "'";
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                while (rs.next()) {
                    for (int j = 1; j <= columnsNumber; j++) {
                        if(j==1){
                            name=rs.getString(j);
                        }else if(j==2){
                            description=rs.getString(j);
                        }else if(j==3){
                            element=rs.getString(j);
                        }else if(j==4){
                            monstertype=rs.getString(j);
                        }else if(j==5){
                            damage=Integer.parseInt(rs.getString(j));
                        }
                    }

                    Card c=new Card(name, description, element, monstertype, damage);
                    fighter1.add(c);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        for(int i=0; i<deck2.length; i++){
            String sql = "SELECT name, description, type, monstertype, damage FROM public.\"Cards\"\n"
                    + "WHERE id='" + deck2[i] + "'";
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                while (rs.next()) {
                    for (int j = 1; j <= columnsNumber; j++) {
                        if(j==1){
                            name=rs.getString(j);
                        }else if(j==2){
                            description=rs.getString(j);
                        }else if(j==3){
                            element=rs.getString(j);
                        }else if(j==4){
                            monstertype=rs.getString(j);
                        }else if(j==5){
                            damage=Integer.parseInt(rs.getString(j));
                        }
                    }
                    Card c=new Card(name, description, element, monstertype, damage);
                    fighter2.add(c);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        int counter=1;
        while(ende==false){
            output+="Runde "+counter+":\n";
            Random r=new Random();
            int cardNr1=r.nextInt(fighter1.size());
            int cardNr2=r.nextInt(fighter2.size());
            output+=player1+": "+fighter1.get(cardNr1).getName()+" - "+fighter1.get(cardNr1).getDescription()+", "+fighter1.get(cardNr1).getDamage()+" damage"+
                    " vs "+player2+": "+fighter2.get(cardNr2).getName()+" - "+fighter2.get(cardNr2).getDescription()+", "+fighter2.get(cardNr2).getDamage()+" damage\n";
            if(fighter1.get(cardNr1).getMonstertype().equals("spell")&&fighter2.get(cardNr2).getMonstertype().equals("spell")){
                if(fighter1.get(cardNr1).getElement().equals("normal")&&fighter2.get(cardNr2).getElement().equals("water")||fighter1.get(cardNr1).getElement().equals("fire")&&fighter2.get(cardNr2).getElement().equals("normal")||fighter1.get(cardNr1).getElement().equals("water")&&fighter2.get(cardNr2).getElement().equals("fire")){
                    if(fighter1.get(cardNr1).getDamage()*2>fighter2.get(cardNr2).getDamage()/2){
                          output+="-> "+player1+" wins\n\n";
                          fighter1.add(fighter2.get(cardNr2));
                          fighter2.remove(cardNr2);
                    }else if(fighter1.get(cardNr1).getDamage()*2<fighter2.get(cardNr2).getDamage()/2){
                        output+="-> "+player2+" wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    }
                    else{
                        output+="-> it is a draw\n\n";
                    }
                }else if(fighter1.get(cardNr1).getElement().equals("water")&&fighter2.get(cardNr2).getElement().equals("normal")||fighter1.get(cardNr1).getElement().equals("normal")&&fighter2.get(cardNr2).getElement().equals("fire")||fighter1.get(cardNr1).getElement().equals("fire")&&fighter2.get(cardNr2).getElement().equals("water")){
                    if(fighter1.get(cardNr1).getDamage()/2>fighter2.get(cardNr2).getDamage()*2){
                        output+="-> "+player1+" wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    }else if(fighter1.get(cardNr1).getDamage()/2<fighter2.get(cardNr2).getDamage()*2){
                        output+="-> "+player2+" wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    }
                    else{
                        output+="-> it is a draw\n\n";
                    }
                }else{
                    if (fighter1.get(cardNr1).getDamage() > fighter2.get(cardNr2).getDamage()) {
                        output += "-> " + player1 + " wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    } else if (fighter1.get(cardNr1).getDamage() < fighter2.get(cardNr2).getDamage()) {
                        output += "-> " + player2 + " wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    } else {
                        output += "-> it is a draw\n\n";
                    }
                }
            }else if(fighter1.get(cardNr1).getMonstertype().equals("spell")&&fighter2.get(cardNr2).getMonstertype().equals("spell")!=true||fighter1.get(cardNr1).getMonstertype().equals("spell")!=true&&fighter2.get(cardNr2).getMonstertype().equals("spell")){
                if(fighter1.get(cardNr1).getElement().equals("normal")&&fighter2.get(cardNr2).getElement().equals("water")||fighter1.get(cardNr1).getElement().equals("fire")&&fighter2.get(cardNr2).getElement().equals("normal")||fighter1.get(cardNr1).getElement().equals("water")&&fighter2.get(cardNr2).getElement().equals("fire")){
                    if(fighter1.get(cardNr1).getName().equals("WaterSpell")&&fighter2.get(cardNr2).getMonstertype().equals("knight")){
                        output+="-> "+player1+" wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    }else if(fighter1.get(cardNr1).getMonstertype().equals("knight")&&fighter2.get(cardNr2).getName().equals("WaterSpell")){
                        output+="-> "+player2+" wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    }
                    else if(fighter1.get(cardNr1).getMonstertype().equals("kraken")){
                        output+="-> "+player1+" wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    }else if(fighter2.get(cardNr2).getMonstertype().equals("kraken")){
                        output+="-> "+player2+" wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    }else{
                        if(fighter1.get(cardNr1).getDamage()*2>fighter2.get(cardNr2).getDamage()/2){
                            output+="-> "+player1+" wins\n\n";
                            fighter1.add(fighter2.get(cardNr2));
                            fighter2.remove(cardNr2);
                        }else if(fighter1.get(cardNr1).getDamage()*2<fighter2.get(cardNr2).getDamage()/2){
                            output+="-> "+player2+" wins\n\n";
                            fighter2.add(fighter1.get(cardNr1));
                            fighter1.remove(cardNr1);
                        }
                        else{
                            output+="-> it is a draw\n\n";
                        }
                    }

                }
                else if(fighter1.get(cardNr1).getElement().equals("water")&&fighter2.get(cardNr2).getElement().equals("normal")||fighter1.get(cardNr1).getElement().equals("normal")&&fighter2.get(cardNr2).getElement().equals("fire")||fighter1.get(cardNr1).getElement().equals("fire")&&fighter2.get(cardNr2).getElement().equals("water")) {
                    if(fighter1.get(cardNr1).getName().equals("WaterSpell")&&fighter2.get(cardNr2).getMonstertype().equals("knight")){
                        output+="-> "+player1+" wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    }else if(fighter1.get(cardNr1).getMonstertype().equals("knight")&&fighter2.get(cardNr2).getName().equals("WaterSpell")){
                        output+="-> "+player2+" wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    }
                    else if(fighter1.get(cardNr1).getMonstertype().equals("kraken")){
                        output+="-> "+player1+" wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    }else if(fighter2.get(cardNr2).getMonstertype().equals("kraken")){
                        output+="-> "+player2+" wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    }else {
                        if (fighter1.get(cardNr1).getDamage() / 2 > fighter2.get(cardNr2).getDamage() * 2) {
                            output += "-> " + player1 + " wins\n\n";
                            fighter1.add(fighter2.get(cardNr2));
                            fighter2.remove(cardNr2);
                        } else if (fighter1.get(cardNr1).getDamage() / 2 < fighter2.get(cardNr2).getDamage() * 2) {
                            output += "-> " + player2 + " wins\n\n";
                            fighter2.add(fighter1.get(cardNr1));
                            fighter1.remove(cardNr1);
                        } else {
                            output += "-> it is a draw\n\n";
                        }
                    }
                }
                else{
                    if (fighter1.get(cardNr1).getDamage() > fighter2.get(cardNr2).getDamage()) {
                        output += "-> " + player1 + " wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    } else if (fighter1.get(cardNr1).getDamage() < fighter2.get(cardNr2).getDamage()) {
                        output += "-> " + player2 + " wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    } else {
                        output += "-> it is a draw\n\n";
                    }
                }
            }else if(fighter1.get(cardNr1).getMonstertype().equals("spell")!=true&&fighter2.get(cardNr2).getMonstertype().equals("spell")!=true){
                if(fighter1.get(cardNr1).getMonstertype().equals("goblin")&&fighter2.get(cardNr2).getMonstertype().equals("dragon")||fighter1.get(cardNr1).getMonstertype().equals("ork")&&fighter2.get(cardNr2).getMonstertype().equals("wizzard")||fighter1.get(cardNr1).getMonstertype().equals("dragon")&&fighter2.get(cardNr2).getMonstertype().equals("fireelf")){
                    output+="-> "+player1+" wins\n\n";
                    fighter1.add(fighter2.get(cardNr2));
                    fighter2.remove(cardNr2);
                }else if(fighter1.get(cardNr1).getMonstertype().equals("dragon")&&fighter2.get(cardNr2).getMonstertype().equals("goblin")||fighter1.get(cardNr1).getMonstertype().equals("wizzard")&&fighter2.get(cardNr2).getMonstertype().equals("ork")||fighter1.get(cardNr1).getMonstertype().equals("fireelf")&&fighter2.get(cardNr2).getMonstertype().equals("dragon")){
                    output+="-> "+player2+" wins\n\n";
                    fighter2.add(fighter1.get(cardNr1));
                    fighter1.remove(cardNr1);
                }
                else{
                    if (fighter1.get(cardNr1).getDamage() > fighter2.get(cardNr2).getDamage()) {
                        output += "-> " + player1 + " wins\n\n";
                        fighter1.add(fighter2.get(cardNr2));
                        fighter2.remove(cardNr2);
                    } else if (fighter1.get(cardNr1).getDamage() < fighter2.get(cardNr2).getDamage()) {
                        output += "-> " + player2 + " wins\n\n";
                        fighter2.add(fighter1.get(cardNr1));
                        fighter1.remove(cardNr1);
                    } else {
                        output += "-> it is a draw\n\n";
                    }
                }
            }
            if(fighter1.size()==0){
                output+=player2+" hat gewonnen!";
                ende=true;
                int elo=0;
                String sql = "SELECT elo FROM public.\"User\"\n"
                        + "WHERE username='" + player2 + "'";
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    while (rs.next()) {
                        for (int j = 1; j <= columnsNumber; j++) {
                            elo=Integer.parseInt(rs.getString(j));
                        }

                        Card c=new Card(name, description, element, monstertype, damage);
                        fighter1.add(c);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String sql2 = "UPDATE public.\"User\"\n"
                        + "SET elo="+(elo+5)+"\n"
                        + "WHERE username='"+player2+"'";
                PreparedStatement pstmt2 = null;
                try {
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                elo=0;
                sql = "SELECT elo FROM public.\"User\"\n"
                        + "WHERE username='" + player1 + "'";
                stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    while (rs.next()) {
                        for (int j = 1; j <= columnsNumber; j++) {
                            elo=Integer.parseInt(rs.getString(j));
                        }

                        Card c=new Card(name, description, element, monstertype, damage);
                        fighter1.add(c);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sql2 = "UPDATE public.\"User\"\n"
                        + "SET elo="+(elo-10)+"\n"
                        + "WHERE username='"+player1+"'";
                pstmt2 = null;
                try {
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(fighter2.size()==0){
                output+=player1+" hat gewonnen!";
                ende=true;
                int elo=0;
                String sql = "SELECT elo FROM public.\"User\"\n"
                        + "WHERE username='" + player1 + "'";
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    while (rs.next()) {
                        for (int j = 1; j <= columnsNumber; j++) {
                            elo=Integer.parseInt(rs.getString(j));
                        }

                        Card c=new Card(name, description, element, monstertype, damage);
                        fighter1.add(c);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String sql2 = "UPDATE public.\"User\"\n"
                        + "SET elo="+(elo+5)+"\n"
                        + "WHERE username='"+player1+"'";
                PreparedStatement pstmt2 = null;
                try {
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                elo=0;
                sql = "SELECT elo FROM public.\"User\"\n"
                        + "WHERE username='" + player2 + "'";
                stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    while (rs.next()) {
                        for (int j = 1; j <= columnsNumber; j++) {
                            elo=Integer.parseInt(rs.getString(j));
                        }

                        Card c=new Card(name, description, element, monstertype, damage);
                        fighter1.add(c);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sql2 = "UPDATE public.\"User\"\n"
                        + "SET elo="+(elo-10)+"\n"
                        + "WHERE username='"+player2+"'";
                pstmt2 = null;
                try {
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(counter==100)
                ende=true;

            counter++;
        }
        return output;

    }
}
