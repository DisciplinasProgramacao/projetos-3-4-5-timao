# Projeto 5 - Gestão de Frota (final)

## Nota: 10,7 + apresentação

**A nota final** se dará pela soma acima, multiplicada por um peso entre 0 e 1 relativo ao acompanhamento semanal do projeto. Este peso é atribuído **individualmente**. Lembre-se: não é só a entrega do produto finalizado que importa, é todo o processo de sua construção e as entregas parciais para o “cliente”
	
## Comentários: -1,5
Não consegui criar carro. Dá exceção ao selecionar gasolina. Assim, não consigo testar o app propriamente.
Veículos com mais rotas, mais custos: mostra endereço do objeto.
Rotas sem autonomia sendo aceitas (while incorreto com reabastecimento infinito)
Estrutura do osberver correta. Implementação ruim: 'maxQuantidadeRotas' não devia ser público; fazer 2 for para localizar e trocar o menor é muito ruim (stream, colecoes etc)
**ATENÇÃO**: vocês estão entendendo errado o conceito do uso de exceções. A classe deve, claro, lançar exceção para sinalizar quebra de condições. O programa, porém, não pode morrer com isso: ele precisa tratar a exceção.
	
## Correção

### Modelagem: 1,2/2   
		- modularidade do tanque
		- enum x constantes nas classes filhas
	
### Persistência de dados: 3/4   
	- arquivo de teste: 0/1 (não encontrei)
	- salvar e carregar: 3/3 

### Robustez: 1,5/4
	- menu principal: 0/1 (morre por exceção de 'frota não existe', fileNotFound, escolha de combustível)
	- regras inválidas do projeto: 1,5/3 (morre por exeção de combustível)
	
### Padrão de projeto implementado: 5/5
	
	
### Documentação e apresentação:  
	- Nenhuma documentação de código
	- nota individual de acordo com a documentação e participação do aluno nas apresentações realizadas ao longo do Projeto

