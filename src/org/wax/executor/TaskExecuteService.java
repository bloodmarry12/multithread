package org.wax.executor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.wax.futuretask.ExecutorFutureTask;
import org.wax.task.BaseTask;
import org.wax.task.TaskGroup;

public class TaskExecuteService<V>  {
	  private final Executor executor;
	  private final BlockingQueue<ExecutorFutureTask<V>> completionQueue;

	    public TaskExecuteService() {
	        this.executor =  Executors.newFixedThreadPool(10);  ;
	        this.completionQueue = new LinkedBlockingQueue<ExecutorFutureTask<V>>();
	    }
	    
	    public void shutDown(){
	    	if(executor instanceof ThreadPoolExecutor){
	    		((ThreadPoolExecutor)executor).shutdown();
	    	}
		}
		
		public void shutDownNow(Map<String,TaskGroup> groupMap){
			this.shutDown();
			for(String key:groupMap.keySet()){
				TaskGroup group=groupMap.get(key);
				if(group==null||group.getTasks()==null)
					continue;
				List<BaseTask> tasks=group.getTasks();
				for(BaseTask task:tasks){
					try{
					task.cancelTask();
					}catch(Exception e){
						task.getTaskGroup().getExceptionHandler().handleException(task, e);
					}
				}
			}
			
		}
		
	    public TaskExecuteService(Executor executor){
	    	this.executor=executor;
	    	 this.completionQueue = new LinkedBlockingQueue<ExecutorFutureTask<V>>();
	    }

	    public void submit(BaseTask<V> task) {
	        if (task == null) throw new NullPointerException();
	        ExecutorFutureTask <V> eft= new ExecutorFutureTask<V>((BaseTask<V>)task);
	        completionQueue.add(eft);
	        executor.execute(eft);
	    }
	    public ExecutorFutureTask<V> take() throws InterruptedException {
	        return completionQueue.take();
	    }


}
