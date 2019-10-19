## Scaling Compose

Stos uruchomieniowy dla systemu asynchronicznego wykorzystującego system kolejkowy w przetważaniu żądań.

### Uruchomienie

Stos uruchamiamy następującą komendą:

`docker-compose -f docker-compose.yml up -d`

Stos udostępnia następujące endpointy:

- `localhost:3000` - Grafana - logowanie `admin/admin`
- `localhost:9090` - Prometheus
- `localhost:9083` - usługa producenta zdarzeń w systemie
- `localhost:8080` - usługa obsługi zdarzeń w systemie