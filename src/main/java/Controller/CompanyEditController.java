package Controller;

import Model.DAO.CompanyDAO;
import Model.DAO.DirectorDAO;
import Model.DAO.Project_CompanyDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CompanyEditController
{
    @FXML
    private Button addCompanyButton;
    @FXML
    private Button updateCompanyButton;
    @FXML
    private Button deleteCompanyButton;
    @FXML
    private TextField editCompanyIDField;
    @FXML
    private TextField editCompanyNameField;
    @FXML
    private TextField editCompanyAddressField;
    @FXML
    private TextField editCompanyProjectIDField;
    @FXML
    private TextField editCompanyRoleField;
    @FXML
    private Label messageLabel;

    public void addCompanyButtonAction(ActionEvent event) throws SQLException
    {
        CompanyDAO companyDAO = new CompanyDAO();
        Project_CompanyDAO project_companyDAO = new Project_CompanyDAO();

        if(!editCompanyNameField.getText().isBlank() && !editCompanyAddressField.getText().isBlank()
                && !editCompanyProjectIDField.getText().isBlank() && !editCompanyRoleField.getText().isBlank())
        {
            if(!isNumeric(editCompanyProjectIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                String companyName = editCompanyNameField.getText().trim();
                String companyAddress = editCompanyAddressField.getText().trim();
                int projectID = Integer.parseInt(editCompanyProjectIDField.getText().trim());
                String companyRole = editCompanyRoleField.getText().trim();

                boolean addCompanySuccess = companyDAO.addCompany(companyName, companyAddress);
                int companyID = companyDAO.getCompanyByName(companyName).get(0).getCompany_id();
                boolean addRelationshipSuccess = project_companyDAO.addProjectCompany(projectID, companyID, companyRole);
                if(addCompanySuccess && addRelationshipSuccess)
                {
                    Stage currentStage = (Stage) addCompanyButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateCompanyButtonAction(ActionEvent event) throws SQLException
    {
        CompanyDAO companyDAO = new CompanyDAO();
        Project_CompanyDAO project_companyDAO = new Project_CompanyDAO();

        if(!editCompanyIDField.getText().isBlank())
        {
            if(isNumeric(editCompanyIDField.getText().trim()) && isNumeric(editCompanyProjectIDField.getText().trim()))
            {
                int companyID = Integer.parseInt(editCompanyIDField.getText().trim());
                String companyName = editCompanyNameField.getText().trim();
                String companyAddress = editCompanyAddressField.getText().trim();
                int projectID = Integer.parseInt(editCompanyProjectIDField.getText().trim());
                String companyRole = editCompanyRoleField.getText().trim();

                if (!companyName.isEmpty() || !companyAddress.isEmpty() || !companyRole.isEmpty())
                {
                    companyName = companyName.isEmpty() ? null : companyName;
                    companyAddress = companyAddress.isEmpty() ? null : companyAddress;
                    companyRole = companyRole.isEmpty() ? null : companyRole;

                    boolean updateCompanySuccess = companyDAO.updateCompany(companyID, companyName, companyAddress);
                    boolean updateRelationshipSuccess = project_companyDAO.updateProjectCompany(projectID, companyID, companyRole);

                    if(updateCompanySuccess && updateRelationshipSuccess)
                    {
                        Stage currentStage = (Stage) updateCompanyButton.getScene().getWindow();
                        currentStage.close();
                    }
                    else
                        messageLabel.setText("信息错误，请重试");
                }
            }
            else
                messageLabel.setText("信息错误，请重试");
        }
    }

    public void deleteCompanyButtonAction(ActionEvent event) throws SQLException
    {
        CompanyDAO companyDAO = new CompanyDAO();
        Project_CompanyDAO project_companyDAO = new Project_CompanyDAO();

        if(!editCompanyIDField.getText().isBlank() && !editCompanyProjectIDField.getText().isBlank())
        {
            if(!isNumeric(editCompanyIDField.getText().trim()) || !isNumeric(editCompanyProjectIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int companyID = Integer.parseInt(editCompanyIDField.getText().trim());
                int projectID = Integer.parseInt(editCompanyProjectIDField.getText().trim());

                boolean deleteRelationshipSuccess = project_companyDAO.deleteProjectCompany(projectID, companyID);
                boolean deleteCompanySuccess = companyDAO.deleteCompany(companyID);

                if(deleteCompanySuccess && deleteRelationshipSuccess)
                {
                    Stage currentStage = (Stage) deleteCompanyButton.getScene().getWindow();
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
