package cn.zzpigt.mybatis.mapper;

import cn.zzpigt.bean.CountInfo;
import cn.zzpigt.bean.CstCustomer;
import cn.zzpigt.bean.CstCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CstCustomerMapper {
    int countByExample(CstCustomerExample example);

    int deleteByExample(CstCustomerExample example);

    int deleteByPrimaryKey(Long custId);

    int insert(CstCustomer record);

    int insertSelective(CstCustomer record);

    List<CstCustomer> selectByExample(CstCustomerExample example);

    CstCustomer selectByPrimaryKey(Long custId);

    int updateByExampleSelective(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByExample(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByPrimaryKeySelective(CstCustomer record);

    int updateByPrimaryKey(CstCustomer record);

    List<CountInfo> getAllIndustryCount();

    List<CountInfo> getAllSourceCount();
}