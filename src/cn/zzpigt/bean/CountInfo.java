package cn.zzpigt.bean;

public class CountInfo {

    private String bid;
    private Integer count;
    private String infoName;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    @Override
    public String toString() {
        return "CountInfo{" +
                "bid='" + bid + '\'' +
                ", count=" + count +
                ", infoName='" + infoName + '\'' +
                '}';
    }
}
