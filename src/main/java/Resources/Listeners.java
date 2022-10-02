package main.java.Resources;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends Base implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");

    }
}
