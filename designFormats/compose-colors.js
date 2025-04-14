module.exports = function({ dictionary, file }) {
  return `import androidx.compose.ui.graphics.Color

${dictionary.allTokens
    .map(token => `val ${token.name.replace(/\./g, '_')} = Color(0xFF${token.value.replace('#', '')})`)
    .join('\n')}`
}