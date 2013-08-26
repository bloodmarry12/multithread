package example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class ShutDownNowTest extends BaseTask<List<String>> {
	private final Logger logger = Logger.getLogger(getClass());
	private volatile boolean cancel;

	public ShutDownNowTest(String taskName) {
		super(taskName);
	}

	public void cancelTask() throws Exception {
		cancel = true;
	}

	@Override
	public List<String> execute() throws Exception {

		List<String> result = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			if (cancel) {
				logger.debug("执行到" + i + "被停止");
				break;
			}

			logger.debug("test " + i);
			Thread.sleep(1000);
			result.add("" + i);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		DefaultTaskGroup group = new DefaultTaskGroup("myGroupName");
		group.addTask(new ShutDownNowTest("myTaskName"));
		TaskExecutor te = new TaskExecutor();
		te.submitTaskGroup(group);

		// 执行三秒后任务立即停止
		// 线程池将不再执行新的任务，正在运行的任务将会被执行取消操作
		Thread.sleep(3000);
		te.shutDownNow();

		DefaultTaskGroup group2 = new DefaultTaskGroup("myGroupName2");
		group2.addTask(new ShutDownNowTest("myTaskName2"));
		te.submitTaskGroup(group2);

	}
}
