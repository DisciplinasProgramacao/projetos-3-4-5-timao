# Projeto 4 - Gestão de Frota (parte 2)

## Nota: 7,1

## Comentários
	- sem app, a nota da parte 5 será extremamente prejudicada.
	- fiquem atentos aos custos variáveis e o mecanismo de abastecimento (comentários abaixo)
	
## Correção

### Modelagem: 0/2   
	- Modularização de tanque e combustível: diagrama totalmente desatualizado
	
### Requisitos dos veículos, de acordo com a modelagem: 2,5/9  
	- Restrição de combustível: 0/3 - não restringiu nada
	- Abastecimento e autonomia: 1/3 - calcula autonomia, mas não está usando para verificar rota nem abastecendo
	- Custos fixos e variáveis: 1,5/3 - custos variáveis configuram classe: têm descrição e preço, e podem ter outros dados.
	
	
### Requisitos da empresa no programa principal: 4,6/9 (app não criado, correção por teste e inspeção valendo menos pontos)
	- Quilometragem média das rotas da empresa: 1,8/3 -- ok, mas um stream funciona melhor (for está n²) 
	- Filtro para busca de rotas por data: 1,8/3  -- idem acima
	- Um dos dois abaixo: 1/3
		- Os 3 veículos que mais fizeram rotas -- não entendi. Aqui você precisa ordenar a coleção usada, ou fazer a ordenação temporária com stream
		- Lista de veículos ordenada decrescentemente por custos gerados -- não entendi. Aqui você precisa ordenar a coleção usada, ou fazer a ordenação temporária com stream

