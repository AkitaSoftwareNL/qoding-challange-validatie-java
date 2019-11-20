package nl.quintor.qodingchallengejavavalidator;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class UnitTester {

    public SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public void run(Class<?> cl) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().
                selectors(selectClass(cl)).build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
