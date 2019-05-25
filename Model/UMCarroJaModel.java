package Model;

import Model.Users.*;
import Model.Veiculos.*;
import Model.Alugueres.*;
import Model.Interfaces.*;
import Exceptions.*;
import Comparators.*;
import Misc.*;


import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class UMCarroJaModel implements Serializable {

    private Pessoa login;
    private Map<String, Cliente> clientes;
    private Map<String, Proprietario> proprietarios;
    private Map<String, Veiculo> veiculos;

    public UMCarroJaModel createData() {
        return null;
    }

    public UMCarroJaModel() {
        login = null;
        clientes = new HashMap<>();
        proprietarios = new HashMap<>();
        veiculos = new HashMap<>();
    }

    public Localizacao getLocalizacao() {
        return ((Cliente) login).getLocalizacao();
    }

    public void loginProprietario(String nif, String password) throws PessoaInvalidException {
        if (!this.proprietarios.containsKey(nif))
            throw new PessoaInvalidException("Nao existe nenhum proprietario com o nif " + nif);

        Proprietario prop = this.proprietarios.get(nif);

        if (prop.getPassword().equals(password))
            this.login = prop;
        else
            throw new PessoaInvalidException("A password que inseriu para o proprietario " + nif + " esta errada");
    }

    public void loginCliente(String nif, String password) throws PessoaInvalidException {
        if (!this.clientes.containsKey(nif))
            throw new PessoaInvalidException("Nao existe nenhum cliente com o nif " + nif);

        Cliente cliente = this.clientes.get(nif);

        if (cliente.getPassword().equals(password))
            this.login = cliente;
        else
            throw new PessoaInvalidException("A password que inseriu para o cliente " + nif + " esta errada");
    }

    public void registarProprietario(String nome, String nif, String email, String morada, String password) throws PessoaInvalidException {
        if (this.proprietarios.containsKey(nif))
            throw new PessoaInvalidException("O proprietario com o nif " + nif + " ja se encontra no sistema");
        proprietarios.put(nif, new Proprietario(nif, email, nome, password, morada));
    }

    public void registarCliente(String nome, String nif, String email, String morada, double x, double y, String password) throws PessoaInvalidException {
        if (this.clientes.containsKey(nif))
            throw new PessoaInvalidException("O cliente com o nif " + nif + " ja se encontra no sistema");
        clientes.put(nif, new Cliente(nif, email, nome, password, morada, x, y));
    }

    public void alteraPassword(String novaPass) {
        login.setPassword(novaPass);
    }

    public void alteraMorada(String novaMorada) {
        login.setMorada(novaMorada);
    }

    public void logoff() {
        this.login = null;
    }

    public void alteraPrecoPorKm(String matricula, double preco) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        this.veiculos.get(matricula).setPrecoPorKm(preco);
    }

    public void removeVeiculo(String matricula) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        ((Proprietario) login).removeVeiculo(matricula);
        this.veiculos.remove(matricula);

    }

    public double indicaClassificacao() {
        return login.getClassificacao();
    }

    public double indicaClassificacao(String matricula) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        return veiculos.get(matricula).getClassificacao();
    }

    public void classifica(String id, double classificacao) throws ClassificacaoInvalidException, EntidadeInexistenteException {
        Veiculo v;
        Proprietario p;
        Cliente c;

        if ((v = this.veiculos.get(id)) != null) {
            v.classifica(classificacao);
            return;
        }

        if ((p = this.proprietarios.get(id)) != null) {
            p.classifica(classificacao);
            return;
        }

        if ((c = this.clientes.get(id)) != null) {
            c.classifica(classificacao);
            return;
        }

        throw new EntidadeInexistenteException("Nao foi encontrada nenhuma entidade com o id: " + id);
    }

    public void addVeiculo(String tipo, String marca, String matricula, double vMedia,
                           double precoPorKm, double consumoPorKm, double autonomia, double x, double y)
            throws VeiculoInvalidoException, PessoaInvalidException {
        this.addVeiculo(tipo, marca, matricula, login.getNif(), vMedia, precoPorKm, consumoPorKm, autonomia, x, y);
    }

    public void addVeiculo(String tipo, String marca, String matricula, String nif, double vMedia,
                           double precoPorKm, double consumoPorKm, double autonomia, double x, double y)
            throws VeiculoInvalidoException, PessoaInvalidException {
        if (veiculos.containsKey(matricula))
            throw new VeiculoInvalidoException("Ja existe um veiculo com a matricula " + matricula);

        if (!proprietarios.containsKey(nif))
            throw new PessoaInvalidException("Nao existe proprietario com NIF " + nif);

        Veiculo v;

        if (tipo.equals("Hibrido"))
            v = new CarroHibrido(nif, tipo, marca, matricula, vMedia, precoPorKm, consumoPorKm, autonomia, x, y);
        else
            v = new CarroSimplex(nif, tipo, marca, matricula, vMedia, precoPorKm, consumoPorKm, autonomia, x, y);

        veiculos.put(matricula, v);
        proprietarios.get(nif).addVeiculo(matricula);
    }

    public List<Cliente> getTop10Clientes() {
        return clientes.values()
                .stream()
                .sorted(new CompMelhorPessoa())
                .limit(10)
                .map(Cliente::clone)
                .collect(Collectors.toList());
    }

    public List<Veiculo> getVeiculos() {
        return ((Proprietario) login).getVeiculos()
                .stream()
                .map(s -> veiculos.get(s).clone())
                .collect(Collectors.toList());
    }

    public List<Veiculo> getAllVeiculos() {
        return veiculos.values().stream()
                .map(Veiculo::clone)
                .collect(Collectors.toList());
    }

    public List<Aluguer> getActAlugueres(LocalDate inicio, LocalDate fim) {
        return login.getHistorico().getSubSet(inicio, fim);
    }

    public List<Aluguer> getActAlugueres(LocalDate inicio, LocalDate fim, String matricula) throws SemPermissaoException {
        if (!((Proprietario) login).temVeiculo(matricula))
            throw new SemPermissaoException("Nao possui permissao para aceder ao veiculo com matricula " + matricula);

        return veiculos.get(matricula).getHistorico().getSubSet(inicio, fim);
    }

    public void tornaDisponivel(String matricula) throws VeiculoInvalidoException {
        safeGuardVeiculo(matricula);
        veiculos.get(matricula).disponivel();
    }

    public String statsAsText() {
        HistoricoAluguer h = login.getHistorico();
        StringBuilder sb = new StringBuilder();
        sb.append("Kilometros: ").append(h.getNumKms());
        sb.append("\nCash flow total: ").append(h.getTotalGasto());
        sb.append("\nTempo total: ").append(h.getTotalTempo()).append(" horas");
        if (login instanceof Proprietario) {
            Proprietario p = (Proprietario) login;
            sb.append("\nNumero veiculos: ").append(p.getNumVeiculos());
            sb.append("\nNumero clientes distintos: ").append(h.getNumClientes());
        } else {
            sb.append("\nNumero proprietarios distintos: ").append(h.getNumProprietarios());
        }
        sb.append("\nNumero veiculos distintos alugados: ").append(h.getNumVeiculos());
        sb.append("\n");
        return sb.toString();
    }

    /*
    public void addAluguer(String matricula, double x, double y)
            throws VeiculoInvalidoException, AcidenteOcorreuException, AutonomiaInsuficienteException {
        if (!veiculos.containsKey(matricula))
            throw new VeiculoInvalidoException("Nao existe nenhum veiculo com a matricula " + matricula);

        Veiculo v = veiculos.get(matricula);

        if(!v.estaDisponivel())
            throw new VeiculoInvalidoException("O veiculo " + matricula + " que pretende alugar, nao se encontra disponivel");

        v.viagem(new Localizacao(x, y));
        this.formalizeAluguer(login.getNif(), v, x, y);
        v.indisponivel();
    }
    */

    public void addAluguer(String nifCliente, double x, double y, String tipo, String preferencia)
            throws PessoaInvalidException, VeiculoInvalidoException, PrefInvalidaException {
        Veiculo chosen;

        if (!clientes.containsKey(nifCliente))
            throw new PessoaInvalidException("Nao existe cliente com Nif " + nifCliente);

        chosen = this.selectFromPreference(nifCliente, tipo, preferencia, 0.0);

        this.formalizeAluguer(nifCliente, chosen, x, y);
        chosen.move(x,y);
    }

    public void addPedido(String matricula, double x, double y)
            throws VeiculoInvalidoException, AutonomiaInsuficienteException {
        if (!veiculos.containsKey(matricula))
            throw new VeiculoInvalidoException("Nao existe nenhum veiculo com a matricula " + matricula);

        Localizacao loc = ((Cliente)login).getLocalizacao();
        Veiculo v = veiculos.get(matricula);
        double dist = loc.getDistancia(x, y);
        if(!v.estaDisponivel() || (v instanceof Carro && dist > ((Carro)v).getAutonomia()))
            throw new VeiculoInvalidoException("O veiculo " + matricula + " que pretende alugar, nao se encontra disponivel");

        PedidoAluguer pa = new PedidoAluguer(login.getNif(), matricula, loc, new Localizacao(x, y) ,loc);
        proprietarios.get(v.getNifDono()).addPedido(pa);
    }

    public String addPedido (double x, double y, String preferencia, double val)
            throws VeiculoInvalidoException, PrefInvalidaException, AutonomiaInsuficienteException {
        String matricula = this.selectFromPreference(login.getNif(), "", preferencia, val).getMatricula();
        this.addPedido(matricula, x, y);
        return matricula;
    }

    private void safeGuardVeiculo(String matricula) throws VeiculoInvalidoException {
        if (!((Proprietario) login).temVeiculo(matricula))
            throw new VeiculoInvalidoException("O veiculo com a matricula " + matricula + " ao qual pretende aceder nao lhe esta associado");
    }

    private void formalizeAluguer(String nifCliente, Veiculo chosen, double x, double y) {
        double dist;
        Aluguer one;
        Cliente c = clientes.get(nifCliente);
        dist = chosen.getLocalizacao().getDistancia(new Localizacao(x, y)) / 1000;

        one = new Aluguer(nifCliente, chosen.getNifDono(), chosen.getMatricula(),
                dist, dist * chosen.getPrecoPorKm(), dist / chosen.getVelocidadeAv());

        c.addAluguer(one);
        c.move(x, y);
        chosen.addAluguer(one);
        proprietarios.get(chosen.getNifDono()).addAluguer(one);
    }

    private Veiculo selectFromPreference(String nifCliente, String tipo, String preferencia, double val)
                    throws PrefInvalidaException, VeiculoInvalidoException{
        Comparator<Veiculo> inuse = null;
        Predicate<Veiculo> extraFilter = v -> true;
        Localizacao loc = clientes.get(nifCliente).getLocalizacao();

        switch (preferencia) {
            case "MaisPerto":
                inuse = new CompMaisProximo(loc);
                break;
            case "MaisBarato":
                inuse = new CompMaisBarato();
                break;
            case "MaisPertoX":
                inuse = new CompMaisProximo(loc);
                extraFilter = PredVeiculos.noRaioDe(loc, val);
                break;
            case "AutoX":
                inuse = new CompAutonomiaDec();
                extraFilter = PredVeiculos.autonomiaDe(val);
                break;
            default:
                throw new PrefInvalidaException("Preferencia " + preferencia + " nao e uma preferencia valida");
        }

        Optional<Veiculo> op = veiculos.values()
                .stream()
                .filter(Veiculo::estaDisponivel)
                .filter(extraFilter)
                .filter(PredVeiculos.doTipo(tipo).negate()).min(inuse);

        if (op.isEmpty())
            throw new VeiculoInvalidoException("Nao ha nenhum veiculo que de momento satisfaça o seu pedido");

        return op.get();
    }
}
