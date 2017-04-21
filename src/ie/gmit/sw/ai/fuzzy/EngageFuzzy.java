package ie.gmit.sw.ai.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class EngageFuzzy {
	
//	private double weaponStrength;
//	private double playerHealth;
//	private double spiderStrength;
	
	//double NewPlayerHealth=100;
	private double damage;
	public double fight(double weaponStrength, double playerHealth, double spiderStrength ){  	   
       
    	System.out.println(spiderStrength);
    	// Load from 'FCL' file
        String fileName = "fcl/gameLogic.fcl";
        
        FIS fis = FIS.load(fileName,true);

        FunctionBlock fb = fis.getFunctionBlock("gameLogic");

        // Set inputs
        fis.setVariable("spider",spiderStrength ); //Apply a value to a variable
        fis.setVariable("weapon",weaponStrength ); //Apply a value to a variable
        
        // Evaluate
        fis.evaluate();
        
        damage = fis.getVariable("damage").getValue();
        System.out.println("Damage: " + damage);
        
        playerHealth -=  damage;
        
        System.out.println("new Player Health: " + playerHealth);
		return playerHealth;
       } 
	

//    public double getNewPlayerHealth() {
//		return NewPlayerHealth;
//    }

}
