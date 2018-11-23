package cn.zzpigt.service;

import cn.zzpigt.bean.*;

import java.util.List;

public interface CstCustomerService {
    PageBean<CstCustomerVo> getCustomerList(QueryVo vo);

    void insertCustomer(CstCustomer customer);

    CstCustomer getCustomerById(Long custId);

    void updateCustomer(CstCustomer customer);

    List<CountInfo> getIndustryCount();

    List<CountInfo> getSourceCount();
}
