package Model.entity;

import java.util.Date;
import java.util.Objects;

public class Director
{
    // 主任的员工ID
    private int employee_id;
    // 主任的任职开始日期
    private Date start_date;
    // 主任的任期年限
    private int tenure_years;
    // 主任所属研究室的ID
    private int lab_id;

    // 构造函数
    public Director(int employee_id, Date start_date, int tenure_years, int lab_id)
    {
        this.employee_id = employee_id;
        this.start_date = start_date;
        this.tenure_years = tenure_years;
        this.lab_id = lab_id;
    }

    // employee_id 的 get set 函数
    public int getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    // start_date 的 get set 函数
    public Date getStart_date() {
        return this.start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    // tenure_years 的 get set 函数
    public int getTenure_years() {
        return this.tenure_years;
    }

    public void setTenure_years(int tenure_years) {
        this.tenure_years = tenure_years;
    }

    // lab_id 的 get set 函数
    public int getLab_id() {
        return this.lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return employee_id == director.employee_id &&
                tenure_years == director.tenure_years &&
                lab_id == director.lab_id &&
                Objects.equals(start_date, director.start_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, start_date, tenure_years, lab_id);
    }

    @Override
    public String toString() {
        return "Director {" +
                "employee_id = " + employee_id +
                ", start_date = '" + start_date + '\'' +
                ", tenure_years = " + tenure_years +
                ", lab_id = " + lab_id +
                "}";
    }
}

