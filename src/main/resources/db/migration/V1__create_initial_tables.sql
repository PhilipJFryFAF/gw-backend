/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `battle` (
  `id` int(11) NOT NULL,
  `fk_planet` int(11) NOT NULL,
  `status` char(1) NOT NULL COMMENT '[I]nitiated, [C]anceled, [R]unning, [F]inished',
  `initiated_at` datetime NOT NULL,
  `started_at` datetime DEFAULT NULL,
  `ended_at` datetime DEFAULT NULL,
  `attacking_faction` char(1) NOT NULL COMMENT '[U]ef, [A]eon, [C]ybran, [S]eraphim',
  `defending_faction` char(1) NOT NULL COMMENT '[U]ef, [A]eon, [C]ybran, [S]eraphim',
  `winning_faction` char(1) DEFAULT NULL COMMENT '[U]ef, [A]eon, [C]ybran, [S]eraphim - NULL on draw'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `battle_participant` (
  `id`           INT(11) NOT NULL,
  `fk_battle` int(11) NOT NULL,
  `fk_character` int(11) NOT NULL,
  `role` char(1) NOT NULL COMMENT '[A]ttacker, [D]efender',
  `result` char(1) NOT NULL COMMENT '[V]ictory, [D]eath, [R]ecall'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `character` (
  `id` int(11) NOT NULL,
  `faf_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `faction` char(1) NOT NULL COMMENT '[U]ef, [A]eon, [C]ybran, [S]eraphim',
  `killed_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `credit_journal` (
  `id` int(11) NOT NULL,
  `fk_character` int(11) NOT NULL,
  `fk_battle` int(11) DEFAULT NULL COMMENT 'id of the battle, where credits where gained or null if regular income or buy',
  `fk_unit_transaction` int(11) DEFAULT NULL COMMENT 'id of the reinforcements bought or null if income',
  `reason` char(1) NOT NULL COMMENT '[R]egular income, [V]ictory, ACU [K]ill, [R]einforcements',
  `amount` int(11) NOT NULL COMMENT 'positive on income, negative on expenses',
  `transaction_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `map_pool` (
  `id` int(11) NOT NULL,
  `faf_map_id` int(11) NOT NULL COMMENT 'id of the map in FAF DB',
  `faf_map_version` int(11) NOT NULL COMMENT 'version-id of the map in FAF DB',
  `total_slots` tinyint(4) NOT NULL,
  `size` int(11) NOT NULL COMMENT 'equivalent to map size 5,10,20,40',
  `ground` char(1) NOT NULL COMMENT '[W]ater, [S]oil, [L]ava, [D]esert, [F]rost'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `planet` (
  `id` int(11) NOT NULL,
  `fk_sun_system` int(11) NOT NULL,
  `fk_map` int(11) NOT NULL,
  `orbit_level` int(11) NOT NULL,
  `size` int(11) NOT NULL COMMENT 'equivalent to map size',
  `habitable` tinyint(1) NOT NULL,
  `ground` char(1) NOT NULL COMMENT '[W]ater, [S]oil, [L]ava, [D]esert, [F]rost'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `promotion` (
  `id`           int(11) NOT NULL,
  `fk_character` int(11) NOT NULL,
  `fk_battle`    INT(11) DEFAULT NULL
  COMMENT 'NULL for level 1',
  `new_rank`     INT(11) NOT NULL,
  `created_at`   datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `quantum_gate_link` (
  `fk_sun_system_from` int(11) NOT NULL,
  `fk_sun_system_to` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rank` (
  `level` int(11) NOT NULL,
  `xp_min` int(11) DEFAULT NULL,
  `max_per_faction` tinyint(4) DEFAULT NULL,
  `uef_title` varchar(20) NOT NULL,
  `aeon_title` varchar(20) NOT NULL,
  `cybran_title` varchar(20) NOT NULL,
  `seraph_title` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `schema_version` (
  `installed_rank` INT(11)       NOT NULL,
  `version`        VARCHAR(50)            DEFAULT NULL,
  `description`    VARCHAR(200)  NOT NULL,
  `type`           VARCHAR(20)   NOT NULL,
  `script`         VARCHAR(1000) NOT NULL,
  `checksum`       INT(11)                DEFAULT NULL,
  `installed_by`   VARCHAR(100)  NOT NULL,
  `installed_on`   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT(11)       NOT NULL,
  `success`        TINYINT(1)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `sun_system` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `z` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `fa_uid` varchar(10) NOT NULL COMMENT 'ID of the unit in Forged Alliance',
  `name` varchar(30) NOT NULL COMMENT 'Name of the unit in Forged Alliance',
  `faction` char(1) NOT NULL COMMENT '[U]ef, [A]eon, [C]ybran, [S]eraphim',
  `tech_level` tinyint(4) NOT NULL COMMENT 'T1-T3',
  `price` int(11) NOT NULL COMMENT 'Price per unit'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `unit_transaction` (
  `id` int(11) NOT NULL,
  `fk_character` int(11) NOT NULL,
  `fk_battle` int(11) DEFAULT NULL COMMENT 'if type=dispatch here goes the battle-id',
  `type` char(1) NOT NULL COMMENT '[B]uy, [D]ispatch',
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `unit_transaction_position` (
  `id` int(11) NOT NULL,
  `fk_unit_transaction` int(11) NOT NULL,
  `fk_unit` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xp_journal` (
  `id` int(11) NOT NULL,
  `fk_battle` int(11) NOT NULL,
  `fk_character` int(11) NOT NULL,
  `reason` char(1) NOT NULL COMMENT '[V]ictory, ACU [K]ill, E[X]perimental Kill',
  `amount` int(11) NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `battle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_planet` (`fk_planet`);

ALTER TABLE `battle_participant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_battle` (`fk_battle`),
  ADD KEY `fk_character` (`fk_character`);

ALTER TABLE `character`
  ADD PRIMARY KEY (`id`),
  ADD KEY `faf_id` (`faf_id`),
  ADD KEY `faction` (`faction`);

ALTER TABLE `credit_journal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_character` (`fk_character`),
  ADD KEY `fk_battle` (`fk_battle`),
  ADD KEY `fk_unit_transaction` (`fk_unit_transaction`);

ALTER TABLE `map_pool`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `planet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sun_system` (`fk_sun_system`),
  ADD KEY `fk_map` (`fk_map`);

ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_character` (`fk_character`),
  ADD KEY `fk_battle` (`fk_battle`);

ALTER TABLE `quantum_gate_link`
  ADD KEY `fk_sun_system_from` (`fk_sun_system_from`),
  ADD KEY `fk_sun_system_to` (`fk_sun_system_to`);

ALTER TABLE `rank`
  ADD PRIMARY KEY (`level`);

ALTER TABLE `schema_version`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `schema_version_s_idx` (`success`);

ALTER TABLE `sun_system`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `fa_uid` (`fa_uid`);

ALTER TABLE `unit_transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_character` (`fk_character`),
  ADD KEY `fk_battle` (`fk_battle`);

ALTER TABLE `unit_transaction_position`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_unit_transaction` (`fk_unit_transaction`),
  ADD KEY `fk_unit` (`fk_unit`);

ALTER TABLE `xp_journal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_battle` (`fk_battle`),
  ADD KEY `fk_character` (`fk_character`);


ALTER TABLE `battle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
ALTER TABLE `character`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
ALTER TABLE `credit_journal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
ALTER TABLE `map_pool`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
ALTER TABLE `planet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
ALTER TABLE `promotion`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 12;
ALTER TABLE `sun_system`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
ALTER TABLE `unit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
ALTER TABLE `unit_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `unit_transaction_position`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `xp_journal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `battle`
  ADD CONSTRAINT `fk_battles_planets` FOREIGN KEY (`fk_planet`) REFERENCES `planet` (`id`);

ALTER TABLE `battle_participant`
  ADD CONSTRAINT `fk_battle_participants_battle` FOREIGN KEY (`fk_battle`) REFERENCES `battle` (`id`)
  ON DELETE CASCADE,
  ADD CONSTRAINT `fk_battle_participants_characters` FOREIGN KEY (`fk_character`) REFERENCES `character` (`id`);

ALTER TABLE `credit_journal`
  ADD CONSTRAINT `fk_credit_journal_battles` FOREIGN KEY (`fk_battle`) REFERENCES `battle` (`id`)
  ON DELETE CASCADE,
  ADD CONSTRAINT `fk_credit_journal_characters` FOREIGN KEY (`fk_character`) REFERENCES `character` (`id`),
  ADD CONSTRAINT `fk_credit_journal_unit_transactions` FOREIGN KEY (`fk_unit_transaction`) REFERENCES `unit_transaction` (`id`);

ALTER TABLE `planet`
  ADD CONSTRAINT `fk_planets_map_pool` FOREIGN KEY (`fk_map`) REFERENCES `map_pool` (`id`),
  ADD CONSTRAINT `fk_planets_sun_systems` FOREIGN KEY (`fk_sun_system`) REFERENCES `sun_system` (`id`);

ALTER TABLE `promotion`
  ADD CONSTRAINT `fk_promotions_battles` FOREIGN KEY (`fk_battle`) REFERENCES `battle` (`id`),
  ADD CONSTRAINT `fk_promotions_characters` FOREIGN KEY (`fk_character`) REFERENCES `character` (`id`);

ALTER TABLE `quantum_gate_link`
  ADD CONSTRAINT `fk_quantum_gate_links_sun_systems_from` FOREIGN KEY (`fk_sun_system_from`) REFERENCES `sun_system` (`id`),
  ADD CONSTRAINT `fk_quantum_gate_links_sun_systems_to` FOREIGN KEY (`fk_sun_system_to`) REFERENCES `sun_system` (`id`);

ALTER TABLE `unit_transaction`
  ADD CONSTRAINT `fk_unit_transactions_battles` FOREIGN KEY (`fk_battle`) REFERENCES `battle` (`id`),
  ADD CONSTRAINT `fk_unit_transactions_characters` FOREIGN KEY (`fk_character`) REFERENCES `character` (`id`);

ALTER TABLE `unit_transaction_position`
  ADD CONSTRAINT `fk_unit_transaction_details_units` FOREIGN KEY (`fk_unit`) REFERENCES `unit` (`id`),
  ADD CONSTRAINT `fk_unit_transaction_details_unit_transaction` FOREIGN KEY (`fk_unit_transaction`) REFERENCES `unit_transaction` (`id`);

ALTER TABLE `xp_journal`
  ADD CONSTRAINT `fk_xp_journal_battles` FOREIGN KEY (`fk_battle`) REFERENCES `battle` (`id`),
  ADD CONSTRAINT `fk_xp_journal_characters` FOREIGN KEY (`fk_character`) REFERENCES `character` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
