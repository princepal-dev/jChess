package engine.pieces;

import engine.Alliance;
import engine.board.Board;
import engine.board.Move;
import java.util.Collection;

public abstract class Piece {
  protected final int piecePosition;
  protected final boolean isFirstMove;
  protected final Alliance pieceAlliance;

  Piece(final int piecePosition, final Alliance pieceAlliance) {
    this.piecePosition = piecePosition;
    this.pieceAlliance = pieceAlliance;
    // todo :: more work todo here!
    this.isFirstMove = false;
  }

  public Alliance getPieceAlliance() {
    return pieceAlliance;
  }

  public boolean isFirstMove() {
    return isFirstMove;
  }

  public abstract Collection<Move> calculateLegalMoves(final Board board);
}
