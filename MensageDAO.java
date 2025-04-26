package socialmedia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MensageDAO {

    private static final String INSERT_SQL = 
        """
        INSERT INTO mensage(value_mensage, id_respond_parent, at_sign_user)
        VALUES
        (?, ?, ?);
        """;

    private static final String DELETE_SQL = 
        """
        DELETE FROM mensage
        WHERE id_respond = ?;      
        """;

    private static final String UPDATE_SQL = 
        """
        UPDATE mensage
        SET value_mensage = ?  
        WHERE id_respond = ?;
        """;

    private static final String SELECT_ALL_MENSAGES = 
        """
        SELECT * FROM mensage;    
        """;

    private static String selectMensagesByUser = 
        """
        SELECT * FROM mensage
        WHERE at_sign_user = ?;    
        """;
    

    public static void addMensage(Mensage mensage) throws Exception {
        int id = -1;

        try {
            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, mensage.getValue());
            if (mensage.getIdMensageFather() != null) {
                preparedStatement.setInt(2, mensage.getIdMensageFather());
            } 
            else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(3, mensage.getUser().getAtSign());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            resultSet.close();
            preparedStatement.close();

            mensage.setIdMensage(id);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        } 
        finally {
            SqlConnection.closeConnection();
        } 
    }

    public static void deleteMensage(int id) throws Exception {
        
        try {
            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(DELETE_SQL);
            
            preparedStatement.setInt(1, id);
            
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

    public static void updateMensage(int id, String value) throws Exception {
        
        try {
            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(UPDATE_SQL);
            
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, id);
            
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

    public static ArrayList<Mensage> showAllMensages() throws Exception {

        ArrayList<Mensage> arrayMensages = new ArrayList<>();
        try {

            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(SELECT_ALL_MENSAGES);
            
            ResultSet mensagesDatabase = preparedStatement.executeQuery();

            while (mensagesDatabase.next()) {

                Mensage mensage = new Mensage(mensagesDatabase.getString("value_mensage"), 
                    UserDAO.findUser(mensagesDatabase.getString("at_sign_user")),
                    mensagesDatabase.getInt("id_respond_parent"));

                mensage.setIdMensage(mensagesDatabase.getInt("id_respond"));
                arrayMensages.add(mensage);  
            }
            
            return arrayMensages;
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally {
            SqlConnection.closeConnection();
        }
    }

    public static ArrayList<Mensage> showAllMensagesByUser(User user) throws Exception {

        ArrayList<Mensage> arrayMensages = new ArrayList<>();
        try {

            SqlConnection.openConnection();
            PreparedStatement preparedStatement = 
                SqlConnection.getConnection().prepareStatement(selectMensagesByUser);
            
            preparedStatement.setString(1, user.getAtSign());

            ResultSet mensagesDatabase = preparedStatement.executeQuery();

            while (mensagesDatabase.next()) {

                Mensage mensage = new Mensage(mensagesDatabase.getString("value_mensage"), 
                    user, mensagesDatabase.getInt("id_respond_parent"));
                
                mensage.setIdMensage(mensagesDatabase.getInt("id_respond"));
                arrayMensages.add(mensage);
            }
            
            return arrayMensages;
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