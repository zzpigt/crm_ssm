package cn.zzpigt.service;


import cn.zzpigt.bean.CstLinkman;
import cn.zzpigt.bean.CstLinkmanVo;
import cn.zzpigt.bean.PageBean;
import cn.zzpigt.bean.QueryVo;

public interface CstLinkmanService {

    void insertOrUpdateLinkman(CstLinkman linkman);

    PageBean<CstLinkmanVo> getLinkmanList(QueryVo vo);

    CstLinkmanVo getLinkman(Long id);
}
