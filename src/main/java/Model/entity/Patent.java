package Model.entity;

import java.util.Objects;

public class Patent
{
    // 成果的ID（作为主键）
    private int achievement_id;
    // 专利类型（发明、实用新型、外观设计）
    private String patent_type;
    // 专利信息
    private String information;

    // 构造函数
    public Patent(int achievement_id, String patent_type, String information) {
        this.achievement_id = achievement_id;
        this.patent_type = patent_type;
        this.information = information;
    }

    // achievement_id 的 get set 函数
    public int getAchievement_id() {
        return this.achievement_id;
    }

    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }

    // patent_type 的 get set 函数
    public String getPatent_type() {
        return this.patent_type;
    }

    public void setPatent_type(String patent_type) {
        this.patent_type = patent_type;
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
        Patent patent = (Patent) o;
        return achievement_id == patent.achievement_id &&
                patent_type == patent.patent_type &&
                Objects.equals(information, patent.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievement_id, patent_type, information);
    }

    @Override
    public String toString() {
        return "Patent {" +
                "achievement_id = " + achievement_id +
                ", patent_type = " + patent_type +
                ", information = '" + information + '\'' +
                "}";
    }
}