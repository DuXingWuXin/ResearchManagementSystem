package Model.entity;

import java.util.Date;
import java.util.Objects;

public class Secretary
{
    // 秘书的员工ID
    private int employee_id;
    // 秘书的姓名
    private String name;
    // 秘书的性别
    private String gender;
    // 秘书的年龄
    private int age;
    // 秘书的入职日期
    private Date hire_date;
    // 秘书的职责
    private String duties;

    // 构造函数
    public Secretary(int employee_id, String name, String gender, int age, Date hire_date, String duties)
    {
        this.employee_id = employee_id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.hire_date = hire_date;
        this.duties = duties;
    }

    // employee_id 的 get set 函数
    public int getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    // name 的 get set 函数
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // gender 的 get set 函数
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // age 的 get set 函数
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // hire_date 的 get set 函数
    public Date getHire_date() {
        return this.hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    // duties 的 get set 函数
    public String getDuties() {
        return this.duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secretary secretary = (Secretary) o;
        return employee_id == secretary.employee_id &&
                gender == secretary.gender &&
                age == secretary.age &&
                Objects.equals(name, secretary.name) &&
                Objects.equals(hire_date, secretary.hire_date) &&
                Objects.equals(duties, secretary.duties);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(employee_id, name, gender, age, hire_date, duties);
    }

    @Override
    public String toString() {
        return "Secretary {" +
                "employee_id = " + employee_id +
                ", name = '" + name + '\'' +
                ", gender = " + gender +
                ", age = " + age +
                ", hire_date = '" + hire_date + '\'' +
                ", duties = '" + duties + '\'' +
                "}";
    }
}

