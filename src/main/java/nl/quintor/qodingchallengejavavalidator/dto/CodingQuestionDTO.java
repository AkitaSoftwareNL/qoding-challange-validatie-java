package nl.quintor.qodingchallengejavavalidator.dto;

import java.util.Objects;

public class CodingQuestionDTO {
    private String code;
    private String test;

    public CodingQuestionDTO() {
    }

    public CodingQuestionDTO(String code, String test) {
        this.code = code;
        this.test = test;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodingQuestionDTO that = (CodingQuestionDTO) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, test);
    }

    @Override
    public String toString() {
        return "CodingQuestionDTO{" +
                "code='" + code + '\'' +
                ", test='" + test + '\'' +
                '}';
    }
}
