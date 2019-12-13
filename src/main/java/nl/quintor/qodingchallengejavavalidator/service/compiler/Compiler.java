package nl.quintor.qodingchallengejavavalidator.service.compiler;

public interface Compiler {

    void clear();

    String addClass(String code);

    String addClass(String codeName, String code);

    Class<?> getCompiledClass(String name);

    boolean compile();
}
