package be.intecbrussel.servlets.dao;

import be.intecbrussel.servlets.model.MessageBean;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {
    // Servlet houdt zich NIET bezig met database operaties
    // DAO haalt data op en schrijft data weg
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createMessage(MessageBean message) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://noelvaes.eu/StudentDB", "student", "student123")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GuestBook (Name, Message) VALUES (?, ?)")) {
                preparedStatement.setString(1, message.getName());
                preparedStatement.setString(2, message.getMessage());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MessageBean> getMessagesFromGuestbook() {
        ArrayList<MessageBean> resultList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://noelvaes.eu/StudentDB", "student", "student123")) {
            try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM GuestBook")) {
                while (resultSet.next()) {
                    MessageBean message = new MessageBean();
                    message.setLocalDateTime(resultSet.getTimestamp("Date").toLocalDateTime());
                    message.setName(resultSet.getString("Name"));
                    message.setMessage(resultSet.getString("Message"));
                    resultList.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}

