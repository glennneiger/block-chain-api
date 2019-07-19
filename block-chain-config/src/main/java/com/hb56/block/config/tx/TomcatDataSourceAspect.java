//package com.hb56.block.config.tx;
//
//import com.codingapi.txlcn.tc.aspect.weave.DTXResourceWeaver;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//
///**
// * @author zhangjun.huangfu
// * @version 1.0
// * @create 2019-04-23-9:53
// * @company www.harbsoft.com
// */
//
//@Component
//@Aspect
//public class TomcatDataSourceAspect implements Ordered {
//
//    private static final Logger logger = LoggerFactory.getLogger(TomcatDataSourceAspect.class);
//
//    /**
//     * TX-LCN 资源切面处理对象
//     */
//    @Autowired
//    private DTXResourceWeaver dtxResourceWeaver;
//
//    @Around("execution(public java.sql.Connection com.alibaba.druid.pool.DruidDataSource.getConnection(..) )")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        logger.info("proxy my aspect..");
//        return dtxResourceWeaver.getConnection(() -> (Connection) point.proceed());
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//
//}
