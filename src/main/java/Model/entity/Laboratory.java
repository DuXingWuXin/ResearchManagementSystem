package Model.entity;

import java.util.Objects;

public class Laboratory
{
    // 研究室的id
    private int lab_id;
    // 研究室的名称
    private String name;
    // 研究室的研究方向介绍
    private String introduction;
    // 研究室秘书
    private int employee_id;

    public Laboratory(int lab_id,String name, String introduction, int employee_id)
    {
        this.lab_id = lab_id;
        this.name = name;
        this.introduction = introduction;
        this.employee_id = employee_id;
    }

    // id 的 get set函数
    public int getLab_id(){return this.lab_id;}
    public void setLab_id(int lab_id) {this.lab_id = lab_id;}

    // name 的 get set函数
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    // introduction 的 get set函数
    public String getIntroduction(){return this.introduction;}
    public void setIntroduction(String introduction){this.introduction = introduction;}

    // employ_id 的 get set函数
    public int getEmployee_id(){return this.employee_id;}
    public void setEmployee_id(int employee_id) {this.employee_id = employee_id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laboratory that = (Laboratory) o;
        return Objects.equals(lab_id, that.lab_id) && Objects.equals(name, that.name) && Objects.equals(introduction, that.introduction) && Objects.equals(employee_id, that.employee_id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lab_id, name, introduction, employee_id);
    }

    @Override
    public String toString()
    {
        return "Laboratory {" +
                "id = " + this.lab_id + '\'' +
                "name = " + this.name + '\'' +
                "introduction = " + this.introduction + '\'' +
                "employee_id = " + this.employee_id +
                "}";
    }
}