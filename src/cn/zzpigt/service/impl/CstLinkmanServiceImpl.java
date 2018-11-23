package cn.zzpigt.service.impl;

import cn.zzpigt.bean.*;
import cn.zzpigt.mybatis.mapper.CstCustomerMapper;
import cn.zzpigt.mybatis.mapper.CstLinkmanMapper;
import cn.zzpigt.service.CstLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CstLinkmanServiceImpl implements CstLinkmanService {

    @Autowired
    private CstLinkmanMapper lkMapper;

    @Autowired
    private CstCustomerMapper ctMapper;

    @Override
    public void insertOrUpdateLinkman(CstLinkman lm) {
        if(lm.getLkmId() != null){
            lkMapper.updateByPrimaryKeySelective(lm);
        }else{
            lkMapper.insertSelective(lm);
        }
    }

    @Override
    public PageBean<CstLinkmanVo> getLinkmanList(QueryVo vo) {
        CstLinkmanExample example = new CstLinkmanExample();
        CstLinkmanExample.Criteria criteria = example.createCriteria();
        if (vo != null && vo.getLinkman() != null && vo.getLinkman().getLkmName() != null && vo.getLinkman().getLkmName().length() > 0) {
            criteria.andLkmNameLike("%" + vo.getLinkman().getLkmName() + "%");
        }

        //查询数据库中客户总数
        int totalCount = lkMapper.countByExample(example);
        PageBean<CstLinkmanVo> pageBean = new PageBean<>(vo.getPageNum(), vo.getPageSize(), totalCount);

        example.setStart(pageBean.getStart());
        example.setSize(pageBean.getPageSize());

        List<CstLinkman> list = lkMapper.selectByExample(example);
        List<CstLinkmanVo> voList = new ArrayList<>();
        for (CstLinkman c : list) {
            CstLinkmanVo linkmanVo = new CstLinkmanVo(c);
            linkmanVo.setLkmCustomer(ctMapper.selectByPrimaryKey(c.getLkmCustId()));
            voList.add(linkmanVo);
        }

        pageBean.setList(voList);
        return pageBean;
    }

    @Override
    public CstLinkmanVo getLinkman(Long id) {
        CstLinkman linkmanPo = lkMapper.selectByPrimaryKey(id);
        CstLinkmanVo linkmanVo = new CstLinkmanVo(linkmanPo);
        linkmanVo.setLkmCustomer(ctMapper.selectByPrimaryKey(linkmanPo.getLkmCustId()));
        return linkmanVo;
    }
}
