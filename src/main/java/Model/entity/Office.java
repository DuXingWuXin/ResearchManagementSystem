package Model.entity;

import java.util.Objects;

public class Office
{
    // id
    private int id;
    // 关联的研究室id
    private int lab_id;
    // 地址
    private String location;
    // 面积
    private double area;

    public Office(int id, int lab_id, String location, double area)
    {
        this.id = id;
        this.lab_id = lab_id;
        this.location = location;
        this.area = area;
    }

    // id的 get set函数
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // lab_id的 get set函数
    public int getLab_id() {return lab_id;}
    public void setLab_id(int lab_id) {this.lab_id = lab_id;}

    // 地址的 get set函数
    public String getLocation(){return this.location;}
    public void setLocation(String address){this.location = address;}

    // 面积的 get set函数
    public double getArea() {return this.area;}
    public void setArea(double area) {this.area = area;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office that = (Office) o;
        return Objects.equals(id, that.id) && Objects.equals(lab_id, that.lab_id) && Objects.equals(location, that.location) && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, lab_id, location, area);
    }

    @Override
    public String toString()
    {
        return "WorkArea {" +
                "id = " + this.id + '\'' +
                "lab_id = " + this.lab_id + '\'' +
                "location = " + this.location + '\'' +
                "area = " + this.area +
                "}";
    }
}
