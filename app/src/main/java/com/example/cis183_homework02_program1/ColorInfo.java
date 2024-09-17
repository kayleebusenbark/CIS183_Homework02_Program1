package com.example.cis183_homework02_program1;

public class ColorInfo
{
    private int red;
    private int green;
    private int blue;
    private String hexRep;

    public ColorInfo()
    {

    }

    public ColorInfo(int r, int g, int b, String hp)
    {
        red = r;
        green = g;
        blue = b;
        hexRep = hp;
    }

    //=======================================
    //          GETTERS/SETTERS
    //=======================================
    public int getRed()
    {
        return red;
    }

    public void setRed(int red)
    {
        this.red = red;
    }

    public int getGreen()
    {
        return green;
    }

    public void setGreen(int green)
    {
        this.green = green;
    }

    public int getBlue()
    {
        return blue;
    }

    public void setBlue(int blue)
    {
        this.blue = blue;
    }

    public String getHexRep()
    {
        return hexRep;
    }

    public void setHexRep(String hexRep)
    {
        this.hexRep = hexRep;
    }
}
