package Controller;

import Model.DAO.ProjectDAO;
import Model.DAO.ResearcherDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ProjectEditController
{
    @FXML
    private Button addProjectButton;
    @FXML
    private Button updateProjectButton;
    @FXML
    private Button deleteProjectButton;
    @FXML
    private TextField editProjectIDField;
    @FXML
    private TextField editProjectNameField;
    @FXML
    private TextField editProjectContentField;
    @FXML
    private TextField editProjectHeadField;
    @FXML
    private TextField editProjectStartDateField;
    @FXML
    private TextField editProjectEndDateField;
    @FXML
    private TextField editProjectFundField;
    @FXML
    private Label messageLabel;

    public void addProjectButtonAction(ActionEvent event) throws SQLException
    {
        ProjectDAO projectDAO = new ProjectDAO();

        if(!editProjectIDField.getText().isBlank() && !editProjectNameField.getText().isBlank()
                && !editProjectContentField.getText().isBlank() && !editProjectHeadField.getText().isBlank()
                && !editProjectStartDateField.getText().isBlank() && !editProjectEndDateField.getText().isBlank() && !editProjectFundField.getText().isBlank())
        {
            if(!isNumeric(editProjectIDField.getText().trim()) || !isNumeric(editProjectFundField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int providedId = Integer.parseInt(editProjectIDField.getText().trim());
                String providedName = editProjectNameField.getText().trim();
                String providedContent = editProjectContentField.getText().trim();
                String providedHead = editProjectHeadField.getText().trim();
                String providedStartDate = editProjectStartDateField.getText().trim();
                String providedEndDate = editProjectEndDateField.getText().trim();
                double providedFund = Double.parseDouble(editProjectFundField.getText().trim());

                boolean addSuccess = projectDAO.addProject(providedId, providedName,
                        providedHead, providedContent, providedFund, providedStartDate, providedEndDate);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addProjectButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateProjectButtonAction(ActionEvent event) throws SQLException
    {
        ProjectDAO projectDAO = new ProjectDAO();

        if(!editProjectIDField.getText().isBlank())
        {
            if (isNumeric(editProjectIDField.getText().trim()) && isNumeric(editProjectFundField.getText().trim()))
            {
                int providedId = Integer.parseInt(editProjectIDField.getText().trim());
                String providedName = editProjectNameField.getText().trim();
                String providedContent = editProjectContentField.getText().trim();
                String providedHead = editProjectHeadField.getText().trim();
                String providedStartDate = editProjectStartDateField.getText().trim();
                String providedEndDate = editProjectEndDateField.getText().trim();
                String providedFund = editProjectFundField.getText().trim();

                if (!providedName.isEmpty() || !providedContent.isEmpty() || !providedHead.isEmpty()
                        || !providedStartDate.isEmpty() || !providedEndDate.isEmpty() || !providedFund.isEmpty())
                {
                    providedName = providedName.isEmpty() ? null : providedName;
                    providedContent = providedContent.isEmpty() ? null : providedContent;
                    providedHead = providedHead.isEmpty() ? null : providedHead;
                    providedStartDate = providedStartDate.isEmpty() ? null : providedStartDate;
                    providedEndDate = providedEndDate.isEmpty() ? null : providedEndDate;
                    providedFund = providedFund.isEmpty() ? null : providedFund;

                    boolean updateSuccess = projectDAO.updateProject(providedId, providedName,
                            providedHead, providedContent, providedFund, providedStartDate, providedEndDate);
                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateProjectButton.getScene().getWindow();
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

    public void deleteProjectButtonAction(ActionEvent event) throws SQLException
    {
        ProjectDAO projectDAO = new ProjectDAO();

        if(!editProjectIDField.getText().isBlank())
        {
            if(isNumeric(editProjectIDField.getText().trim()))
            {
                int providedId = Integer.parseInt(editProjectIDField.getText().trim());

                boolean deleteSuccess = projectDAO.deleteProject(providedId);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteProjectButton.getScene().getWindow();
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
