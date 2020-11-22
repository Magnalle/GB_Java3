package Task_2_1;

// Вопрос: я пробовала в котлине тек же работать с библиотекой Room, она создавала базу SQL Lite в соотвтетсвии с классом java, дополненным аннотациями.
// Есть ли какая-то бибилиотека, которая может делать то же, но с сетевыми СУБД?

import java.sql.*;

public class main {

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {
        try {
            connect();
            createTable();
            insert("user1","111","user1");
            insert("user2","222","user2");
            insert("user3","333","user3");
            ResultSet res = selectByLogin("user2");
            while(res.next()){
                System.out.println("login = " + res.getString("login") +
                        ", pwd = " + res.getString("password") +
                        ", nickname = " + res.getString("nickname")) ;
            }
            deleteByLogin("user1");
            //dropTable();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/task_2_1/task2.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() throws SQLException {
        connection.close();
    }

    // создание базы как в сетевом чате
    private static void createTable() throws Exception {
        stmt.executeUpdate("DROP TABLE IF EXISTS users;" +
                "CREATE TABLE users (\n" +
                "login TEXT,\n" +
                "password TEXT,\n" +
                "nickname TEXT,\n" +
                "PRIMARY KEY(login)\n" +
                ")");
    }

    private static void insert(String login, String password, String nickname) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (login, password, nickname) values (?, ?, ?);");
        pstmt.setString(1, login);
        pstmt.setString(2, password);
        pstmt.setString(3, nickname);
        pstmt.executeUpdate();
    }

    private static ResultSet selectByLogin(String login) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
        pstmt.setString(1, login);
        return pstmt.executeQuery();
    }

    private static void deleteByLogin(String login) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM users WHERE login = ?");
        pstmt.setString(1, login);
        pstmt.executeUpdate();
    }

    private static void dropTable() throws Exception {
        stmt.executeUpdate("DROP TABLE users");
    }
}

