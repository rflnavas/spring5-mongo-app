package guru.springframework.utils;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.context.ApplicationContext;

public class ConfigurationUtils {
	
	private ConfigurationUtils() {
	}
	
	public static void showLoadedBeans(ApplicationContext ctx){
		Arrays.asList(ctx.getBeanDefinitionNames()).stream().forEach((b) -> System.out.println(">>> " + b));
	}
	
	public static boolean isBeanLoaded(ApplicationContext ctx, String beanName) {
		Optional<String> optBeanNanme = Arrays.asList(ctx.getBeanDefinitionNames()).stream()
				.filter(s -> s.equalsIgnoreCase(beanName)).findFirst();
		if (optBeanNanme.isPresent()) {
			System.out.println(beanName + " is loaded");
			return true;
		} else {
			System.out.println(beanName + " could not be injected");
			return false;
		}
	}
}
