DELIMITER $$

create procedure findProductsByPrice(
minPrice DECIMAL(10,2),
maxPrice DECIMAL(10,2)
)
BEGIN
SELECT id,name,description,price,category_id
from products where price between minPrice and maxPrice order by name;
END $$

DELIMITER ;