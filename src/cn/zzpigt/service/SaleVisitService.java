package cn.zzpigt.service;

import cn.zzpigt.bean.PageBean;
import cn.zzpigt.bean.QueryVo;
import cn.zzpigt.bean.SaleVisit;
import cn.zzpigt.bean.SaleVisitVo;

public interface SaleVisitService {
    void insertOrUpdate(SaleVisit saleVisit);

    PageBean<SaleVisitVo> getSaleVisitList(QueryVo vo);

    SaleVisitVo getSaleVisit(Long id);
}
