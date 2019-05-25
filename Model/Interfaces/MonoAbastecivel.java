package Model.Interfaces;

import Exceptions.DepositoCheioException;

@FunctionalInterface
public interface MonoAbastecivel {
    void abastecer(double quant) throws DepositoCheioException;
}
