package Agenda.CampusCAD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CampusCadApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusCadApplication.class, args);
	}
        
        @Bean
        public WebMvcConfigurer corsConfigurer(){
            return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins("https://agenda-campus-852bb.web.app") // Especifica el dominio
   	            .allowCredentials(true) // Permite credenciales como cookies o tokens
                    .allowedHeaders("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
            }
            };
        }
        
}
