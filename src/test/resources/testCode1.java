import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

public class TestCode {

    private Code sut;

    @BeforeEach
    void setUp() {
        sut = new Code();
    }

    @Test
    void Test1() {
        Assertions.assertTrue(sut.equals("1", "1"));
    }

    @Test
    void Test2() {
        Assertions.assertTrue(sut.equals("2", "2"));
    }
}
