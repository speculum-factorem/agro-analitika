import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

// Совпадает с GATEWAY_PUBLISH_PORT по умолчанию в docker-compose (прокси /api при npm run dev)
const apiGatewayProxyTarget =
  process.env.API_GATEWAY_PROXY || 'http://localhost:18080'

export default defineConfig({
  plugins: [react()],
  resolve: {
    // Одна копия react / react-redux в бандле — иначе <Provider> и хуки смотрят в разный Context → «store is null»
    dedupe: ['react', 'react-dom', 'react-redux', 'react-is', 'use-sync-external-store'],
    alias: {
      '@domain': path.resolve(__dirname, 'src/domain'),
      '@application': path.resolve(__dirname, 'src/application'),
      '@infrastructure': path.resolve(__dirname, 'src/infrastructure'),
      '@presentation': path.resolve(__dirname, 'src/presentation'),
      '@': path.resolve(__dirname, 'src'),
    },
  },
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: apiGatewayProxyTarget,
        changeOrigin: true,
      },
    },
  },
})
