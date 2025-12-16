# Backend E2E Notes

- Start backend (port 3001 by default): ./gradlew bootRun
- Endpoints expected by the frontend:
  - GET / -> welcome text
  - GET /health -> basic JSON health
  - GET /api/info -> info JSON
- Swagger UI:
  - http://localhost:3001/swagger-ui.html

CORS
- Configured via CorsConfig to allow http://localhost:3000 and common localhost origins.

Frontend .env alignment:
- REACT_APP_API_BASE=http://localhost:3001
- REACT_APP_BACKEND_URL=http://localhost:3001
- REACT_APP_FRONTEND_URL=http://localhost:3000
- REACT_APP_HEALTHCHECK_PATH=/health
- REACT_APP_BACKEND_DOCS_PATH=/swagger-ui.html
