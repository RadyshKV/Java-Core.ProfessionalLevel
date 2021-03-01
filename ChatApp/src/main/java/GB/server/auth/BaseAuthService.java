package GB.server.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class BaseAuthService implements AuthService {

    public static Connection connection;
    public static Statement stmt;
    public static ResultSet rs;
    private static final Logger logger = LogManager.getLogger();

    public BaseAuthService() {
        try {
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("соединение с DB не установлено");
        }

    }


    void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/GB/server/database.db");
        stmt = connection.createStatement();
    }

    void disconnect() throws SQLException {
        connection.close();
    }

    @Override
    public String getUserNameByLoginAndPassword(String login, String password) {
        try {
            rs = stmt.executeQuery(String.format("SELECT username FROM users WHERE login = '%s' AND password = '%s'", login, password));
            return rs.getString("username");
        } catch (SQLException e) {
            logger.warn(String.format("Пользователя с login = %s и password = %s в DB не существует%n", login, password));
            return null;
        }
    }

    @Override
    public boolean updateUsername(String newUsername, String oldUsername) {
        try {
            stmt.executeUpdate(String.format("UPDATE users SET username = '%s' WHERE username = '%s'", newUsername, oldUsername));
            logger.info("Имя обновлено");
            return true;
        } catch (SQLException e) {
            logger.warn(String.format("Не удалось обновить имя пользователя '%s' на '%s'", oldUsername, newUsername));
            return false;
        }
    }

    @Override
    public boolean newUserRegister(String login, String username, String password) {
        try {
            stmt.executeUpdate(String.format("INSERT INTO users (login, password, username) VALUES ('%s', '%s', '%s')", login, password, username));
            return true;
        } catch (SQLException e) {
            logger.warn("Не удалось добавить нового пользователя " + username);
            return false;
        }
    }

    @Override
    public void startAuthentication() {

    }

    @Override
    public void endAuthentication() {

    }
}
