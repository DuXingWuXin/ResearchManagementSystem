package Controller;

import Model.DAO.CompanyDAO;
import Model.DAO.ContactDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ContactEditController
{
    @FXML
    private Button addContactButton;
    @FXML
    private Button updateContactButton;
    @FXML
    private Button deleteContactButton;
    @FXML
    private TextField editCompanyIDField;
    @FXML
    private TextField editContactIDField;
    @FXML
    private TextField editContactNameField;
    @FXML
    private TextField editContactRoleField;
    @FXML
    private TextField editContactMobilePhoneField;
    @FXML
    private TextField editContactOfficePhoneField;
    @FXML
    private TextField editContactEmailField;
    @FXML
    private Label messageLabel;

    public void addContactButtonAction(ActionEvent event) throws SQLException
    {
        ContactDAO contactDAO = new ContactDAO();

        if(!editCompanyIDField.getText().isBlank() && !editContactNameField.getText().isBlank() && !editContactRoleField.getText().isBlank()
                && !editContactMobilePhoneField.getText().isBlank() && !editContactOfficePhoneField.getText().isBlank()
                && !editContactEmailField.getText().isBlank())
        {
            if(!isNumeric(editCompanyIDField.getText().trim()))
                messageLabel.setText("信息错误，请重试");
            else
            {
                int providedCompanyID = Integer.parseInt(editCompanyIDField.getText().trim());
                String providedName = editContactNameField.getText().trim();
                String providedRole = editContactRoleField.getText().trim();
                String providedMobilePhone = editContactMobilePhoneField.getText().trim();
                String providedOfficePhone = editContactOfficePhoneField.getText().trim();
                String providedEmail = editContactEmailField.getText().trim();

                boolean addSuccess = contactDAO.addContact(providedCompanyID, providedName, providedOfficePhone, providedMobilePhone,
                        providedEmail, providedRole);
                if(addSuccess)
                {
                    Stage currentStage = (Stage) addContactButton.getScene().getWindow();
                    currentStage.close();
                }
                else
                    messageLabel.setText("信息错误，请重试");
            }
        }
    }

    public void updateContactButtonAction(ActionEvent event) throws SQLException
    {
        ContactDAO contactDAO = new ContactDAO();

        if(!editContactIDField.getText().isBlank())
        {
            if (isNumeric(editContactIDField.getText().trim()))
            {
                int providedContactID = Integer.parseInt(editContactIDField.getText().trim());
                String providedCompanyID = editCompanyIDField.getText().trim();
                String providedName = editContactNameField.getText().trim();
                String providedRole = editContactRoleField.getText().trim();
                String providedMobilePhone = editContactMobilePhoneField.getText().trim();
                String providedOfficePhone = editContactOfficePhoneField.getText().trim();
                String providedEmail = editContactEmailField.getText().trim();

                if (!providedCompanyID.isEmpty() || !providedName.isEmpty() || !providedRole.isEmpty()
                        || !providedMobilePhone.isEmpty() || !providedOfficePhone.isEmpty() || !providedEmail.isEmpty())
                {
                    providedCompanyID = providedCompanyID.isEmpty() ? null : providedCompanyID;
                    providedName = providedName.isEmpty() ? null : providedName;
                    providedRole = providedRole.isEmpty() ? null : providedRole;
                    providedMobilePhone = providedMobilePhone.isEmpty() ? null : providedMobilePhone;
                    providedOfficePhone = providedOfficePhone.isEmpty() ? null : providedOfficePhone;
                    providedEmail = providedEmail.isEmpty() ? null : providedEmail;

                    boolean updateSuccess = contactDAO.updateContact(providedContactID, providedCompanyID, providedName, providedOfficePhone, providedMobilePhone,
                            providedEmail, providedRole);
                    if(updateSuccess)
                    {
                        Stage currentStage = (Stage) updateContactButton.getScene().getWindow();
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

    public void deleteContactButtonAction(ActionEvent event) throws SQLException
    {
        ContactDAO contactDAO = new ContactDAO();

        if(!editContactIDField.getText().isBlank())
        {
            if(isNumeric(editContactIDField.getText().trim()))
            {
                int providedContactID = Integer.parseInt(editContactIDField.getText().trim());

                boolean deleteSuccess = contactDAO.deleteContact(providedContactID);
                if(deleteSuccess)
                {
                    Stage currentStage = (Stage) deleteContactButton.getScene().getWindow();
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
