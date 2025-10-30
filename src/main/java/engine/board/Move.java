package engine.board;

import engine.pieces.Piece;

public abstract class Move {
  final Board board;
  final Piece piece;
  final int destination;

  private Move(Board board, Piece movedPiece, int destinationCoordinate) {
    this.board = board;
    this.piece = movedPiece;
    this.destination = destinationCoordinate;
  }

  public static final class MajorMove extends Move {
    public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
      super(board, movedPiece, destinationCoordinate);
    }
  }

  public static final class AttackMove extends Move {
    final Piece attackedPiece;

    public AttackMove(
        final Board board,
        final Piece movedPiece,
        final int destinationCoordinate,
        final Piece attackedPiece) {
      super(board, movedPiece, destinationCoordinate);
      this.attackedPiece = attackedPiece;
    }
  }
}
