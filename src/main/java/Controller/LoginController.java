package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Model.DAO.UserDAO;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.sql.SQLException;


public class LoginController
{
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    public void loginButtonAction(ActionEvent event) throws SQLException
    {
        UserDAO userDAO = new UserDAO();
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank())
        {
            String providedId = usernameField.getText();
            String providedPwd = passwordField.getText();

            int user_type = userDAO.login(providedId,providedPwd);

            if(user_type != 10)
            {
                enterMainPage();
                UserType.user_type = user_type;
            }
            else
                loginMessageLabel.setText("用户名或密码错误，请重试");
        }
        else
            loginMessageLabel.setText("请输入用户名和密码");
    }

    public void enterMainPage()
    {
        try
        {
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();

            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));

            Stage mainStage = new Stage();
            mainStage.initStyle(StageStyle.DECORATED);
            mainStage.setTitle("科研管理系统");
            mainStage.setScene(new Scene(root, 1000, 800));
            mainStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }


}
