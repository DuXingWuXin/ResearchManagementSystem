package Controller;

import Model.DAO.*;
import Model.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.Date;

public class MainController
{
    @FXML
    private Button laboratoryButton;
    @FXML
    private Button projectButton;
    @FXML
    private Button researcherButton;
    @FXML
    private Button secretaryButton;
    @FXML
    private Button companyButton;
    @FXML
    private Button laboratoryOfficeButton;
    @FXML
    private Button laboratoryDirectorButton;
    @FXML
    private Button laboratoryResearcherButton;
    @FXML
    private Button projectResearcherButton;
    @FXML
    private Button projectSubtopicButton;
    @FXML
    private Button projectAchievementButton;
    @FXML
    private Button projectAchievementContributorButton;
    @FXML
    private Button companyContactButton;

    @FXML
    private TableView<Laboratory> laboratoryTable;
    @FXML
    private TableColumn<Laboratory, Integer> laboratoryIDTableColumn;
    @FXML
    private TableColumn<Laboratory, String> laboratoryNameTableColumn;
    @FXML
    private TableColumn<Laboratory, String> laboratoryIntroductionTableColumn;
    @FXML
    private TableColumn<Laboratory, Integer> laboratorySecretaryIDTableColumn;
    @FXML
    private Button editLaboratoryButton;
    @FXML
    private Button searchLaboratoryButton;
    @FXML
    private TextField searchLaboratoryNameField;

    @FXML
    private TableView<Office> laboratoryOfficeTable;
    @FXML
    private TableColumn<Office,Integer> labOfficeLabIDTableColumn;
    @FXML
    private TableColumn<Office,Integer> labOfficeOfficeIDTableColumn;
    @FXML
    private TableColumn<Office,Double> officeAreaTableColumn;
    @FXML
    private TableColumn<Office,String> officeLocationTableColumn;
    @FXML
    private Button editLaboratoryOfficeButton;
    @FXML
    private Button searchOfficeByLaboratoryIDButton;
    @FXML
    private TextField searchOfficeByLaboratoryIDField;

    @FXML
    private TableView<Director> laboratoryDirectorTable;
    @FXML
    private TableColumn<Director,Integer> labDirLabIDTableColumn;
    @FXML
    private TableColumn<Director,Integer> labDirDirectorIDTableColumn;
    @FXML
    private TableColumn<Director,Date> directorStartDateTableColumn;
    @FXML
    private TableColumn<Director,Integer> directorTenureYearTableColumn;
    @FXML
    private Button editLaboratoryDirectorButton;
    @FXML
    private Button searchDirectorByLaboratoryIDButton;
    @FXML
    private TextField searchDirectorByLaboratoryIDField;

    @FXML
    private TableView<Researcher> laboratoryResearcherTable;
    @FXML
    private TableColumn<Researcher,Integer> labResLabIDTableColumn;
    @FXML
    private TableColumn<Researcher,Integer> labResResearcherIDTableColumn;
    @FXML
    private TableColumn<Researcher,String> labResNameTableColumn;
    @FXML
    private TableColumn<Researcher,String> labResGenderTableColumn;
    @FXML
    private TableColumn<Researcher,String> labResTitleTableColumn;
    @FXML
    private TableColumn<Researcher,Integer> labResAgeTableColumn;
    @FXML
    private TableColumn<Researcher,String> labResResearchAreaTableColumn;
    @FXML
    private Button editLaboratoryResearcherButton;
    @FXML
    private Button searchResearcherByLaboratoryIDButton;
    @FXML
    private TextField searchResearcherByLaboratoryIDField;

    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, Integer> projectIDTableColumn;
    @FXML
    private TableColumn<Project, String> projectNameTableColumn;
    @FXML
    private TableColumn<Project, String> projectHeadTableColumn;
    @FXML
    private TableColumn<Project, Integer> projectContentTableColumn;
    @FXML
    private TableColumn<Project, Double> projectFundTableColumn;
    @FXML
    private TableColumn<Project, Date> projectStartDateTableColumn;
    @FXML
    private TableColumn<Project, Date> projectEndDateTableColumn;
    @FXML
    private Button editProjectButton;
    @FXML
    private Button searchProjectButton;
    @FXML
    private TextField searchProjectNameField;

    @FXML
    private TableView<Project_Researcher> projectResearcherTable;
    @FXML
    private TableColumn<Project_Researcher,Integer> proResProjectIDTableColumn;
    @FXML
    private TableColumn<Project_Researcher,Integer> proResResearcherTableColumn;
    @FXML
    private TableColumn<Project_Researcher,Date> proResJoinTimeTableColumn;
    @FXML
    private TableColumn<Project_Researcher,Double> proResControlFundTableColumn;
    @FXML
    private TableColumn<Project_Researcher,Double> proResWorkloadTableColumn;
    @FXML
    private Button editProjectResearcherButton;
    @FXML
    private Button searchResearcherByProjectIDButton;
    @FXML
    private TextField searchResearcherByProjectIDField;

    @FXML
    private TableView<Subtopic> projectSubtopicTable;
    @FXML
    private TableColumn<Subtopic,Integer> proSubProjectIDTableColumn;
    @FXML
    private TableColumn<Subtopic,Integer> proSubSubtopicIDTableColumn;
    @FXML
    private TableColumn<Subtopic,String> proSubHeadTableColumn;
    @FXML
    private TableColumn<Subtopic,Date> proSubEndDateTableColumn;
    @FXML
    private TableColumn<Subtopic,Double> proSubTotalFundTableColumn;
    @FXML
    private TableColumn<Subtopic,String> proSubTechnologyTableColumn;
    @FXML
    private Button editProjectSubtopicButton;
    @FXML
    private Button searchSubtopicByProjectIDButton;
    @FXML
    private TextField searchSubtopicByProjectIDField;

    @FXML
    private TableView<Achievement> projectAchievementTable;
    @FXML
    private TableColumn<Achievement,Integer> proAchProjectIDTableColumn;
    @FXML
    private TableColumn<Achievement,Integer> proAchAchievementIDTableColumn;
    @FXML
    private TableColumn<Achievement,String> proAchNameTableColumn;
    @FXML
    private TableColumn<Achievement,Date> proAchGetTimeTableColumn;
    @FXML
    private TableColumn<Achievement,Integer> proAchRankTableColumn;
    @FXML
    private TableColumn<Achievement,String> proAchTypeTableColumn;
//    @FXML
//    private TableColumn<Achievement,String> proAchPatentTypeTableColumn;
    @FXML
    private TableColumn<Achievement,String> proAchInformationTableColumn;
    @FXML
    private Button editProjectAchievementButton;
    @FXML
    private Button searchAchievementByProjectIDButton;
    @FXML
    private TextField searchAchievementByProjectIDField;

    @FXML
    private TableView<Researcher> researcherTable;
    @FXML
    private TableColumn<Researcher, Integer> researcherIDTableColumn;
    @FXML
    private TableColumn<Researcher, String> researcherNameTableColumn;
    @FXML
    private TableColumn<Researcher, String> researcherGenderTableColumn;
    @FXML
    private TableColumn<Researcher, String> researcherTitleTableColumn;
    @FXML
    private TableColumn<Researcher, Integer> researcherAgeTableColumn;
    @FXML
    private TableColumn<Researcher, String> researcherAreaTableColumn;
    @FXML
    private TableColumn<Researcher, Integer> researcherLaboratoryIDTableColumn;
    @FXML
    private Button editResearcherButton;
    @FXML
    private Button searchResearcherButton;
    @FXML
    private TextField searchResearcherNameField;

    @FXML
    private TableView<Achievement_Contributor> achievementContributorTable;
    @FXML
    private TableColumn<Achievement_Contributor, Integer> achConAchievementIDTableColumn;
    @FXML
    private TableColumn<Achievement_Contributor, String> achConAchievementNameTableColumn;
    @FXML
    private TableColumn<Achievement_Contributor, Integer> achConContributorTableColumn;
    @FXML
    private TableColumn<Achievement_Contributor, String> achConContributorNameTableColumn;
    @FXML
    private Button editAchievementContributorButton;
    @FXML
    private Button searchContributorByAchievementIDButton;
    @FXML
    private TextField searchContributorByAchievementIDField;

    @FXML
    private TableView<Secretary> secretaryTable;
    @FXML
    private TableColumn<Secretary, Integer> secretaryIDTableColumn;
    @FXML
    private TableColumn<Secretary, String> secretaryNameTableColumn;
    @FXML
    private TableColumn<Secretary, String> secretaryGenderTableColumn;
    @FXML
    private TableColumn<Secretary, Integer> secretaryAgeTableColumn;
    @FXML
    private TableColumn<Secretary, Date> secretaryHireDateTableColumn;
    @FXML
    private TableColumn<Secretary, String> secretaryDutyTableColumn;
    @FXML
    private Button editSecretaryButton;
    @FXML
    private Button searchSecretaryButton;
    @FXML
    private TextField searchSecretaryNameField;

    @FXML
    private TableView<Company> companyTable;
    @FXML
    private TableColumn<Company, Integer> companyIDTableColumn;
    @FXML
    private TableColumn<Company, String> companyNameTableColumn;
    @FXML
    private TableColumn<Company, String> companyAddressTableColumn;
    @FXML
    private TableColumn<Company, Integer> companyProjectIDTableColumn;
    @FXML
    private TableColumn<Company, String> companyRoleTableColumn;
    @FXML
    private Button editCompanyButton;
    @FXML
    private Button searchCompanyButton;
    @FXML
    private TextField searchCompanyNameField;

    @FXML
    private TableView<Contact> companyContactTable;
    @FXML
    private TableColumn<Contact,Integer> comConCompanyIDTableColumn;
    @FXML
    private TableColumn<Contact,Integer> comConContactIDTableColumn;
    @FXML
    private TableColumn<Contact,String> comConContactNameTableColumn;
    @FXML
    private TableColumn<Contact,String> comConOfficePhoneTableColumn;
    @FXML
    private TableColumn<Contact,String> comConMobilePhoneTableColumn;
    @FXML
    private TableColumn<Contact,Integer> comConEmailTableColumn;
    @FXML
    private TableColumn<Contact,String> comConContactTypeTableColumn;
    @FXML
    private Button editCompanyContactButton;
    @FXML
    private Button searchCompanyContactButton;
    @FXML
    private TextField searchCompanyContactField;


    public void laboratoryButtonAction(ActionEvent event) throws SQLException
    {
        try {
            LaboratoryDAO laboratoryDAO = new LaboratoryDAO();
            ObservableList<Laboratory> data;
            data = FXCollections.observableArrayList(
                    laboratoryDAO.getAllLaboratories()
            );
            laboratoryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
            laboratoryNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            laboratoryIntroductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("introduction"));
            laboratorySecretaryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));

            // 将数据源设置到 TableView
            laboratoryTable.getItems().clear();
            laboratoryTable.setItems(data);

            setLaboratoryVisible();
            if(UserType.user_type == 1)
            {
                editLaboratoryButton.setDisable(true);
                editLaboratoryButton.setVisible(false);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLaboratoryButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/laboratory_edit.fxml"));

            Stage laboratory_editStage = new Stage();
            laboratory_editStage.initStyle(StageStyle.DECORATED);
            laboratory_editStage.setTitle("编辑研究室");
            laboratory_editStage.setScene(new Scene(root, 600, 400));
            laboratory_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchLaboratoryButtonAction()
    {
        if(!searchLaboratoryNameField.getText().isBlank())
        {
            String providedName = searchLaboratoryNameField.getText().trim();
            try {
                LaboratoryDAO laboratoryDAO = new LaboratoryDAO();
                ObservableList<Laboratory> data;
                data = FXCollections.observableArrayList(
                        laboratoryDAO.getLaboratoriesByName(providedName)
                );
                laboratoryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
                laboratoryNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                laboratoryIntroductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("introduction"));
                laboratorySecretaryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));

                // 将数据源设置到 TableView
                laboratoryTable.getItems().clear();
                laboratoryTable.setItems(data);

                setLaboratoryVisible();
                if(UserType.user_type == 1)
                {
                    editLaboratoryButton.setDisable(true);
                    editLaboratoryButton.setVisible(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void laboratoryOfficeButtonAction(ActionEvent event) throws SQLException
    {
        setLaboratoryOfficeVisible();
        if(UserType.user_type == 1)
        {
            editLaboratoryOfficeButton.setDisable(true);
            editLaboratoryOfficeButton.setVisible(false);
        }
    }

    public void editLaboratoryOfficeButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/office_edit.fxml"));

            Stage office_editStage = new Stage();
            office_editStage.initStyle(StageStyle.DECORATED);
            office_editStage.setTitle("编辑办公场地");
            office_editStage.setScene(new Scene(root, 600, 400));
            office_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchOfficeByLaboratoryIDButtonAction()
    {
        if(!searchOfficeByLaboratoryIDField.getText().isBlank())
        {
            if(isNumeric(searchOfficeByLaboratoryIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchOfficeByLaboratoryIDField.getText().trim());
                try {
                    OfficeDAO officeDAO = new OfficeDAO();
                    ObservableList<Office> data;
                    data = FXCollections.observableArrayList(
                            officeDAO.getOfficesByLabId(providedID)
                    );
                    labOfficeLabIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
                    labOfficeOfficeIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    officeAreaTableColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
                    officeLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

                    // 将数据源设置到 TableView
                    laboratoryOfficeTable.getItems().clear();
                    laboratoryOfficeTable.setItems(data);

                    setLaboratoryOfficeVisible();
                    if(UserType.user_type == 1)
                    {
                        editLaboratoryOfficeButton.setDisable(true);
                        editLaboratoryOfficeButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void laboratoryDirectorButtonAction(ActionEvent event) throws SQLException
    {
        setLaboratoryDirectorVisible();
        if(UserType.user_type == 1)
        {
            editLaboratoryDirectorButton.setDisable(true);
            editLaboratoryDirectorButton.setVisible(false);
        }
    }

    public void editLaboratoryDirectorButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/director_edit.fxml"));

            Stage director_editStage = new Stage();
            director_editStage.initStyle(StageStyle.DECORATED);
            director_editStage.setTitle("编辑研究室主任");
            director_editStage.setScene(new Scene(root, 600, 400));
            director_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchDirectorByLaboratoryIDButtonAction()
    {
        if(!searchDirectorByLaboratoryIDField.getText().isBlank())
        {
            if(isNumeric(searchDirectorByLaboratoryIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchDirectorByLaboratoryIDField.getText().trim());
                try {
                    DirectorDAO directorDAO = new DirectorDAO();
                    ObservableList<Director> data;
                    data = FXCollections.observableArrayList(
                            directorDAO.getDirectorsByLabId(providedID)
                    );
                    labDirLabIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
                    labDirDirectorIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                    directorStartDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
                    directorTenureYearTableColumn.setCellValueFactory(new PropertyValueFactory<>("tenure_years"));

                    // 将数据源设置到 TableView
                    laboratoryDirectorTable.getItems().clear();
                    laboratoryDirectorTable.setItems(data);

                    setLaboratoryDirectorVisible();
                    if(UserType.user_type == 1)
                    {
                        editLaboratoryDirectorButton.setDisable(true);
                        editLaboratoryDirectorButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void laboratoryResearcherButtonAction(ActionEvent event) throws SQLException
    {
        setLaboratoryResearcherVisible();
        if(UserType.user_type == 1)
        {
            editLaboratoryResearcherButton.setDisable(true);
            editLaboratoryResearcherButton.setVisible(false);
        }
    }

    public void editLaboratoryResearcherButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/lab_researcher_edit.fxml"));

            Stage researcher_editStage = new Stage();
            researcher_editStage.initStyle(StageStyle.DECORATED);
            researcher_editStage.setTitle("编辑研究室科研人员");
            researcher_editStage.setScene(new Scene(root, 600, 400));
            researcher_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchResearcherByLaboratoryIDButtonAction()
    {
        if(!searchResearcherByLaboratoryIDField.getText().isBlank())
        {
            if(isNumeric(searchResearcherByLaboratoryIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchResearcherByLaboratoryIDField.getText().trim());
                try {
                    ResearcherDAO researcherDAO = new ResearcherDAO();
                    ObservableList<Researcher> data;
                    data = FXCollections.observableArrayList(
                            researcherDAO.getResearchersByLabId(providedID)
                    );
                    labResLabIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
                    labResResearcherIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                    labResNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    labResGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                    labResTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    labResAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
                    labResResearchAreaTableColumn.setCellValueFactory(new PropertyValueFactory<>("research_area"));

                    // 将数据源设置到 TableView
                    laboratoryResearcherTable.getItems().clear();
                    laboratoryResearcherTable.setItems(data);

                    setLaboratoryResearcherVisible();
                    if(UserType.user_type == 1)
                    {
                        editLaboratoryResearcherButton.setDisable(true);
                        editLaboratoryResearcherButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void projectButtonAction(ActionEvent event) throws SQLException
    {
        try {
            ProjectDAO projectDAO = new ProjectDAO();
            ObservableList<Project> data;
            data = FXCollections.observableArrayList(
                    projectDAO.getAllProjects()
            );
            projectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
            projectNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_name"));
            projectHeadTableColumn.setCellValueFactory(new PropertyValueFactory<>("head"));
            projectContentTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
            projectFundTableColumn.setCellValueFactory(new PropertyValueFactory<>("total_funds"));
            projectStartDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
            projectEndDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));

            // 将数据源设置到 TableView
            projectTable.getItems().clear();
            projectTable.setItems(data);

            setProjectVisible();
            if(UserType.user_type == 1)
            {
                editProjectButton.setDisable(true);
                editProjectButton.setVisible(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProjectButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/project_edit.fxml"));

            Stage project_editStage = new Stage();
            project_editStage.initStyle(StageStyle.DECORATED);
            project_editStage.setTitle("编辑科研项目");
            project_editStage.setScene(new Scene(root, 600, 400));
            project_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchProjectButtonAction()
    {
        if(!searchProjectNameField.getText().isBlank())
        {
            String providedName = searchProjectNameField.getText();
            try {
                ProjectDAO projectDAO = new ProjectDAO();
                ObservableList<Project> data;
                data = FXCollections.observableArrayList(
                        projectDAO.getProjectByName(providedName)
                );
                projectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
                projectNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_name"));
                projectHeadTableColumn.setCellValueFactory(new PropertyValueFactory<>("head"));
                projectContentTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
                projectFundTableColumn.setCellValueFactory(new PropertyValueFactory<>("total_funds"));
                projectStartDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
                projectEndDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));

                // 将数据源设置到 TableView
                projectTable.getItems().clear();
                projectTable.setItems(data);

                setProjectVisible();
                if(UserType.user_type == 1)
                {
                    editProjectButton.setDisable(true);
                    editProjectButton.setVisible(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void projectResearcherButtonAction(ActionEvent event) throws SQLException
    {
        setProjectResearcherVisible();
        if(UserType.user_type == 1)
        {
            editProjectResearcherButton.setDisable(true);
            editProjectResearcherButton.setVisible(false);
        }
    }

    public void editProjectResearcherButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/project_researcher_edit.fxml"));

            Stage project_researcher_editStage = new Stage();
            project_researcher_editStage.initStyle(StageStyle.DECORATED);
            project_researcher_editStage.setTitle("编辑科研项目的科研员");
            project_researcher_editStage.setScene(new Scene(root, 600, 400));
            project_researcher_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchResearcherByProjectIDButtonAction()
    {
        if(!searchResearcherByProjectIDField.getText().isBlank())
        {
            if(isNumeric(searchResearcherByProjectIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchResearcherByProjectIDField.getText().trim());
                try {
                    Project_ResearcherDAO project_researcherDAO = new Project_ResearcherDAO();
                    ObservableList<Project_Researcher> data;
                    data = FXCollections.observableArrayList(
                            project_researcherDAO.getProjectResearchersByProjectId(providedID)
                    );
                    proResProjectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
                    proResResearcherTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                    proResJoinTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("join_date"));
                    proResControlFundTableColumn.setCellValueFactory(new PropertyValueFactory<>("control_funds"));
                    proResWorkloadTableColumn.setCellValueFactory(new PropertyValueFactory<>("workload"));

                    // 将数据源设置到 TableView
                    projectResearcherTable.getItems().clear();
                    projectResearcherTable.setItems(data);

                    setProjectResearcherVisible();
                    if(UserType.user_type == 1)
                    {
                        editProjectResearcherButton.setDisable(true);
                        editProjectResearcherButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void projectSubtopicButtonAction(ActionEvent event) throws SQLException
    {
        setProjectSubtopicVisible();
        if(UserType.user_type == 1)
        {
            editProjectSubtopicButton.setDisable(true);
            editProjectSubtopicButton.setVisible(false);
        }
    }

    public void editProjectSubtopicButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/project_subtopic_edit.fxml"));

            Stage subtopic_editStage = new Stage();
            subtopic_editStage.initStyle(StageStyle.DECORATED);
            subtopic_editStage.setTitle("编辑科研项目子课题");
            subtopic_editStage.setScene(new Scene(root, 600, 400));
            subtopic_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchSubtopicByProjectIDButtonAction()
    {
        if(!searchSubtopicByProjectIDField.getText().isBlank())
        {
            if(isNumeric(searchSubtopicByProjectIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchSubtopicByProjectIDField.getText().trim());
                try {
                    SubtopicDAO subtopicDAO = new SubtopicDAO();
                    ObservableList<Subtopic> data;
                    data = FXCollections.observableArrayList(
                            subtopicDAO.getSubtopicsByProjectId(providedID)
                    );
                    proSubProjectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
                    proSubSubtopicIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("subtopic_id"));
                    proSubHeadTableColumn.setCellValueFactory(new PropertyValueFactory<>("head"));
                    proSubEndDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));
                    proSubTotalFundTableColumn.setCellValueFactory(new PropertyValueFactory<>("total_funds"));
                    proSubTechnologyTableColumn.setCellValueFactory(new PropertyValueFactory<>("technology_condition"));

                    // 将数据源设置到 TableView
                    projectSubtopicTable.getItems().clear();
                    projectSubtopicTable.setItems(data);

                    setProjectSubtopicVisible();
                    if(UserType.user_type == 1)
                    {
                        editProjectSubtopicButton.setDisable(true);
                        editProjectSubtopicButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void projectAchievementButtonAction(ActionEvent event) throws SQLException
    {
        setProjectAchievementVisible();
        if(UserType.user_type == 1)
        {
            editProjectAchievementButton.setDisable(true);
            editProjectAchievementButton.setVisible(false);
        }
    }

    public void editProjectAchievementButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/achievement_edit.fxml"));

            Stage project_achievement_editStage = new Stage();
            project_achievement_editStage.initStyle(StageStyle.DECORATED);
            project_achievement_editStage.setTitle("编辑科研项目成果");
            project_achievement_editStage.setScene(new Scene(root, 600, 400));
            project_achievement_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchAchievementByProjectIDButtonAction()
    {
        if(!searchAchievementByProjectIDField.getText().isBlank())
        {
            if(isNumeric(searchAchievementByProjectIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchAchievementByProjectIDField.getText().trim());
                try {
                    AchievementDAO achievementDAO = new AchievementDAO();
                    ObservableList<Achievement> data;
                    data = FXCollections.observableArrayList(
                            achievementDAO.getAchievementsByProjectId(providedID)
                    );

                    for(Achievement achievement : data)
                    {
                        int achievementID = achievement.getAchievement_id();
                        String type = achievement.getType();

                        if(type.equals("patent"))
                        {
                            PatentDAO patentDAO = new PatentDAO();
                            ObservableList<Patent> patent_data;
                            patent_data = FXCollections.observableArrayList(
                                    patentDAO.getPatentByAchievementId(achievementID)
                            );
                            int index = 0;
                            for (int i = 0; i < patent_data.size(); i++)
                                if (patent_data.get(i) != null)
                                {
                                    index = i;
                                    break;
                                }

                            achievement.setDetailType(achievement.getType() + "(" + patent_data.get(index).getPatent_type() + ")");
                            achievement.setInformation(patent_data.get(index).getInformation());

                        }
                        else if(type.equals("paper"))
                        {
                            PaperDAO paperDAO = new PaperDAO();
                            ObservableList<Paper> paper_data;
                            paper_data = FXCollections.observableArrayList(
                                    paperDAO.getPaperByAchievementId(achievementID)
                            );
                            int index = 0;
                            for (int i = 0; i < paper_data.size(); i++)
                                if (paper_data.get(i) != null)
                                {
                                    index = i;
                                    break;
                                }

                            achievement.setDetailType(achievement.getType());
                            achievement.setInformation(paper_data.get(index).getInformation());
                        }
                        else if (type.equals("software_copyright"))
                        {
                            SoftwareDAO softwareDAO = new SoftwareDAO();
                            ObservableList<Software> software_data;
                            software_data = FXCollections.observableArrayList(
                                    softwareDAO.getSoftwareByAchievementId(achievementID)
                            );
                            int index = 0;
                            for (int i = 0; i < software_data.size(); i++)
                                if (software_data.get(i) != null)
                                {
                                    index = i;
                                    break;
                                }

                            achievement.setDetailType(achievement.getType());
                            achievement.setInformation(software_data.get(index).getInformation());
                        }
                    }

                    proAchProjectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
                    proAchAchievementIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("achievement_id"));
                    proAchNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("achievement_name"));
                    proAchGetTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("get_time"));
                    proAchRankTableColumn.setCellValueFactory(new PropertyValueFactory<>("rank_number"));
                    proAchTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("detailType"));
                    proAchInformationTableColumn.setCellValueFactory(new PropertyValueFactory<>("information"));

                    // 将数据源设置到 TableView
                    projectAchievementTable.getItems().clear();
                    projectAchievementTable.setItems(data);

                    setProjectAchievementVisible();
                    if(UserType.user_type == 1)
                    {
                        editProjectAchievementButton.setDisable(true);
                        editProjectAchievementButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void researcherButtonAction(ActionEvent event) throws SQLException
    {
        try {
            ResearcherDAO researcherDAO = new ResearcherDAO();
            ObservableList<Researcher> data;
            data = FXCollections.observableArrayList(
                    researcherDAO.getAllResearchers()
            );
            researcherIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
            researcherNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            researcherGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
            researcherTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            researcherAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            researcherAreaTableColumn.setCellValueFactory(new PropertyValueFactory<>("research_area"));
            researcherLaboratoryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));

            // 将数据源设置到 TableView
            researcherTable.getItems().clear();
            researcherTable.setItems(data);

            setResearcherVisible();
            if(UserType.user_type == 1)
            {
                editResearcherButton.setDisable(true);
                editResearcherButton.setVisible(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editResearcherButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/researcher_edit.fxml"));

            Stage researcher_editStage = new Stage();
            researcher_editStage.initStyle(StageStyle.DECORATED);
            researcher_editStage.setTitle("编辑科研人员");
            researcher_editStage.setScene(new Scene(root, 600, 400));
            researcher_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchResearcherButtonAction()
    {
        if(!searchResearcherNameField.getText().isBlank())
        {
            String providedName = searchResearcherNameField.getText().trim();
            try {
                ResearcherDAO researcherDAO = new ResearcherDAO();
                ObservableList<Researcher> data;
                data = FXCollections.observableArrayList(
                        researcherDAO.getResearcherByName(providedName)
                );
                researcherIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                researcherNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                researcherGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                researcherTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                researcherAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
                researcherAreaTableColumn.setCellValueFactory(new PropertyValueFactory<>("research_area"));
                researcherLaboratoryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("lab_id"));

                // 将数据源设置到 TableView
                researcherTable.getItems().clear();
                researcherTable.setItems(data);

                setResearcherVisible();
                if(UserType.user_type == 1)
                {
                    editResearcherButton.setDisable(true);
                    editResearcherButton.setVisible(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void projectAchievementContributorButtonAction(ActionEvent event) throws SQLException
    {
        setAchievementContributorVisible();
        if(UserType.user_type == 1)
        {
            editAchievementContributorButton.setDisable(true);
            editAchievementContributorButton.setVisible(false);
        }
    }

    public void editAchievementContributorButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/contributor_edit.fxml"));

            Stage achievement_contributor_editStage = new Stage();
            achievement_contributor_editStage.initStyle(StageStyle.DECORATED);
            achievement_contributor_editStage.setTitle("编辑科研成果贡献人");
            achievement_contributor_editStage.setScene(new Scene(root, 600, 400));
            achievement_contributor_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchContributorByAchievementIDButtonAction()
    {
        if(!searchContributorByAchievementIDField.getText().isBlank())
        {
            if(isNumeric(searchContributorByAchievementIDField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchContributorByAchievementIDField.getText().trim());
                try {
                    Achievement_ContributorDAO achievementContributorDAO = new Achievement_ContributorDAO();
                    AchievementDAO achievementDAO = new AchievementDAO();
                    ResearcherDAO researcherDAO = new ResearcherDAO();

                    ObservableList<Achievement_Contributor> Achievement_Contributor_data;
                    Achievement_Contributor_data = FXCollections.observableArrayList(
                            achievementContributorDAO.getContributorsByAchievementId(providedID)
                    );

                    int achievementID = 0;
                    int researcherID = 0;
                    for (Achievement_Contributor achievement_contributor : Achievement_Contributor_data)
                    {
                        achievementID = achievement_contributor.getAchievement_id();
                        researcherID = achievement_contributor.getEmployee_id();

                        ObservableList<Achievement> achievement_data;
                        achievement_data = FXCollections.observableArrayList(
                                achievementDAO.getAchievementsByAchievementId(achievementID)
                        );

                        ObservableList<Researcher> researcher_data;
                        researcher_data = FXCollections.observableArrayList(
                                researcherDAO.getResearchersByEmployeeID(researcherID)
                        );

                        for (Achievement achievement : achievement_data)
                            achievement_contributor.setAchievement_name(achievement.getAchievement_name());

                        for (Researcher researcher : researcher_data)
                            achievement_contributor.setEmployee_name(researcher.getName());
                    }

                    achConAchievementIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("achievement_id"));
                    achConAchievementNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("achievement_name"));
                    achConContributorTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                    achConContributorNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_name"));

                    // 将数据源设置到 TableView
                    achievementContributorTable.getItems().clear();
                    achievementContributorTable.setItems(Achievement_Contributor_data);

                    setAchievementContributorVisible();
                    if(UserType.user_type == 1)
                    {
                        editAchievementContributorButton.setDisable(true);
                        editAchievementContributorButton.setVisible(false);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void secretaryButtonAction(ActionEvent event) throws SQLException
    {
        try {
            SecretaryDAO secretaryDAO = new SecretaryDAO();
            ObservableList<Secretary> data;
            data = FXCollections.observableArrayList(
                    secretaryDAO.getAllSecretaries()
            );
            secretaryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
            secretaryNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            secretaryGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
            secretaryAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            secretaryHireDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("hire_date"));
            secretaryDutyTableColumn.setCellValueFactory(new PropertyValueFactory<>("duties"));

            // 将数据源设置到 TableView
            secretaryTable.getItems().clear();
            secretaryTable.setItems(data);

            setSecretaryVisible();
            if(UserType.user_type == 1)
            {
                editSecretaryButton.setDisable(true);
                editSecretaryButton.setVisible(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSecretaryButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/secretary_edit.fxml"));

            Stage secretary_editStage = new Stage();
            secretary_editStage.initStyle(StageStyle.DECORATED);
            secretary_editStage.setTitle("编辑研究室秘书");
            secretary_editStage.setScene(new Scene(root, 600, 400));
            secretary_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchSecretaryButtonAction()
    {
        if(!searchSecretaryNameField.getText().isBlank())
        {
            String providedName = searchSecretaryNameField.getText().trim();
            try {
                SecretaryDAO secretaryDAO = new SecretaryDAO();
                ObservableList<Secretary> data;
                data = FXCollections.observableArrayList(
                        secretaryDAO.getSecretaryByName(providedName)
                );
                secretaryIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                secretaryNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                secretaryGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                secretaryAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
                secretaryHireDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("hire_date"));
                secretaryDutyTableColumn.setCellValueFactory(new PropertyValueFactory<>("duties"));

                // 将数据源设置到 TableView
                secretaryTable.getItems().clear();
                secretaryTable.setItems(data);

                setSecretaryVisible();
                if(UserType.user_type == 1)
                {
                    editSecretaryButton.setDisable(true);
                    editSecretaryButton.setVisible(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void companyButtonAction(ActionEvent event) throws SQLException
    {
        try {
            CompanyDAO companyDAO = new CompanyDAO();
            Project_CompanyDAO project_companyDAO = new Project_CompanyDAO();
            ObservableList<Company> company_data;
            company_data = FXCollections.observableArrayList(
                    companyDAO.getAllCompanies()
            );
            ObservableList<Project_Company> project_company_data;
            project_company_data = FXCollections.observableArrayList(
                    project_companyDAO.getAllProjectCompany()
            );

            // 将 project_company_data 的数据连接到 company_data 的数据上
            for (Company company : company_data) {
                for (Project_Company project_company : project_company_data) {
                    if (company.getCompany_id() == project_company.getCompany_id()) {
                        // 连接操作，将 TableB 的数据合并到 TableA 的数据上
                        company.setProject_id(project_company.getProject_id());
                        company.setRole(project_company.getRole());
                        break;
                    }
                }
            }

            companyIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("company_id"));
            companyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("company_name"));
            companyAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            companyProjectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
            companyRoleTableColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

            // 将数据源设置到 TableView
            companyTable.getItems().clear();
            companyTable.setItems(company_data);

            setCompanyVisible();
            if(UserType.user_type == 1)
            {
                editCompanyButton.setDisable(true);
                editCompanyButton.setVisible(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCompanyButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/company_edit.fxml"));

            Stage company_editStage = new Stage();
            company_editStage.initStyle(StageStyle.DECORATED);
            company_editStage.setTitle("编辑相关单位");
            company_editStage.setScene(new Scene(root, 600, 400));
            company_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchCompanyButtonAction()
    {
        if(!searchCompanyNameField.getText().isBlank())
        {
            String providedName = searchCompanyNameField.getText().trim();
            try {
                CompanyDAO companyDAO = new CompanyDAO();
                Project_CompanyDAO project_companyDAO = new Project_CompanyDAO();
                ObservableList<Company> company_data;
                company_data = FXCollections.observableArrayList(
                        companyDAO.getCompanyByName(providedName)
                );

                int companyID = 0;
                for (Company company : company_data)
                    companyID = company.getCompany_id();

                ObservableList<Project_Company> project_company_data;
                project_company_data = FXCollections.observableArrayList(
                        project_companyDAO.getCompanyByCompanyID(companyID)
                );

                // 将 project_company_data 的数据连接到 company_data 的数据上
                for (Company company : company_data) {
                    for (Project_Company project_company : project_company_data) {
                        if (company.getCompany_id() == project_company.getCompany_id()) {
                            // 连接操作，将 TableB 的数据合并到 TableA 的数据上
                            company.setProject_id(project_company.getProject_id());
                            company.setRole(project_company.getRole());
                            break;
                        }
                    }
                }

                companyIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("company_id"));
                companyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("company_name"));
                companyAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
                companyProjectIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
                companyRoleTableColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

                // 将数据源设置到 TableView
                companyTable.getItems().clear();
                companyTable.setItems(company_data);

                setCompanyVisible();
                if(UserType.user_type == 1)
                {
                    editCompanyButton.setDisable(true);
                    editCompanyButton.setVisible(false);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void companyContactButtonAction(ActionEvent event) throws SQLException
    {
        setCompanyContactVisible();
        if(UserType.user_type == 1)
        {
            editCompanyContactButton.setDisable(true);
            editCompanyContactButton.setVisible(false);
        }
    }

    public void editCompanyContactButtonAction()
    {
        try
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/contact_edit.fxml"));

            Stage company_contact_editStage = new Stage();
            company_contact_editStage.initStyle(StageStyle.DECORATED);
            company_contact_editStage.setTitle("编辑单位代表");
            company_contact_editStage.setScene(new Scene(root, 600, 400));
            company_contact_editStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchCompanyContactButtonAction()
    {
        if(!searchCompanyContactField.getText().isBlank())
        {
            if(isNumeric(searchCompanyContactField.getText().trim()))
            {
                int providedID = Integer.parseInt(searchCompanyContactField.getText().trim());
                try {
                    ContactDAO contactDAO = new ContactDAO();
                    ObservableList<Contact> data;
                    data = FXCollections.observableArrayList(
                            contactDAO.getContactsByCompanyId(providedID)
                    );
                    comConCompanyIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("company_id"));
                    comConContactIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
                    comConContactNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    comConOfficePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("office_phone"));
                    comConMobilePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("mobile_phone"));
                    comConEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                    comConContactTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

                    // 将数据源设置到 TableView
                    companyContactTable.getItems().clear();
                    companyContactTable.setItems(data);

                    setCompanyContactVisible();
                    if(UserType.user_type == 1)
                    {
                        editCompanyContactButton.setDisable(true);
                        editCompanyContactButton.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

    public void setLaboratoryVisible()
    {
        laboratoryTable.setVisible(true);
        editLaboratoryButton.setVisible(true);
        editLaboratoryButton.setDisable(false);
        searchLaboratoryButton.setVisible(true);
        searchLaboratoryButton.setDisable(false);
        searchLaboratoryNameField.setVisible(true);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setLaboratoryOfficeVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(true);
        editLaboratoryOfficeButton.setVisible(true);
        editLaboratoryOfficeButton.setDisable(false);
        searchOfficeByLaboratoryIDButton.setVisible(true);
        searchOfficeByLaboratoryIDButton.setDisable(false);
        searchOfficeByLaboratoryIDField.setVisible(true);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setLaboratoryDirectorVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(true);
        editLaboratoryDirectorButton.setVisible(true);
        editLaboratoryDirectorButton.setDisable(false);
        searchDirectorByLaboratoryIDButton.setVisible(true);
        searchDirectorByLaboratoryIDButton.setDisable(false);
        searchDirectorByLaboratoryIDField.setVisible(true);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setLaboratoryResearcherVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(true);
        editLaboratoryResearcherButton.setVisible(true);
        editLaboratoryResearcherButton.setDisable(false);
        searchResearcherByLaboratoryIDButton.setVisible(true);
        searchResearcherByLaboratoryIDButton.setDisable(false);
        searchResearcherByLaboratoryIDField.setVisible(true);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setProjectVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(true);
        editProjectButton.setVisible(true);
        editProjectButton.setDisable(false);
        searchProjectButton.setVisible(true);
        searchProjectButton.setDisable(false);
        searchProjectNameField.setVisible(true);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setProjectResearcherVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(true);
        editProjectResearcherButton.setVisible(true);
        editProjectResearcherButton.setDisable(false);
        searchResearcherByProjectIDButton.setVisible(true);
        searchResearcherByProjectIDButton.setDisable(false);
        searchResearcherByProjectIDField.setVisible(true);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setProjectSubtopicVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(true);
        editProjectSubtopicButton.setVisible(true);
        editProjectSubtopicButton.setDisable(false);
        searchSubtopicByProjectIDButton.setVisible(true);
        searchSubtopicByProjectIDButton.setDisable(false);
        searchSubtopicByProjectIDField.setVisible(true);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setProjectAchievementVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(true);
        editProjectAchievementButton.setVisible(true);
        editProjectAchievementButton.setDisable(false);
        searchAchievementByProjectIDButton.setVisible(true);
        searchAchievementByProjectIDButton.setDisable(false);
        searchAchievementByProjectIDField.setVisible(true);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setResearcherVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(true);
        editResearcherButton.setVisible(true);
        editResearcherButton.setDisable(false);
        searchResearcherButton.setVisible(true);
        searchResearcherButton.setDisable(false);
        searchResearcherNameField.setVisible(true);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setAchievementContributorVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(true);
        editAchievementContributorButton.setVisible(true);
        editAchievementContributorButton.setDisable(false);
        searchContributorByAchievementIDButton.setVisible(true);
        searchContributorByAchievementIDButton.setDisable(false);
        searchContributorByAchievementIDField.setVisible(true);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setSecretaryVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(true);
        editSecretaryButton.setVisible(true);
        editSecretaryButton.setDisable(false);
        searchSecretaryButton.setVisible(true);
        searchSecretaryButton.setDisable(false);
        searchSecretaryNameField.setVisible(true);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setCompanyVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(true);
        editCompanyButton.setVisible(true);
        editCompanyButton.setDisable(false);
        searchCompanyButton.setVisible(true);
        searchCompanyButton.setDisable(false);
        searchCompanyNameField.setVisible(true);

        companyContactTable.setVisible(false);
        editCompanyContactButton.setVisible(false);
        editCompanyContactButton.setDisable(true);
        searchCompanyContactButton.setVisible(false);
        searchCompanyContactButton.setDisable(true);
        searchCompanyContactField.setVisible(false);
    }

    public void setCompanyContactVisible()
    {
        laboratoryTable.setVisible(false);
        editLaboratoryButton.setVisible(false);
        editLaboratoryButton.setDisable(true);
        searchLaboratoryButton.setVisible(false);
        searchLaboratoryButton.setDisable(true);
        searchLaboratoryNameField.setVisible(false);

        laboratoryOfficeTable.setVisible(false);
        editLaboratoryOfficeButton.setVisible(false);
        editLaboratoryOfficeButton.setDisable(true);
        searchOfficeByLaboratoryIDButton.setVisible(false);
        searchOfficeByLaboratoryIDButton.setDisable(true);
        searchOfficeByLaboratoryIDField.setVisible(false);

        laboratoryDirectorTable.setVisible(false);
        editLaboratoryDirectorButton.setVisible(false);
        editLaboratoryDirectorButton.setDisable(true);
        searchDirectorByLaboratoryIDButton.setVisible(false);
        searchDirectorByLaboratoryIDButton.setDisable(true);
        searchDirectorByLaboratoryIDField.setVisible(false);

        laboratoryResearcherTable.setVisible(false);
        editLaboratoryResearcherButton.setVisible(false);
        editLaboratoryResearcherButton.setDisable(true);
        searchResearcherByLaboratoryIDButton.setVisible(false);
        searchResearcherByLaboratoryIDButton.setDisable(true);
        searchResearcherByLaboratoryIDField.setVisible(false);

        projectTable.setVisible(false);
        editProjectButton.setVisible(false);
        editProjectButton.setDisable(true);
        searchProjectButton.setVisible(false);
        searchProjectButton.setDisable(true);
        searchProjectNameField.setVisible(false);

        projectResearcherTable.setVisible(false);
        editProjectResearcherButton.setVisible(false);
        editProjectResearcherButton.setDisable(true);
        searchResearcherByProjectIDButton.setVisible(false);
        searchResearcherByProjectIDButton.setDisable(true);
        searchResearcherByProjectIDField.setVisible(false);

        projectSubtopicTable.setVisible(false);
        editProjectSubtopicButton.setVisible(false);
        editProjectSubtopicButton.setDisable(true);
        searchSubtopicByProjectIDButton.setVisible(false);
        searchSubtopicByProjectIDButton.setDisable(true);
        searchSubtopicByProjectIDField.setVisible(false);

        projectAchievementTable.setVisible(false);
        editProjectAchievementButton.setVisible(false);
        editProjectAchievementButton.setDisable(true);
        searchAchievementByProjectIDButton.setVisible(false);
        searchAchievementByProjectIDButton.setDisable(true);
        searchAchievementByProjectIDField.setVisible(false);

        researcherTable.setVisible(false);
        editResearcherButton.setVisible(false);
        editResearcherButton.setDisable(true);
        searchResearcherButton.setVisible(false);
        searchResearcherButton.setDisable(true);
        searchResearcherNameField.setVisible(false);

        achievementContributorTable.setVisible(false);
        editAchievementContributorButton.setVisible(false);
        editAchievementContributorButton.setDisable(true);
        searchContributorByAchievementIDButton.setVisible(false);
        searchContributorByAchievementIDButton.setDisable(true);
        searchContributorByAchievementIDField.setVisible(false);

        secretaryTable.setVisible(false);
        editSecretaryButton.setVisible(false);
        editSecretaryButton.setDisable(true);
        searchSecretaryButton.setVisible(false);
        searchSecretaryButton.setDisable(true);
        searchSecretaryNameField.setVisible(false);

        companyTable.setVisible(false);
        editCompanyButton.setVisible(false);
        editCompanyButton.setDisable(true);
        searchCompanyButton.setVisible(false);
        searchCompanyButton.setDisable(true);
        searchCompanyNameField.setVisible(false);

        companyContactTable.setVisible(true);
        editCompanyContactButton.setVisible(true);
        editCompanyContactButton.setDisable(false);
        searchCompanyContactButton.setVisible(true);
        searchCompanyContactButton.setDisable(false);
        searchCompanyContactField.setVisible(true);
    }

}
