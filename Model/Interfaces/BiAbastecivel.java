package Model.Interfaces;

import Exceptions.DepositoCheioException;

public interface BiAbastecivel
{
    void abastecerGas(double quant) throws DepositoCheioException;
    void abastecerKwh(double qunnt) throws DepositoCheioException;
}