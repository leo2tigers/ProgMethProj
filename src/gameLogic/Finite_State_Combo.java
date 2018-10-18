package gameLogic;

import java.util.ArrayList;

import gameComponent.Player;

public class Finite_State_Combo {
	private static Player player;
	private static Finite_State_Machine fsc;
	
	public static void setOwner(Player player) {
		Finite_State_Combo.player = player;
	}
	
	public static void generateCombo() {
		fsc = new Finite_State_Machine();
		
		fsc.setOwner(player);
		fsc.addState("Free Stance");
		fsc.setStartState("Free Stance");
		fsc.setCurrentState("Free Stance");
		fsc.addState("Draw Stance");
		fsc.addState("Sword Stance");
		fsc.addState("Before Punch");
		fsc.addState("After Punch");
		fsc.addState("Before Loop");
		fsc.addState("Distanced");
		fsc.addState("GUAGE 1");
		fsc.addProduction(
				"Free Stance",
				InputEnum.Y, 
				new ActionOutput(
						PlayerSkill.UNSHEALTH_SLASH.skill(player.orientation), 
						fsc.getState("Draw Stance")
						)
				);
		fsc.addProduction(
				"Draw Stance",
				InputEnum.Y, 
				new ActionOutput(
						PlayerSkill.OVERHEAD_SLASH.skill(player.orientation), 
						fsc.getState("Before Punch")
						)
				);
		fsc.addProduction(
				"Draw Stance",
				InputEnum.X, 
				new ActionOutput(
						PlayerSkill.PUNCH.skill(player.orientation), 
						fsc.getState("After Punch")
						)
				);
		fsc.addProduction(
				"Sword Stance",
				InputEnum.Y, 
				new ActionOutput(
						PlayerSkill.OVERHEAD_SLASH.skill(player.orientation), 
						fsc.getState("Draw Stance")
						)
				);
		fsc.addProduction(
				"Sword Stance",
				InputEnum.X, 
				new ActionOutput(
						PlayerSkill.PUNCH.skill(player.orientation), 
						fsc.getState("After Punch")
						)
				);
		fsc.addProduction(
				"Before Punch",
				InputEnum.Y, 
				new ActionOutput(
						PlayerSkill.PUNCH.skill(player.orientation), 
						fsc.getState("After Punch")
						)
				);
		fsc.addProduction(
				"After Punch",
				InputEnum.Y, 
				new ActionOutput(
						PlayerSkill.UPWARD_SLASH.skill(player.orientation), 
						fsc.getState("Before Loop")
						)
				);
		fsc.addProduction(
				"Before Loop",
				InputEnum.Y, 
				new ActionOutput(
						PlayerSkill.OVERHEAD_SLASH.skill(player.orientation), 
						fsc.getState("Sword Stance")
						)
				);
		fsc.addProduction(
				"Before Loop",
				InputEnum.X, 
				new ActionOutput(
						PlayerSkill.PUNCH.skill(player.orientation), 
						fsc.getState("After Punch")
						)
				);
		
		for (State state : fsc.getStates()) {
			if (state.getName() != "Free Stance" && state.getName() != "Sword Stance") {
				fsc.addProduction(
						state.getName(), 
						InputEnum.TIMEOUT, 
						new ActionOutput(
								null,
								fsc.getState("Sword Stance")
								)
						);
			}
			
			if (state.getName() != "Distanced" && state.getName() != "GUAGE 1") {
				fsc.addProduction(
						state.getName(), 
						InputEnum.XY, 
						new ActionOutput(
								PlayerSkill.BACKWARD_SWEEP.skill(player.orientation),
								fsc.getState("Distanced")
								)
						);
				fsc.addProduction(
						state.getName(), 
						InputEnum.R2, 
						new ActionOutput(
								PlayerSkill.FIRST_GUAGE_SLASH.skill(player.orientation),
								fsc.getState("GUAGE 1")
								)
						);
			}
		}
		
		System.out.println("generate Finite State Combo");
	}
	
	public static void inputButton(InputEnum inputEnum) {
		fsc.compileInput(inputEnum);
	}
	
	public static void main(String[] args) {
		Player player = new Player();
		Finite_State_Combo.setOwner(player);
		Finite_State_Combo.generateCombo();
		
		ArrayList<InputEnum> inputs = new ArrayList<InputEnum>();
		inputs.add(InputEnum.Y);
		inputs.add(InputEnum.Y);
		inputs.add(InputEnum.Y);
		inputs.add(InputEnum.Y);
		inputs.add(InputEnum.Y);
		inputs.add(InputEnum.Y);
		inputs.add(InputEnum.Y);
		
		for(InputEnum inputEnum : inputs) {
			Finite_State_Combo.inputButton(inputEnum);
		}
	}
}
