package cn.zzpigt.mybatis.mapper;

import cn.zzpigt.bean.SaleVisit;
import cn.zzpigt.bean.SaleVisitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleVisitMapper {
    int countByExample(SaleVisitExample example);

    int deleteByExample(SaleVisitExample example);

    int deleteByPrimaryKey(Long visitId);

    int insert(SaleVisit record);

    int insertSelective(SaleVisit record);

    List<SaleVisit> selectByExample(SaleVisitExample example);

    SaleVisit selectByPrimaryKey(Long visitId);

    int updateByExampleSelective(@Param("record") SaleVisit record, @Param("example") SaleVisitExample example);

    int updateByExample(@Param("record") SaleVisit record, @Param("example") SaleVisitExample example);

    int updateByPrimaryKeySelective(SaleVisit record);

    int updateByPrimaryKey(SaleVisit record);
}