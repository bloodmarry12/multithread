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
		group.addTask(new ShutDownNowTest("myTaskName"));
		TaskExecutor te = new TaskExecutor();
		te.submitTaskGroup(group);

		// ִ���������������ֹͣ
		// �̳߳ؽ�����ִ���µ������������е����񽫻ᱻִ��ȡ������
		Thread.sleep(3000);
		te.shutDownNow();

		DefaultTaskGroup group2 = new DefaultTaskGroup("myGroupName2");
		group2.addTask(new ShutDownNowTest("myTaskName2"));
		te.submitTaskGroup(group2);

	}
}
