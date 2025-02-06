package pieces;

import Main.Board;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;

public class Piece {

    public int col, row;
    public int xPos, yPos;
    public Boolean isWhite;
    public String name;
    public int value;
    public Boolean isFirstMove = true;

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(new FileInputStream("res/pieces.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int sheetScale = sheet.getWidth()/6;

    Image sprite;

    Board board;

    public Piece(Board board){
        this.board = board;
    }

    public Boolean isValidMovement(int col, int row){
        return true;
    }

    public Boolean moveCollidesWithPiece(int col, int row){
        return false;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, xPos, yPos, null);

    }
}
