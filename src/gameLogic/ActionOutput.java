package gameLogic;

public class ActionOutput {
	Skill skill;
	State nextState;
	
	public ActionOutput(Skill skill, State nextState) {
		this.skill = skill;
		this.nextState = nextState;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{ " + this.skill + " , " + this.nextState.name + " }";
	}
}