package gameLogic;

import java.util.*;
import gameComponent.*;

public class Finite_State_Machine {
	
	private Player owner;
	
	private State currentState;
	private State startState;
	
	protected ArrayList<State> states;
	
	public Finite_State_Machine() {
		states = new ArrayList<State>();
	}
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	public void setCurrentState(String stateName) {
		this.currentState = this.getState(stateName);
	}

	public void showCurrentState() {
		System.out.println("CURRENT : " + this.currentState);
	}
	
	public State getCurrentState() {
		return this.currentState;
	}
	
	public State getStartState() {
		return this.startState;
	}

	public void setStartState(State startState) {
		this.startState = startState;
	}
	
	public void setStartState(String stateName) {
		this.startState = this.getState(stateName);
	}

	public ArrayList<State> getStates() {
		return states;
	}
	
	public State getState(String stateName) {
		for (State state : this.states) {
			if (state.name == stateName) {
				return state;
			}
		}
		return null;
	}
	
	public State getState(int stateNumber) {
		return this.states.get(stateNumber);
	}
	
	public void addState() {
		this.states.add(new State(this));
	}

	public void addState(String stateName) {
		this.states.add(new State(stateName, this));
	}
	
	public boolean addProduction(int StateNumber, InputEnum inputEnum, ActionOutput actionOutput) {
		if (this.states.size() > StateNumber) {
			this.states.get(StateNumber).setProduction(inputEnum, actionOutput);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addProduction(String stateName, InputEnum inputEnum, ActionOutput actionOutput) {
		for (State state : this.states) {
			if (state.name == stateName) {
				state.setProduction(inputEnum, actionOutput);
				return true;
			}
		}
		return false;
	}

	public void showAllStates() {
		for (State state : states) {
			System.out.println(state);
		}
	}
	
	public boolean compileInput(InputEnum inputEnum) {
		if (this.currentState.productions.containsKey(inputEnum)) {
			ActionOutput actionOutput = this.currentState.productions.get(inputEnum);
			if(actionOutput.skill != null) {
				this.owner.activateSkill(actionOutput.skill);
			}
			this.currentState = actionOutput.nextState;
			return true;
		}
		else {
			return false;
		}
	}
}