package by.training.entity.support;

import by.training.entity.Entity;

/**
 * Created by angelina on 19.04.2017.
 */
public class TechSupport extends Entity {
    private int techSupportId;
    private int userId;
    private String theme;
    private String text;
    private boolean state;

    public TechSupport() {
    }

    public TechSupport(int techSupportId, int userId, String theme, String text, boolean state) {
        this.techSupportId = techSupportId;
        this.userId = userId;
        this.theme = theme;
        this.text = text;
        this.state = state;
    }

    public int getTechSupportId() {
        return techSupportId;
    }

    public void setTechSupportId(int techSupportId) {
        this.techSupportId = techSupportId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechSupport)) return false;

        TechSupport that = (TechSupport) o;

        if (getTechSupportId() != that.getTechSupportId()) return false;
        if (getUserId() != that.getUserId()) return false;
        if (isState() != that.isState()) return false;
        if (getTheme() != null ? !getTheme().equals(that.getTheme()) : that.getTheme() != null) return false;
        return getText() != null ? getText().equals(that.getText()) : that.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getTechSupportId();
        result = 31 * result + getUserId();
        result = 31 * result + (getTheme() != null ? getTheme().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (isState() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechSupport{" +
                "techSupportId=" + techSupportId +
                ", userId=" + userId +
                ", theme='" + theme + '\'' +
                ", text='" + text + '\'' +
                ", state=" + state +
                '}';
    }
}
