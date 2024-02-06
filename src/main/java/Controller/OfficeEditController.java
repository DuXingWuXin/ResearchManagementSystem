package Controller;

import Model.DAO.OfficeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class OfficeEditController
{
    @FXML
    private Button addOfficeButton;
    @FXML
    private Button updateOfficeButton;
    @FXML
    private Button deleteOfficeButton;
    @FXML
    private TextField editOfficeIDField;
    @FXML
    private TextField editOfficeAreaField;
    @FXML
    private TextField editOfficeLocationField;
    @FXML
    private TextField editOfficeLabIDField;
    @FXML
    private Label messageLabel;

    public void addOfficeButtonAction(ActionEvent event) throws SQLException
    {
        OfficeDAO officeDAO = new OfficeDAO();

        if(!editOfficeAreaField.getText().isBlank() && !editOfficeLocationField.getText().isBlank() && !editOfficeLabIDField.getText().isBlank())
        {
            if(!isNumeric(editOfficeLabIDField.getText().trim()) || !isNumeric(editOfficeAreaField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int providedLabID = Integer.parseInt(editOfficeLabIDField.getText().trim());
                String providedLocation = editOfficeLocationField.getText().trim();
                double providedArea = Double.parseDouble(editOfficeAreaField.getText().trim());

                boolean addSuccess = officeDAO.addOffice(providedLabID, providedLocation, providedArea);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addOfficeButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateOfficeButtonAction(ActionEvent event) throws SQLException
    {
        OfficeDAO officeDAO = new OfficeDAO();

        if(!editOfficeIDField.getText().isBlank())
        {
            if (isNumeric(editOfficeIDField.getText().trim()))
            {
                int providedOfficeID = Integer.parseInt(editOfficeIDField.getText());
                String providedLabID = editOfficeLabIDField.getText();
                String providedLocation = editOfficeLocationField.getText();
                String providedArea = editOfficeAreaField.getText();

                if (!providedLabID.isEmpty() || !providedLocation.isEmpty() || !providedArea.isEmpty())
                {
                    providedLabID = providedLabID.isEmpty() ? null : providedLabID;
                    providedLocation = providedLocation.isEmpty() ? null : providedLocation;
                    providedArea = providedArea.isEmpty() ? null : providedArea;

                    boolean updateSuccess = officeDAO.updateOffice(providedOfficeID,providedLabID, providedLocation, providedArea);
                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateOfficeButton.getScene().getWindow();
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

    public void deleteOfficeButtonAction(ActionEvent event) throws SQLException
    {
        OfficeDAO officeDAO = new OfficeDAO();

        if(!editOfficeIDField.getText().isBlank())
        {
            if(isNumeric(editOfficeIDField.getText().trim()))
            {
                int providedOfficeID = Integer.parseInt(editOfficeIDField.getText().trim());

                boolean deleteSuccess = officeDAO.deleteOffice(providedOfficeID);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteOfficeButton.getScene().getWindow();
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
