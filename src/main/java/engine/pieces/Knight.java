package engine.pieces;

import com.google.common.collect.ImmutableList;
import engine.Alliance;
import engine.board.Board;
import engine.board.Move;
import engine.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
  private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

  Knight(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public List<Move> calculateLegalMoves(Board board) {
    int candidateDestinationCoordinate;
    final List<Move> legalMoves = new ArrayList<>();

    for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
      candidateDestinationCoordinate = this.piecePosition + currentCandidate;

      if (true /* IS VALID TILE COORDINATE*/) {
        final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
        if (!candidateDestinationTile.isTileOccupied()) {
          legalMoves.add(new Move());
        } else {
          final Piece pieceAtDestination = candidateDestinationTile.getPiece();
          final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
          if (this.pieceAlliance != pieceAlliance) {
            legalMoves.add(new Move());
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }
}
