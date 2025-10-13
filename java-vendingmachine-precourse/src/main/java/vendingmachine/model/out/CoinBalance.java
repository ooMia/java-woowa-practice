package vendingmachine.model.out;

import java.util.EnumMap;

import vendingmachine.model.Coin;

public record CoinBalance(EnumMap<Coin, Integer> map) { }
