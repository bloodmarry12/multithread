package example;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

/**
 * �Զ����߼���������Ҫ�̳���BaseTask���������뷵�ؽ������
 */
public class SimpleTest extends BaseTask<List<String>> {
	private static final Logger logger = Logger.getLogger(SimpleTest.class);

	public SimpleTest(String taskName) {
		super(taskName);
	}

	@Override
	public List<String> execute() throws Exception {

		List<String> result = new ArrayList<String>();

		//ģ�⸴��ҵ���߼�
		for (int i = 0; i < 10; i++) {
			logger.debug("test " + i);
			Thread.sleep(1000);
			result.add("" + i);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		// ������
		DefaultTaskGroup group = new DefaultTaskGroup("myGroupName");
		//��ִ���̼߳�������
		group.addTask(new SimpleTest("myTaskName"));
		//����ִ����ִ��
		new TaskExecutor().submitTaskGroup(group);

	}
}
