package cn.zzpigt.service.impl;

import cn.zzpigt.bean.*;
import cn.zzpigt.mybatis.mapper.BaseDictMapper;
import cn.zzpigt.mybatis.mapper.CstCustomerMapper;
import cn.zzpigt.service.CstCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CstCustomerServiceImpl implements CstCustomerService {

    @Autowired
    private CstCustomerMapper ccm;

    @Autowired
    private BaseDictMapper bdm;

    @Override
    public PageBean<CstCustomerVo> getCustomerList(QueryVo vo) {
        CstCustomerExample example = new CstCustomerExample();
        CstCustomerExample.Criteria criteria = example.createCriteria();
        if (vo != null && vo.getCustomer() != null && vo.getCustomer().getCustName() != null && vo.getCustomer().getCustName().length() > 0) {
            criteria.andCustNameLike("%" + vo.getCustomer().getCustName() + "%");
        }

        //查询数据库中客户总数
        int totalCount = ccm.countByExample(example);
        PageBean<CstCustomerVo> pageBean = new PageBean<>(vo.getPageNum(), vo.getPageSize(), totalCount);

        example.setStart(pageBean.getStart());
        example.setSize(pageBean.getPageSize());

        List<CstCustomer> list = ccm.selectByExample(example);
        List<CstCustomerVo> voList = new ArrayList<>();
        for (CstCustomer c : list) {
            CstCustomerVo customerVo = new CstCustomerVo(c);
            customerVo.setCustIndustry(bdm.selectByPrimaryKey(c.getCustIndustry()));
            customerVo.setCustLevel(bdm.selectByPrimaryKey(c.getCustLevel()));
            customerVo.setCustSource(bdm.selectByPrimaryKey(c.getCustSource()));
            voList.add(customerVo);
        }

        pageBean.setList(voList);

        return pageBean;
    }

    @Override
    public void insertCustomer(CstCustomer customer) {
        ccm.insert(customer);
    }

    @Override
    public CstCustomer getCustomerById(Long custId) {
        return ccm.selectByPrimaryKey(custId);
    }

    @Override
    public void updateCustomer(CstCustomer customer) {
        ccm.updateByPrimaryKey(customer);
    }

    @Override
    public List<CountInfo> getIndustryCount() {
        //分组查询customer表
        List<CountInfo> list = ccm.getAllIndustryCount();
        for (CountInfo c:list) {
            //根据数据字典表查basedict
            c.setInfoName(bdm.selectByPrimaryKey(c.getBid()).getDictItemName());
        }
        System.out.println(list);
        return list;
    }

    @Override
    public List<CountInfo> getSourceCount() {
        //分组查询customer表
        List<CountInfo> list = ccm.getAllSourceCount();
        System.out.println("查表："+list);
        for (CountInfo c:list) {
            //根据数据字典表查basedict
            System.out.println(bdm.selectByPrimaryKey(c.getBid()).getDictItemName());
            c.setInfoName(bdm.selectByPrimaryKey(c.getBid()).getDictItemName());
        }
        System.out.println(list);
        return list;
    }
}
