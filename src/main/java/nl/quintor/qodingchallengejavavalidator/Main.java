package nl.quintor.qodingchallengejavavalidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final String INPUT_CODE_PATH = "src/main/resources/InputCode.java";
    private static final String TESTING_CODE_PATH = "src/main/resources/Test.java";

    public static void main(String[] args) {
        try {
            String inputCodeStr = getFile(INPUT_CODE_PATH);
            String testCodeStr = getFile(TESTING_CODE_PATH);
            codeTester(inputCodeStr, testCodeStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFile(String path) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(path)));
        return data;
    }

    public static boolean codeTester(String baseCodeStr, String testCodeStr) {
        try {
            RuntimeCompiler compiler = new RuntimeCompiler();
            compiler.addClass(getCodeClassName(baseCodeStr), baseCodeStr);
            compiler.addClass(getCodeClassName(testCodeStr), testCodeStr);
            if (compiler.compile()) {
                Class<?> testCode = compiler.getCompiledClass(getCodeClassName(testCodeStr));
                UnitTester tester = new UnitTester();
                tester.run(testCode);

                // Fancy printing
                Console.printMarked(tester.listener.getSummary().getTestsFoundCount() + " Total Tests", Console.BLUE_BACKGROUND, Console.BLACK_BOLD);
                Console.printMarked(tester.listener.getSummary().getTestsStartedCount() + " Started Tests", Console.GREEN_BACKGROUND, Console.GREEN_BOLD);
                Console.printMarked(tester.listener.getSummary().getTestsSkippedCount() + " Skipped Tests", Console.YELLOW_BACKGROUND, Console.YELLOW_BOLD);
                Console.printMarked(tester.listener.getSummary().getTotalFailureCount() + " Failed Tests", Console.RED_BACKGROUND, Console.RED_BOLD);
                Console.printMarked(tester.listener.getSummary().getTestsSucceededCount() + " Succeeded Tests", Console.GREEN_BACKGROUND, Console.GREEN_BOLD);

                return tester.listener.getSummary().getTotalFailureCount() <= 0;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCodeClassName(String code) {
        String codeName = code.split("public class ")[1].split(" ")[0];
        return codeName;
    }
}
