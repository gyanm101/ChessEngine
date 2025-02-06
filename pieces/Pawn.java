package pieces;

import java.awt.image.BufferedImage;
import Main.Board;

public class Pawn extends Piece{
    public Pawn(Board board, int col, int row, Boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.size;
        this.yPos = row * board.size;
        
        this.name = "Pawn";
        this.sprite = sheet.getSubimage(sheetScale*5, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.size, board.size, BufferedImage.SCALE_SMOOTH);
    }

    public Boolean isValidMovement(int col, int row){
        
        int colorIndex = isWhite ? 1 : -1;

        //push pawn 1
        if(this.col == col && this.row == row + colorIndex && board.getPiece(col, row) == null){
            return true;
        }

        //push pawn 2
        if(isFirstMove && this.col == col && this.row == row + colorIndex*2 && board.getPiece(col, row) == null && board.getPiece(col, row + colorIndex) == null){
            return true;
        }
        
        //capture left
        if(col == this.col-1 && row == this.row - colorIndex && board.getPiece(col, row) != null){
            return true;
        }

        //capture right
        if(col == this.col+1 && row == this.row - colorIndex && board.getPiece(col, row) != null){
            return true;
        }

        //enPassant left
        if(board.getTileNum(col, row) == board.enPassantTile && col == this.col - 1 && row == this.row - colorIndex && board.getPiece(col, row+colorIndex) != null){
            return true;
        }

        //enPassant right
        if(board.getTileNum(col, row) == board.enPassantTile && col == this.col + 1 && row == this.row - colorIndex && board.getPiece(col, row+colorIndex) != null){
            return true;
        }

        return false;
    }
}
