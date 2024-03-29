package main;

import com.eudycontreras.othello.models.GameBoardState;

public class AlphaBetaPruning {
    // The maximum search depth
    private static final int MAX_DEPTH = 6;
    private int NO_NODES = 0;
    private int depthReached = 0;

    public GameBoardState alphaBetaSearch(GameBoardState state, int depth, int alpha, int beta, boolean maximizingPlayer) {
        NO_NODES++;
        if (depth == 0 || state.getChildStates().isEmpty() || state.getChildStates() == null ) {
            if ((MAX_DEPTH - depth) > depthReached) {
                depthReached = (MAX_DEPTH - depth);
            }
            return state;
        }

        if (maximizingPlayer) {
            GameBoardState bestState = null;
            for (GameBoardState child : state.getChildStates()) {
                GameBoardState value = alphaBetaSearch(child, depth - 1, alpha, beta, false);
                if (value != null && value.getBlackCount() > alpha) {
                    alpha = value.getBlackCount();
                    bestState = child;
                }
                if (beta <= alpha) {
                    break; // beta cut-off
                }
            }
            return bestState;
        } else {
            GameBoardState bestState = null;
            for (GameBoardState child : state.getChildStates()) {
                GameBoardState value = alphaBetaSearch(child, depth - 1, alpha, beta, true);
                if (value != null && value.getBlackCount() < beta) {
                    beta = value.getBlackCount();
                    bestState = child;
                }
                if (beta <= alpha) {
                    break; // alpha cut-off
                }
            }
            return bestState;
        }
    }

    public GameBoardState findBestMove(GameBoardState state) {
        GameBoardState gbs = alphaBetaSearch(state, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
        System.out.println("Nodes searched: " + NO_NODES);
        System.out.println("Depth reached: " + depthReached);
        return gbs;

    }
}

