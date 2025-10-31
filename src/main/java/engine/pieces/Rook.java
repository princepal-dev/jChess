package engine.pieces;

import com.google.common.collect.ImmutableList;
import engine.Alliance;
import engine.board.Board;
import engine.board.BoardUtils;
import engine.board.Move;
import engine.board.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {
  private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATE = {-8, -1, 1, 8};

  Rook(int piecePosition, Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {
    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATE) {
      int candidateDestinationCoordinate = this.piecePosition;
      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
        if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
            || isLastColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset))
          break;
        candidateDestinationCoordinate += candidateCoordinateOffset;
        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
          if (!candidateDestinationTile.isTileOccupied()) {
            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
          } else {
            final Piece pieceAtDestination = candidateDestinationTile.getPiece();
            final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
            if (this.pieceAlliance != pieceAlliance) {
              legalMoves.add(
                  new Move.AttackMove(
                      board, this, candidateDestinationCoordinate, pieceAtDestination));
            }
            break;
          }
        }
      }
    }

    return ImmutableList.copyOf(legalMoves);
  }

  private static boolean isFirstColumnExclusion(
      final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition]
        && (candidateOffset == -1);
  }

  private static boolean isLastColumnExclusion(
      final int currentPosition, final int candidateOffset) {
    return BoardUtils.EIGHT_COLUMN[currentPosition]
        && (candidateOffset == 1);
  }
}
