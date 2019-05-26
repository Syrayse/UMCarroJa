import Model.UMCarroJaModel;
import Model.Veiculos.*;
import Model.Users.*;
import Model.Interfaces.*;
import Model.Alugueres.*;
import Exceptions.*;
import Misc.*;
import Comparators.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.format.*;

public class UMCarroJaController implements Serializable {

    private UMCarroJaView view;
    private UMCarroJaModel model;

    public UMCarroJaController() {
        view = null;
        model = null;
    }

    public void startController() {
        int i;
        boolean in = true;
        while (in) {
            UMCarroJaView.clearScreen();
            UMCarroJaView.menuPrincipal();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch (i) {
                case 0:
                    in = false;
                    break;
                case 1:
                    this.carregaModelBinaryMenu();
                    break;
                case 2:
                    this.guardaModelBinaryMenu();
                    break;
                case 3:
                    this.carregaLogMenu();
                    break;
                case 4:
                    this.loginProprietario();
                    break;
                case 5:
                    this.loginCliente();
                    break;
                case 6:
                    this.registarProprietario();
                    break;
                case 7:
                    this.registarCliente();
                    break;
                case 8:
                    this.showTop10();
                    break;
                default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
            }
        }
    }

    public void set(UMCarroJaView view) {
        this.view = view;
    }

    public void set(UMCarroJaModel model) {
        this.model = model;
    }

    private void carregaModelBinaryMenu() {
        UMCarroJaView.imprimeLinha("Indique o nome do ficheiro do qual pretende carregar o modelo:");
        String file = Input.leString();

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.model = (UMCarroJaModel) in.readObject();
            in.close();
            fileIn.close();
            UMCarroJaView.imprimeLinha("A informaçao foi deserializar com sucesso do ficheiro " + file);
            Input.leString();
        } catch (IOException exc) {
            UMCarroJaView.imprimeLinha("Erro inesperado ocorreu!");
            Input.leString();
        } catch (ClassNotFoundException exc) {
            UMCarroJaView.imprimeLinha("Classe nao se encontra definida neste modelo!");
            Input.leString();
        }
    }

    private void guardaModelBinaryMenu() {
        UMCarroJaView.imprimeLinha("Indique o nome do ficheiro onde pretende serializar o modelo atual:");
        String file = Input.leString();

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.model);
            out.flush();
            out.close();
            UMCarroJaView.imprimeLinha("A informaçao foi serializada com sucesso no ficheiro " + file);
            Input.leString();
        } catch (IOException exc) {
            UMCarroJaView.imprimeLinha("Um erro ocorreu ao tentar serializar o modelo atual, tente mais tarde!");
            Input.leString();
        }
    }

    private void carregaLogMenu() {
        List<String> linhas;
        UMCarroJaView.imprimeLinha("De qual ficheiro CSV pretende carregar a info?:");
        String file = Input.leString();

        try {
            linhas = Files.readAllLines(Paths.get(file));

            linhas.forEach(this::parseLine);
        } catch (IOException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Ficheiro CSV carregado com sucesso!");
        Input.leString();

    }

    private void loginProprietario() {
        int i;
        boolean login = true;
        String username, password;
        UMCarroJaView.imprime("Nif:");
        username = Input.leString();
        UMCarroJaView.imprime("Password:");
        password = Input.leString();

        try {
            this.model.loginProprietario(username, password);
        } catch (PessoaInvalidException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Login efetuado com sucesso");
        Input.leString();

        while (login) {
            UMCarroJaView.clearScreen();
            UMCarroJaView.menuProprietario();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch (i) {
                case 0:
                    login = false;
                    break;
                case 1: 
                    this.pedidosMenu();
                    break;
                case 2:
                    this.dispoMenu();
                    break;
                case 3:
                    this.abastecerCarro();
                    break;
                case 4:
                    this.altPrecoMenu();
                    break;
                case 5:
                    this.addVeiculoMenu();
                    break;
                case 6:
                    this.removVeiculoMenu();
                    break;
                case 7:
                    this.alugueresEntreDatas();
                    break;
                case 8:
                    this.showStats();
                    break;
                case 9:
                    this.alteraPassMenu();
                    break;
                case 10:
                    this.alteraMoradaMenu();
                    break;
                case 11:
                    this.obtemClassificacao(model.indicaClassificacao());
                    break;
                case 12:
                    this.obtemClassVeiculo();
                    break;
                case 13:
                    this.aluguerEntreDatasVec();
                    break;
                case 14:
                    this.verificarVecMenu();
                    break;
                case 15:
                    this.classificarMenu();
                    break;
                default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
            }

        }

        this.model.logoff();
    }

    private void loginCliente() {
        int i;
        boolean login = true;
        String username, password;
        UMCarroJaView.imprime("Nif:");
        username = Input.leString();
        UMCarroJaView.imprime("Password:");
        password = Input.leString();

        try {
            this.model.loginCliente(username, password);
        } catch (PessoaInvalidException exc) {
            UMCarroJaView.imprimeLinha("O username ou password estao errados");
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Login efetuado com sucesso");
        Input.leString();

        while (login) {
            UMCarroJaView.clearScreen();
            UMCarroJaView.menuCliente();
            i = Input.leInt();
            switch (i) {
                case 0:
                    login = false;
                    break;
                case 1:
                    this.showMenuAluguer();
                    break;
                case 2:
                    this.alugueresEntreDatas();
                    break;
                case 3:
                    this.showStats();
                    break;
                case 4:
                    this.alteraPassMenu();
                    break;
                case 5:
                    this.alteraMoradaMenu();
                    break;
                case 6:
                    this.obtemClassificacao(model.indicaClassificacao());
                    break;
                case 7:
                    this.showAdvancedAluguer();
                    break;
                case 8:
                    this.classificarMenu();
                    break;
                default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
            }

        }

        this.model.logoff();
    }

    private void registarProprietario() {
        String nome, nif, email, morada, password;
        UMCarroJaView.imprimeLinha("Insira os dados com os quais se pretende registar:");
        UMCarroJaView.imprime("Nome:");
        nome = Input.leString();
        UMCarroJaView.imprime("Nif:");
        nif = Input.leString();
        UMCarroJaView.imprime("Email:");
        email = Input.leString();
        UMCarroJaView.imprime("Morada:");
        morada = Input.leString();
        UMCarroJaView.imprime("Password:");
        password = Input.leString();

        try {
            model.registarProprietario(nome, nif, email, morada, password);
        } catch (PessoaInvalidException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Proprietario registado com sucesso");
        Input.leString();
    }

    private void registarCliente() {
        double x, y;
        String nome, nif, email, morada, password;
        UMCarroJaView.imprimeLinha("Insira os dados com os quais se pretende registar:");
        UMCarroJaView.imprime("Nome:");
        nome = Input.leString();
        UMCarroJaView.imprime("Nif:");
        nif = Input.leString();
        UMCarroJaView.imprime("Email:");
        email = Input.leString();
        UMCarroJaView.imprime("Morada:");
        morada = Input.leString();
        UMCarroJaView.imprime("Posicao X:");
        x = Input.leDouble();
        UMCarroJaView.imprime("Posicao Y:");
        y = Input.leDouble();
        UMCarroJaView.imprime("Password:");
        password = Input.leString();

        try {
            model.registarCliente(nome, nif, email, morada, x, y, password);
        } catch (PessoaInvalidException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Cliente registado com sucesso");
        Input.leString();
    }

    private void altPrecoMenu() {
        String matricula;
        double preco;

        UMCarroJaView.imprimeLinha("Insira a matricula do veiculo pretendido:");
        matricula = Input.leString();
        UMCarroJaView.imprimeLinha("Insira o novo preco");
        preco = Input.leDouble();

        try {
            model.alteraPrecoPorKm(matricula, preco);
        } catch (VeiculoInvalidoException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Preco alterado com sucesso");
        Input.leString();
    }

    private void removVeiculoMenu() {
        String matricula;

        UMCarroJaView.imprimeLinha("Insira matricula do veiculo que pretende remover do seu catalogo:");
        matricula = Input.leString();

        try {
            model.removeVeiculo(matricula);
        } catch (VeiculoInvalidoException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }

        UMCarroJaView.imprimeLinha("Veiculo " + matricula + " removido do seu catalogo com sucesso!");
        Input.leString();
    }

    private void obtemClassificacao(double classif) {
        UMCarroJaView.imprimeLinha("A sua classificacao atual e: " + classif);
        Input.leString();
    }

    private void obtemClassVeiculo() {
        String matricula;

        UMCarroJaView.imprimeLinha("Insira a matricula do veiculo:");
        matricula = Input.leString();

        try {
            double c = model.indicaClassificacao(matricula);
            UMCarroJaView.imprimeLinha("A classificacao do seu veiculo e: " + c);
            Input.leString();
        } catch (VeiculoInvalidoException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            Input.leString();
        }

    }

    private void showMenuAluguer() {
        double tmp;
        int i;
        boolean in = true;
        while (in) {
            UMCarroJaView.clearScreen();
            UMCarroJaView.menuSolAluguer();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            try {
                switch (i) {
                    case 0:
                        in = false;
                        break;
                    case 1:
                        this.askForCoordPref("MaisPerto");
                        break;
                    case 2: // aluguer carro mais barato
                        this.askForCoordPref("MaisBarato");
                        break;
                    case 3: // aluguer carro mais barato em raio x
                        UMCarroJaView.imprime("Insira a distancia máxima desejada: ");
                        tmp = Input.leDouble();
                        this.askForCoordPref("MaisPertoX", tmp);
                        break;
                    case 4:
                        this.alugarEspecifico();
                        break;
                    case 5: // aluguer com autonomia x
                        UMCarroJaView.imprime("Insira a autonomia minima desejada: ");
                        tmp = Input.leDouble();
                        this.askForCoordPref("AutoX", tmp);
                        break;
                    default:
                        UMCarroJaView.imprimeLinha("Opçao invalida!");
                        Input.leString();
                        break;
                }
                
                if(i>0 && i<=5)
                    Input.leString();
            } catch(Exception exc) {
                UMCarroJaView.imprimeLinha(exc.getMessage());
            }

        }
    }


    private void parseLine(String line) {
        String[] arr = line.split("[:,]+");

        try {
            switch (arr[0]) {
                case "NovoProp":
                    if (arr.length == 5) {
                        model.registarProprietario(arr[1], arr[2], arr[3], arr[4], arr[2]);
                    }
                    break;
                case "NovoCliente":
                    if (arr.length == 7) {
                        model.registarCliente(arr[1], arr[2], arr[3], arr[4], Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), arr[2]);
                    }
                    break;
                case "NovoCarro":
                    if (arr.length == 11) {
                        model.addVeiculo(arr[1], arr[2], arr[3], arr[4], Double.parseDouble(arr[5]), Double.parseDouble(arr[6]),
                                Double.parseDouble(arr[7]), Double.parseDouble(arr[8]), Double.parseDouble(arr[9]), Double.parseDouble(arr[10]));
                    }
                    break;
                case "Aluguer":
                    if (arr.length == 6) {
                        model.addAluguer(arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), arr[4], arr[5]);
                    }
                    break;
                case "Classificar":
                    if (arr.length == 3) {
                        model.classifica(arr[1], Double.parseDouble(arr[2]));
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception exc) {
            // Mistakes happen, print error.
            UMCarroJaView.imprimeLinha("Em " + arr[0] + ", " + exc.getMessage());
        }
    }

    private void showTop10() {
        UMCarroJaView.clearScreen();
        new NavControl<>("Top 10 Clientes", model.getTop10Clientes(), this.imprimeCliente()).showPage();
        Input.leString();
    }

    private Consumer<Cliente> imprimeCliente() {
        return c -> {
            HistoricoAluguer h = c.getHistorico();
            UMCarroJaView.imprimeLinha(String.format("NIF='%s', NºAlugueres=%5d, NºKms=%6.2f", c.getNif(), h.size(), h.getNumKms()));
        };
    }

    private void alugueresEntreDatas() {
        LocalDate init, end;
        NavControl<Aluguer> inbetween;
        List<Aluguer> la;

        UMCarroJaView.imprimeLinha("Por favor, insira as datas na seguinte formataçao: dd/MM/yyyy");

        UMCarroJaView.imprime("Data inicial: ");
        init = Input.leData();

        UMCarroJaView.imprime("Data final: ");
        end = Input.leData();

        la = model.getActAlugueres(init, end);
        
        navControlMenu(new NavControl<>("Alugueres entre " + init.toString() + " e " + end.toString(), la,
                a -> UMCarroJaView.imprimeLinha(a.toString())), la.stream().mapToDouble(Aluguer::getTotalPago).sum());
    }

    private void aluguerEntreDatasVec() {
        String matricula;
        LocalDate init, end;
        NavControl<Aluguer> inbetween;
        List<Aluguer> list;

        UMCarroJaView.imprimeLinha("Por favor, insira as datas na seguinte formataçao: dd/MM/yyyy");

        UMCarroJaView.imprime("Data inicial: ");
        init = Input.leData();

        UMCarroJaView.imprime("Data final: ");
        end = Input.leData();

        UMCarroJaView.imprime("Matricula: ");
        matricula = Input.leString();

        try {
            list = model.getActAlugueres(init, end, matricula);
        } catch (SemPermissaoException exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
            return;
        }

        navControlMenu(new NavControl<Aluguer>("Alugueres entre " + init.toString() + " e " + end.toString(), list,
                a -> UMCarroJaView.imprimeLinha(a.toString())), list.stream().mapToDouble(Aluguer::getTotalPago).sum());
    }

    private void alteraPassMenu() {
        UMCarroJaView.imprimeLinha("Insira a sua nova password:");
        model.alteraPassword(Input.leString());
        UMCarroJaView.imprimeLinha("Password alterada com sucesso");
    }

    private void alteraMoradaMenu() {
        UMCarroJaView.imprimeLinha("Insira a sua nova morada:");
        model.alteraMorada(Input.leString());
        UMCarroJaView.imprimeLinha("Morada alterada com sucesso");
    }

    private void verificarVecMenu() {
        this.navControlMenu(new NavControl<>("Veiculos Atuais",
                model.getVeiculos(),
                v -> UMCarroJaView.imprimeLinha(v.toString())), 0.0);
    }

    private void navControlMenu(NavControl<? extends Object> inbetween, double val) {
        boolean in = true;
        int i;

        while (in) {
            UMCarroJaView.clearScreen();
            inbetween.showPage();
            
            if(val != 0.0) {
                UMCarroJaView.imprimeLinha("Cash flow total entre as duas datas: " + val);
                UMCarroJaView.printFooter();
            }
            
            UMCarroJaView.listOptions();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch (i) {
                case 0:
                    in = false;
                    break;
                case 1:
                    inbetween.proximaPagina();
                    break;
                case 2:
                    inbetween.retrocePagina();
                    break;
                default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
            }
        }

    }

    private void showStats() {
        UMCarroJaView.clearScreen();
        UMCarroJaView.printHeader("Informçao estatistica", UMCarroJaView.BLUE);
        UMCarroJaView.imprime(model.statsAsText());
        UMCarroJaView.printFooter();
        Input.leString();
    }


    private void addVeiculoMenu() {
        String tipo, marca, matricula;
        double vMedia, pPkm, cPkm, auto, x, y;

        UMCarroJaView.clearScreen();
        UMCarroJaView.printHeader("Adicionar novo Veiculo", UMCarroJaView.BLUE);

        try {
            UMCarroJaView.imprime("Tipo: ");
            tipo = Input.leString();

            UMCarroJaView.imprime("Marca: ");
            marca = Input.leString();

            UMCarroJaView.imprime("Matricula: ");
            matricula = Input.leString();

            UMCarroJaView.imprime("Velocidade media: ");
            vMedia = Input.leDouble();

            UMCarroJaView.imprime("Preco por KM: ");
            pPkm = Input.leDouble();

            UMCarroJaView.imprime("Custo por KM: ");
            cPkm = Input.leDouble();

            UMCarroJaView.imprime("Autonomia: ");
            auto = Input.leDouble();

            UMCarroJaView.imprime("Posicao x: ");
            x = Input.leDouble();

            UMCarroJaView.imprime("Posicao y: ");
            y = Input.leDouble();

            model.addVeiculo(tipo, marca, matricula, vMedia, pPkm, cPkm, auto, x, y);

            UMCarroJaView.imprimeLinha("Veiculo com matricula " + matricula + " adicionado com sucesso!");
        } catch (Exception exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
        }


        Input.leString();

    }

    private void dispoMenu() {
        UMCarroJaView.imprimeLinha("Insira a matricula do veiculo que pretende sinalizar");
        String matricula = Input.leString();

        try {
            model.tornaDisponivel(matricula);
            UMCarroJaView.imprimeLinha("Veiculo " + matricula + " sinalizado com sucesso");
        } catch (VeiculoInvalidoException vie) {
            UMCarroJaView.imprimeLinha(vie.getMessage());
        }

        Input.leString();
    }

    private void showAdvancedAluguer() {
        boolean in = true;
        int i;
        double tmp;
        List<Veiculo> veic = model.getAllVeiculos();
        NavControl<Veiculo> nc = new NavControl<>("Solicitar Veiculo - ADVANCED", model.getAllVeiculos(), v -> UMCarroJaView.imprimeLinha(v.toString()));

        while (in) {
            UMCarroJaView.clearScreen();
            nc.showPage();
            UMCarroJaView.menuSolAdvanced();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch (i) {
                case 0:
                    in = false;
                    break;
                case 1:
                    nc.proximaPagina();
                    break;
                case 2:
                    nc.retrocePagina();
                    break;
                case 3:
                    veic.removeIf(PredVeiculos.doTipo("Electrico").negate());
                    nc.changeDict(veic);
                    break;
                case 4:
                    veic.removeIf(PredVeiculos.doTipo("Hibrido").negate());
                    nc.changeDict(veic);
                    break;
                case 5:
                    veic.removeIf(PredVeiculos.doTipo("Gasolina").negate());
                    nc.changeDict(veic);
                    break;
                case 6:
                    veic.removeIf(PredVeiculos.doTipo("Gasoleo").negate());
                    nc.changeDict(veic);
                    break;
                case 7:
                    veic.sort(new CompVeiculoProx(model.getLocalizacao()));
                    nc.changeDict(veic);
                    break;
                case 8:
                    veic.sort(new CompMaisBarato());
                    nc.changeDict(veic);
                    break;
                case 9:
                    veic.sort(new CompAutonomiaDec());
                    nc.changeDict(veic);
                    break;
                case 10:
                    veic.sort(new CompClassDec());
                    nc.changeDict(veic);
                    break;
                case 11:
                    UMCarroJaView.imprime("Insira a autonomia minima: ");
                    tmp = Input.leDouble();
                    veic.removeIf(PredVeiculos.autonomiaDe(tmp).negate());
                    nc.changeDict(veic);
                    break;
                case 12:
                    UMCarroJaView.imprime("Insira a distancia maxima: ");
                    tmp = Input.leDouble();
                    veic.removeIf(PredVeiculos.noRaioDe(model.getLocalizacao(), tmp).negate());
                    nc.changeDict(veic);
                    break;
                case 13:
                    veic = model.getAllVeiculos();
                    nc.changeDict(veic);
                    break;
                case 14:
                    this.alugarEspecifico();
                    Input.leString();
                    break;
                case 15:
                    if(veic.size() > 0)
                        this.askForCoord(veic.remove(0).getMatricula());
                    else
                        UMCarroJaView.imprimeLinha("Nao ha veiculos disponiveis com as definiçoes selecionadas!");
                    Input.leString();
                    break;
                default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
            }
        }
    }

    private void alugarEspecifico() {
        String matricula;
        double x, y;
        UMCarroJaView.imprime("Insira a matricula do veiculo que pretende alugar: ");
        matricula = Input.leString();

        askForCoord(matricula);

        Input.leString();
    }

    private void askForCoord(String matricula) {
        double x, y;
        UMCarroJaView.imprimeLinha("Insira as coodernadas do destino:");
        UMCarroJaView.imprime("x: ");
        x = Input.leDouble();
        UMCarroJaView.imprime("y: ");
        y = Input.leDouble();

        try {
            model.addPedido(matricula, x, y);
            UMCarroJaView.imprimeLinha("Pedido de viagem realizado com sucesso no veiculo " + matricula);
        } catch (Exception exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
        }
    }

    private void askForCoordPref(String preferencia) {
        this.askForCoordPref(preferencia, 0.0);
    }

    private void askForCoordPref(String preferencia, double val) {
        double x, y;
        PedidoAluguer pa;
        UMCarroJaView.imprimeLinha("Insira as coodernadas do destino:");
        UMCarroJaView.imprime("x: ");
        x = Input.leDouble();
        UMCarroJaView.imprime("y: ");
        y = Input.leDouble();

        try {
            pa = model.addPedido(x, y, preferencia, val);
            UMCarroJaView.imprimeLinha("Pedido de viagem realizado com sucesso no veiculo " + pa.getIdVeiculo() + " com custo estimado de: " + pa.getCustoEstimado());
        } catch (Exception exc) {
            UMCarroJaView.imprimeLinha(exc.getMessage());
        }
    }
    
    private void pedidosMenu() {
        int i;
        boolean in = true;
        List<PedidoAluguer> la = model.getPedidos();
        NavControl<PedidoAluguer> nc= new NavControl<>("Pedidos de Aluguer Atuais", la, pa -> view.imprimeLinha(pa.toString()));
        
        while(in) {
            UMCarroJaView.clearScreen();
            nc.showPage();
            UMCarroJaView.menuPedidos();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch(i) {
                case 0:
                    in = false;
                    break;
                case 1:
                    nc.proximaPagina();
                    break;
                case 2:
                    nc.retrocePagina();
                    break;
                case 3:
                    try {
                        nc.changeDict(model.aceitaPedido());
                        view.imprimeLinha("Aluguer realizado com sucesso!");
                    } catch(Exception exc) {
                        view.imprimeLinha(exc.getMessage());
                    }
                    Input.leString();
                    break;
                case 4:
                    try {
                        nc.changeDict(model.recusaPedido());
                        view.imprimeLinha("Recusado com sucesso!");
                    } catch(Exception exc) {
                        view.imprimeLinha(exc.getMessage());
                    }
                    Input.leString();                    
                    break;
                default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
            }
        }
        
    }
    
    private void abastecerCarro() {
        int bi;
        double tmp1, tmp2;
        String matricula, tipo;
        UMCarroJaView.imprime("Insira a matricula do carro que pretende abastecer: ");
        matricula = Input.leString();
        
        try {
            tipo = model.getTipoVeiculo(matricula);
            bi = model.getInterVeiculo(matricula);
        
        switch(bi) {
            case 1:
                view.imprimeLinha("Possui um veiculo do tipo " + tipo + " com um motor simples");
                view.imprime("Com quanto pretende abastecer o seu veiculo: ");
                tmp1 = Input.leDouble();
                model.abastecerMono(matricula, tmp1);
                view.imprimeLinha("Veiculo abastecido com sucesso!");
                Input.leString();
                break;
            case 2:
                view.imprimeLinha("Possui um veiculo do tipo " + tipo + " com uma motor com dois tipos de combustivel");
                view.imprime("Quantos Kwh pretende carregar: ");
                tmp1 = Input.leDouble();
                view.imprime("Quanto combustivel pretende abastecer: ");
                tmp2 = Input.leDouble();
                model.abastecerBi(matricula, tmp1, tmp2);
                view.imprimeLinha("Veiculo abastecido com sucesso!");
                Input.leString();
                break;
            default:
                view.imprimeLinha("O veiculo que pretende abastecer, nao e abastecivel");
                Input.leString();
                break;
                
        }
        } catch(Exception spe) {
            UMCarroJaView.imprimeLinha(spe.getMessage());
            Input.leString();
            return;
        }
        
    }
    
    private void classificarMenu() {
        int i;
        double val;
        boolean ok,in = true;
        ok = false;
        List<Aluguer> recentes = model.getRecentes();
        NavControl<Aluguer> nc = new NavControl<>("Alugueres Recentemente Realizados", recentes, a -> view.imprimeLinha(a.toString()));
    
        while(in) {
            UMCarroJaView.clearScreen();
            nc.showPage();
            UMCarroJaView.menuClassificar();
            UMCarroJaView.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch(i) {
                case 0: 
                    in = false;
                    break;
                case 1:
                    nc.proximaPagina();
                    break;
                case 2:
                    nc.retrocePagina();
                    break;
                case 3:
                    while(!ok) {
                        if(recentes.size() == 0) {
                            UMCarroJaView.imprimeLinha("Nao ha mais alugueres recentes!");
                            Input.leString();
                            break;
                        }
                        try {
                            UMCarroJaView.imprime("Classificacao: ");
                            val = Input.leDouble();
                            recentes = model.classificaRecente(val);
                            nc.changeDict(recentes);
                            UMCarroJaView.imprimeLinha("Classificao registada com sucesso!");
                            ok = true;
                            Input.leString();
                        } catch(ClassificacaoInvalidException exc) {
                            UMCarroJaView.imprimeLinha("Insira uma classificacao pertencente ao intervalo [0.0;100.0]");
                            Input.leString();
                        } catch(EntidadeInexistenteException ex) {
                            UMCarroJaView.imprimeLinha(ex.getMessage());
                            ok = true;
                            Input.leString();
                        }
                    }
                    break;
                 case 4:
                    try {
                        recentes = model.removeRecente();
                        nc.changeDict(recentes);
                        UMCarroJaView.imprimeLinha("Entrada removida com sucesso!");
                    } catch(EntidadeInexistenteException exc) {
                        UMCarroJaView.imprimeLinha(exc.getMessage());
                    }
                    Input.leString();
                    break;
                 default:
                    UMCarroJaView.imprimeLinha("Opçao invalida!");
                    Input.leString();
                    break;
        
            }
        }
    }
}
