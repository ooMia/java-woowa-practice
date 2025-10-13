package vendingmachine.model.out;

import java.util.EnumMap;

import vendingmachine.model.Coin;

public record Balance(EnumMap<Coin, Integer> map) { }
