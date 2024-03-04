package com.khoabeo.quanlyphongkham;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring boot Health Care System REST APIS Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Khoa Beos",
                        email = "anhkhoa180902@gmail.com",
                        url = "https://www.linkedin.com/notifications/?filter=all"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring boot Health Care  System",
                url = "https://github.com/AnhkhoaBeoz/HealthCareSystem"
        )
)
public class QuanlyphongkhamApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuanlyphongkhamApplication.class, args);
    }

}
