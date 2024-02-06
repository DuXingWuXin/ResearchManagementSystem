package Controller;

import Model.DAO.LaboratoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LaboratoryEditController
{
    @FXML
    private Button addLaboratoryButton;
    @FXML
    private Button deleteLaboratoryButton;
    @FXML
    private Button updateLaboratoryButton;
    @FXML
    private TextField editLaboratoryIDField;
    @FXML
    private TextField editLaboratoryNameField;
    @FXML
    private TextField editLaboratoryIntroductionField;
    @FXML
    private TextField editLaboratoryEmployeeIDField;
    @FXML
    private Label messageLabel;


    public void addLaboratoryButtonAction(ActionEvent event) throws SQLException
    {
        LaboratoryDAO laboratoryDAO = new LaboratoryDAO();

        if(!editLaboratoryIDField.getText().isBlank())
        {
            if(isNumeric(editLaboratoryIDField.getText().trim()))
            {
                int providedLaboratoryID = Integer.parseInt(editLaboratoryIDField.getText().trim());
                String providedLaboratoryName = editLaboratoryNameField.getText().trim();
                String providedLaboratoryIntroduction = editLaboratoryIntroductionField.getText();

                if(!editLaboratoryEmployeeIDField.getText().isBlank())
                {
                    String providedEmployeeID = editLaboratoryEmployeeIDField.getText().trim();
                    if(isNumeric(providedEmployeeID))
                    {
                        boolean addSuccess = laboratoryDAO.addLaboratory(providedLaboratoryID, providedLaboratoryName, providedLaboratoryIntroduction, providedEmployeeID);
                        if(addSuccess)
                        {
                            Stage currentStage = (Stage) addLaboratoryButton.getScene().getWindow();
                            currentStage.close();
                        }
                        else
                            messageLabel.setText("信息错误，请重试");
                    }
                }
            }
        }
        else
            messageLabel.setText("信息错误，请重试");
    }

    public void updateLaboratoryButtonAction(ActionEvent event) throws SQLException
    {
        LaboratoryDAO laboratoryDAO = new LaboratoryDAO();

        if (!editLaboratoryIDField.getText().isBlank())
        {
            if (isNumeric(editLaboratoryIDField.getText().trim()))
            {
                int providedLaboratoryID = Integer.parseInt(editLaboratoryIDField.getText().trim());
                String providedLaboratoryName = editLaboratoryNameField.getText().trim();
                String providedLaboratoryIntroduction = editLaboratoryIntroductionField.getText();
                String providedEmployeeID = editLaboratoryEmployeeIDField.getText().trim();

                if (!providedLaboratoryName.isEmpty() || !providedLaboratoryIntroduction.isEmpty() || !providedEmployeeID.isEmpty())
                {
                    providedLaboratoryName = providedLaboratoryName.isEmpty() ? null : providedLaboratoryName;
                    providedLaboratoryIntroduction = providedLaboratoryIntroduction.isEmpty() ? null : providedLaboratoryIntroduction;
                    providedEmployeeID = providedEmployeeID.isEmpty() ? null : providedEmployeeID;

                    boolean updateSuccess = laboratoryDAO.updateLaboratory(providedLaboratoryID, providedLaboratoryName, providedLaboratoryIntroduction, providedEmployeeID);

                    if (updateSuccess)
                    {
                        Stage currentStage = (Stage) updateLaboratoryButton.getScene().getWindow();
                        currentStage.close();
                    }
                }
                else
                    messageLabel.setText("没有提供更新信息");
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void deleteLaboratoryButtonAction(ActionEvent event) throws SQLException
    {
        LaboratoryDAO laboratoryDAO = new LaboratoryDAO();

        if(!editLaboratoryIDField.getText().isBlank())
        {
            if(isNumeric(editLaboratoryIDField.getText().trim()))
            {
                int providedLaboratoryID = Integer.parseInt(editLaboratoryIDField.getText().trim());

                boolean deleteSuccess = laboratoryDAO.deleteLaboratory(providedLaboratoryID);

                if (deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteLaboratoryButton.getScene().getWindow();
                    currentStage.close();
                }
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
