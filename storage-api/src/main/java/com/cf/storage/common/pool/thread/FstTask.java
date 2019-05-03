package com.cf.storage.common.pool.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class FstTask implements Runnable {

    protected static Logger logger = LogManager.getLogger(FstTask.class);

    public abstract void execute();

    @Override
    public void run() {
        try {
            execute();
            logger.info("dblog task execute success!!");
        }
        catch (Exception e) {
            logger.error(e, e);
        }
    }
}
