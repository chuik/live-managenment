package com.hzlx.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title MetaObjectHandlerImpl
 * @description mybatisPlus 自动维护时间处理器
 * @createTime 2023/8/17 9:29
 **/
@Component
public class MetaObjectHandlerImpl implements MetaObjectHandler {
    /**
     * 当数据表发生 insert 的时候，触发的操作
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * 当数据表放生 update 的时候，触发的操作
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
