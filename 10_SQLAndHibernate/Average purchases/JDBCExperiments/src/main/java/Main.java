import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=UTC";
        String user = "root";
        String pass = "afrby736132";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select course_name, count(course_name)/(month(max(subscription_date)) - month(min(subscription_date))) as avgPerMonth from purchaselist group by course_name");
            while (resultSet.next()){
                String courseName = resultSet.getString("course_name");
                double avgPerMonth = resultSet.getDouble("avgPerMonth");
                System.out.println(courseName + " - " + avgPerMonth);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
