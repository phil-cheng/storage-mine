package com.cf.storage.core.log.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cf.storage.core.log.dao.SysLogMapper;
import com.cf.storage.core.log.po.SysLog;

/** 
 * <p>类名称     ：com.fst.core.log.service.LogServiceImpl</p>
 * <p>描述          ：</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月11日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private SysLogMapper logMapper;

    @Override
    public int addLog(SysLog log) {
        int res = 0;
        res = logMapper.insert(log);
        return res;
    }
}
