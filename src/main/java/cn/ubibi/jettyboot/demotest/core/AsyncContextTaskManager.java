package cn.ubibi.jettyboot.demotest.core;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.AsyncContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class AsyncContextTaskManager {
    private static Logger logger = LoggerFactory.getLogger(AsyncContextTaskManager.class);

    private static ExecutorService execPools = Executors.newFixedThreadPool(10);

    //正在执行的
    private static final Map<String, DeferredResultUnionTask> runningTaskMap = new HashMap<>();

    public static synchronized void addTask(String taskKey, AsyncContext deferredResult, Callable callable) {
        DeferredResultUnionTask deferredResultUnionTask = runningTaskMap.get(taskKey);
        if (deferredResultUnionTask == null) {
            deferredResultUnionTask = new DeferredResultUnionTask(taskKey);
            deferredResultUnionTask.doRun(callable);
            runningTaskMap.put(taskKey, deferredResultUnionTask);
        }
        deferredResultUnionTask.addCallbackDeferredResult(deferredResult);
    }


    private static synchronized void removeTask(String taskKey) {
        runningTaskMap.remove(taskKey);
    }

    private static class DeferredResultUnionTask {
        private List<AsyncContext> deferredResultList = new ArrayList<>();
        private String taskKey;

        public DeferredResultUnionTask(String taskKey) {
            this.taskKey = taskKey;
        }

        public void addCallbackDeferredResult(AsyncContext deferredResult) {
            deferredResultList.add(deferredResult);
        }

        public void doRun(Callable callable) {
            execPools.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        Object callableResult = callable.call();


                        AsyncContextTaskManager.removeTask(taskKey);

                        for (AsyncContext asyncContext : deferredResultList) {
                            String json = JSON.toJSONString(callableResult);

                            asyncContext.getResponse().getWriter().write(json);

                            asyncContext.getResponse().getWriter().close();
                            asyncContext.getResponse().flushBuffer();
                            System.out.println("response..");
                            asyncContext.complete();
                        }
                    } catch (Exception e) {
                        logger.error("",e);
                    }
                }
            });
        }
    }
}
