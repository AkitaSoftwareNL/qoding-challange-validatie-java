package nl.quintor.qodingchallengejavavalidator.dto;

import java.util.Objects;

public class CodingQuestionDTO {
    private final long maxExecutionTime;
    private final String code;
    private final String test;

    public CodingQuestionDTO() {
        this.maxExecutionTime = 0;
        this.code = "";
        this.test = "";
    }

    public CodingQuestionDTO(long maxExecutionTime, String code, String test) {
        this.maxExecutionTime = maxExecutionTime;
        this.code = code;
        this.test = test;
    }

    public String getCode() {
        return code;
    }


    public long getMaxExecutionTime() {
        return Math.abs(maxExecutionTime);
    }

    public String getTest() {
        return test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodingQuestionDTO that = (CodingQuestionDTO) o;
        return maxExecutionTime == that.maxExecutionTime &&
                Objects.equals(code, that.code) &&
                Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxExecutionTime, code, test);
    }

    @Override
    public String toString() {
        return "CodingQuestionDTO{" +
                "maxExecutionTime=" + maxExecutionTime +
                ", code='" + code + '\'' +
                ", test='" + test + '\'' +
                '}';
    }
}
