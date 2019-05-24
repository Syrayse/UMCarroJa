
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        while(in){
            view.clearScreen();
            view.menuPrincipal();
            view.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch(i) {
                case 0: in = false;
                        break;
                case 1: this.carregaModelBinaryMenu();
                        break;
                case 2: this.guardaModelBinaryMenu();
                        break;
                case 3: this.carregaLogMenu();
                        break;
                case 4: this.loginProprietario();
                        break;
                case 5: this.loginCliente();
                        break;
                case 6: this.registarProprietario();
                        break;
                case 7: this.registarCliente();
                        break;
                case 8: this.showTop10();
                        break;
                default: view.imprimeLinha("Opçao invalida!");
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
        view.imprimeLinha("Indique o nome do ficheiro do qual pretende carregar o modelo:");
        String file = Input.leString();
        
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.model = (UMCarroJaModel)in.readObject();
            in.close();
            fileIn.close();
            view.imprimeLinha("A informaçao foi deserializar com sucesso do ficheiro " + file);
            Input.leString();
        } catch(IOException exc) {
            view.imprimeLinha("Erro inesperado ocorreu!");
            Input.leString();
            return;
        } catch(ClassNotFoundException exc) {
            view.imprimeLinha("Classe nao se encontra definida neste modelo!");
            Input.leString();
            return;
        }
    }
    
    private void guardaModelBinaryMenu() {
        view.imprimeLinha("Indique o nome do ficheiro onde pretende serializar o modelo atual:");
        String file = Input.leString();
        
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.model);
            out.flush();
            out.close();
            view.imprimeLinha("A informaçao foi serializada com sucesso no ficheiro " + file);
            Input.leString();
        } catch(IOException exc) {
            view.imprimeLinha("Um erro ocorreu ao tentar serializar o modelo atual, tente mais tarde!");
            Input.leString();
            return;
        }
    }
    
    private void carregaLogMenu() {
        List<String> linhas;
        view.imprimeLinha("De qual ficheiro CSV pretende carregar a info?:");
        String file = Input.leString();
        
        try {
            linhas = Files.readAllLines(Paths.get(file));
            
            linhas.forEach(s -> this.parseLine(s));            
        } catch (IOException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Ficheiro CSV carregado com sucesso!");
        Input.leString();
        
    }

    private void loginProprietario() {
        int i;
        boolean login = true;
        String username, password;
        view.imprime("Nif:");
        username = Input.leString();
        view.imprime("Password:");
        password = Input.leString();
        
        try {
            this.model.loginProprietario(username, password);
        } catch(PessoaInvalidException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Login efetuado com sucesso");
        Input.leString();
        
        while(login) {
            view.clearScreen();
            view.menuProprietario();
            view.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch(i) {
                case 0: login = false;
                        break;
                case 1: 
                        break;
                case 2: 
                        break;
                case 3:
                        break;
                case 4: this.altPrecoMenu();
                        break;
                case 5:
                        break;
                case 6: this.removVeiculoMenu();
                        break;
                case 7: this.alugueresEntreDatas();
                        break;
                case 8:
                        break;
                case 9: this.alteraPassMenu();
                        break;
                case 10: this.alteraMoradaMenu();
                        break;
                case 11: this.obtemClassificacao(model.indicaClassificacao());
                        break;
                case 12: this.obtemClassVeiculo();
                        break;
                case 13: this.aluguerEntreDatasVec();
                        break;
                case 14: this.verificarVecMenu();
                        break;
                default: view.imprimeLinha("Opçao invalida!");
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
        view.imprime("Nif:");
        username = Input.leString();
        view.imprime("Password:");
        password = Input.leString();
        
        try {
            this.model.loginCliente(username, password);
        } catch(PessoaInvalidException exc) {
            view.imprimeLinha("O username ou password estao errados");
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Login efetuado com sucesso");
        Input.leString();
        
        while(login) {
            view.clearScreen();
            view.menuCliente();
            i = Input.leInt();
            switch(i) {
                case 0: login = false;
                        break;
                case 1: this.showMenuAluguer();
                        break;
                case 2: this.alugueresEntreDatas();
                        break;
                case 3:
                        break;
                case 4: this.alteraPassMenu();
                        break;
                case 5: this.alteraMoradaMenu();
                        break;
                case 6: this.obtemClassificacao(model.indicaClassificacao());
                        break;
                default: view.imprimeLinha("Opçao invalida!");
                         Input.leString();
                         break;
            }
        
        }
                
        this.model.logoff();
    }

    private void registarProprietario() {
        String nome, nif, email, morada, password;
        view.imprimeLinha("Insira os dados com os quais se pretende registar:");
        view.imprime("Nome:");
        nome = Input.leString();
        view.imprime("Nif:");
        nif = Input.leString();
        view.imprime("Email:");
        email = Input.leString();
        view.imprime("Morada:");
        morada = Input.leString();
        view.imprime("Password:");
        password = Input.leString();
        
        try {
            model.registarProprietario(nome, nif, email, morada, password);
        } catch (PessoaInvalidException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Proprietario registado com sucesso");
        Input.leString();
    }
    
    private void registarCliente() {
        double x, y;
        String nome, nif, email, morada, password;
        view.imprimeLinha("Insira os dados com os quais se pretende registar:");
        view.imprime("Nome:");
        nome = Input.leString();
        view.imprime("Nif:");
        nif = Input.leString();
        view.imprime("Email:");
        email = Input.leString();
        view.imprime("Morada:");
        morada = Input.leString();
        view.imprime("Posicao X:");
        x = Input.leDouble();
        view.imprime("Posicao Y:");
        y = Input.leDouble();
        view.imprime("Password:");
        password = Input.leString();
        
        try {
            model.registarCliente(nome, nif, email, morada, x, y, password);
        } catch (PessoaInvalidException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Cliente registado com sucesso");
        Input.leString();
    }
    
    private void altPrecoMenu() {
        String matricula;
        double preco;
    
        view.imprimeLinha("Insira a matricula do veiculo pretendido:");
        matricula = Input.leString();
        view.imprimeLinha("Insira o novo preco");
        preco = Input.leDouble();
        
        try {
            model.alteraPrecoPorKm(matricula, preco);
        } catch(VeiculoInvalidoException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Preco alterado com sucesso");
        Input.leString();
    }
    
    private void removVeiculoMenu() {
        String matricula;
        
        view.imprimeLinha("Insira matricula do veiculo que pretende remover do seu catalogo:");
        matricula = Input.leString();
        
        try {
            model.removeVeiculo(matricula);
        } catch(VeiculoInvalidoException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
            return;
        }
        
        view.imprimeLinha("Veiculo " + matricula + " removido do seu catalogo com sucesso!");
        Input.leString();
    }

    private void obtemClassificacao(double classif) {
        view.imprimeLinha("A sua classificacao atual e: " + classif);
        Input.leString();
    }

    private void obtemClassVeiculo() {
        String matricula;
        
        view.imprimeLinha("Insira a matricula do veiculo:");
        matricula = Input.leString();
        
        try {
            double c = model.indicaClassificacao(matricula);
            view.imprimeLinha("A classificacao do seu veiculo e: " + c);
            Input.leString();
        } catch(VeiculoInvalidoException exc) {
            view.imprimeLinha(exc.getMessage());
            Input.leString();
        }
        
    }
    
    private void showMenuAluguer() {
        int i;
        boolean in = true;
        while(in){
            view.clearScreen();
            view.menuSolAluguer();
            view.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch(i) {
                case 0: in = false;
                        break;
                case 1: // aluguer carro mais proximo
                        break;
                case 2: // aluguer carro mais barato
                        break;
                case 3: // aluguer carro mais barato em raio x
                        break;
                case 4: // aluguer veiculo especifico
                        break;
                case 5: // aluguer com autonomia x
                        break;
                default: view.imprimeLinha("Opçao invalida!");
                        Input.leString();
                        break;
            }
        }
    }
    
    
    private void parseLine(String line) {
        String[] arr = line.split("[:,]+");
        
        try {
            switch(arr[0]) {
            case "NovoProp": if(arr.length == 5) {
                                model.registarProprietario(arr[1],arr[2],arr[3],arr[4],arr[2]);
                            }
                            break;
            case "NovoCliente": if(arr.length == 7) {
                                model.registarCliente(arr[1], arr[2], arr[3], arr[4], Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), arr[2]);
                            }
                            break;
            case "NovoCarro": if(arr.length == 11) {
                                model.addVeiculo(arr[1],arr[2],arr[3],arr[4],Double.parseDouble(arr[5]),Double.parseDouble(arr[6]),
                                        Double.parseDouble(arr[7]),Double.parseDouble(arr[8]),Double.parseDouble(arr[9]),Double.parseDouble(arr[10]));
                            }
                            break;
            case "Aluguer":
                            break;
            case "Classificar": if(arr.length == 3) {
                                model.classifica(arr[1], Double.parseDouble(arr[2]));
                            }
                            break;
            default: break;
        }
        }catch(Exception exc) {
            // Mistakes happen, print error.
            view.imprimeLinha("Em " + arr[0] + ", " + exc.getMessage());
        }
    }
    
    private void showTop10() {
        view.clearScreen();
        new NavControl<Cliente>("Top 10 Clientes", model.getTop10Clientes(), this.imprimeCliente()).showPage();
        Input.leString();
    }
    
    private Consumer<Cliente> imprimeCliente() {
        return c -> {
            HistoricoAluguer h = c.getHistorico();
            view.imprimeLinha(String.format("NIF='%s', NºAlugueres=%5d, NºKms=%6.2f", c.getNif(), h.size(), h.getNumKms()));
        };
    }

    private void alugueresEntreDatas() {
        String inicio, fim, format = "dd/MM/yyyy";
        LocalDate init, end;
        NavControl<Aluguer> inbetween;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        
        view.imprimeLinha("Por favor, insira as datas na seguinte formataçao: " + format);
        view.imprime("Data inicial: ");
        inicio = Input.leString();
        init = LocalDate.parse(inicio, formatter);
        view.imprime("Data inicial: ");
        fim = Input.leString();
        end = LocalDate.parse(fim, formatter);
        
        navControlMenu(new NavControl<>("Alugueres entre " +  inicio + " e " + fim, model.getActAlugueres(init, end),
                    a -> view.imprimeLinha(a.toString())));
    }
    
    private void aluguerEntreDatasVec() {
        String matricula, inicio, fim, format = "dd/MM/yyyy";
        LocalDate init, end;
        NavControl<Aluguer> inbetween;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        List<Aluguer> list;
        
        view.imprimeLinha("Por favor, insira as datas na seguinte formataçao: " + format);
        view.imprime("Data inicial: ");
        inicio = Input.leString();
        init = LocalDate.parse(inicio, formatter);
        view.imprime("Data inicial: ");
        fim = Input.leString();
        end = LocalDate.parse(fim, formatter);
        view.imprime("Matricula: ");
        matricula = Input.leString();
        
        try {
            list = model.getActAlugueres(init, end, matricula);
        } catch(SemPermissaoException exc) {
            view.imprimeLinha(exc.getMessage());
            return;
        }
        
        navControlMenu(new NavControl<>("Alugueres entre " +  inicio + " e " + fim, list,
                    a -> view.imprimeLinha(a.toString())));
    }
    
    private void alteraPassMenu() {
        view.imprimeLinha("Insira a sua nova password:");
        model.alteraPassword(Input.leString());
        view.imprimeLinha("Password alterada com sucesso");
    }
    
    private void alteraMoradaMenu() {
        view.imprimeLinha("Insira a sua nova morada:");
        model.alteraMorada(Input.leString());
        view.imprimeLinha("Morada alterada com sucesso");
    }
    
    private void verificarVecMenu() {
        this.navControlMenu(new NavControl<>("Veiculos Atuais",
                            model.getVeiculos(),
                            v -> view.imprimeLinha(v.toString())));
    }
    private void navControlMenu(NavControl<? extends Object> inbetween) {
        boolean in = true;
        int i;
        
        while(in){
            view.clearScreen();
            inbetween.showPage();
            view.listOptions();
            view.imprime("Insira a opçao que deseja: ");
            i = Input.leInt();
            switch(i) {
                case 0: in = false;
                        break;
                case 1: inbetween.proximaPagina();
                        break;
                case 2: inbetween.retrocePagina();
                        break;
                default: view.imprimeLinha("Opçao invalida!");
                        Input.leString();
                        break;
            }
        }
        
    }
    
}
