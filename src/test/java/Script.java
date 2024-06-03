import java.sql.*;

public class Script {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NAME FROM ANIMALS");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("NAME"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
