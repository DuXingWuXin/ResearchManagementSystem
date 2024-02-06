package Controller;

import Model.DAO.Project_ResearcherDAO;
import Model.DAO.SubtopicDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SubtopicEditController
{
    @FXML
    private Button addProSubButton;
    @FXML
    private Button updateProSubButton;
    @FXML
    private Button deleteProSubButton;
    @FXML
    private TextField editProSubProjectIDField;
    @FXML
    private TextField editProSubSubtopicIDField;
    @FXML
    private TextField editProSubHeadField;
    @FXML
    private TextField editProSubTechnologyField;
    @FXML
    private TextField editProSubFundField;
    @FXML
    private TextField editProSubEndDateField;
    @FXML
    private Label messageLabel;

    public void addProSubButtonAction(ActionEvent event) throws SQLException
    {
        SubtopicDAO subtopicDAO = new SubtopicDAO();

        if(!editProSubProjectIDField.getText().isBlank()
                && !editProSubHeadField.getText().isBlank() && !editProSubTechnologyField.getText().isBlank()
                && !editProSubFundField.getText().isBlank() && !editProSubEndDateField.getText().isBlank())
        {
            if(isNumeric(editProSubProjectIDField.getText().trim()) && isNumeric(editProSubFundField.getText().trim()))
            {
                int providedProId = Integer.parseInt(editProSubProjectIDField.getText().trim());
                String providedHead = editProSubHeadField.getText().trim();
                String providedEndDate = editProSubEndDateField.getText().trim();
                double providedFund = Double.parseDouble(editProSubFundField.getText().trim());
                String providedTechnology = editProSubTechnologyField.getText().trim();

                boolean addSuccess = subtopicDAO.addSubtopic(providedProId, providedHead,
                        providedEndDate, providedFund, providedTechnology);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addProSubButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void updateProSubButtonAction(ActionEvent event) throws SQLException
    {
        SubtopicDAO subtopicDAO = new SubtopicDAO();

        if(!editProSubSubtopicIDField.getText().isBlank())
        {
            if(isNumeric(editProSubSubtopicIDField.getText().trim()))
            {
                int providedSubId = Integer.parseInt(editProSubSubtopicIDField.getText().trim());
                String providedProId = editProSubProjectIDField.getText().trim();
                String providedHead = editProSubHeadField.getText().trim();
                String providedEndDate = editProSubEndDateField.getText().trim();
                String providedFund = editProSubFundField.getText().trim();
                String providedTechnology = editProSubTechnologyField.getText().trim();

                if (!providedProId.isEmpty() || !providedHead.isEmpty() || !providedEndDate.isEmpty()
                        || !providedFund.isEmpty() || !providedTechnology.isEmpty())
                {
                    providedProId = providedProId.isEmpty() ? null : providedProId;
                    providedHead = providedHead.isEmpty() ? null : providedHead;
                    providedEndDate = providedEndDate.isEmpty() ? null : providedEndDate;
                    providedFund = providedFund.isEmpty() ? null : providedFund;
                    providedTechnology = providedTechnology.isEmpty() ? null : providedTechnology;

                    boolean updateSuccess = subtopicDAO.updateSubtopic(providedProId, providedSubId, providedHead,
                            providedEndDate, providedFund, providedTechnology);
                    if (updateSuccess) {
                        Stage currentStage = (Stage) updateProSubButton.getScene().getWindow();
                        currentStage.close();
                    } else
                        messageLabel.setText("信息错误，请重试");
                }
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void deleteProSubButtonAction(ActionEvent event) throws SQLException
    {
        SubtopicDAO subtopicDAO = new SubtopicDAO();

        if(!editProSubSubtopicIDField.getText().isBlank())
        {
            if(isNumeric(editProSubSubtopicIDField.getText().trim()))
            {
                int providedSubId = Integer.parseInt(editProSubSubtopicIDField.getText().trim());

                boolean deleteSuccess = subtopicDAO.deleteSubtopic(providedSubId);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteProSubButton.getScene().getWindow();
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
