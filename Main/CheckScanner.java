package Main;

import pieces.Piece;

public class CheckScanner {

    Board board;
    public CheckScanner(Board board){
        this.board = board;
    }

    public boolean isKingChecked(Move move){

        Piece king = board.findKing(move.piece.isWhite);
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;

        if(board.selectedPiece != null && board.selectedPiece.name.equals("King")){
             kingCol = move.newCol;
             kingRow = move.newRow;
        }
        
        return  hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 0, 1) || //up
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 1, 0) || //right
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 0, -1) || //down
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, -1, 0) || //left
              hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, -1) || //upleft
              hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, -1) || //upright
              hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, 1) || //downright
              hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, 1) || //downleft
                hitByKnight(move.newCol, move.newRow, king, kingCol, kingRow) ||
                hitByPawn(move.newCol, move.newRow, king, kingCol, kingRow) ||
                hitByKing(king, kingCol, kingRow);
    }

    private Boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal){
        for(int i=1; i<9; i++){
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if(!board.isSameTeam(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private Boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal){
        for(int i=1; i<9; i++){
            if(kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if(!board.isSameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private Boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow){
        return  checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    }

    private Boolean checkKnight(Piece p, Piece king, int col, int row){
        return p != null && !board.isSameTeam(p, king) && p.name.equals("Rook") && !(p.col == col && p.row == row );
    }

    private Boolean hitByKing(Piece king, int kingCol, int kingRow){
        return  checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) || 
                checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) || 
                checkKing(board.getPiece(kingCol, kingRow - 1), king) || 
                checkKing(board.getPiece(kingCol - 1, kingRow), king) || 
                checkKing(board.getPiece(kingCol + 1, kingRow), king) || 
                checkKing(board.getPiece(kingCol - 1, kingRow + 1), king) || 
                checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) || 
                checkKing(board.getPiece(kingCol, kingRow + 1), king);
    }

    private Boolean checkKing(Piece p, Piece k){
        return p != null && !board.isSameTeam(p, k) && p.name.equals("King");
    }

    private Boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow){
        int colorVal = king.isWhite ? -1 : 1;
        return  checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king, col, row) ||
                checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king, col, row);
    }

    private boolean checkPawn(Piece p, Piece k, int col, int row){
        return p != null && !board.isSameTeam(p, k) && p.name.equals("Pawn");
    }

    public boolean isGameOver(Piece king){
        for(Piece piece: board.pieceList){
            if(board.isSameTeam(piece, king)){
                board.selectedPiece = piece == king ? king : null;
                for(int row=0; row < board.rows; row++){
                    for(int col=0; col < board.cols; col++){
                        Move move = new Move(board, piece, col, row);
                        if(board.isValidMove(move)){
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }
}
