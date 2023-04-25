import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '^/dogs': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      },
      '^/owners': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      },
      '^/vets': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      },
      '^/licenses': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }
    },
    // port: 80
  }
})
