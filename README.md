# UMCarroJa

O projecto encontra-se distribuido em packages, que correspondem a uma estruturação do ficheiro de código. Como acontece num livro, quando se pretende organizar este efetua-se um indice, que reparte o livro em vários capítulos que individualmente possuem a sua própria estrutura, mas que no total de todos os capítulos formam um livro, constituido de micro-grupos que passo a passo fornecem uma estrutura mais restrita ao nossos livro.

Há 6 packages atualmente:

* Aluguer: Todos os ficheiros de códigos relacionados com a gestão de alugueres, pedidos de alugueres e históricos de aluguer.
* PublicInfo: Toda a informação que é constante, imutável e descritiva de cada entidade do nosso projeto (ex.: marca do carro, nome da pessoa).
* Users: Contem todos os utilizadores, proprietarios, cliente. Se no futuro se pretende adicionar um novo tipo de usuários coloca-se aqui.
* Veiculos: Contem todo o tipo de veiculos, carros, carroseletricos, carroshibridos e no futuro trotinetes, bicicletas, etc...
* lib: Contem código que é estritamente necessário para o projeto, como a Localização.
* Interfaces: Contem ficheiro com todos os adjetivos que se pode dar a uma classe, por exemplo um Carro é Classificavel, tal como um proprietário, mas um cliente não é. Um Carro é Abastecivel, mas um cliente e proprietário não.

Merdas que falta definir:

* Todo os Users
* Todo os Veiculos

Merdas para procurar para perceber a estrutura do projeto:

* Classes abstratas / não instânciaveis.
* Interfaces / "adjetivos".
