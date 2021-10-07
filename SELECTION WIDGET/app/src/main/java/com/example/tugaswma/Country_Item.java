package com.example.tugaswma;

public class Country_Item {
    private String countryName;
    private int flagImage;
// Public constructor
    public Country_Item(String countryName, int flagImage){
        this.countryName=countryName;
        this.flagImage  =flagImage;

    }
    public String getCountryName(){
        return countryName;
    }
        public int getFlagImage(){
        return flagImage;
        }
}
