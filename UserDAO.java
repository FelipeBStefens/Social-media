package socialmedia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    
    private static final String INSERT_SQL = 
        """
        INSERT INTO user_relation(name_user, password_user, email_user, at_sign_user)
        VALUES
        (?, ?, ?, ?);
        """;

    private static final String DELETE_SQL = 
        """
        DELETE FROM user_relation
        WHERE email_user = ? AND at_sign_user = ?;      
        """;

    private static final String UPDATE_SQL = 
        """
        UPDATE user_relation
        SET name_user = ?, password_user = ?  
        WHERE email_user = ? AND at_sign_user = ?;
        """;

    private static final String SELECT_ALL_USERS = 
        """
        SELECT * FROM user_relation;    
        """;

    private static final String SELECT_USER_SQL_ENTER =    
        """
        SELECT * FROM user_relation
        WHERE email_user = ? AND password_user = ?;    
        """;
    

    private static final String SELECT_USER_AT_SIGN =
        """
        SELECT * FROM user_relation
        WHERE at_sign_user = ?;    
        """;
    

    public static void addUser(User user) throws Exception {

        try {
            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(INSERT_SQL);
            
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAtSign());

            preparedStatement.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }

    public static void deleteUser(User user) throws Exception {

        try {
            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(DELETE_SQL);
            
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getAtSign());

            preparedStatement.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }

    public static void updateUser(User user, String userName, String password) throws Exception {

        try {
            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(UPDATE_SQL);
            
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAtSign());

            preparedStatement.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }

    public static User findUser(String email, String password) throws Exception {

        try {

            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(SELECT_USER_SQL_ENTER);
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet userDatabase = preparedStatement.executeQuery();

            if (userDatabase.next()) {
                return new User(userDatabase.getString("name_user"), 
                    userDatabase.getString("email_user"), userDatabase.getString("password_user"), 
                    userDatabase.getString("at_sign_user"));
            }
            else {
                throw new Exception("User hasn't found....");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }

    public static User findUser(String atSign) throws Exception {

        try {

            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(SELECT_USER_AT_SIGN);
            
            preparedStatement.setString(1, atSign);
            ResultSet userDatabase = preparedStatement.executeQuery();

            if (userDatabase.next()) {
                return new User(userDatabase.getString("name_user"), 
                    userDatabase.getString("email_user"), userDatabase.getString("password_user"), 
                    userDatabase.getString("at_sign_user"));
            }
            else {
                throw new Exception("User hasn't found....");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }

    public static ArrayList<User> allUsers() throws Exception {

        ArrayList<User> arrayUsers = new ArrayList<>();
        try {

            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(SELECT_ALL_USERS);
            
            ResultSet userDatabase = preparedStatement.executeQuery();

            while (userDatabase.next()) {
                arrayUsers.add(new User(userDatabase.getString("name_user"), 
                    userDatabase.getString("email_user"), 
                    userDatabase.getString("password_user"), 
                    userDatabase.getString("at_sign_user")));
            }
            
            return arrayUsers;
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }
}
