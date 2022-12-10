package main;

import com.eudycontreras.othello.capsules.AgentMove;
import com.eudycontreras.othello.capsules.MoveWrapper;
import com.eudycontreras.othello.capsules.ObjectiveWrapper;
import com.eudycontreras.othello.controllers.Agent;
import com.eudycontreras.othello.controllers.AgentController;
import com.eudycontreras.othello.enumerations.PlayerTurn;
import com.eudycontreras.othello.models.GameBoard;
import com.eudycontreras.othello.models.GameBoardState;
import com.eudycontreras.othello.threading.ThreadManager;
import com.eudycontreras.othello.threading.TimeSpan;
import com.eudycontreras.othello.utilities.GameTreeUtility;

import java.util.*;

/**
 * <H2>Created by</h2> Eudy Contreras
 * <h4> Mozilla Public License 2.0 </h4>
 * Licensed under the Mozilla Public License 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <a href="https://www.mozilla.org/en-US/MPL/2.0/">visit Mozilla Public Lincense Version 2.0</a>
 * <H2>Class description</H2>
 * 
 * @author Eudy Contreras
 */
public class AlphaBetaAgent extends Agent{

	private AlphaBetaAgent() {
		super(PlayerTurn.PLAYER_ONE);
		// TODO Auto-generated constructor stub
	}

	AlphaBetaAgent(PlayerTurn playerTurn) {
		super(playerTurn);
		// TODO Auto-generated constructor stub
	}



	/**
	 * Delete the content of this method and Implement your logic here!
	 */
	@Override
	public AgentMove getMove(GameBoardState gameState) {



		List<ObjectiveWrapper> allValidMoves = AgentController.getAvailableMoves(gameState, playerTurn);
		List<MoveWrapper> moves = new ArrayList<>();
		for (ObjectiveWrapper move : allValidMoves) {

			AgentMove ex = new MoveWrapper(move);
			moves.add(new MoveWrapper(move));

		}

		int depth = 6;

		GameBoardState dt = GameTreeUtility.buildDecisionTree(gameState,depth,2500);
		GameTreeUtility.printBoard(dt.getCells());
		//int outAlfa = alfabetagrej(dt, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
		int outAlfa = abp(dt,depth,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
		MoveWrapper wrap = null;
		for (GameBoardState childState : gameState.getChildStates()) {
			System.out.println(childState.getBlackCount() + " == " + outAlfa);
			if(childState.getBlackCount() == outAlfa) {
				System.out.println("BF moves");
				System.out.println(childState.getLeadingMove());
				wrap = new MoveWrapper(childState.getLeadingMove());
			}
		}
		System.out.println("----------");
		//abp(gameState,depth,Integer.MIN_VALUE,Integer.MIN_VALUE,true);

		AlphaBetaPruning moveFinder = new AlphaBetaPruning();

		GameBoardState bestState = moveFinder.findBestMove(gameState);

		/*
		* IF alpha beta move can be determined, do move.
		* Else get random move from state.
		*/
		if (bestState.getLeadingMove() != null) {
			return new MoveWrapper(bestState.getLeadingMove());
		} else {
			return getExampleMove(gameState);
		}
	}


	private int abp (GameBoardState gbs, int depth, int alpha, int beta, boolean max) {
//		System.out.println("Depth is : " + depth);
		if (depth == 0 || gbs.isTerminal()) {
//			System.out.println("no, it is working, promise");
//			System.out.println("num black :" + gbs.getBlackCount());
			System.out.println("num black : " + gbs.getBlackCount() + ", depth :" + depth );
			return gbs.getBlackCount();
		}
		int value = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		//System.out.println(" pre val: " + value + ", black : " + max);
		for (GameBoardState child : gbs.getChildStates()) {
			value = max ? Math.max(value,abp(child,depth-1,alpha,beta, false)) : Math.min(value,abp(child,depth-1,alpha,beta, true));
			//System.out.println("post val: " + value + ", black : " + max);
			if (max) {
				if (value >= beta) {
					break;
				}
			} else {
				if (value <= alpha) {
					break;
				}
			}
			if (!max) {
				alpha = Math.max(alpha,value);
			} else {
				beta = Math.min(beta,value);
			}
		}

		return value;
	}

	private int alfabetagrej(GameBoardState bst, int depth, int alfa, int beta, boolean max) {
		int value;
		if(depth == 0 || bst.isTerminal()) {
			return bst.getBlackCount();

		}
		if(max){
			value = Integer.MIN_VALUE;
			for (GameBoardState childState : bst.getChildStates()) {
				value = Math.max(value, alfabetagrej(childState, depth-1, alfa, beta, false));
				if(value >= beta) {
					break;
				}
				alfa = Math.max(alfa, value);
			}
			System.out.println("value is : " + value);

			return value;
		} else {
			value = Integer.MAX_VALUE;
			for (GameBoardState childState : bst.getChildStates()) {
				value = Math.min(value, alfabetagrej(childState, depth-1, alfa, beta, true));

				if(value <= alfa) {
					break;
				}
				beta = Math.min(beta, value);
			}
			System.out.println("value is : " + value);

			return value;
		}
	}

	/**
	 * Default template move which serves as an example of how to implement move
	 * making logic. Note that this method does not use Alpha beta pruning and
	 * the use of this method can disqualify you
	 * 
	 * @param gameState
	 * @return
	 */
	private AgentMove getExampleMove(GameBoardState gameState){
		
		int waitTime = UserSettings.MIN_SEARCH_TIME; // 1.5 seconds
		
		ThreadManager.pause(TimeSpan.millis(waitTime)); // Pauses execution for the wait time to cause delay
		
		return AgentController.getExampleMove(gameState, playerTurn); // returns an example AI move Note: this is not AB Pruning
	}

}
