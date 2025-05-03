# Demo Serverless - Arquitectura Hexagonal

Este proyecto sigue una arquitectura hexagonal (ports & adapters) para una aplicación serverless.

## Estructura del Proyecto

```
src/
├── application/     # Casos de uso y lógica de aplicación
├── domain/         # Entidades y reglas de negocio
├── infrastructure/ # Implementaciones concretas
│   ├── adapters/   # Adaptadores para servicios externos
│   └── ports/      # Implementaciones de puertos
└── interfaces/     # Puntos de entrada a la aplicación
    ├── http/       # Controladores HTTP (API Gateway)
    └── events/     # Manejadores de eventos (SQS, SNS, etc.)

tests/
├── unit/          # Pruebas unitarias
└── integration/   # Pruebas de integración
```

## Instalación

```bash
npm install
```

## Desarrollo

Para desarrollo local:
```bash
npm run dev
```

## Despliegue

Para desplegar a AWS:
```bash
npm run deploy
``` 