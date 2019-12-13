package nl.quintor.qodingchallengejavavalidator.service;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.service.compiler.Compiler;
import nl.quintor.qodingchallengejavavalidator.service.compiler.UnitTester;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompilerServiceImpl implements CompilerService {

    @Autowired
    private Compiler compiler;

    @Override
    public TestResultDTO runTests(CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException {
        try {
            compiler.addClass(codingQuestionDTO.getCode());
            String testCodename = compiler.addClass(codingQuestionDTO.getTest());
            if (compiler.compile()) {
                Class<?> testCode = compiler.getCompiledClass(testCodename);
                UnitTester tester = new UnitTester();
                tester.run(testCode);
                return new TestResultDTO(tester.listener.getSummary());
            } else {
                throw new CanNotCompileException();
            }
        } catch (Exception e) {
            throw new CanNotCompileException(e);
        }
    }

    @Override
    public boolean canCompile(CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException {
        try {
            compiler.addClass(codingQuestionDTO.getCode());
            compiler.addClass(codingQuestionDTO.getTest());
            return compiler.compile();
        } catch (Exception e) {
            throw new CanNotCompileException(e);
        }
    }

    @Override
    public void setCompiler(Compiler compiler) {
        this.compiler = compiler;
    }
}
