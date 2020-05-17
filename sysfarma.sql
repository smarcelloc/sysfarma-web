-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 12-Abr-2019 às 04:12
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sysfarma`
--

-- --------------------------------------------------------

CREATE DATABASE IF NOT EXISTS `SysFarma` DEFAULT CHARACTER SET utf8 ;
USE `SysFarma` ;

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `uf` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`id`, `nome`, `uf`) VALUES
(1, 'São PAULO', 'SP');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `Pessoa_id` int(11) NOT NULL,
  `cpf` char(11) NOT NULL,
  `dt_nasc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `Pessoa_id` int(11) NOT NULL,
  `cep` varchar(20) NOT NULL,
  `logradouro` varchar(80) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `compl` varchar(45) DEFAULT NULL,
  `Cidade_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`Pessoa_id`, `cep`, `logradouro`, `numero`, `compl`, `Cidade_id`) VALUES
(1, '11111111', 'Sem Rua', '1', '', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornec`
--

CREATE TABLE `fornec` (
  `Pessoa_id` int(11) NOT NULL,
  `cnpj` char(14) NOT NULL,
  `ramal` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `Pessoa_id` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `senha` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `admin` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`Pessoa_id`, `email`, `senha`, `admin`) VALUES
(1, 'admin@admin.com', 'dbe75b8cd09890c6c39bb9b74d989fef', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `medic`
--

CREATE TABLE `medic` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `preco` double NOT NULL,
  `estoque` smallint(6) NOT NULL,
  `dt_validade` date NOT NULL,
  `controlado` tinyint(4) DEFAULT NULL,
  `TipoMedic_id` int(11) DEFAULT NULL,
  `Fornec_Pessoa_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `tel` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`id`, `nome`, `tel`) VALUES
(1, 'Admin', '1111111111');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipomedic`
--

CREATE TABLE `tipomedic` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `total` double NOT NULL,
  `dt_vendida` date NOT NULL,
  `pgto` varchar(45) NOT NULL,
  `Cliente_Pessoa_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Pessoa_id`),
  ADD UNIQUE KEY `cpf_UNIQUE` (`cpf`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`Pessoa_id`),
  ADD KEY `fk_Endereco_Cidade1_idx` (`Cidade_id`);

--
-- Indexes for table `fornec`
--
ALTER TABLE `fornec`
  ADD PRIMARY KEY (`Pessoa_id`),
  ADD UNIQUE KEY `cnpj_UNIQUE` (`cnpj`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`Pessoa_id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `medic`
--
ALTER TABLE `medic`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Medicamento_TipoMedic1_idx` (`TipoMedic_id`),
  ADD KEY `fk_Medicamento_Fornecedor1_idx` (`Fornec_Pessoa_id`);

--
-- Indexes for table `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tipomedic`
--
ALTER TABLE `tipomedic`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome_UNIQUE` (`nome`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Venda_Cliente1_idx` (`Cliente_Pessoa_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cidade`
--
ALTER TABLE `cidade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `medic`
--
ALTER TABLE `medic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tipomedic`
--
ALTER TABLE `tipomedic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_Cliente_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `fk_Endereco_Cidade1` FOREIGN KEY (`Cidade_id`) REFERENCES `cidade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Endereco_Pessoa` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `fornec`
--
ALTER TABLE `fornec`
  ADD CONSTRAINT `fk_Fornecedor_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `fk_Funcionario_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `medic`
--
ALTER TABLE `medic`
  ADD CONSTRAINT `fk_Medicamento_Fornecedor1` FOREIGN KEY (`Fornec_Pessoa_id`) REFERENCES `fornec` (`Pessoa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Medicamento_TipoMedic1` FOREIGN KEY (`TipoMedic_id`) REFERENCES `tipomedic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fk_Venda_Cliente1` FOREIGN KEY (`Cliente_Pessoa_id`) REFERENCES `cliente` (`Pessoa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
