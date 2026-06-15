import react from '@vitejs/plugin-react';
import { defineConfig, loadEnv } from 'vite';

const commitHash = require('child_process').execSync('git rev-parse --short HEAD').toString();

export default defineConfig(({ mode }) => ({
  plugins: [react()],
  build: {
    minify: mode !== 'development',
  },
  test: {
    environment: 'jsdom',
    coverage: {
      reporter: ['text', 'html'],
    },
  },
  define: {
    'process.env': { ...process.env, ...loadEnv(mode, process.cwd()) },
    'import.meta.env.VITE_APP_VERSION': JSON.stringify(process.env.npm_package_version),
    'import.meta.env.COMMIT_HASH': JSON.stringify(commitHash.trim()),
  },
}));
