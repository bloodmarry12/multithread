package example;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

/**
 * 自定义逻辑处理类需要继承自BaseTask，并将加入返回结果泛型
 */
public class SimpleTest extends BaseTask<List<String>> {
	private static final Logger logger = Logger.getLogger(SimpleTest.class);

	public SimpleTest(String taskName) {
		super(taskName);
	}

	@Override
	public List<String> execute() throws Exception {

		List<String> result = new ArrayList<String>();

		//模拟复杂业务逻辑
		for (int i = 0; i < 10; i++) {
			logger.debug("test " + i);
			Thread.sleep(1000);
			result.add("" + i);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		// 创建组
		DefaultTaskGroup group = new DefaultTaskGroup("myGroupName");
		//将执行线程加入组中
		group.addTask(new SimpleTest("myTaskName"));
		//启动执行器执行
		new TaskExecutor().submitTaskGroup(group);

	}
}
