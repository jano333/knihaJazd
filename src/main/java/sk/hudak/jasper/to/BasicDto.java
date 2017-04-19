package sk.hudak.jasper.to;

/**
 * Created by hudak on 29.09.2016.
 */
public class BasicDto {

    private String groupName;
    private String unit;

    public BasicDto() {
    }

    public BasicDto(String groupName, String unit) {
        this.groupName = groupName;
        this.unit = unit;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
