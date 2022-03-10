package ai.gr64.Engine.DTOs;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Engine.DTOs.GameGraph.OuterNode;

// The GameState, containing information about how the game is looking at this point in time
// Has information about, the board, how many pieces each player has left, etc.
public class GameState {
   private int whitePiecesLeft, blackPiecesLeft;
   private INode[] graph, outerNodes;

   public GameState(int startingPieces, INode[] graph, int layers) {
      this.whitePiecesLeft = startingPieces;
      this.blackPiecesLeft = startingPieces;
      this.graph = graph;

      // An array containing all the outer-nodes on the board, sorted starting with
      // the top-left, going clockwise.
      outerNodes = new INode[(layers + 1) * 6];
      outerNodes[0] = graph[0];

      // A loop which finds all the outer nodes and adds them to the outerNodes array
      // in the correct order.
      int workingIndex = 0;
      for (int i = 0; i < 6; i++) {
         for (int j = 0; j < layers + 1; j++) {
            workingIndex++;
            if (workingIndex == outerNodes.length)
               break;
            INode nextNode = outerNodes[workingIndex - 1];
            nextNode = nextNode.neighbor(Direction.fromValue(i + 3));
            nextNode = nextNode.neighbor(Direction.fromValue(i + 1));
            outerNodes[workingIndex] = nextNode;
         }
      }
   }

   // To be implemented, the method called when making a move on the board,
   public void MakeMove(Move move) {

       ((OuterNode)outerNodes[move.getPlacementNode()]).placePiece(move.getPiece(), move.getDirection());;
    }

}
