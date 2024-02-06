import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application
{
    @Override
    public void start(Stage loginStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        loginStage.initStyle(StageStyle.DECORATED);
        loginStage.setTitle("科研管理系统");
        loginStage.setScene(new Scene(root, 520, 400));
        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}