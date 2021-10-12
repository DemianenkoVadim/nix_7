package ua.com.alevel.entity;

public class Problem {

    Integer id;
    Integer fromIdLocation;
    Integer toIdLocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromIdLocation() {
        return fromIdLocation;
    }

    public void setFromIdLocation(Integer fromIdLocation) {
        this.fromIdLocation = fromIdLocation;
    }

    public Integer getToIdLocation() {
        return toIdLocation;
    }

    public void setToIdLocation(Integer toIdLocation) {
        this.toIdLocation = toIdLocation;
    }
}
