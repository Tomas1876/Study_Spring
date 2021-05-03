package DI_Annotation_04_2;

public class MemberRegisterService {
	
	private MemberDao memberdao;
	
	public MemberRegisterService(MemberDao memberdao){
		this.memberdao = memberdao;
	}
	
	public void getEethod() {
		System.out.println("MemberRegisterService의 함수");
		System.out.println("주입된 memberdao의 주소값 : " + memberdao);
	}
	
}
