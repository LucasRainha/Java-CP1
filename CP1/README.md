# 🚗 Car Management API

Uma aplicação desenvolvida com **Spring Boot** para cadastro e gerenciamento de veículos.

## 📦 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Maven
- Lombok

## 📁 Estrutura

A aplicação gerencia objetos da classe `Car`, que possuem os seguintes atributos:

- `id`: Identificador único (Long)
- `model`: Modelo do carro (String)
- `brand`: Marca do carro (String)
- `year`: Ano de fabricação (Long)
- `power`: Potência do motor em HP (Long)
- `type`: Tipo do carro (`COMBUSTION`, `HYBRID`, `ELECTRIC`)
- `price`: Preço (String)
- `efficiency`: Economia (Para carros a combustão: `"15km/l"`, para carros elétricos: `"6.8km/kWh"`)