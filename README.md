# Motorista Inteligente S21

<img alt="License" src="https://img.shields.io/badge/license-MIT-green">
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.0-blue">
<img alt="Android" src="https://img.shields.io/badge/Android-15-green">
<img alt="Compose" src="https://img.shields.io/badge/Jetpack%20Compose-Latest-brightgreen">

## 📱 Descrição

Aplicativo Android nativo desenvolvido em **Kotlin** utilizando **Jetpack Compose**, **Material 3**, **MVVM/Clean Architecture**, **Room Database**, **WorkManager** e componentes modernos do Android.

Um assistente inteligente de desempenho, estabilidade e manutenção do celular, projetado especialmente para motoristas profissionais que utilizam plataformas como 99, Uber, Waze e WhatsApp.

### 🎯 Princípios Fundamentais

- ✅ **Segurança em primeiro lugar**: Nunca apaga dados automaticamente
- ✅ **Transparência total**: Toda alteração é registrada e reversível
- ✅ **Sem root necessário**: Funciona com permissões padrão do Android
- ✅ **Não interfere com trabalho**: Protege apps críticos (99, Uber, Waze, WhatsApp)
- ✅ **Interface intuitiva**: Desenvolvida para usuários leigos

## 🛠️ Tecnologias

| Componente | Versão |
|---|---|
| Kotlin | 2.0+ |
| Jetpack Compose | Latest |
| Material 3 | Latest |
| Room | Latest |
| Hilt | Latest |
| Coroutines | Latest |
| WorkManager | Latest |
| JUnit | 4 |
| Instrumented Tests | Latest |

## 📦 Estrutura do Projeto

```
motorista-inteligente-s21/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/motorista/s21/
│   │   │   │   ├── data/               # Camada de dados (Room, API)
│   │   │   │   ├── domain/             # Lógica de negócio (Use Cases)
│   │   │   │   ├── presentation/       # UI (Compose, ViewModels)
│   │   │   │   ├── navigation/         # Navegação
│   │   │   │   ├── di/                 # Injeção de dependência (Hilt)
│   │   │   │   ├── core/               # Utilitários, extensões
│   │   │   │   └── features/           # Features específicas
│   │   │   │       ├── dashboard/
│   │   │   │       ├── diagnostics/
│   │   │   │       ├── optimization/
│   │   │   │       ├── network/
│   │   │   │       ├── battery/
│   │   │   │       ├── history/
│   │   │   │       ├── settings/
│   │   │   │       └── laboratory/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/                      # Testes unitários
│   │   └── androidTest/               # Testes instrumentados
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml              # Version Catalog
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🚀 Início Rápido

### Pré-requisitos
- Android Studio Koala (2024.1.1) ou superior
- Android SDK 31+
- Kotlin 2.0+
- JDK 17+

### Compilação

```bash
# Clone o repositório
git clone https://github.com/Samuel9125/motorista-inteligente-s21.git
cd motorista-inteligente-s21

# Compile o projeto
./gradlew build

# Gere o APK para desenvolvimento
./gradlew assembleDebug

# Gere o APK otimizado
./gradlew assembleRelease
```

### Instalação no Samsung Galaxy S21 Ultra

```bash
# Via ADB
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Ou simplesmente abra o APK gerado no device
```

## 📋 Funcionalidades Principais

### 🏠 Dashboard (Tela Inicial)
- Saúde geral do aparelho (0-100)
- Temperatura em tempo real
- Nível e estado da bateria
- RAM (utilizada/disponível)
- Armazenamento (utilizado/disponível)
- Qualidade da rede
- Estado do GPS
- Status dos apps críticos (99, Uber, Waze)
- Alertas importantes
- Último diagnóstico realizado

### 🔍 Diagnóstico Completo
- Informações do device
- RAM e armazenamento
- Estado da bateria
- Rede e conectividade
- GPS
- Aplicativos prioritários
- Permissões
- Relatório detalhado (Normal/Atenção/Crítico)

### ⚡ Otimização Segura
- **Diagnóstico**: Somente análise, sem alterações
- **Recomendada**: Ações seguras e reversíveis
- **Avançada**: Requer confirmação individual
- Limpeza inteligente com seleção manual
- Análise de armazenamento
- Recuperação de espaço

### 🌐 Monitor de Rede
- Tipo de conexão (Wi-Fi / Móvel)
- Operadora
- Sinal e latência
- Tipo de rede (3G/4G/5G)
- Histórico de quedas
- Teste de conectividade

### 🔋 Monitor de Bateria
- Temperatura
- Estado de carregamento
- Percentual
- Velocidade de carregamento
- Alertas configuráveis
- Histórico de consumo

### 🚗 Modo Motorista
- Atalhos rápidos para 99, Uber, Waze
- Monitoramento contínuo
- Alertas de temperatura/bateria/rede
- Proteção de apps críticos
- Limites configuráveis
- Sem interferência no trabalho

### 📊 Assistente de Corridas
- Calculadora manual
- Valor por km
- Receita líquida estimada
- Custo por km
- Receita por hora
- Apenas auxilia decisão (sem automação)

### 📈 Histórico
- Diagnósticos salvo em Room
- Alterações realizadas
- Resultados antes/depois
- Alertas de temperatura/bateria/rede
- Filtros por data
- Comparação de diagnósticos
- Exportação (PDF/Texto)
- Compartilhamento

### 🔐 Backup e Restauração
- Backup das configurações do app
- Preferências
- Limites de alerta
- Regras de análise
- Validação de integridade
- Restauração segura

### 🧪 Laboratório de Testes
- Dados fictícios
- Simulação de cenários críticos
- Testes sem alterar device real
- Cobre: RAM, armazenamento, temperatura, bateria, rede, GPS, permissões, etc.

## 🔒 Segurança e Privacidade

- ✅ Solicita apenas permissões necessárias
- ✅ Explica cada permissão
- ✅ Não coleta dados sem consentimento
- ✅ Não envia dados para servidores sem autorização
- ✅ Não armazena senhas ou tokens
- ✅ Logs sem dados pessoais
- ✅ Banco de dados protegido
- ✅ Política de privacidade integrada

## 📝 Testes

### Testes Unitários
```bash
./gradlew test
```

### Testes Instrumentados
```bash
./gradlew connectedAndroidTest
```

### Cobertura de Testes
```bash
./gradlew testDebugUnitTestCoverage
```

## 🗂️ Documentação Completa

- [Guia de Instalação](docs/INSTALACAO.md)
- [Guia de Compilação](docs/COMPILACAO.md)
- [Guia de Geração de APK](docs/APK.md)
- [Limitações do Android](docs/LIMITACOES.md)
- [Política de Privacidade](docs/PRIVACIDADE.md)
- [Relatório de Segurança](docs/SEGURANCA.md)
- [Checklist de Testes](docs/TESTES.md)
- [Arquitetura Detalhada](docs/ARQUITETURA.md)

## 📋 Roadmap

- [x] Setup base do projeto
- [ ] Dashboard com indicadores reais
- [ ] Sistema de diagnóstico
- [ ] Otimização segura
- [ ] Monitor de rede
- [ ] Monitor de bateria
- [ ] Modo motorista
- [ ] Assistente de corridas
- [ ] Histórico com Room
- [ ] Backup e restauração
- [ ] Laboratório de testes
- [ ] IA híbrida (local + nuvem opcional)
- [ ] Cobertura completa de testes
- [ ] Documentação final

## 🤝 Contribuições

Este é um projeto de código aberto. Sugestões e PRs são bem-vindas!

## 📄 Licença

MIT License - veja [LICENSE](LICENSE) para detalhes.

## 📧 Contato

Samuel9125 - [@Samuel9125](https://github.com/Samuel9125)

---

**Status:** Em Desenvolvimento 🚧
**Última Atualização:** 2026-09-10
