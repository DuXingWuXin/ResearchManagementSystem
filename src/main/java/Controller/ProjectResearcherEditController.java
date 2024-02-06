package Controller;

import Model.DAO.ProjectDAO;
import Model.DAO.Project_ResearcherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ProjectResearcherEditController
{
    @FXML
    private Button addProResButton;
    @FXML
    private Button updateProResButton;
    @FXML
    private Button deleteProResButton;
    @FXML
    private TextField editProResProjectIDField;
    @FXML
    private TextField editProResResearcherIDField;
    @FXML
    private TextField editProResJoinDateField;
    @FXML
    private TextField editProResFundField;
    @FXML
    private TextField editProResWorkloadField;
    @FXML
    private Label messageLabel;

    public void addProResButtonAction(ActionEvent event) throws SQLException
    {
        Project_ResearcherDAO project_researcherDAO = new Project_ResearcherDAO();

        if(!editProResProjectIDField.getText().isBlank() && !editProResResearcherIDField.getText().isBlank()
                && !editProResJoinDateField.getText().isBlank() && !editProResFundField.getText().isBlank() && !editProResWorkloadField.getText().isBlank())
        {
            if(isNumeric(editProResProjectIDField.getText().trim()) && isNumeric(editProResResearcherIDField.getText().trim())
            && isNumeric(editProResWorkloadField.getText().trim()) && isNumeric(editProResFundField.getText().trim()))
            {
                int providedProId = Integer.parseInt(editProResProjectIDField.getText().trim());
                int providedResId = Integer.parseInt(editProResResearcherIDField.getText().trim());
                String providedJoinDate = editProResJoinDateField.getText().trim();
                double providedWorkload = Double.parseDouble(editProResWorkloadField.getText().trim());
                double providedFund = Double.parseDouble(editProResFundField.getText().trim());

                boolean addSuccess = project_researcherDAO.addProjectResearcher(providedProId, providedResId,
                        providedJoinDate, providedWorkload, providedFund);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addProResButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void updateProResButtonAction(ActionEvent event) throws SQLException
    {
        Project_ResearcherDAO project_researcherDAO = new Project_ResearcherDAO();

        if(!editProResProjectIDField.getText().isBlank() && !editProResResearcherIDField.getText().isBlank())
        {
            if(isNumeric(editProResProjectIDField.getText().trim()))
            {
                int providedProId = Integer.parseInt(editProResProjectIDField.getText().trim());
                int providedResId = Integer.parseInt(editProResResearcherIDField.getText().trim());
                String providedJoinDate = editProResJoinDateField.getText().trim();
                String providedWorkload = editProResWorkloadField.getText().trim();
                String providedFund = editProResFundField.getText().trim();

                if (!providedJoinDate.isEmpty() || !providedWorkload.isEmpty() || !providedFund.isEmpty())
                {
                    providedJoinDate = providedJoinDate.isEmpty() ? null : providedJoinDate;
                    providedWorkload = providedWorkload.isEmpty() ? null : providedWorkload;
                    providedFund = providedFund.isEmpty() ? null : providedFund;

                    boolean updateSuccess = project_researcherDAO.updateProjectResearcher(providedProId, providedResId,
                            providedJoinDate, providedWorkload, providedFund);
                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateProResButton.getScene().getWindow();
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

    public void deleteProResButtonAction(ActionEvent event) throws SQLException
    {
        Project_ResearcherDAO project_researcherDAO = new Project_ResearcherDAO();

        if(!editProResProjectIDField.getText().isBlank() && !editProResResearcherIDField.getText().isBlank())
        {
            if(isNumeric(editProResProjectIDField.getText().trim()) && isNumeric(editProResResearcherIDField.getText().trim()))
            {
                int providedProId = Integer.parseInt(editProResProjectIDField.getText().trim());
                int providedResId = Integer.parseInt(editProResResearcherIDField.getText().trim());

                boolean deleteSuccess = project_researcherDAO.deleteProjectResearcher(providedProId, providedResId);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteProResButton.getScene().getWindow();
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
