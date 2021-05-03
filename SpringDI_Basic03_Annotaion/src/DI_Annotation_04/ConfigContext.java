package DI_Annotation_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration를 적는 순간 평범한 java파일은 DI_Config.xml파일같은 기능을 갖게 된다
// 객체를 생성하고 주입할 수 있게 되는 것
@Configuration
public class ConfigContext {
	
	/*
		만약 xml 파일이었다면
		<bean id="" class="DI_Annotation_04.User"> 이런식이었을 것
		그러나 여기서는 함수를 통해 객체를 리턴한다
	*/
	
	@Bean // xml파일 없이 bean객체를 생성하게 해주는 Annotation
	public User user() {
		return new User();
	}
	//위 코드는 <bean id="user" class="DI_Annotation_04.User">
	
	@Bean
	public User2 user2() {
		return new User2();
	}
}
