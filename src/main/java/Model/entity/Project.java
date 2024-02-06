package Model.entity;

import java.util.Date;
import java.util.Objects;

public class Project
{
    // 项目的ID（作为主键）
    private int project_id;
    // 项目名称
    private String project_name;
    // 项目负责人
    private String head;
    // 项目内容
    private String content;
    // 项目总经费
    private double total_funds;
    // 项目开始日期
    private Date start_date;
    // 项目结束日期
    private Date end_date;

    // 构造函数
    public Project(int project_id, String project_name, String head, String content, double total_funds, Date start_date, Date end_date) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.head = head;
        this.content = content;
        this.total_funds = total_funds;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    // project_id 的 get set 函数
    public int getProject_id() {
        return this.project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    // project_name 的 get set 函数
    public String getProject_name() {
        return this.project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    // head 的 get set 函数
    public String getHead() {
        return this.head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    // content 的 get set 函数
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // total_funds 的 get set 函数
    public double getTotal_funds() {
        return this.total_funds;
    }

    public void setTotal_funds(double total_funds) {
        this.total_funds = total_funds;
    }

    // start_date 的 get set 函数
    public Date getStart_date() {
        return this.start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    // end_date 的 get set 函数
    public Date getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return project_id == project.project_id &&
                Double.compare(project.total_funds, total_funds) == 0 &&
                Objects.equals(project_name, project.project_name) &&
                Objects.equals(head, project.head) &&
                Objects.equals(content, project.content) &&
                Objects.equals(start_date, project.start_date) &&
                Objects.equals(end_date, project.end_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project_id, project_name, head, content, total_funds, start_date, end_date);
    }

    @Override
    public String toString() {
        return "Project {" +
                "project_id = " + project_id +
                ", project_name = '" + project_name + '\'' +
                ", head = '" + head + '\'' +
                ", content = '" + content + '\'' +
                ", total_funds = " + total_funds +
                ", start_date = '" + start_date + '\'' +
                ", end_date = '" + end_date + '\'' +
                "}";
    }
}
