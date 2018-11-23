package cn.zzpigt.service.impl;

import cn.zzpigt.bean.BaseDict;
import cn.zzpigt.bean.BaseDictExample;
import cn.zzpigt.mybatis.mapper.BaseDictMapper;
import cn.zzpigt.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDictServiceImpl implements BaseDictService {

    @Autowired
    private BaseDictMapper bdm;

    @Override
    public List<BaseDict> getByTypeCode(String typecode) {
        BaseDictExample example = new BaseDictExample();
        BaseDictExample.Criteria criteria = example.createCriteria();
        criteria.andDictTypeCodeEqualTo(typecode);
        List<BaseDict> list = bdm.selectByExample(example);
        return list;
    }
}
