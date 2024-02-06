package Controller;

import Model.DAO.SecretaryDAO;
import Model.DAO.SubtopicDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SecretaryEditController
{
    @FXML
    private Button addSecretaryButton;
    @FXML
    private Button updateSecretaryButton;
    @FXML
    private Button deleteSecretaryButton;
    @FXML
    private TextField editSecretaryIDField;
    @FXML
    private TextField editSecretaryNameField;
    @FXML
    private TextField editSecretaryHireDateField;
    @FXML
    private TextField editSecretaryDutyField;
    @FXML
    private TextField editSecretaryGenderField;
    @FXML
    private TextField editSecretaryAgeField;
    @FXML
    private Label messageLabel;

    public void addSecretaryButtonAction(ActionEvent event) throws SQLException
    {
        SecretaryDAO secretaryDAO = new SecretaryDAO();

        if(!editSecretaryIDField.getText().isBlank() && !editSecretaryNameField.getText().isBlank() && !editSecretaryHireDateField.getText().isBlank()
                && !editSecretaryDutyField.getText().isBlank() && !editSecretaryGenderField.getText().isBlank() && !editSecretaryAgeField.getText().isBlank())
        {
            if(isNumeric(editSecretaryIDField.getText().trim()) && isNumeric(editSecretaryAgeField.getText().trim()))
            {
                int providedId = Integer.parseInt(editSecretaryIDField.getText().trim());
                String providedName = editSecretaryNameField.getText().trim();
                String providedHireDate = editSecretaryHireDateField.getText().trim();
                String providedDuty = editSecretaryDutyField.getText().trim();
                String providedGender = editSecretaryGenderField.getText().trim();
                int providedAge = Integer.parseInt(editSecretaryAgeField.getText().trim());

                boolean addSuccess = secretaryDAO.addSecretary(providedId, providedName,
                        providedGender, providedAge, providedHireDate, providedDuty);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addSecretaryButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void updateSecretaryButtonAction(ActionEvent event) throws SQLException
    {
        SecretaryDAO secretaryDAO = new SecretaryDAO();

        if(!editSecretaryIDField.getText().isBlank())
        {
            if(isNumeric(editSecretaryIDField.getText().trim()))
            {
                int providedId = Integer.parseInt(editSecretaryIDField.getText().trim());
                String providedName = editSecretaryNameField.getText().trim();
                String providedHireDate = editSecretaryHireDateField.getText().trim();
                String providedDuty = editSecretaryDutyField.getText().trim();
                String providedGender = editSecretaryGenderField.getText().trim();
                String providedAge = editSecretaryAgeField.getText().trim();

                if (!providedName.isEmpty() || !providedHireDate.isEmpty() || !providedDuty.isEmpty()
                        || !providedGender.isEmpty() || !providedAge.isEmpty())
                {
                    providedName = providedName.isEmpty() ? null : providedName;
                    providedHireDate = providedHireDate.isEmpty() ? null : providedHireDate;
                    providedDuty = providedDuty.isEmpty() ? null : providedDuty;
                    providedGender = providedGender.isEmpty() ? null : providedGender;
                    providedAge = providedAge.isEmpty() ? null : providedAge;

                    boolean updateSuccess = secretaryDAO.updateSecretary(providedId, providedName,
                            providedGender, providedAge, providedHireDate, providedDuty);
                    if (updateSuccess)
                    {
                        Stage currentStage = (Stage) updateSecretaryButton.getScene().getWindow();
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

    public void deleteSecretaryButtonAction(ActionEvent event) throws SQLException
    {
        SecretaryDAO secretaryDAO = new SecretaryDAO();

        if(!editSecretaryIDField.getText().isBlank())
        {
            if(isNumeric(editSecretaryIDField.getText().trim()))
            {
                int providedId = Integer.parseInt(editSecretaryIDField.getText().trim());

                boolean deleteSuccess = secretaryDAO.deleteSecretary(providedId);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteSecretaryButton.getScene().getWindow();
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
