package Model.entity;

import java.util.Objects;

public class Project_Company
{
    // 项目ID
    private int project_id;
    // 公司ID
    private int company_id;
    // 角色
    private String role;


    // 构造函数
    public Project_Company(int project_id, int company_id, String role) {
        this.project_id = project_id;
        this.company_id = company_id;
        this.role = role;
    }

    // project_id 的 get set 函数
    public int getProject_id() {
        return this.project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    // company_id 的 get set 函数
    public int getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    // role 的 get set 函数
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project_Company that = (Project_Company) o;
        return project_id == that.project_id &&
                company_id == that.company_id &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project_id, company_id, role);
    }

    @Override
    public String toString() {
        return "ProjectCompany {" +
                "project_id = " + project_id +
                ", company_id = " + company_id +
                ", role = " + role +
                "}";
    }
}
