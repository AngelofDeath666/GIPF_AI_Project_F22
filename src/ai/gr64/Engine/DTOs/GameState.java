package ai.gr64.Engine.DTOs;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.INode;

public class GameState {
   
   private int whitePiecesLeft, blackPiecesLeft;
   private INode[] graph, outerNodes;

   public GameState(int startingPieces, INode[] graph, int layers) {
      this.whitePiecesLeft = startingPieces;
      this.blackPiecesLeft = startingPieces;
      this.graph = graph;
      
      outerNodes = new INode[(layers+1)*6];
      outerNodes[0] = graph[0];

      int workingIndex = 0;
      for (int i = 0; i < 6; i++) {
         for (int j = 0; j < layers + 1; j++) {
            workingIndex++;
            if (workingIndex == outerNodes.length) break; 
            outerNodes[workingIndex] = outerNodes[workingIndex-1].neighbour(Direction.fromValue(i+2));
            
         }

      }
      System.out.println("Game state constructed.");
   }

   public void MakeMove(Move move) {
      throw new Error("Not implemented");
   }



}
