const StyleDictionary = require('style-dictionary').default;
const path = require('path');

StyleDictionary.registerFormat({
  name: 'compose/colors',
  format: require('./designFormats/compose-colors'),
});

module.exports = {
  source: ['designTokens/**/*.json'],
  platforms: {
    compose: {
      buildPath: 'app/src/main/java/com/dhc/dhcandroid/ui/theme/',
      files: [
        {
          destination: 'Colors.kt',
          format: 'compose/colors',
        }
      ]
    }
  }
};