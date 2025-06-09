#!/bin/bash
set -e

# Validar que la API Key esté presente
if [[ -z "$OWASP_DEPENDENCY_CHECK_APIKEY" ]]; then
  echo "ERROR: La variable OWASP_DEPENDENCY_CHECK_APIKEY no está configurada."
  exit 1
fi

# Ejecutar Dependency Check via Docker
docker run --rm \
    -e NVD_API_KEY="$OWASP_DEPENDENCY_CHECK_APIKEY" \
    -v "$(pwd)":/src \
    owasp/dependency-check:latest \
    --scan /src \
    --format HTML \
    --out /src/build/reports/dependency-check.html
