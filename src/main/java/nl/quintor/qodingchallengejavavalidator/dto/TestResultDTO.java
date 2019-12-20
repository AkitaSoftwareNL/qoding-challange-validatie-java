package nl.quintor.qodingchallengejavavalidator.dto;

import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.Objects;

public class TestResultDTO {
    private final int totalTests;
    private final int totalTestsPassed;
    private final int totalTestsFailed;

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

    public int getTotalTestsPassed() {
        return totalTestsPassed;
    }

    public int getTotalTestsFailed() {
        return totalTestsFailed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResultDTO that = (TestResultDTO) o;
        return totalTests == that.totalTests &&
                totalTestsPassed == that.totalTestsPassed &&
                totalTestsFailed == that.totalTestsFailed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalTests, totalTestsPassed, totalTestsFailed);
    }

    @Override
    public String toString() {
        return "TestResultDTO{" +
                "totalTests=" + totalTests +
                ", totalTestsPassed=" + totalTestsPassed +
                ", totalTestsFailed=" + totalTestsFailed +
                '}';
    }
}
