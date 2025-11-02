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

  Pawn(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {
    final List<Move> legalMoves = new ArrayList<>();
    for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
      final int candidateDestinationCoordinate =
          this.piecePosition + (currentCandidateOffset * this.pieceAlliance.getDirection());
      if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) continue;
      if (currentCandidateOffset == 8
          && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
        // TODO : More work to do with promotions!
        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
      } else if (currentCandidateOffset == 16
              && this.isFirstMove()
              && (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack())
          || (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
        final int behindCandidateDestinationCoordinate =
            this.piecePosition + (this.pieceAlliance.getDirection() + 8);
        if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()
            && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
          legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
        }
      } else if(currentCandidateOffset == 7) {

      } else if(currentCandidateOffset == 9){

      }
    }
    return ImmutableList.copyOf(legalMoves);
  }
}
