// src/config/database.js
const mysql = require('mysql2');

// Configurações do banco de dados
const connection = mysql.createConnection({
    host: 'bd-fitsync.mysql.database.azure.com',
    user: 'vini',
    password: '20012005Mae',
    database: 'fitsync',
    port: 3306,
    ssl: {
        rejectUnauthorized: false // Adicione esta linha para ignorar erros de autorização
    }
});

// Conectar ao banco de dados
connection.connect((err) => {
    if (err) {
        console.error('Erro ao conectar ao banco de dados: ' + err.stack);
        return;
    }
    console.log('Conectado ao banco de dados MySQL como id ' + connection.threadId);
});

module.exports = connection; // Exporte a conexão
