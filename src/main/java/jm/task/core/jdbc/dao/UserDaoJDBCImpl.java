package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Statement statement;
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try {
            statement = util.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (Id INTEGER not null PRIMARY KEY AUTO_INCREMENT, " +
                    "Name VARCHAR(45), " +
                    "Lastname VARCHAR(45), " +
                    "age INTEGER);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement = util.getConnection().createStatement();
            statement.execute("DROP TABLE  IF EXISTS users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных" );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            statement = util.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from users");
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                byte age = rs.getByte("age");
                list.add(new User(name, lastName, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement = util.getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
