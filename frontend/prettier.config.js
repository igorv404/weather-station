const cssOrder = require('prettier-plugin-css-order');

module.exports = {
  plugins: [cssOrder],
  semi: true,
  singleQuote: true,
  trailingComma: 'none',
  printWidth: 100,
  tabWidth: 2,
  bracketSpacing: true,
  arrowParens: 'avoid'
};
