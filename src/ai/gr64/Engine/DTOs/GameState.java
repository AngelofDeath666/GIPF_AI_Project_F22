package ai.gr64.Engine.DTOs;

import ai.gr64.Data.Interfaces.INode;

public class GameState {
   
   private int whitePiecesLeft, blackPiecesLeft;
   private INode[] graph, outterNodes;

   public GameState(int startingPieces, INode[] graph, int layers) {
      this.whitePiecesLeft = startingPieces;
      this.blackPiecesLeft = startingPieces;
      this.graph = graph;
      
   }

   public void MakeMove(Move move) {
      throw new Error("Not implemented");
   }



}
