package DI_Annotation_04_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration를 적는 순간 평범한 java파일은 DI_Config.xml파일같은 기능을 갖게 된다
// 객체를 생성하고 주입할 수 있게 되는 것
@Configuration
public class ConfigContext {
	
	@Bean
	public MemberDao memberdao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberdao());
	}


	
}
