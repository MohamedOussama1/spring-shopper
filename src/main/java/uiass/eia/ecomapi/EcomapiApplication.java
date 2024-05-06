package uiass.eia.ecomapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uiass.eia.ecomapi.service.IServiceMetier;

@SpringBootApplication
public class EcomapiApplication implements CommandLineRunner {
    @Autowired
    IServiceMetier serviceMetier;

    public static void main(String[] args) {
        System.out.println("HEllo Jenkins!");
        SpringApplication.run(EcomapiApplication.class, args);

    }

    @Override
    public void run(String... args){
    }
}
