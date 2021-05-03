package DI_07_Spring;

import java.util.List;

public class ProtocolHandler {
	
	private List<MyFilter> filters;
	
	//filters의 getter, setter
	public List<MyFilter> getFilters(){		
		return this.filters;
		
	}
	
	public void setFilters(List<MyFilter> filters){
		
		this.filters = filters;
		
	}
	
	//검증함수
	public int filter_length() {
		
		return this.filters.size();
	}
	
}
