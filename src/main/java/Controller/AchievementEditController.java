package Controller;

import Model.DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AchievementEditController
{
    @FXML
    private Button addAchievementButton;
    @FXML
    private Button updateAchievementButton;
    @FXML
    private Button deleteAchievementButton;
    @FXML
    private TextField editAchievementIdField;
    @FXML
    private TextField editProjectIDField;
    @FXML
    private TextField editGetTimeField;
    @FXML
    private TextField editRankField;
    @FXML
    private TextField editTypeField;
    @FXML
    private TextField editPatentTypeField;
    @FXML
    private TextField editNameField;
    @FXML
    private TextField editInformationField;
    @FXML
    private Label messageLabel;

    public void addAchievementButtonAction(ActionEvent event) throws SQLException
    {
        AchievementDAO achievementDAO = new AchievementDAO();

        if(!editProjectIDField.getText().isBlank() && !editGetTimeField.getText().isBlank()
                && !editRankField.getText().isBlank() && !editTypeField.getText().isBlank()
                && !editNameField.getText().isBlank() && !editInformationField.getText().isBlank())
        {
            if(!isNumeric(editProjectIDField.getText().trim()) || !isNumeric(editRankField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int projectID = Integer.parseInt(editProjectIDField.getText().trim());
                String name = editNameField.getText();
                String getTime = editGetTimeField.getText().trim();
                int rank = Integer.parseInt(editRankField.getText().trim());
                String type = editTypeField.getText().trim();
                String patent_type = editPatentTypeField.getText().trim();
                String information = editInformationField.getText();

                boolean addSuccess = achievementDAO.addAchievement(projectID, name, getTime, rank, type);
                int achievementID = achievementDAO.selectAchievement(projectID, name, getTime, rank, type);

                if(type.equals("patent"))
                {
                    if(!editPatentTypeField.getText().isBlank())
                    {
                        PatentDAO patentDAO = new PatentDAO();
                        addSuccess = addSuccess && patentDAO.addPatent(achievementID, patent_type, information);
                    }
                }
                else if(type.equals("paper"))
                {
                    PaperDAO paperDAO = new PaperDAO();
                    addSuccess = addSuccess && paperDAO.addPaper(achievementID, information);
                }
                else if(type.equals("software_copyright"))
                {
                    SoftwareDAO softwareDAO = new SoftwareDAO();
                    addSuccess = addSuccess && softwareDAO.addSoftware(achievementID, information);
                }
                else
                    addSuccess = false;

                if(addSuccess)
                {
                    Stage currentStage = (Stage) addAchievementButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateAchievementButtonAction(ActionEvent event) throws SQLException
    {
        AchievementDAO achievementDAO = new AchievementDAO();

        if (!editAchievementIdField.getText().isBlank())
        {
            if (isNumeric(editAchievementIdField.getText().trim()))
            {
                int achievementID = Integer.parseInt(editAchievementIdField.getText().trim());
                String projectID = editProjectIDField.getText().trim();
                String name = editNameField.getText();
                String getTime = editGetTimeField.getText().trim();
                String rank = editRankField.getText().trim();
                String type = editTypeField.getText().trim();
                String patent_type = editPatentTypeField.getText().trim();
                String information = editInformationField.getText();

                if (!projectID.isEmpty() || !name.isEmpty() || !getTime.isEmpty() || !rank.isEmpty()
                        || !type.isEmpty() || !patent_type.isEmpty() || !information.isEmpty())
                {
                    projectID = projectID.isEmpty() ? null : projectID;
                    name = name.isEmpty() ? null : name;
                    getTime = getTime.isEmpty() ? null : getTime;
                    rank = rank.isEmpty() ? null : rank;
                    type = type.isEmpty() ? null : type;
                    patent_type = patent_type.isEmpty() ? null : patent_type;
                    information = information.isEmpty() ? null : information;

                    boolean updateSuccess = achievementDAO.updateAchievement(achievementID, projectID, name, getTime, rank, type);

                    if(type.equals("patent"))
                    {
                        PatentDAO patentDAO = new PatentDAO();
                        updateSuccess = updateSuccess && patentDAO.updatePatent(achievementID, patent_type, information);
                    }
                    else if(type.equals("paper"))
                    {
                        if(information != null)
                        {
                            PaperDAO paperDAO = new PaperDAO();
                            updateSuccess = updateSuccess && paperDAO.updatePaper(achievementID, information);
                        }
                    }
                    else if(type.equals("software_copyright"))
                    {
                        if(information != null)
                        {
                            SoftwareDAO softwareDAO = new SoftwareDAO();
                            updateSuccess = updateSuccess && softwareDAO.updateSoftware(achievementID, information);
                        }
                    }
                    else
                        updateSuccess = false;

                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateAchievementButton.getScene().getWindow();
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

    public void deleteAchievementButtonAction(ActionEvent event) throws SQLException
    {
        AchievementDAO achievementDAO = new AchievementDAO();

        if(!editAchievementIdField.getText().isBlank())
        {
            if(isNumeric(editAchievementIdField.getText().trim()))
            {
                int achievementID = Integer.parseInt(editAchievementIdField.getText());
                String type = achievementDAO.getAchievementsByAchievementId(achievementID).get(0).getType();

                boolean deleteSuccess = false;
                if(type.equals("patent"))
                {
                    PatentDAO patentDAO = new PatentDAO();
                    deleteSuccess = patentDAO.deletePatent(achievementID);
                }
                else if(type.equals("paper"))
                {
                    PaperDAO paperDAO = new PaperDAO();
                    deleteSuccess = paperDAO.deletePaper(achievementID);
                }
                else if(type.equals("software_copyright"))
                {
                    SoftwareDAO softwareDAO = new SoftwareDAO();
                    deleteSuccess = softwareDAO.deleteSoftware(achievementID);
                }

                deleteSuccess = deleteSuccess && achievementDAO.deleteAchievement(achievementID);

                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteAchievementButton.getScene().getWindow();
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
