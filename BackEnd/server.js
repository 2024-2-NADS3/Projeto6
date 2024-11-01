// server.js
const app = require('./app'); 
const connection = require('./src/config/bDados'); // Conexão com o banco de dados

const PORT = process.env.PORT || 5000;


app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});
