package cn.zzpigt.service;

import cn.zzpigt.bean.BaseDict;

import java.util.List;

public interface BaseDictService {
    List<BaseDict> getByTypeCode(String typecode);
}
