create TABLE wishlist
(
    product_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_wishlist PRIMARY KEY (product_id, user_id)
);

alter table wishlist
    add CONSTRAINT fk_wishlist_on_product FOREIGN KEY (product_id) REFERENCES products (id);

alter table wishlist
    add CONSTRAINT fk_wishlist_on_user FOREIGN KEY (user_id) REFERENCES users (id);