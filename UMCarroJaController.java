import java.io.*;

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
            view.imprimeLinha("Erro de escrita ocorreu!");
            Input.leString();
            return;
        } catch(ClassNotFoundException exc) {
            view.imprimeLinha("Erro inesperado ocorreu!");
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
            out.close();
            fileOut.close();
            view.imprimeLinha("A informaçao foi serializada com sucesso no ficheiro " + file);
            Input.leString();
        } catch(IOException exc) {
            view.imprimeLinha("Um erro ocorreu ao tentar serializar o modelo atual, tente mais tarde!");
            Input.leString();
            return;
        }
    }
    
    private void carregaLogMenu() {
        // fazer isto
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
                case 7:
                        break;
                case 8:
                        break;
                case 9: view.imprimeLinha("Insira a sua nova password:");
                        model.alteraPasswordP(Input.leString());
                        view.imprimeLinha("Password alterada com sucesso");
                        break;
                case 10: view.imprimeLinha("Insira a sua nova morada:");
                         model.alteraMoradaP(Input.leString());
                         view.imprimeLinha("Morada alterada com sucesso");
                        break;
                case 11: this.obtemClassificacao();
                        break;
                case 12: this.obtemClassVeiculo();
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
                case 2:
                        break;
                case 3:
                        break;
                case 4: view.imprimeLinha("Insira a sua nova password:");
                        model.alteraPasswordC(Input.leString());
                        view.imprimeLinha("Password alterada com sucesso");
                        break;
                case 5: view.imprimeLinha("Insira a sua nova morada:");
                        model.alteraMoradaC(Input.leString());
                        view.imprimeLinha("Morada alterada com sucesso");
                        break;
                case 6: this.obtemClassificacao();
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

    private void obtemClassificacao() {
        view.imprimeLinha("A sua classificacao atual e: " + model.indicaClassificacao());
        Input.leString();
    }

    private void obtemClassVeiculo() {
        String matricula;
        
        view.imprimeLinha("Insira a matricula do veiculo:");
        matricula = Input.leString();
        
        try {
            double c = model.indicaClassificacao(matricula);
            view.imprimeLinha("A matricula do seu veiculo e: " + c);
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
}
