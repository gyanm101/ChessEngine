package pieces;

import java.awt.image.BufferedImage;
import Main.Board;

public class Rook extends Piece{
    public Rook(Board board, int col, int row, Boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.size;
        this.yPos = row * board.size;
        
        this.name = "Rook";
        this.sprite = sheet.getSubimage(sheetScale*4, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.size, board.size, BufferedImage.SCALE_SMOOTH);
    }

    public Boolean isValidMovement(int col, int row){
        return this.col == col || this.row == row;
    }

    public Boolean moveCollidesWithPiece(int col, int row){
        
        
        //left
        if(this.col > col){
            for(int c = this.col-1; c > col; c--){
                if(board.getPiece(c, this.row) != null){
                    return true;
                }
            }
        }

         //right
         if(this.col < col){
            for(int c = this.col+1; c < col; c++){
                if(board.getPiece(c, this.row) != null){
                    return true;
                }
            }
        }

        //up
        if(this.row > row){
            for(int r = this.row-1; r > row; r--){
                if(board.getPiece(this.col, r) != null){
                    return true;
                }
            }
        }

         //down
         if(this.row < row){
            for(int r = this.row+1; r < row; r++){
                if(board.getPiece(this.col, r) != null){
                    return true;
                }
            }
        }
        
        return false;
    }
}
