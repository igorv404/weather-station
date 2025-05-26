const eslintTemplatePlugin = require('@angular-eslint/template-parser');

module.exports = [
  {
    ignores: [
      'src/index.html',
      'src/assets/'
    ]
  },
  {
    files: ['**/*.ts'],
    languageOptions: {
      parser: require('@typescript-eslint/parser'),
      parserOptions: {
        project: ['./tsconfig.json'],
        createDefaultProgram: true,
        ecmaVersion: 'latest',
        sourceType: 'module'
      }
    },
    plugins: {
      '@typescript-eslint': require('@typescript-eslint/eslint-plugin'),
      '@angular-eslint': require('@angular-eslint/eslint-plugin'),
      'prettier': require('eslint-plugin-prettier')
    },
    rules: {
      ...require('@typescript-eslint/eslint-plugin').configs.recommended.rules,
      ...require('@angular-eslint/eslint-plugin').configs.recommended.rules,
      ...require('eslint-plugin-prettier').configs.recommended.rules,
      '@typescript-eslint/no-unused-vars': 'warn',
      'prettier/prettier': 'warn'
    }
  },
  {
    files: ['**/*.html'],
    languageOptions: {
      parser: eslintTemplatePlugin
    },
    plugins: {
      '@angular-eslint/template': require('@angular-eslint/eslint-plugin-template')
    },
    rules: {
      ...require('@angular-eslint/eslint-plugin-template').configs.recommended.rules
    }
  }
];
