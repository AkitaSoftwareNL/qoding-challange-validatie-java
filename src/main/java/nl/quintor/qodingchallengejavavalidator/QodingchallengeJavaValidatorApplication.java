package nl.quintor.qodingchallengejavavalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class QodingchallengeJavaValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(QodingchallengeJavaValidatorApplication.class, args);
    }

}
