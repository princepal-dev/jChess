package engine.pieces;

import com.google.common.collect.ImmutableList;
import engine.Alliance;
import engine.board.Board;
import engine.board.BoardUtils;
import engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {
  private static final int[] CANDIDATE_MOVE_COORDINATE = {8};

  Pawn(int piecePosition, Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {
    final List<Move> legalMoves = new ArrayList<>();
    for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
      int candidateDestinationCoordinate =
          this.piecePosition + (currentCandidateOffset * this.pieceAlliance.getDirection());
      if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) continue;
      if (currentCandidateOffset == 8
          && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
        // TODO : more work to do here!!
        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }
}
