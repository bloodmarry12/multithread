package example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class CustomGroupTest extends BaseTask<List<String>> {
	private static final Logger logger = Logger.getLogger(SimpleTest.class);

	public CustomGroupTest(String taskName) {
		super(taskName);
	}

	@Override
	public List<String> execute() throws Exception {

		List<String> result = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			logger.debug("test " + i);
			Thread.sleep(1000);
			result.add("" + i);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		//使用自定义CustomTaskGroup，程序保证在正确的时机调用响应的执行逻辑
		CustomTaskGroup group = new CustomTaskGroup("myGroupName");
		group.addTask(new CustomGroupTest("myTaskName"));
		new TaskExecutor().submitTaskGroup(group);

	}
}
