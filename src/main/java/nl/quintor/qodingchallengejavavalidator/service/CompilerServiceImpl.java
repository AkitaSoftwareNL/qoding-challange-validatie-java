package nl.quintor.qodingchallengejavavalidator.service;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.rest.exceptionhandler.RestResponseEntityExceptionHandler;
import nl.quintor.qodingchallengejavavalidator.service.compiler.Compiler;
import nl.quintor.qodingchallengejavavalidator.service.compiler.RuntimeCompiler;
import nl.quintor.qodingchallengejavavalidator.service.compiler.UnitTester;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import nl.quintor.qodingchallengejavavalidator.service.exception.ExecutionTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class CompilerServiceImpl implements CompilerService {

    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);


    private Compiler compiler = new RuntimeCompiler();

    @Override
    public void setCompiler(Compiler compiler) {
        this.compiler = compiler;
    }

    @Override
    public TestResultDTO runTests(CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException, ExecutionTimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            compiler.addClass(codingQuestionDTO.getCode());
            String testCodename = compiler.addClass(codingQuestionDTO.getTest());
            if (compiler.compile()) {
                Class<?> testCode = compiler.getCompiledClass(testCodename);
                UnitTester tester = new UnitTester(testCode);
                var future = executorService.submit(tester);
                return (TestResultDTO) future.get(codingQuestionDTO.getMaxExecutionTime(), TimeUnit.SECONDS);
            } else {
                throw new Exception();
            }
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw new ExecutionTimeoutException();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CanNotCompileException(e.getMessage());
        } finally {
            compiler.clear();
            executorService.shutdownNow();
        }
    }

    @Override
    public boolean canCompile(CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException {
        try {
            compiler.addClass(codingQuestionDTO.getCode());
            compiler.addClass(codingQuestionDTO.getTest());
            return compiler.compile();
        } catch (Exception e) {
            throw new CanNotCompileException(e.getMessage());
        } finally {
            compiler.clear();
        }
    }

}
