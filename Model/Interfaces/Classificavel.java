package Model.Interfaces;

import Exceptions.ClassificacaoInvalidException;

@FunctionalInterface
public interface Classificavel
{
    void classifica(double classificacao) throws ClassificacaoInvalidException;
}
