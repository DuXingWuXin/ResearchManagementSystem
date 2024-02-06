package Model.entity;

import java.util.Objects;

public class Software
{
    // 成果的ID（作为主键）
    private int achievement_id;
    // 软件信息
    private String information;

    // 构造函数
    public Software(int achievement_id, String information) {
        this.achievement_id = achievement_id;
        this.information = information;
    }

    // achievement_id 的 get set 函数
    public int getAchievement_id() {
        return this.achievement_id;
    }

    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }

    // information 的 get set 函数
    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Software software = (Software) o;
        return achievement_id == software.achievement_id &&
                Objects.equals(information, software.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievement_id, information);
    }

    @Override
    public String toString() {
        return "Software {" +
                "achievement_id = " + achievement_id +
                ", information = '" + information + '\'' +
                "}";
    }
}
