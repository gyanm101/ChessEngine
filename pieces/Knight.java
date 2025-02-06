package pieces;

import java.awt.image.BufferedImage;

import Main.Board;

public class Knight extends Piece {
    public Knight(Board board, int col, int row, Boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.size;
        this.yPos = row * board.size;
        
        this.name = "Knight";
        this.sprite = sheet.getSubimage(sheetScale*3, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.size, board.size, BufferedImage.SCALE_SMOOTH);
    }

    public Boolean isValidMovement(int col, int row){
        return Math.abs(col - this.col) * Math.abs(row - this.row) == 2;
    }
    
}
