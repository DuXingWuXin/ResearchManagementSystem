package Model.entity;

import java.util.Objects;

public class Contact
{
    // 联系人ID（作为主键）
    private int contact_id;
    // 公司ID
    private int company_id;
    // 联系人姓名
    private String name;
    // 办公电话
    private String office_phone;
    // 移动电话
    private String mobile_phone;
    // 电子邮件
    private String email;
    // 角色（head或contact）
    private String role;

    // 构造函数
    public Contact(int contact_id, int company_id, String name, String office_phone, String mobile_phone, String email, String role)
    {
        this.contact_id = contact_id;
        this.company_id = company_id;
        this.name = name;
        this.office_phone = office_phone;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.role = role;
    }

    // contact_id 的 get set 函数
    public int getContact_id() {
        return this.contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    // company_id 的 get set 函数
    public int getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    // name 的 get set 函数
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // office_phone 的 get set 函数
    public String getOffice_phone() {
        return this.office_phone;
    }

    public void setOffice_phone(String office_phone) {
        this.office_phone = office_phone;
    }

    // mobile_phone 的 get set 函数
    public String getMobile_phone() {
        return this.mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    // email 的 get set 函数
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Contact contact = (Contact) o;
        return contact_id == contact.contact_id &&
                company_id == contact.company_id &&
                Objects.equals(name, contact.name) &&
                Objects.equals(office_phone, contact.office_phone) &&
                Objects.equals(mobile_phone, contact.mobile_phone) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(role, contact.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact_id, company_id, name, office_phone, mobile_phone, email, role);
    }

    @Override
    public String toString() {
        return "Contact {" +
                "contact_id = " + contact_id +
                ", company_id = " + company_id +
                ", name = '" + name + '\'' +
                ", office_phone = '" + office_phone + '\'' +
                ", mobile_phone = '" + mobile_phone + '\'' +
                ", email = '" + email + '\'' +
                ", role = '" + role + '\'' +
                "}";
    }
}
