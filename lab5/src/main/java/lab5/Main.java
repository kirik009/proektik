package lab5;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {		
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Main.class);
	}
}
