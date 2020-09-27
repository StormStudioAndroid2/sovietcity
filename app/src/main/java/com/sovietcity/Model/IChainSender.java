package com.sovietcity.Model;

import java.io.Serializable;

//посыльщик доставки
public interface IChainSender extends Serializable {
    void addCell(Cell cell);
    void deleteCell(Cell cell);
}
