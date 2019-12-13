package nl.quintor.qodingchallengejavavalidator.dto;

import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class TestResultDTO {
    public int totalTests;
    public int totalTestsPassed;
    public int totalTestsFailed;

    public TestResultDTO() {
        this.totalTests = 0;
        this.totalTestsPassed = 0;
        this.totalTestsFailed = 0;
    }

    public TestResultDTO(int totalTests, int totalTestsPassed, int totalTestsFailed) {
        this.totalTests = totalTests;
        this.totalTestsPassed = totalTestsPassed;
        this.totalTestsFailed = totalTestsFailed;
    }

    public TestResultDTO(TestExecutionSummary summary) {
        this.totalTests = (int) summary.getTestsFoundCount();
        this.totalTestsPassed = (int) summary.getTestsSucceededCount();
        this.totalTestsFailed = (int) summary.getTestsFailedCount();
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getTotalTestsPassed() {
        return totalTestsPassed;
    }

    public void setTotalTestsPassed(int totalTestsPassed) {
        this.totalTestsPassed = totalTestsPassed;
    }

    public int getTotalTestsFailed() {
        return totalTestsFailed;
    }

    public void setTotalTestsFailed(int totalTestsFailed) {
        this.totalTestsFailed = totalTestsFailed;
    }
}
