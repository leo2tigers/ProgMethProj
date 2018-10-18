package gameLogic;

import java.util.HashMap;
import java.util.Map;

public class State {
	
	protected String name;
	
	protected Map<InputEnum, ActionOutput> productions;
	
	private final Finite_State_Machine fsc;
	
	State(Finite_State_Machine fsc) {
		this.name = "";
		this.productions = new HashMap<>();
		this.fsc = fsc;
		if (this.fsc.states.size() > 0) {
			this.productions.put(
					InputEnum.TIMEOUT, 
					new ActionOutput(
							null, 
							this.fsc.getStartState()
							)
					);
		}
	}
	
	State(String name, Finite_State_Machine fsc) {
		this.name = name;
		this.productions = new HashMap<InputEnum, ActionOutput>();
		this.fsc = fsc;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProduction(InputEnum inputEnum, ActionOutput actionOutput) {
		this.productions.put(inputEnum, actionOutput);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " : " + this.productions;
	}
}