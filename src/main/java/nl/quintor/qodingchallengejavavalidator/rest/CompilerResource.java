package nl.quintor.qodingchallengejavavalidator.rest;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.service.CompilerService;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompilerResource {

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
    public ResponseEntity getTestResult(@RequestBody CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException {
        return ResponseEntity.ok().body(compilerService.runTests(codingQuestionDTO));
    }

    @ResponseBody
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/validator/java/can-compile",
            method = RequestMethod.POST
    )
    public ResponseEntity canCompileCode(@RequestBody CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException {
        return ResponseEntity.ok().body(compilerService.canCompile(codingQuestionDTO));
    }


}
