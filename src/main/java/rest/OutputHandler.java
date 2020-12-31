package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

public class OutputHandler {

    BufferedWriter writer;
    String username="";

    public OutputHandler(BufferedWriter writer){
        this.writer = writer;
    }

    public void response(Message message, MessageCollection messageCollection) {
        //Ressource in Message wird gesplittet, damit ermittelt wird, was gemacht werden muss
        String[] segments = message.getResource().split("/");
        String messageText = "";


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

            String password="";
            boolean admin=false;
            Map<String,Object> map=new Map<String, Object>() {
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
            messageText=message.getPayload();
            ObjectMapper mapper = new ObjectMapper();
            try {
                map = mapper.readValue(messageText, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            username=map.get("Username").toString();
            password=map.get("Password").toString();

            if(message.getResource().equals("/users")) {
                String SQL = "INSERT INTO public.\"User\"(admin,password,username,coins) "
                        + "VALUES(?,?,?,?)";


                try (PreparedStatement pstmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)) {
                    if (username.equals("admin")) {
                        admin = true;
                    }
                    pstmt.setBoolean(1, admin);
                    pstmt.setString(2, password);
                    pstmt.setString(3, username);
                    pstmt.setInt(4, 20);

                    pstmt.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(message.getResource().equals("/sessions")) {
                boolean usergefunden=false;
                String sql = "SELECT username, password FROM public.\"User\"\n"
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

                            if (i > 1) System.out.print(",  ");
                            String columnValue = rs.getString(i);
                            System.out.print(columnValue + " " + rsmd.getColumnName(i));
                        }
                        System.out.println("");
                    }
                    if(usergefunden==false){
                        username="";
                        password="";
                        System.out.println("Anmeldung fehlgeschlagen, User nicht gefunden!");
                    }
                    System.out.println("Der gespeicherte Benutzernamen: "+username);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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
