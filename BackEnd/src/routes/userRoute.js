const express = require('express');
const router = express.Router();
const { registerUser, loginUser } = require('../../controllers/userController');
const { body } = require('express-validator');

// Rota para registrar um novo usuário
router.post(
  '/register', 
  [
    body('name').notEmpty().withMessage('Nome é obrigatório'),
    body('email').isEmail().withMessage('E-mail inválido'),
    body('password').isLength({ min: 6 }).withMessage('A senha deve ter pelo menos 6 caracteres')
  ],
  registerUser
);

// Rota para fazer login
router.post(
  '/login', 
  [
    body('email').isEmail().withMessage('E-mail inválido'),
    body('password').notEmpty().withMessage('Senha é obrigatória')
  ],
  loginUser
);

module.exports = router;
