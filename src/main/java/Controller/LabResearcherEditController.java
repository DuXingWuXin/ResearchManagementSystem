package Controller;

import Model.DAO.DirectorDAO;
import Model.DAO.ResearcherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LabResearcherEditController
{
    @FXML
    private Button addLabResearcherButton;
    @FXML
    private Button updateLabResearcherButton;
    @FXML
    private Button deleteLabResearcherButton;
    @FXML
    private TextField editLabResearcherIDField;
    @FXML
    private TextField editLabResearcherNameField;
    @FXML
    private TextField editLabResearcherAreaField;
    @FXML
    private TextField editLabResearcherTitleField;
    @FXML
    private TextField editLabResearcherGenderField;
    @FXML
    private TextField editLabResearcherAgeField;
    @FXML
    private TextField editLabResearcherLabIDField;
    @FXML
    private Label messageLabel;

    public void addLabResearcherButtonAction(ActionEvent event) throws SQLException
    {
        ResearcherDAO researcherDAO = new ResearcherDAO();

        if(!editLabResearcherIDField.getText().isBlank() && !editLabResearcherNameField.getText().isBlank()
                && !editLabResearcherAreaField.getText().isBlank() && !editLabResearcherTitleField.getText().isBlank()
                && !editLabResearcherGenderField.getText().isBlank() && !editLabResearcherAgeField.getText().isBlank() && !editLabResearcherLabIDField.getText().isBlank())
        {
            if(!isNumeric(editLabResearcherIDField.getText().trim()) || !isNumeric(editLabResearcherAgeField.getText().trim())
                    || !isNumeric(editLabResearcherLabIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int providedEmployeeId = Integer.parseInt(editLabResearcherIDField.getText().trim());
                String providedName = editLabResearcherNameField.getText().trim();
                String providedGender = editLabResearcherGenderField.getText().trim();
                String providedTitle = editLabResearcherTitleField.getText().trim();
                int providedAge = Integer.parseInt(editLabResearcherAgeField.getText().trim());
                String providedResearchArea = editLabResearcherAreaField.getText().trim();
                int providedLabId = Integer.parseInt(editLabResearcherLabIDField.getText().trim());

                boolean addSuccess = researcherDAO.addResearcher(providedEmployeeId, providedName,
                        providedGender, providedTitle, providedAge, providedResearchArea, providedLabId);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addLabResearcherButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateLabResearcherButtonAction(ActionEvent event) throws SQLException
    {
        ResearcherDAO researcherDAO = new ResearcherDAO();

        if(!editLabResearcherIDField.getText().isBlank())
        {
            if(isNumeric(editLabResearcherIDField.getText().trim()))
            {
                int providedEmployeeId = Integer.parseInt(editLabResearcherIDField.getText().trim());
                String providedName = editLabResearcherNameField.getText().trim();
                String providedGender = editLabResearcherGenderField.getText().trim();
                String providedTitle = editLabResearcherTitleField.getText().trim();
                String providedAge = editLabResearcherAgeField.getText().trim();
                String providedResearchArea = editLabResearcherAreaField.getText().trim();
                String providedLabId = editLabResearcherLabIDField.getText().trim();

                if (!providedName.isEmpty() || !providedGender.isEmpty() || !providedTitle.isEmpty()
                        || !providedAge.isEmpty() || !providedResearchArea.isEmpty() || !providedLabId.isEmpty())
                {
                    providedName = providedName.isEmpty() ? null : providedName;
                    providedGender = providedGender.isEmpty() ? null : providedGender;
                    providedTitle = providedTitle.isEmpty() ? null : providedTitle;
                    providedAge = providedAge.isEmpty() ? null : providedAge;
                    providedResearchArea = providedResearchArea.isEmpty() ? null : providedResearchArea;
                    providedLabId = providedLabId.isEmpty() ? null : providedLabId;

                    boolean updateSuccess = researcherDAO.updateResearcher(providedEmployeeId, providedName,
                            providedGender, providedTitle, providedAge, providedResearchArea, providedLabId);
                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateLabResearcherButton.getScene().getWindow();
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

    public void deleteLabResearcherButtonAction(ActionEvent event) throws SQLException
    {
        ResearcherDAO researcherDAO = new ResearcherDAO();

        if(!editLabResearcherIDField.getText().isBlank())
        {
            if(isNumeric(editLabResearcherIDField.getText().trim()))
            {
                int providedEmployeeId = Integer.parseInt(editLabResearcherIDField.getText().trim());

                boolean deleteSuccess = researcherDAO.deleteResearcher(providedEmployeeId);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteLabResearcherButton.getScene().getWindow();
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
