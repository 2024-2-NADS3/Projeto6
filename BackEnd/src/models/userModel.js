const connection = require('../config/bDados'); 

const User = {
  
  create: (userData, callback) => {
    const { email, password } = userData; 
    const query = 'INSERT INTO usuarios (email, senha) VALUES (?, ?)'; 

    connection.query(query, [email, password], (error, results) => {
      if (error) {
        return callback(error); 
      }
      callback(null, results.insertId); 
    });
  },

  
};

module.exports = User;
