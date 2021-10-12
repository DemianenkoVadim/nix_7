package ua.com.alevel.entity;

public class Route {

    Integer id;
    Integer cost;
    Integer fromIdLocation;
    Integer toIdLocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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
