package com.aluracurso.desafio;

import com.aluracurso.desafio.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AnimesearchApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(AnimesearchApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.Menu();
    }
}
