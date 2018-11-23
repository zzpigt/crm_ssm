package cn.zzpigt.service.impl;

import cn.zzpigt.bean.*;
import cn.zzpigt.mybatis.mapper.CstCustomerMapper;
import cn.zzpigt.mybatis.mapper.SaleVisitMapper;
import cn.zzpigt.mybatis.mapper.SysUserMapper;
import cn.zzpigt.service.SaleVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleVisitServiceImpl implements SaleVisitService {

    @Autowired
    private SaleVisitMapper saleVisitMapper;
    @Autowired
    private CstCustomerMapper customerMapper;
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public void insertOrUpdate(SaleVisit saleVisit) {
        //

        //判断传上来的salevisit有没有visitId
        if(saleVisit.getVisitId() != null){
            //update
            saleVisitMapper.updateByPrimaryKeySelective(saleVisit);
            System.out.println(saleVisit+"不是添加而是修改");
        }else{
            //insert
            saleVisitMapper.insertSelective(saleVisit);
        }


    }

    @Override
    public PageBean<SaleVisitVo> getSaleVisitList(QueryVo vo) {
        SaleVisitExample example = new SaleVisitExample();
        SaleVisitExample.Criteria criteria = example.createCriteria();
        if (vo != null && vo.getCustomer() != null && vo.getCustomer().getCustId() != null) {
            criteria.andVisitCustIdEqualTo(vo.getCustomer().getCustId());
        }

        //查询数据库中客户总数
        int totalCount = saleVisitMapper.countByExample(example);
        PageBean<SaleVisitVo> pageBean = new PageBean<>(vo.getPageNum(), vo.getPageSize(), totalCount);

        example.setStart(pageBean.getStart());
        example.setSize(pageBean.getPageSize());

        List<SaleVisit> list = saleVisitMapper.selectByExample(example);
        List<SaleVisitVo> voList = new ArrayList<>();
        for (SaleVisit c : list) {
            SaleVisitVo saleVisitVo = new SaleVisitVo(c);
            saleVisitVo.setVisitCustId(customerMapper.selectByPrimaryKey(c.getVisitCustId()));
            saleVisitVo.setVisitUserId(userMapper.selectByPrimaryKey(c.getVisitUserId()));
            voList.add(saleVisitVo);
        }
        pageBean.setList(voList);
        return pageBean;
    }

    @Override
    public SaleVisitVo getSaleVisit(Long id) {
        SaleVisit saleVisit = saleVisitMapper.selectByPrimaryKey(id);
        SaleVisitVo saleVisitVo = new SaleVisitVo(saleVisit);
        saleVisitVo.setVisitUserId(userMapper.selectByPrimaryKey(saleVisit.getVisitUserId()));
        saleVisitVo.setVisitCustId(customerMapper.selectByPrimaryKey(saleVisit.getVisitCustId()));
        return saleVisitVo;
    }
}
