package example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class ProgressTest extends BaseTask<List<String>> {
	private static final Logger logger = Logger.getLogger(ProgressTest.class);

	public ProgressTest(String taskName) {
		super(taskName);
	}

	@Override
	public List<String> execute() throws Exception {

		List<String> result = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			logger.debug("test " + i);
			Thread.sleep(1000);
			// 根据自身逻辑设置进度
			super.getTaskExecuteInfo().setProcess((i + 1) * 10);
			result.add("" + i);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		DefaultTaskGroup group = new DefaultTaskGroup("myGroupName");
		group.addTask(new ProgressTest("myTaskName"));
		new TaskExecutor().submitTaskGroup(group);

		Thread.sleep(5000);

		logger.debug("当前执行进度：" + group.getProcessByTaskName("myTaskName") + "%");

	}
}
