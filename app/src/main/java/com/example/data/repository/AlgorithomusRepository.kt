package com.example.data.repository

import com.example.data.model.AnomalySignal
import com.example.data.model.CharacterProfile
import com.example.data.model.Episode
import com.example.data.model.QuizQuestion
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.EntityViolet
import com.example.ui.theme.GlitchRed
import com.example.ui.theme.LinnBlue
import com.example.ui.theme.MorajoPurple
import com.example.ui.theme.TorajoGold
import com.example.ui.theme.TorajoOrange
import com.example.ui.theme.ZulmiGreen

object AlgorithomusRepository {

    val episodes = listOf(
        Episode(
            id = "algo_ep1",
            title = "ALGORITHOMUS - Episódio 1: O Início do Pesadelo",
            seriesCategory = "Algorithomus",
            episodeNumber = 1,
            duration = "14:20",
            releaseDate = "2024",
            synopsis = "Morajo começa a ter pesadelos recorrentes com uma anomalia digital desconhecida. Um código estranho começa a distorcer a realidade de Verade sem que ninguém perceba o perigo.",
            youtubeUrl = "https://www.youtube.com/results?search_query=ALGORITHOMUS+Mundo+Torajo+Episodio+1",
            glitchLevel = 45,
            featuredCharacters = listOf("Torajo", "Morajo", "Zulmi"),
            secretLore = "O primeiro sinal do Algorithomus apareceu sutilmente nos reflexos dos espelhos de Verade.",
            badgeLabel = "CANÔNICO"
        ),
        Episode(
            id = "algo_ep2",
            title = "ALGORITHOMUS - Episódio 2: A Corrupção de Morajo",
            seriesCategory = "Algorithomus",
            episodeNumber = 2,
            duration = "16:45",
            releaseDate = "2024",
            synopsis = "A entidade toma força. Morajo fica cada vez mais debilitado à medida que o Algorithomus invade seus pensamentos e tenta fragmentar sua conexão com Torajo.",
            youtubeUrl = "https://www.youtube.com/results?search_query=ALGORITHOMUS+Mundo+Torajo+Episodio+2",
            glitchLevel = 78,
            featuredCharacters = listOf("Morajo", "Torajo", "Linn", "Algorithomus"),
            secretLore = "O Algorithomus não ataca por acaso: ele se alimenta das dúvidas e do isolamento de Morajo.",
            badgeLabel = "CLÍMAX"
        ),
        Episode(
            id = "algo_ep3",
            title = "ALGORITHOMUS - Episódio 3: O Mistério de Verade",
            seriesCategory = "Algorithomus",
            episodeNumber = 3,
            duration = "18:10",
            releaseDate = "2024",
            synopsis = "Zulmi e Linn investigam arquivos antigos do Diário dos Mistérios em busca da cura antes que o Algorithomus apague as memórias primordiais do mundo animado.",
            youtubeUrl = "https://www.youtube.com/results?search_query=ALGORITHOMUS+Mundo+Torajo+Episodio+3",
            glitchLevel = 88,
            featuredCharacters = listOf("Zulmi", "Linn", "Torajo", "Morajo"),
            secretLore = "O Diário dos Mistérios de Verade contém coordenadas para a câmara do núcleo do algoritmo.",
            badgeLabel = "REVELAÇÃO"
        ),
        Episode(
            id = "algo_ep4",
            title = "ALGORITHOMUS - Episódio 4: Confronto das Frequências",
            seriesCategory = "Algorithomus",
            episodeNumber = 4,
            duration = "19:30",
            releaseDate = "2024",
            synopsis = "Torajo reúne toda a turma para sincronizar suas auras e desafiar a anomalia face a face. Uma batalha cósmica e emocional onde a lealdade é a única defesa.",
            youtubeUrl = "https://www.youtube.com/results?search_query=ALGORITHOMUS+Mundo+Torajo+Episodio+4",
            glitchLevel = 95,
            featuredCharacters = listOf("Torajo", "Morajo", "Zulmi", "Linn", "Azedo"),
            secretLore = "A união dos irmãos gera uma frequência imune a qualquer corrupção digital.",
            badgeLabel = "FINAL DE TEMPORADA"
        ),
        Episode(
            id = "torajo_vs_morajo_mus",
            title = "TORAJO vs MORAJO - Musical Oficial",
            seriesCategory = "Torajo vs Morajo",
            episodeNumber = 1,
            duration = "04:15",
            releaseDate = "2023",
            synopsis = "O clássico duelo musical que definiu a rivalidade e o laço fraternal entre os gêmeos Torajo e Morajo com milhões de visualizações.",
            youtubeUrl = "https://www.youtube.com/results?search_query=TORAJO+vs+MORAJO+musical+oficial",
            glitchLevel = 20,
            featuredCharacters = listOf("Torajo", "Morajo"),
            secretLore = "A letra da música já antecipava que Morajo enfrentaria uma escuridão interior.",
            badgeLabel = "MUSICAL"
        ),
        Episode(
            id = "linn_reveal",
            title = "O LINN É... (A Grande Revelação)",
            seriesCategory = "Especiais",
            episodeNumber = 1,
            duration = "12:50",
            releaseDate = "2023",
            synopsis = "Um dos episódios mais marcantes de Mundo Torajo, explorando os mistérios por trás da personalidade enigmática e origem de Linn.",
            youtubeUrl = "https://www.youtube.com/results?search_query=Mundo+Torajo+O+Linn+e",
            glitchLevel = 35,
            featuredCharacters = listOf("Linn", "Torajo", "Zulmi"),
            secretLore = "Linn possui ligação direta com as anomalias quânticas registradas em Verade.",
            badgeLabel = "HISTÓRIA"
        )
    )

    val characters = listOf(
        CharacterProfile(
            id = "char_torajo",
            name = "Torajo",
            title = "O Guardião de Verade & Criador",
            role = "Protagonista / Líder Inspirador",
            accentColor = TorajoOrange,
            quote = "\"Nós criamos o nosso próprio destino. Ninguém vai apagar o nosso mundo!\"",
            corruptionLevel = 10,
            biography = "Torajo é o coração do Mundo Torajo. Com seu espírito alegre, leal e determinação inabalável, ele faz tudo para manter seu mundo vivo e salvar seu irmão gêmeo Morajo das garras do Algorithomus.",
            keyTraits = listOf("Otimismo Inabalável", "Protetor do Irmão", "Energia Criativa", "Aura Laranja"),
            secretNote = "A coroa e as cores quentes de Torajo representam a fagulha vital que alimenta a realidade de Verade contra apagões cibernéticos.",
            signatureIconName = "LocalFireDepartment"
        ),
        CharacterProfile(
            id = "char_morajo",
            name = "Morajo",
            title = "O Gêmeo Sombrio & Alvo da Anomalia",
            role = "Co-Protagonista / Vítima do Glitch",
            accentColor = MorajoPurple,
            quote = "\"O pesadelo não para quando eu acordo... ele só fica mais real.\"",
            corruptionLevel = 84,
            biography = "Irmão gêmeo de Torajo. Mais reservado, reflexivo e irônico. Em Algorithomus, Morajo é atormentado por pesadelos intensos e se torna o principal hospedeiro da anomalia que ameaça devorar Verade.",
            keyTraits = listOf("Mente Analítica", "Sensibilidade ao Glitch", "Lealdade Oculta", "Aura Roxa"),
            secretNote = "Mesmo corrompido, Morajo luta internamente a cada segundo para proteger seus amigos do controle total da entidade.",
            signatureIconName = "Psychology"
        ),
        CharacterProfile(
            id = "char_zulmi",
            name = "Zulmi",
            title = "A Estrategista & Decodificadora",
            role = "Investigadora Principal",
            accentColor = ZulmiGreen,
            quote = "\"Todo glitch tem um padrão. Se tem padrão, nós podemos quebrar o código.\"",
            corruptionLevel = 5,
            biography = "Zulmi é inteligente, observadora e prática. Quando anomalias começam a pipocar por Verade, ela é a primeira a abrir o Diário dos Mistérios e rastrear as frequências do Algorithomus.",
            keyTraits = listOf("Pensamento Lógico", "Decodificadora de Códigos", "Firmeza & Coragem", "Aura Esmeralda"),
            secretNote = "Zulmi mantém um caderno secreto com anotações sobre as falhas dimensionais de Verade.",
            signatureIconName = "Terminal"
        ),
        CharacterProfile(
            id = "char_linn",
            name = "Linn",
            title = "O Guardião Silencioso",
            role = "Aliado Enigmático",
            accentColor = LinnBlue,
            quote = "\"Há coisas no código de Verade que nem mesmo o criador previu.\"",
            corruptionLevel = 18,
            biography = "Linn é misterioso, sereno e carrega consigo uma das histórias de fundo mais surpreendentes de Mundo Torajo. Suas habilidades especiais são cruciais para conter as brechas de realidade.",
            keyTraits = listOf("Segredo Dimensional", "Calma sob Pressão", "Protetor Oculto", "Aura Azul Cósmica"),
            secretNote = "Linn consegue pressentir a aproximação de flutuações no Algorithomus antes de qualquer sensor.",
            signatureIconName = "Visibility"
        ),
        CharacterProfile(
            id = "char_algorithomus",
            name = "Algorithomus",
            title = "A Entidade Glitch Parasitária",
            role = "Ameaça Dimensional",
            accentColor = GlitchRed,
            quote = "\"01010011 01001111 01010011... REESCREVENDO DADOS DE VERADE...\"",
            corruptionLevel = 100,
            biography = "Uma entidade cibernética e misteriosa que emergiu das profundezas dos dados esquecidos. Invade mentes vulneráveis através de pesadelos e tenta reescrever os códigos fundamentais de Verade.",
            keyTraits = listOf("Corrupção Digital", "Voz Distorcida", "Absorção de Memórias", "Glitch Fractal"),
            secretNote = "Sua fraqueza conhecida reside na ressonância harmônica da memória afetiva entre Torajo e Morajo.",
            signatureIconName = "Warning"
        ),
        CharacterProfile(
            id = "char_turma",
            name = "Azedo, Abel & Pessy",
            title = "O Esquadrão de Apoio",
            role = "Amigos Leais",
            accentColor = TorajoGold,
            quote = "\"Se mexer com um de nós, mexeu com toda a turma de Verade!\"",
            corruptionLevel = 12,
            biography = "Os fiéis companheiros que completam a família de Mundo Torajo. Sempre prontos com humor, bravura e apoio quando o perigo aperta.",
            keyTraits = listOf("União de Amigos", "Alívio Cômico & Ação", "Coração Leal"),
            secretNote = "Eles foram essenciais para criar a distração que permitiu a Zulmi invadir o terminal do algoritmo.",
            signatureIconName = "Groups"
        )
    )

    val anomalies = listOf(
        AnomalySignal(
            id = "anom_01",
            code = "PESADELO_MOR_432",
            title = "Pesadelo do Quarto de Morajo",
            location = "Residência dos Gêmeos - Setor Sul",
            threatLevel = "Moderada",
            description = "Flutuações eletromagnéticas e distorções visuais registradas durante o sono de Morajo. Padrão fractal de glitch nos ponteiros do relógio.",
            frequencyHz = 432,
            correctKeySequence = listOf(1, 0, 1, 2),
            unlockedDiaryTitle = "Diário de Verade - Pág. 14: Os Primeiros Sintomas",
            unlockedDiaryText = "\"O Morajo acorda suando frio. Ele diz que vê sombras com linhas de código escorrendo pelas paredes. O Algorithomus não é um vírus comum; ele se ancora em sentimentos de abandono.\""
        ),
        AnomalySignal(
            id = "anom_02",
            code = "FRACTAL_LINN_780",
            title = "Ressonância do Portão Quântico",
            location = "Limite Dimensional de Verade",
            threatLevel = "Alta",
            description = "Uma fenda azulada pulsa em harmonia com a energia de Linn. Códigos binários antigos flutuam no ar como partículas incandescentes.",
            frequencyHz = 780,
            correctKeySequence = listOf(2, 2, 0, 1),
            unlockedDiaryTitle = "Diário de Verade - Pág. 32: A Origem dos Portais",
            unlockedDiaryText = "\"Linn sempre soube que Verade é sustentada por laços de dados. Se o Algorithomus romper o firewall central, a memória de todos os personagens será formatada para sempre.\""
        ),
        AnomalySignal(
            id = "anom_03",
            code = "NUCLEO_BRUNO_920",
            title = "O Código Criador Original",
            location = "Torre de Transmissão Central",
            threatLevel = "CRÍTICA",
            description = "A chave mestra que liga o canal de animação ao mundo dos personagens. Algorithomus está tentando bloquear a transmissão para os milhões de espectadores humanos.",
            frequencyHz = 920,
            correctKeySequence = listOf(0, 1, 2, 0),
            unlockedDiaryTitle = "Diário de Verade - Pág. 50: O Segredo de Bruno & Isabela",
            unlockedDiaryText = "\"Verade só existe porque milhões de pessoas assistem e acreditam nessa história. O amor dos inscritos é a barreira física que impede o Algorithomus de devorar tudo!\""
        )
    )

    val quizQuestions = listOf(
        QuizQuestion(
            id = 1,
            question = "Quem são os criadores originais do universo de Mundo Torajo e da série Algorithomus?",
            options = listOf("Bruno Gemin e Isabela Silva", "Pedro Sampaio e Clara Mendes", "Lucas Neto e Felipe Silva", "Guilherme Santos e Júlia Rocha"),
            correctIndex = 0,
            explanation = "Algorithomus foi criado pelo YouTuber Bruno Gemin em conjunto com a talentosa artista e animadora Isabela Silva!",
            difficulty = "Fácil"
        ),
        QuizQuestion(
            id = 2,
            question = "Qual personagem é o alvo central dos pesadelos causados pela anomalia Algorithomus?",
            options = listOf("Torajo", "Morajo", "Zulmi", "Azedo"),
            correctIndex = 1,
            explanation = "Morajo é o gêmeo que sofre com pesadelos vívidos e se torna o hospedeiro vulnerável da entidade Algorithomus.",
            difficulty = "Fácil"
        ),
        QuizQuestion(
            id = 3,
            question = "Qual é o título do livro oficial lançado por Bruno Gemin ambientado no universo da série?",
            options = listOf("O Segredo de Torajo", "Diário dos Mistérios de Verade", "As Crônicas de Morajo", "Manual do Algoritmo Perdido"),
            correctIndex = 1,
            explanation = "O livro oficial é o 'Diário dos Mistérios de Verade', cheio de pistas, segredos e enigmas canônicos!",
            difficulty = "Médio"
        ),
        QuizQuestion(
            id = 4,
            question = "Quantos inscritos o canal Mundo Torajo ultrapassou no YouTube?",
            options = listOf("Mais de 500 mil", "Mais de 1 milhão", "Mais de 3 milhões", "Apenas 100 mil"),
            correctIndex = 2,
            explanation = "Mundo Torajo é um fenômeno da animação brasileira com mais de 3 milhões de inscritos no YouTube!",
            difficulty = "Médio"
        ),
        QuizQuestion(
            id = 5,
            question = "Qual é o domínio oficial na web que direciona para a série Algorithomus?",
            options = listOf("algorithomus.com.br", "mundotorajo.com", "verademisterios.br", "torajovsmorajo.com.br"),
            correctIndex = 0,
            explanation = "algorithomus.com.br é o portal oficial na internet dedicado à série Algorithomus e Mundo Torajo!",
            difficulty = "Fácil"
        ),
        QuizQuestion(
            id = 6,
            question = "Qual é a cor característica que identifica a aura e o tema de Zulmi?",
            options = listOf("Roxo", "Laranja", "Verde Esmeralda", "Vermelho"),
            correctIndex = 2,
            explanation = "Zulmi é representada pela cor verde esmeralda, simbolizando perspicácia e estratégia!",
            difficulty = "Médio"
        ),
        QuizQuestion(
            id = 7,
            question = "Como a série Algorithomus se conecta com a história anterior do canal?",
            options = listOf("É uma continuação direta de TORAJO vs MORAJO", "É um universo totalmente sem relação", "É um filme live-action", "É uma animação estrangeira traduzida"),
            correctIndex = 0,
            explanation = "Algorithomus é a continuação direta dos acontecimentos de TORAJO vs MORAJO, aprofundando o drama e o mistério de Verade.",
            difficulty = "Difícil"
        )
    )
}
