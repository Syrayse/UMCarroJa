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
        view.imprime("Username:");
        username = Input.leString();
        view.imprime("Password:");
        password = Input.leString();
        
        try {
            this.model.loginProprietario(username, password);
        } catch(PessoaInvalidException exc) {
            view.imprimeLinha("O username ou password estao errados");
            Input.leString();
            return;
        }
        
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
                case 4:
                        break;
                case 5:
                        break;
                case 6:
                        break;
                case 7:
                        break;
                case 8:
                        break;
                case 9:
                        break;
                case 10:
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
        view.imprime("Username:");
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
        
        while(login) {
            view.clearScreen();
            view.menuCliente();
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
                case 4:
                        break;
                case 5:
                        break;
                default: view.imprimeLinha("Opçao invalida!");
                         Input.leString();
                         break;
            }
        
        }
                
        this.model.logoff();
    }

    private void registarProprietario() {
    
    }
    
    private void registarCliente() {
    
    }
}
