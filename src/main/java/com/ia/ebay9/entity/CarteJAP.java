package com.ia.ebay9.entity;

public class CarteJAP {
    private CartePokemon cartePokemon;

    public CarteJAP(CartePokemon cartePokemon) {
        this.cartePokemon = cartePokemon;
    }
    public String toString(){
        if(cartePokemon!=null)
            return "("+cartePokemon.getId()+") "+cartePokemon.getCard()+" "+cartePokemon.getJapNum()+" { "+cartePokemon.getJapName()+" } ";
        else
            return "";
    }

    public CartePokemon getCartePokemon() {
        return cartePokemon;
    }

    public void setCartePokemon(CartePokemon cartePokemon) {
        this.cartePokemon = cartePokemon;
    }
}
