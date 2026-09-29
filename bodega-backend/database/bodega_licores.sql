CREATE DATABASE bodega_licores; 
USE bodega_licores; 

CREATE TABLE licores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    foto VARCHAR(50) NOT NULL 
); 

INSERT INTO licores (tipo, marca, foto) VALUES    
    ('ron', 'Flor de Caña 12 Años', 'flor_de_cana.png'),    
    ('ron', 'Barceló Imperial', 'barcelo.png'),    
    ('whisky', 'Johnnie Walker Black Label', 'black_label.png'),    
    ('whisky', 'Chivas Regal', 'chivas_regal.png'),    
    ('whisky', 'Ballantines', 'ballantines.png'),    
    ('cerveza', 'Corona', 'corona.png'),    
    ('cerveza', 'Cuzqueña', 'cuzquena.png'),
    ('aperitivo', 'Cinzano Bianco', 'bianco.jpg'),     
    ('cerveza', 'Patagonia', 'patagonia.png'),    
    ('vino', 'La Linda', 'linda.png'),    
    ('vino', 'Rutini', 'rutini.png'), 
    ('cerveza', 'Andes Roja', 'andesroja.png'),    
    ('cerveza', 'Andes IPA', 'andesipa.jpg'), 
    ('vino', 'Zuccardi A', 'amalbec.jpeg'), 
    ('aperitivo', 'Cinzano Rosso', 'rosso.jpg'); 