package dto;

import java.time.LocalDate;

public class Terminal {
    private String code;
    private String address;
    private String status;
    private LocalDate created_date;
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "code='" + code +
                ", address='" + address +
                ", status='" + status +
                ", created_date=" + created_date +
                ", visible=" + visible +
                '}';
    }
}
