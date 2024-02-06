package Controller;

import Model.DAO.DirectorDAO;
import Model.DAO.OfficeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DirectorEditController
{
    @FXML
    private Button addDirectorButton;
    @FXML
    private Button updateDirectorButton;
    @FXML
    private Button deleteDirectorButton;
    @FXML
    private TextField editDirectorIDField;
    @FXML
    private TextField editTenureField;
    @FXML
    private TextField editStartDateField;
    @FXML
    private TextField editDirectorLabIDField;
    @FXML
    private Label messageLabel;

    public void addDirectorButtonAction(ActionEvent event) throws SQLException
    {
        DirectorDAO directorDAO = new DirectorDAO();

        if(!editDirectorIDField.getText().isBlank() && !editTenureField.getText().isBlank()
                && !editStartDateField.getText().isBlank() && !editDirectorLabIDField.getText().isBlank())
        {
            if(!isNumeric(editDirectorIDField.getText().trim()) || !isNumeric(editTenureField.getText().trim()) || !isNumeric(editDirectorLabIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int providedDirectorID = Integer.parseInt(editDirectorIDField.getText().trim());
                int providedTenure = Integer.parseInt(editTenureField.getText().trim());
                int providedLabID = Integer.parseInt(editDirectorLabIDField.getText().trim());
                String providedStartDate = editStartDateField.getText().trim();

                boolean addSuccess = directorDAO.addDirector(providedDirectorID, providedStartDate, providedTenure, providedLabID);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addDirectorButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateDirectorButtonAction(ActionEvent event) throws SQLException
    {
        DirectorDAO directorDAO = new DirectorDAO();

        if(!editDirectorLabIDField.getText().isBlank())
        {
            if(isNumeric(editDirectorLabIDField.getText().trim()))
            {
                String providedDirectorID = editDirectorIDField.getText().trim();
                String providedTenure = editTenureField.getText().trim();
                String providedStartDate = editStartDateField.getText().trim();
                int providedLabID = Integer.parseInt(editDirectorLabIDField.getText().trim());

                if(!providedDirectorID.isEmpty() || !providedTenure.isEmpty() || !providedStartDate.isEmpty())
                {
                    providedDirectorID = providedDirectorID.isEmpty() ? null : providedDirectorID;
                    providedTenure = providedTenure.isEmpty() ? null : providedTenure;
                    providedStartDate = providedStartDate.isEmpty() ? null : providedStartDate;

                    boolean updateSuccess = directorDAO.updateDirector(providedDirectorID, providedStartDate, providedTenure, providedLabID);
                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateDirectorButton.getScene().getWindow();
                        currentStage.close();
                    }
                    else
                        messageLabel.setText("信息错误，请重试");
                }
                else
                    messageLabel.setText("没有提供更新信息");
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void deleteDirectorButtonAction(ActionEvent event) throws SQLException
    {
        DirectorDAO directorDAO = new DirectorDAO();

        if(!editDirectorLabIDField.getText().isBlank())
        {
            if(isNumeric(editDirectorLabIDField.getText().trim()))
            {
                int providedLabID = Integer.parseInt(editDirectorLabIDField.getText().trim());

                boolean deleteSuccess = directorDAO.deleteDirector(providedLabID);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteDirectorButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
            else
                messageLabel.setText("信息错误，请重试");
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
