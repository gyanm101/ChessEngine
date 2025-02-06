package pieces;

import java.awt.image.BufferedImage;
import Main.Board;
import Main.Move;

public class King extends Piece{
    public King(Board board, int col, int row, Boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.size;
        this.yPos = row * board.size;
        
        this.name = "King";
        this.sprite = sheet.getSubimage(sheetScale*0, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.size, board.size, BufferedImage.SCALE_SMOOTH);
    }

    public Boolean isValidMovement(int col, int row){
        return Math.abs((col - this.col)*(row-this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row - this.row) == 1 || canCastle(col, row);
    }

    private Boolean canCastle(int col, int row){
        if(this.row == row){
            if(col == 6){
                Piece rook = board.getPiece(7, row);
                if(rook != null && rook.isFirstMove && isFirstMove){
                    return board.getPiece(5, row) == null &&
                            board.getPiece(6, row) == null &&
                            !board.checkScanner.isKingChecked(new Move(board, this, 5, row)); 
                }
            } else if(col == 2){
                Piece rook = board.getPiece(0, row);
                if(rook != null && rook.isFirstMove && isFirstMove){
                    return board.getPiece(3, row) == null &&
                            board.getPiece(2, row) == null &&
                            board.getPiece(1, row) == null &&
                            !board.checkScanner.isKingChecked(new Move(board, this, 3, row)); 
                }
            }

        }
        return false;
    }

    
}
