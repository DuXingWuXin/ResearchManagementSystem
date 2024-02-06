package Model.entity;

import java.util.Date;
import java.util.Objects;

public class Achievement
{
    // 成果的ID（自增长）
    private int achievement_id;
    // 项目的ID
    private int project_id;
    // 成果的名称
    private String achievement_name;
    // 获取成果的时间
    private Date get_time;
    // 排名
    private int rank_number;
    // 成果类型（专利、论文、软件著作权）
    private String type;
    // 包含专利类型的总类型
    private String detailType;
    // 成果简介
    private String information;

    // 构造函数
    public Achievement(int achievement_id, int project_id, String achievement_name, Date get_time, int rank_number, String type)
    {
        this.achievement_id = achievement_id;
        this.project_id = project_id;
        this.achievement_name = achievement_name;
        this.get_time = get_time;
        this.rank_number = rank_number;
        this.type = type;
    }

    // achievement_id 的 get 函数
    public int getAchievement_id() {
        return this.achievement_id;
    }

    // project_id 的 get set 函数
    public int getProject_id() {
        return this.project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    // achievement_name 的 get set 函数
    public String getAchievement_name() {
        return this.achievement_name;
    }
    public void setAchievement_name(String achievement_name) {
        this.achievement_name = achievement_name;
    }

    // get_time 的 get set 函数
    public Date getGet_time() {
        return this.get_time;
    }
    public void setGet_time(Date get_time) {
        this.get_time = get_time;
    }

    // rank_number 的 get set 函数
    public int getRank_number() {
        return this.rank_number;
    }
    public void setRank_number(int rank_number) {
        this.rank_number = rank_number;
    }

    // type 的 get set 函数
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // detailType 的 get set 函数
    public String getDetailType() {
        return this.detailType;
    }
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    // information 的 get set 函数
    public String getInformation() {
        return this.information;
    }
    public void setInformation(String information) {
        this.information = information;
    }


    // equals、hashCode、toString 方法同样需要实现
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return project_id == that.project_id &&
                rank_number == that.rank_number &&
                achievement_name.equals(that.achievement_name) &&
                get_time.equals(that.get_time) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(project_id, achievement_name, get_time, rank_number, type);
    }

    @Override
    public String toString() {
        return "Achievement {" +
                "achievement_id = " + achievement_id +
                ", project_id = " + project_id +
                ", achievement_name = '" + achievement_name + '\'' +
                ", get_time = '" + get_time + '\'' +
                ", rank_number = " + rank_number +
                ", type = " + type +
                "}";
    }
}
