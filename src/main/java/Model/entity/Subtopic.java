package Model.entity;

import java.util.Objects;

public class Subtopic
{
    // 子课题ID（作为主键）
    private int subtopic_id;
    // 项目ID
    private int project_id;
    // 子课题负责人
    private String head;
    // 截止日期
    private String end_date;
    // 总经费
    private double total_funds;
    // 技术条件
    private String technology_condition;

    // 构造函数
    public Subtopic(int subtopic_id, int project_id, String head, String end_date, double total_funds, String technology_condition)
    {
        this.subtopic_id = subtopic_id;
        this.project_id = project_id;
        this.head = head;
        this.end_date = end_date;
        this.total_funds = total_funds;
        this.technology_condition = technology_condition;
    }

    // subtopic_id 的 get set 函数
    public int getSubtopic_id() {
        return this.subtopic_id;
    }

    public void setSubtopic_id(int subtopic_id) {
        this.subtopic_id = subtopic_id;
    }

    // project_id 的 get set 函数
    public int getProject_id() {
        return this.project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    // head 的 get set 函数
    public String getHead() {
        return this.head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    // end_date 的 get set 函数
    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    // total_funds 的 get set 函数
    public double getTotal_funds() {
        return this.total_funds;
    }

    public void setTotal_funds(double total_funds) {
        this.total_funds = total_funds;
    }

    // technology_condition 的 get set 函数
    public String getTechnology_condition() {
        return this.technology_condition;
    }

    public void setTechnology_condition(String technology_condition) {
        this.technology_condition = technology_condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtopic subtopic = (Subtopic) o;
        return subtopic_id == subtopic.subtopic_id &&
                project_id == subtopic.project_id &&
                Double.compare(subtopic.total_funds, total_funds) == 0 &&
                Objects.equals(head, subtopic.head) &&
                Objects.equals(end_date, subtopic.end_date) &&
                Objects.equals(technology_condition, subtopic.technology_condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtopic_id, project_id, head, end_date, total_funds, technology_condition);
    }

    @Override
    public String toString() {
        return "Subtopic {" +
                "subtopic_id = " + subtopic_id +
                ", project_id = " + project_id +
                ", head = '" + head + '\'' +
                ", end_date = '" + end_date + '\'' +
                ", total_funds = " + total_funds +
                ", technology_condition = '" + technology_condition + '\'' +
                "}";
    }
}
