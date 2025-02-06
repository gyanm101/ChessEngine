package pieces;

import java.awt.image.BufferedImage;
import Main.Board;

public class Bishop extends Piece {
    public Bishop(Board board, int col, int row, Boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.size;
        this.yPos = row * board.size;
        
        this.name = "Bishop";
        this.sprite = sheet.getSubimage(sheetScale*2, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.size, board.size, BufferedImage.SCALE_SMOOTH);
    }
    
    public Boolean isValidMovement(int col, int row){
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public Boolean moveCollidesWithPiece(int col, int row){
        // up left
        if (this.col > col && this.row > row){
            for (int i = 1; i < Math.abs(this.col - col); i++){
                if (board.getPiece(this.col - i, this.row - i) != null)
                    return true;
            }
        }

        // up right
        if (this.col < col && this.row > row){
            for (int i = 1; i < Math.abs(this.col - col); i++){
                if (board.getPiece( this.col + i, this.row - i) != null)
                    return true;
            }
        }

        // down left
        if (this.col > col && this.row < row){
            for (int i = 1; i < Math.abs(this.col - col); i++){
                if (board.getPiece(this.col - i, this.row + i) != null)
                    return true;
            }
        }

        // down right
        if (this.col < col && this.row < row){
            for (int i = 1; i < Math.abs(this.col - col); i++){
                if (board.getPiece(this.col + i, this.row + i) != null)
                    return true;
            }
        }
         return false;

    }
}
