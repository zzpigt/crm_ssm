package cn.zzpigt.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleVisitVo {
    private Long visitId;

    private CstCustomer visitCustId;

    private SysUser visitUserId;

    private Date visitTime;

    private String visitInterviewee;

    private String visitAddr;

    private String visitDetail;

    private Date visitNexttime;


    public SaleVisitVo(SaleVisit po) {
        this.visitId = po.getVisitId();
        this.visitTime = po.getVisitTime();
        this.visitInterviewee = po.getVisitInterviewee();
        this.visitAddr = po.getVisitAddr();
        this.visitDetail = po.getVisitDetail();
        this.visitNexttime = po.getVisitNexttime();
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public CstCustomer getVisitCustId() {
        return visitCustId;
    }

    public void setVisitCustId(CstCustomer visitCustId) {
        this.visitCustId = visitCustId;
    }

    public SysUser getVisitUserId() {
        return visitUserId;
    }

    public void setVisitUserId(SysUser visitUserId) {
        this.visitUserId = visitUserId;
    }

    public String getVisitTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(visitTime);
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitInterviewee() {
        return visitInterviewee;
    }

    public void setVisitInterviewee(String visitInterviewee) {
        this.visitInterviewee = visitInterviewee == null ? null : visitInterviewee.trim();
    }

    public String getVisitAddr() {
        return visitAddr;
    }

    public void setVisitAddr(String visitAddr) {
        this.visitAddr = visitAddr == null ? null : visitAddr.trim();
    }

    public String getVisitDetail() {
        return visitDetail;
    }

    public void setVisitDetail(String visitDetail) {
        this.visitDetail = visitDetail == null ? null : visitDetail.trim();
    }

    public String getVisitNexttime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(visitNexttime);
    }

    public void setVisitNexttime(Date visitNexttime) {
        this.visitNexttime = visitNexttime;
    }

    @Override
    public String toString() {
        return "SaleVisitVo{" +
                "visitId=" + visitId +
                ", visitCustId=" + visitCustId +
                ", visitUserId=" + visitUserId +
                ", visitTime=" + visitTime +
                ", visitInterviewee='" + visitInterviewee + '\'' +
                ", visitAddr='" + visitAddr + '\'' +
                ", visitDetail='" + visitDetail + '\'' +
                ", visitNexttime=" + visitNexttime +
                '}';
    }
}