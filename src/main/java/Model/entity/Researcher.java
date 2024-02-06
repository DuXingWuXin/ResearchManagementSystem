package Model.entity;

import java.util.Objects;

public class Researcher
{
    // 研究员的员工ID
    private int employee_id;
    // 研究员的姓名
    private String name;
    // 研究员的性别
    private String gender;
    // 研究员的职称
    private String title;
    // 研究员的年龄
    private int age;
    // 研究员的研究领域
    private String research_area;
    // 研究员所属研究室的ID
    private int lab_id;

    // 构造函数
    public Researcher(int employee_id, String name, String gender, String title, int age, String research_area, int lab_id)
    {
        this.employee_id = employee_id;
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.age = age;
        this.research_area = research_area;
        this.lab_id = lab_id;
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

    // title 的 get set 函数
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // age 的 get set 函数
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // research_area 的 get set 函数
    public String getResearch_area() {
        return this.research_area;
    }

    public void setResearch_area(String research_area) {
        this.research_area = research_area;
    }

    // lab_id 的 get set 函数
    public int getLab_id() {
        return this.lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Researcher researcher = (Researcher) o;
        return employee_id == researcher.employee_id &&
                Objects.equals(gender, researcher.gender) &&
                age == researcher.age &&
                lab_id == researcher.lab_id &&
                Objects.equals(name, researcher.name) &&
                Objects.equals(title, researcher.title) &&
                Objects.equals(research_area, researcher.research_area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, name, gender, title, age, research_area, lab_id);
    }

    @Override
    public String toString() {
        return "Researcher {" +
                "employee_id = " + employee_id +
                ", name = '" + name + '\'' +
                ", gender = " + gender +
                ", title = '" + title + '\'' +
                ", age = " + age +
                ", research_area = '" + research_area + '\'' +
                ", lab_id = " + lab_id +
                "}";
    }
}

