package Controller;

import Model.DAO.Achievement_ContributorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ContributorEditController
{
    @FXML
    private Button addContributorButton;
    @FXML
    private Button updateContributorButton;
    @FXML
    private Button deleteContributorButton;
    @FXML
    private TextField editAchievementIdField;
    @FXML
    private TextField editContributorIDField;
    @FXML
    private Label messageLabel;

    public void addContributorButtonAction(ActionEvent event) throws SQLException
    {
        Achievement_ContributorDAO achievement_contributorDAO = new Achievement_ContributorDAO();

        if(!editAchievementIdField.getText().isBlank() && !editContributorIDField.getText().isBlank() )
        {
            if(!isNumeric(editAchievementIdField.getText().trim()) || !isNumeric(editContributorIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int achievementID = Integer.parseInt(editAchievementIdField.getText().trim());
                int contributorID = Integer.parseInt(editContributorIDField.getText().trim());

                boolean addSuccess = achievement_contributorDAO.addContributor(achievementID, contributorID);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addContributorButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateContributorButtonAction(ActionEvent event) throws SQLException
    {
        Achievement_ContributorDAO achievement_contributorDAO = new Achievement_ContributorDAO();

        if(!editAchievementIdField.getText().isBlank() && !editContributorIDField.getText().isBlank() )
        {
            if(!isNumeric(editAchievementIdField.getText().trim()) || !isNumeric(editContributorIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int achievementID = Integer.parseInt(editAchievementIdField.getText().trim());
                int contributorID = Integer.parseInt(editContributorIDField.getText().trim());

                boolean updateSuccess = achievement_contributorDAO.updateContributor(achievementID, contributorID);
                if(updateSuccess)
                {
                    Stage currentStage = (Stage) updateContributorButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void deleteContributorButtonAction(ActionEvent event) throws SQLException
    {
        Achievement_ContributorDAO achievement_contributorDAO = new Achievement_ContributorDAO();

        if(!editAchievementIdField.getText().isBlank() && !editContributorIDField.getText().isBlank() )
        {
            if(!isNumeric(editAchievementIdField.getText().trim()) || !isNumeric(editContributorIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int achievementID = Integer.parseInt(editAchievementIdField.getText().trim());
                int contributorID = Integer.parseInt(editContributorIDField.getText().trim());

                boolean deleteSuccess = achievement_contributorDAO.deleteContributor(achievementID, contributorID);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteContributorButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public boolean isNumeric(String input)
    {
        try
        {
            // 尝试将输入字符串转换为数字
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
