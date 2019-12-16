package nl.quintor.qodingchallengejavavalidator.service.compiler;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class UnitTester implements Runnable {

    public SummaryGeneratingListener listener = new SummaryGeneratingListener();
    private Class<?> classToTest;

    public void setClassToTest(Class<?> classToTest) {
        this.classToTest = classToTest;
    }

    @Override
    public void run() {
        this.run(classToTest);
    }

    public void run(Class<?> cl) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().
                selectors(selectClass(cl)).build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
