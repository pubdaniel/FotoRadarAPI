CREATE SCHEMA IF NOT EXISTS api_senac DEFAULT CHARACTER SET utf8 ;
USE api_senac;

-- -----------------------------------------------------
-- Table categoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS categoria (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  ativo INT NOT NULL DEFAULT 1,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table produto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  valor DOUBLE NOT NULL,
  ativo INT NOT NULL DEFAULT 1,
  categoria_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_cliente_cidade_idx (categoria_id ASC),
  CONSTRAINT fk_cliente_cidade
    FOREIGN KEY (categoria_id)
    REFERENCES categoria (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;