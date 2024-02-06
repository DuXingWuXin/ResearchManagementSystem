package Model.entity;

import java.util.Objects;

public class Company
{
    // 单位ID（作为主键）
    private int company_id;
    // 单位名称
    private String company_name;
    // 单位地址
    private String address;
    // 角色
    private String role;
    // 相关的项目id
    private int project_id;

    // 构造函数
    public Company(int company_id, String company_name, String address) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.address = address;
    }

    // company_id 的 get set 函数
    public int getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    // company_name 的 get set 函数
    public String getCompany_name() {
        return this.company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    // address 的 get set 函数
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // role 的 get set 函数
    public String getRole(){return this.role;}
    public void setRole(String role) {
        this.role = role;
    }

    // project_id 的 get set 函数
    public int getProject_id(){return this.project_id;}
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return company_id == company.company_id &&
                Objects.equals(company_name, company.company_name) &&
                Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company_id, company_name, address);
    }

    @Override
    public String toString() {
        return "Company {" +
                "company_id = " + company_id +
                ", company_name = '" + company_name + '\'' +
                ", address = '" + address + '\'' +
                "}";
    }
}
