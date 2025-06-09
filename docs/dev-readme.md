# Guía de desarrollo

## Revisión previa a Commit

- `./gradlew checkAll`
- Validar `dependency-check-suppressions.xml`:

```bash
xmllint --schema ./config/schemas/dependency-suppression.1.3.xsd ./config/dependency-check-suppressions.xml
