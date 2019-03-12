/*
 * Course: CS2852 - 021
 * Spring 2019
 * Lab 1 - Dot2Dot
 * Name: David Gonzalez-Salzwedel
 * Created: 03/05/2019
 */
package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Picture class is designed to store an ArrayList of Dot objects and provide the functionality
 * to draw each dot and connect them with lines.
 */
public class Picture {

    private boolean drawNumbers = false;
    private boolean drawDots = true;
    private boolean drawLines = true;
    private Label numDotsLabel;

    private double dotSize = 5;

    private List<Dot> dots;

    private Path path;

    private Canvas canvas;

    /**
     * This method sets provides access to the Controller's canvas
     * @param canvas the canvas from the controller class
     */
    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    /**
     * This method reads a file and converts it into an ArrayList of Dot objects and
     * then prints out all of the dots and their respective lines to the canvas.
     * @param path The path to the selected file
     * @throws IOException
     */
    protected void load(Path path) throws IOException{
        this.path = path;
        dots = new ArrayList<>();


        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                Scanner input = new Scanner(line);
                input.useDelimiter("\\s*,\\s*");
                double x = input.nextDouble()*canvas.getWidth();
                double y = input.nextDouble()*canvas.getHeight();
                dots.add(new Dot(x, y));
            }

            refresh();

        }

    }

    /**
     * This method uses the initial canvas from the controller class to call
     * the more specific drawLines() method
     */
    protected void drawLines(){
        drawLines(canvas);
    }

    /**
     * This method is used to draw a line in between each dot in the stored array of dots.
     * @param canvas the FXML canvas on which the lines will be drawn
     */
    protected void drawLines(Canvas canvas){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.beginPath();
        graphicsContext.moveTo(dots.get(dots.size()-1).x, canvas.getHeight()-dots.get(dots.size()-1).y);

        //Drawing using lineTo
        for (int i = 0; i < dots.size(); i++) {
            graphicsContext.lineTo(dots.get(i).x, canvas.getHeight()- dots.get(i).y);
        }

        //Drawing using quadraticCurveTo()
        /*for (int i = 0; i < dots.size()-1; i++) {
            double x1 = dots.get(i).x;
            double y1 = canvas.getHeight()- dots.get(i).y;
            double x2 = dots.get(i+1).x;
            double y2 = canvas.getHeight()- dots.get(i+1).y;
            graphicsContext.quadraticCurveTo(x1, y1, x2, y2);
        }
        graphicsContext.quadraticCurveTo(dots.get(dots.size()-1).x, canvas.getHeight()- dots.get(dots.size()-1).y, dots.get(0).x, canvas.getHeight()- dots.get(0).y);*/

        //Drawing using bezierCurveTo()
        /*for (int i = 0; i < dots.size()-2; i++) {
            double x1 = dots.get(i).x;
            double y1 = canvas.getHeight()- dots.get(i).y;
            double x2 = dots.get(i+1).x;
            double y2 = canvas.getHeight()- dots.get(i+1).y;
            double x3 = dots.get(i+2).x;
            double y3 = canvas.getHeight()- dots.get(i+2).y;
            graphicsContext.bezierCurveTo(x1, y1, x2, y2, x3, y3);
        }
        graphicsContext.bezierCurveTo(dots.get(dots.size()-2).x, canvas.getHeight()- dots.get(dots.size()-2).y, dots.get(dots.size()-1).x, canvas.getHeight()- dots.get(dots.size()-1).y, dots.get(0).x, canvas.getHeight()- dots.get(0).y);
        graphicsContext.bezierCurveTo(dots.get(dots.size()-1).x, canvas.getHeight()- dots.get(dots.size()-1).y, dots.get(0).x, canvas.getHeight()- dots.get(0).y, dots.get(1).x, canvas.getHeight()- dots.get(1).y);*/

        graphicsContext.closePath();
        graphicsContext.stroke();
    }

    /**
     * This method uses the initial canvas from the controller class to call
     * the more specific drawDots() method
     */
    protected void drawDots(){
        drawDots(canvas);
    }

    /**
     * This method is used to draw all of the dots in the stored array of dots.
     * @param canvas the FXML canvas on which the dots will be drawn
     */
    protected void drawDots(Canvas canvas){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        for (int i = 0; i < dots.size(); i++) {
            double x = dots.get(i).x;
            double y = canvas.getHeight()-dots.get(i).y;
            if (drawNumbers){
                graphicsContext.strokeText(Integer.toString(i), x + (dotSize), y + (dotSize/2));
            }
            graphicsContext.fillOval(x-(dotSize/2), y-(dotSize/2), dotSize, dotSize);
        }

    }


    /**
     * This method uses the canvas object to call the more specific
     * clear() method
     */
    protected void clear(){
        clear(canvas);
    }

    /**
     * This method clears the canvas
     * @param canvas The FXML canvas
     */
    protected void clear(Canvas canvas){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * This method sets whether or not to draw the Numbers next to
     * each dot
     * @param drawNumbers
     */
    protected void setNumbers(Boolean drawNumbers){
        this.drawNumbers = drawNumbers;
    }

    /**
     * This method uses the stored draw conditions to call the
     * more specific refresh() method
     */
    protected void refresh(){
        refresh(drawDots, drawLines);
    }

    /**
     * This method coordinated the content on the UI
     * @param drawDots the condition of whether or not to draw the dots
     * @param drawLines the condition of whether or not to draw the lines
     */
    protected void refresh(Boolean drawDots, Boolean drawLines){
        this.drawDots = drawDots;
        this.drawLines = drawLines;
        clear();
        if(drawDots){
            drawDots();
        }

        if(drawLines){
            drawLines();
        }

        numDotsLabel.setText("Number of Dots:  " + dots.size());
    }

    /**
     * This method gives this class access to the Label in the UI
     * @param numDotsLabel
     */
    protected void setNumDotsLabel(Label numDotsLabel){
        this.numDotsLabel = numDotsLabel;
    }

}
