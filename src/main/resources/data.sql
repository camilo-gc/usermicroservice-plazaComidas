INSERT INTO `users-powerup`.`roles` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `users-powerup`.`roles` (`id`, `description`, `name`) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO `users-powerup`.`roles` (`id`, `description`, `name`) VALUES ('3', 'ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');
INSERT INTO `users-powerup`.`roles` (`id`, `description`, `name`) VALUES ('4', 'ROLE_CLIENT', 'ROLE_CLIENT');


INSERT INTO `users-powerup`.`users`
  (
    `birth_date`,
    `dni`,
    `email`,
    `name`,
    `password`,
    `phone`,
    `surname`,
    `id_role`
  )
VALUES
  (
    '14/01/1995',
    111,
    'admin@gmail.com',
    'Admin',
    '$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6',
    '1111111111',
    'Admon',
    1
  );
