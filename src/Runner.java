import util.DBConnector;


public class Runner {

    public static void main(String[] args) {
        Thread thread = new Thread(new UserController());
        DBConnector.connectToDB();
        try {
            thread.start();
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnectFromDB();
        }
    }
}
