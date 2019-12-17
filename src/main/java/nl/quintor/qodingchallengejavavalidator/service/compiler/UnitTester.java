package nl.quintor.qodingchallengejavavalidator.service.compiler;

import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.util.concurrent.Callable;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class UnitTester implements Callable {


    private SummaryGeneratingListener listener = new SummaryGeneratingListener();
    private Class<?> classToTest;

    public UnitTester(Class<?> classToTest) {
        this.classToTest = classToTest;
    }

    @Override
    public TestResultDTO call() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().
                selectors(selectClass(this.classToTest)).build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        return new TestResultDTO(this.listener.getSummary());

    }
}
