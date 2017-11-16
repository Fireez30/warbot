package MonEquipe2;

import java.util.ArrayList;

import edu.warbot.agents.MovableWarAgent;
import edu.warbot.agents.WarResource;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.agents.enums.WarAgentType;

public abstract class WarExplorerBrainController extends WarExplorerBrain {

	public WarExplorerBrainController() {
		super();

	}

	private WarMessage getMessageFromBase() {
		for (WarMessage m : getMessages()) {
			if(m.getSenderType().equals(WarAgentType.WarBase))
				return m;
		}
		return null;
	}

	@Override
	public String action() {


		for (WarMessage m : getMessages()) {
			if (m.getMessage() == "I'm under attack") this.setHeading(m.getAngle());
			if (!isBagFull() && m.getMessage()== "De la nourriture !") this.setHeading(m.getAngle());
		}

		for (WarAgentPercept wp : getPercepts()) {
			if (wp.getType().equals(WarAgentType.WarBase) && isEnemy(wp)) {
				broadcastMessageToAgentType(WarAgentType.WarLight,"base ennemie","");
			}
			
			/*if (!isBagEmpty() && wp.getType().equals(WarAgentType.WarBase) && wp.getDistance() < WarResource.MAX_DISTANCE_TAKE) {
				setDebugString("Donne");
				setIdNextAgentToGive(wp.getID());
				return give();
			}

			else if (!isBagFull() && wp.getType().equals(WarAgentType.WarFood)) {
				setDebugString("Vivre sa vie");
				broadcastMessageToAgentType(WarAgentType.WarExplorer,"De la nourriture !","");

				if (!isBagFull() && wp.getDistance() < WarResource.MAX_DISTANCE_TAKE){
					setDebugString("recupere nourriture");
					return take();
				}

				else {
					setDebugString("va vers nourriture");
					setHeading(wp.getAngle());
					return(MovableWarAgent.ACTION_MOVE);
				}
			}

			else if (isBagFull()){
				setDebugString("Sac plein");				
				broadcastMessageToAgentType(WarAgentType.WarBase, "Where is the base", (String[]) null);
				ArrayList<WarAgentPercept> basePercepts =
						(ArrayList<WarAgentPercept>) getPerceptsAlliesByType(WarAgentType.WarBase);

				if(basePercepts == null | basePercepts.size() == 0){

					WarMessage m = getMessageFromBase();
					//Si j'ai un message de la base je vais vers elle
					if(m != null){
						if (m.getDistance() < WarResource.MAX_DISTANCE_TAKE) {
							return give();
						}
						setHeading(m.getAngle());						
					}

					return(MovableWarAgent.ACTION_MOVE);
				}


			}*/

			
		}



		if (isBlocked())
			setRandomHeading();


		return WarExplorer.ACTION_MOVE;

	}
}
