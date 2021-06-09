package ejemplos.oyentes;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class Oyente implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestStart");
		System.out.println(result.getName());
		System.out.println(result.getTestName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getStatus());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestSuccess");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestFailure");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestSkipped");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getSkipCausedBy());
		System.out.println(result.getEndMillis());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestFailedWithTimeout");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("=================================");
		System.out.println("OnStart");
		System.out.println(context.getClass().getName());
		for(ITestNGMethod m: context.getAllTestMethods()) {
			System.out.println(m.getMethodName());
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("=================================");
		System.out.println("OnFinish");
		System.out.println(context.getClass().getName());
		System.out.println(context.getEndDate());
		System.out.println(context.getFailedTests());
	}

}