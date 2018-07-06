package guru.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import guru.springframework.utils.ConfigurationUtils;

@SpringBootApplication
public class Spring5MongoRecipeAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Spring5MongoRecipeAppApplication.class, args);
		//This leads to ClassNotFoundException
		//ConfigurationUtils.showLoadedBeans(ctx);
	}
}
