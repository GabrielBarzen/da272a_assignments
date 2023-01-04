package pacman.entries.pacman;

import dataRecording.DataTuple;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.util.ArrayList;
import java.util.List;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE>
{
	private MOVE myMove=MOVE.NEUTRAL;
	
	public MOVE getMove(Game game, long timeDue) 
	{

		//Place your game logic here to play the game as Ms Pac-Man

		return myMove;
	}

	//public Tree generateTree(List<DataTuple> D, List<>)
}

class Tree {

	Node root;
	class Node {
		ArrayList<Node> children = new ArrayList<>();

		public ArrayList<Node> getChildren() {
			return children;
		}
	}

	public Node getRoot() {
		return root;
	}
}

/*
	ID3 (Examples, Target_Attribute, Attributes)
		Create a root node for the tree
		If all examples are positive, Return the single-node tree Root, with label = +.
		If all examples are negative, Return the single-node tree Root, with label = -.
		If number of predicting attributes is empty, then Return the single node tree Root,
		with label = most common value of the target attribute in the examples.
		Otherwise Begin
			A ← The Attribute that best classifies examples.
			Decision Tree attribute for Root = A.
			For each possible value, vi, of A,
				Add a new tree branch below Root, corresponding to the test A = vi.
				Let Examples(vi) be the subset of examples that have the value vi for A
				If Examples(vi) is empty
					Then below this new branch add a leaf node with label = most common target value in the examples
				Else below this new branch add the subtree ID3 (Examples(vi), Target_Attribute, Attributes – {A})
		End
		Return Root
 */