package Model.entity;

import java.util.Objects;

public class Achievement_Contributor
{
    // 成果的ID
    private int achievement_id;
    // 成果名
    private String achievement_name;
    // 员工的ID
    private int employee_id;
    // 员工姓名
    private String employee_name;

    // 构造函数
    public Achievement_Contributor(int achievement_id, int employee_id)
    {
        this.achievement_id = achievement_id;
        this.employee_id = employee_id;
    }

    // achievement_id 的 get set 函数
    public int getAchievement_id() {
        return this.achievement_id;
    }
    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }

    // achievement_name 的 get set 函数
    public String getAchievement_name() {
        return this.achievement_name;
    }
    public void setAchievement_name(String achievement_name) {
        this.achievement_name = achievement_name;
    }

    // employee_id 的 get set 函数
    public int getEmployee_id() {
        return this.employee_id;
    }
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    // employee_name 的 get set 函数
    public String getEmployee_name() {
        return this.employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement_Contributor that = (Achievement_Contributor) o;
        return achievement_id == that.achievement_id &&
                employee_id == that.employee_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievement_id, employee_id);
    }

    @Override
    public String toString() {
        return "Achievement_Contributor {" +
                "achievement_id = " + achievement_id +
                ", employee_id = " + employee_id +
                "}";
    }
}
