/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;

/**
 * Displays a custom text and a custom color in a JList or JComboBox when combined
 * with ResultCellRenderer.
 * @author G11
 */
public class ColoredCellDisplay
{
    private String displayText;
    
    private Color displayBackColor;
    
    public ColoredCellDisplay()
    {
        
    }

    /**
     * Returns the text that is being displayed.
     * @return (String) The text being displayed.
     */
    public String getDisplayText()
    {
        return displayText;
    }

    /**
     * Sets the text to be displayed.
     * @param displayText (String) The text to be displayed.
     */
    public void setDisplayText(String displayText)
    {
        this.displayText = displayText;
    }

    /**
     * Returns the background color of this display.
     * @return (Color) The backgrounnd color.
     */
    public Color getDisplayBackColor()
    {
        return displayBackColor;
    }

    /**
     * Sets a new color for the background.
     * @param displayBackColor (Color) The new color.
     */
    public void setDisplayBackColor(Color displayBackColor)
    {
        this.displayBackColor = displayBackColor;
    }
    
    /**
     * Blends two colors into one.
     * @param c0 (Color) The first color to blend.
     * @param c1 (Color) The second color to blend.
     * @return (Color) The resulting blend color.
     */
    public static Color blend(Color c0, Color c1) {
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
  }
    
    @Override
    public String toString()
    {
        return displayText;
    }
}
