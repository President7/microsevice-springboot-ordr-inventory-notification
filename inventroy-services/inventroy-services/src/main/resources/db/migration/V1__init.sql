CREATE table t_inventory(
id bigint(20) not null AUTO_INCREMENT,
sku_code varchar(255) default null,
quantity int(11) default null,
PRIMARY KEY(id)
);