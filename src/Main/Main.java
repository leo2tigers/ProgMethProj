package Main;

public class Main {
	
	public static void main(String[] args) {
		while (menus()) {
			setup();
			while(loop()) {
				//
			}
		}
		exit();
	}
	
	static boolean menus() {
		System.out.println("menu");
		return false;
	}
	
	static void exit() {
		System.out.println("exit");
	}
	
	static void setup() {
		System.out.println("setup");
	}
	
	static boolean loop() {
		return false;
	}

}
