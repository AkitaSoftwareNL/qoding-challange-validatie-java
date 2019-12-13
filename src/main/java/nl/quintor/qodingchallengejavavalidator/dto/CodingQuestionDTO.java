package nl.quintor.qodingchallengejavavalidator.dto;

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
}
