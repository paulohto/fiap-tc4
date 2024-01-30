## Desafios e implementação com CleanArchitecture

O projeto foi construído com base nas boas práticas Clean Architecture. Sendo dividido da seguinte forma:

## Camadas Internas:
Garantindo a independência das camadas e isolamento com Framework e partes externas.

<!-- Entities -->
<ul>
    <li>Entities:
        <ul>
            <li>VideoEntity</li>
            <li>CurtidaEntity</li>
        </ul>
    </li>
</ul>

<!-- UserCases -->
<ul>
    <li>UserCases:
        <ul>
            <li>VideoCrudUseCase</li>
        </ul>
    </li>
</ul>

<!-- Adapters -->
<ul>
    <li>Adapters:
        <ul>
            <li>Gateways:
                <ul>
                    <li>IVideoGateway</li>
                </ul>
            </li>
        </ul>
    </li>
</ul>

## Camadas Externas:
Permitindo interação com ambiente externo e relação entre classes.

<!-- Infrastructure -->
<ul>
    <li>Infrastructure:
        <ul>
            <!-- Persistence -->
            <li>Persistence:
                <ul>
                    <li>VideoEntityAux</li>
                    <li>CurtidaEntityAux</li>
                    <li>ICurtidaRepository</li>
                    <li>IVideoRepository</li>
                </ul>
            </li>
            <!-- Gateway -->
            <li>Gateway:
                <ul>
                    <li>VideoRepositoryGateway</li>
                    <li>VideoEntityAuxMapper</li>
                </ul>
            </li>
            <!-- Controllers -->
            <li>Controllers:
                <ul>
                    <li>VideoController</li>
                </ul>
            </li>
        </ul>
    </li>
</ul>

<!-- Main -->
<ul>
    <li>Main:
        <ul>
            <li>VideoConfig: Classe central permitindo a injeção de dependências entre as classes.</li>
        </ul>
    </li>
</ul>

<p>
O principal desafio do trabalho foi justamente implementar toda a funcionalidade mantendo as bases Clean Architecture. O que torna o trabalho maior, com mais classes e linhas, porém mantem a limpeza entre as camadas e independência. Também torna-se mais fácil para manutenção do código.
<p>
Outro desafio relevante foi a inclusão da estrutura Reativa implementando com ReactiveMongoRepository. O que exigiu um pouco mais de pesquisa para manter toda estrutura conforme o requisito do trabalho.
<p>
Ainda observamos como ponto de melhoria a implementação de Curtida única ligado ao Id do Usuário em ambiente logado não permitindo inúmeras curtidas. 
