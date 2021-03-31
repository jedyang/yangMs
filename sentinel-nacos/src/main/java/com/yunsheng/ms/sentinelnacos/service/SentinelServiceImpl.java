package com.yunsheng.ms.sentinelnacos.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.stereotype.Service;

@Service
public class SentinelServiceImpl {

    @SentinelResource(value = "testSentinel", blockHandler = "demoBlockHandler")
    public void testServiceMethodSentinel() throws IllegalStateException {
        // 模拟业务处理时间
        double time = Math.random() * 100;
        try {
            Thread.sleep((long) time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 要求
     * 方法返回值、访问修饰符、抛出异常要与调用的方法完全相同。
     * @param e
     * @throws IllegalStateException
     */
    public void demoBlockHandler(BlockException e) throws IllegalStateException {
        String msg = null;
        if (e instanceof FlowException) {//限流异常
            msg = "资源已被限流";
        } else if (e instanceof DegradeException) {//熔断异常
            msg = "资源已被熔断,请稍后再试";
        } else if (e instanceof ParamFlowException) { //热点参数限流
            msg = "热点参数限流";
        } else if (e instanceof SystemBlockException) { //系统规则异常
            msg = "系统规则(负载/....不满足要求)";
        } else if (e instanceof AuthorityException) { //授权规则异常
            msg = "授权规则不通过";
        }
        throw new IllegalStateException(msg);
    }
}
