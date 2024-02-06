package Model.entity;

import java.util.Date;
import java.util.Objects;

public class Project_Researcher
{
    // ID（作为主键）
    private int participation_id;
    // 项目ID
    private int project_id;
    // 员工ID
    private int employee_id;
    // 加入日期
    private Date join_date;
    // 工作量
    private double workload;
    // 可控制的资金
    private double control_funds;

    // 构造函数
    public Project_Researcher(int participation_id, int project_id, int employee_id, Date join_date, double workload, double control_funds)
    {
        this.participation_id = participation_id;
        this.project_id = project_id;
        this.employee_id = employee_id;
        this.join_date = join_date;
        this.workload = workload;
        this.control_funds = control_funds;
    }

    // participation_id 的 get set 函数
    public int getParticipation_id() {
        return this.participation_id;
    }

    public void setParticipation_id(int participation_id) {
        this.participation_id = participation_id;
    }

    // project_id 的 get set 函数
    public int getProject_id() {
        return this.project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    // employee_id 的 get set 函数
    public int getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    // join_date 的 get set 函数
    public Date getJoin_date() {
        return this.join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    // workload 的 get set 函数
    public double getWorkload() {
        return this.workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    // control_funds 的 get set 函数
    public double getControl_funds() {
        return this.control_funds;
    }

    public void setControl_funds(double control_funds) {
        this.control_funds = control_funds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project_Researcher that = (Project_Researcher) o;
        return participation_id == that.participation_id &&
                project_id == that.project_id &&
                employee_id == that.employee_id &&
                Double.compare(that.workload, workload) == 0 &&
                Double.compare(that.control_funds, control_funds) == 0 &&
                Objects.equals(join_date, that.join_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participation_id, project_id, employee_id, join_date, workload, control_funds);
    }

    @Override
    public String toString() {
        return "ProjectResearcher {" +
                "participation_id = " + participation_id +
                ", project_id = " + project_id +
                ", employee_id = " + employee_id +
                ", join_date = '" + join_date + '\'' +
                ", workload = " + workload +
                ", control_funds = " + control_funds +
                "}";
    }
}
