package com.example.andersoneshop;

/** Shared data: every screen uses this ONE cart, so items are not lost between screens. */
public class ShopData {
    public static Cart cart = new Cart();
    public static Order lastOrder = null;
}