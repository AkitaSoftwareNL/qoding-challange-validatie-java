package nl.quintor.qodingchallengejavavalidator.rest;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.rest.exceptionhandler.RestResponseEntityExceptionHandler;
import nl.quintor.qodingchallengejavavalidator.service.CompilerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompilerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    private CompilerService compilerService;

    @Autowired
    public void setCompilerService(CompilerService compilerService) {
        this.compilerService = compilerService;
    }

    @ResponseBody
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/validator/java/test",
            method = RequestMethod.POST
    )
    public ResponseEntity<TestResultDTO> getTestResult(@RequestBody CodingQuestionDTO codingQuestionDTO) {
        LOGGER.info(codingQuestionDTO.toString());
        return ResponseEntity.ok().body(compilerService.runTests(codingQuestionDTO));
    }

    @ResponseBody
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/validator/java/compile",
            method = RequestMethod.POST
    )
    public ResponseEntity canCompileCode(@RequestBody CodingQuestionDTO codingQuestionDTO) {
        LOGGER.debug(codingQuestionDTO.toString());
        return ResponseEntity.ok().body(compilerService.canCompile(codingQuestionDTO));
    }


}
