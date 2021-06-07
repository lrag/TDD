package ejemplos.oyentes;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class Oyente implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestStart");
		System.out.println(result.getName());
		System.out.println(result.getTestName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getStatus());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestSuccess");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestFailure");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
	}

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

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("=================================");
		System.out.println("OnTestFailedWithTimeout");
		System.out.println(result.getName());
		System.out.println(result.getStartMillis());
		System.out.println(result.getTestName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
	}

	public void onStart(ITestContext context) {
		System.out.println("=================================");
		System.out.println("OnStart");
		System.out.println(context.getClass().getName());
		for(ITestNGMethod m: context.getAllTestMethods()) {
			System.out.println(m.getMethodName());
		}
	}

	public void onFinish(ITestContext context) {
		System.out.println("=================================");
		System.out.println("OnFinish");
		System.out.println(context.getClass().getName());
		System.out.println(context.getEndDate());
		System.out.println(context.getFailedTests());
	}

}