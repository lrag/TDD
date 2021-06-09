package ejemplos.precondiciones;

import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class OyenteMetodosPrecondiciones implements IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod invokedMethod, 
			                     ITestResult result) {
        
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method == null) {
            return;
        }
        if (method.isAnnotationPresent(WindowsOnly.class) 
            && !System.getProperty("os.name").contains("Windows")) {
            throw new SkipException("Este test solo debe ejecutarse en Windows");
        }
        if (method.isAnnotationPresent(LinuxOnly.class) 
            && !System.getProperty("os.name").contains("Linux")) {
            throw new SkipException("Este test solo debe ejecutarse en Linux");
        }
        return;
    }

	@Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("DESPUES");
    }
    
}