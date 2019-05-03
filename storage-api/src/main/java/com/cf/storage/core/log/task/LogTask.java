package com.cf.storage.core.log.task;

import com.cf.storage.common.pool.thread.FstTask;
import com.cf.storage.core.log.po.SysLog;
import com.cf.storage.core.log.service.LogService;

/** 
 * <p>类名称     ：com.fst.core.log.task.LogTask</p>
 * <p>描述          ：自动记录日志任务</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月12日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class LogTask extends FstTask {

    private SysLog log;
    private LogService logService;

    @Override
    public void execute() {
        logService.addLog(log);
    }

    public LogTask(SysLog log, LogService logService) {
        super();
        this.log = log;
        this.logService = logService;
    }

}
