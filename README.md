## Thread Scaling Compose

Stos uruchomieniowy dla systemu asynchronicznego wykorzystującego system kolejkowy w przetwarzaniu żądań.

### Uruchomienie

Stos uruchamiamy następującą komendą:

`docker-compose -f docker-compose.yml up -d`

Stos udostępnia następujące endpointy:

- `localhost:3000` - Grafana - logowanie `admin/admin`
- `localhost:9090` - Prometheus
- `localhost:9083` - usługa producenta zdarzeń w systemie
- `localhost:8080` - usługa obsługi zdarzeń w systemie

### Uruchomienie testów

Test uruchamiamy komendą:

`.\run-test.ps1 -fastScale 0.7 -slowScale 0.3 -fastUsersNo 10 -slowUsersNo 3 -duration 60`

gdzie poszczególne parametry oznaczają:

- `fastScale` - część liczby wątków z całej puli przeznaczonych do obsługi zdarzeń typu `fast`
- `slowScale` - część liczby wątków z całej puli przeznaczonych do obsługi zdarzeń typu `slow`
- `fastUserNo` - liczba użytkowników generujących zdarzenia typu `fast`
- `slowUserNo` - liczba użytkowników generujących zdarzenia typu `slow`
- `duration` - czas w sekundach całego testu
