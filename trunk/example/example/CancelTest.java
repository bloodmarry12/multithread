package example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class CancelTest extends BaseTask<List<String>> {
	private final Logger logger = Logger.getLogger(getClass());
	private volatile boolean cancel;

	public CancelTest(String taskName) {
		super(taskName);
	}

	/**
	 * ����ȡ�������β����
	 */
	public void cancelTask() throws Exception {
		cancel = true;
	}

	@Override
	public List<String> execute() throws Exception {

		List<String> result = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			if (cancel) {
				logger.debug("ִ�е�" + i + "��ֹͣ");
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
		group.addTask(new CancelTest("myTaskName"));
		new TaskExecutor().submitTaskGroup(group);

		//ִ�������ȡ������
		Thread.sleep(5000);
		group.cancelTask("myTaskName");

	}
}
